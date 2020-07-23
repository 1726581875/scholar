package cn.scholar.common.aliyunOSS;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URL;
import java.util.Date;
import java.util.zip.Adler32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author yrk
 * @date 2020/6/14 - 10:00
 */
@Controller
public class OSSUtil {

    private static Logger LOGGER = LoggerFactory.getLogger(OSSUtil.class);

    private static String ENDPOINT;

    private static String ACCESS_KEY_ID;

    private static String ACCESS_KEY_SECRET;

    private static String BACKET_NAME;

    private static String FOLDER;

    static {
        ENDPOINT = OSSClientConstants.ENDPOINT;
        ACCESS_KEY_ID = OSSClientConstants.ACCESS_KEY_ID;
        ACCESS_KEY_SECRET = OSSClientConstants.ACCESS_KEY_SECRET;
        BACKET_NAME = OSSClientConstants.BACKET_NAME;
        FOLDER = OSSClientConstants.HOMEWORKFOLDER;
    }

    /**
     * 获取OSS客户端对象
     * @return
     */
    public static OSSClient getOSSClient() {
        return (OSSClient) new OSSClientBuilder().build(ENDPOINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET);
    }

    /**
     * 创建文件夹
     * @param ossClient
     * @param bucketName
     * @param folderName
     * @return 文件夹名
     */
    public static String createFolder(OSSClient ossClient, String bucketName, String folderName) {

        //判断文件夹是否存在，不存在则创建
        if (!ossClient.doesObjectExist(bucketName, folderName)) {
            //创建文件夹
            ossClient.putObject(bucketName, folderName, new ByteArrayInputStream(new byte[0]));
            LOGGER.info("------创建文件夹成功！-------");
            //获取文件夹名
            OSSObject object = ossClient.getObject(bucketName, folderName);
            String fileDir = object.getKey();
            return fileDir;
        }
        return folderName;
    }


    /**
     * 单文件上传
     * @param ossClient
     * @param bucketName
     * @param uploadfile
     * @param uploadFolder
     * @return
     */
    public static String uploadFileToOSS(OSSClient ossClient ,  String bucketName, MultipartFile uploadfile, String uploadFolder){

        String resultStr = null;
        try {
            //以输入流的形式上传文件
            InputStream is = uploadfile.getInputStream();
            String fileName = uploadfile.getOriginalFilename();
            //文件的大小
            long fileSize = uploadfile.getSize();
            //创建上传文件的元数据Metadata信息
            ObjectMetadata metadata = new ObjectMetadata();
            //上传文件的长度
            metadata.setContentLength(is.available());
            //指定该Object被下载时网页的缓存行为
            metadata.setCacheControl("no-cache");
            //指定该Object下设置Header
            metadata.setHeader("Pragma", "no-cache");
            //指定该Object被下载时的内容编码格式
            metadata.setContentEncoding("utf-8");
            LOGGER.info("上传文件的名字===>" + fileName);
            LOGGER.info("上传的位置 ===>" + uploadFolder);
            //上传文件
            PutObjectResult putObjectResult = ossClient.putObject(bucketName,uploadFolder, is, metadata);
            //解析结果
            resultStr = putObjectResult.getETag();
        }catch (OSSException oe) {
            oe.printStackTrace();
        } catch (ClientException ce) {
            ce.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultStr;
    }

    /**
     * 多文件上传
     * @param ossClient
     * @param bucketName
     * @param uploadfiles
     * @param uploadFolder
     * @return
     */
    public static boolean MutilUploadFileToOSS(OSSClient ossClient ,  String bucketName, MultipartFile[] uploadfiles, String uploadFolder){

        String resultStr = null;
        InputStream is = null;
        ObjectMetadata metadata = null;
        boolean flag = false;
        try {

            for (int i = 0; i < uploadfiles.length; i++){
                //以输入流的形式上传文件
                is = uploadfiles[i].getInputStream();
                String fileName = uploadfiles[i].getOriginalFilename();
                //创建上传文件的元数据Metadata信息
                metadata = new ObjectMetadata();
                //上传文件的长度
                metadata.setContentLength(is.available());
                //指定该Object被下载时网页的缓存行为
                metadata.setCacheControl("no-cache");
                //指定该Object下设置Header
                metadata.setHeader("Pragma", "no-cache");
                //指定该Object被下载时的内容编码格式
                metadata.setContentEncoding("utf-8");
                LOGGER.info("上传文件的名字===>" + fileName);
                LOGGER.info("上传的位置 ===>" + uploadFolder);
                //上传文件
                PutObjectResult putObjectResult = ossClient.putObject(bucketName,uploadFolder, is, metadata);
                flag = true;
            }

        }catch (OSSException oe) {
            oe.printStackTrace();
        } catch (ClientException ce) {
            ce.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 流式下载文件
     * @param ossClient
     * @param folder
     */
    public static InputStream downloadFile(OSSClient ossClient,String folder) {

        InputStream is=null;
        try {
            // ossObject包含文件所在的存储空间名称、文件名称、文件元信息以及一个输入流。
            OSSObject ossObject = ossClient.getObject(OSSClientConstants.BACKET_NAME, folder);
            is = ossObject.getObjectContent();
            return is;
        }catch (OSSException oe) {
            return is;
        }catch(Exception e) {
            LOGGER.error("下载文件失败"+e.getMessage());
            return is;
        }catch (Throwable e) {
            e.printStackTrace();
            return is;
        }
    }

    /**
     * 批量打包压缩下载
     * @param ossClient
     * @param targetFloder
     * @return 压缩输出流
     */
    public static File  homeworkZipDownLoad(OSSClient ossClient, String targetFloder, File zipFile) {

        FileOutputStream fos = null;
        InputStream is = null;
        CheckedOutputStream checkedOutputStream = null;
        ZipOutputStream zos = null;

        try {
            fos = new FileOutputStream(zipFile);
            /**
             * CheckedOutputStream：校验和可用于验证输出数据的完整性。
             * @param  Adler32() [可用于计算数据流的 Adler-32 校验和的类]
             */
            checkedOutputStream = new CheckedOutputStream(fos, new Adler32());
            //用于将数据压缩成zip文件格式
            zos = new ZipOutputStream(checkedOutputStream);
            //构造ListObjectsRequest请求
            ListObjectsRequest listObjectsRequest = new ListObjectsRequest(OSSClientConstants.BACKET_NAME);
            //将列出此文件夹下的所有文件和目录
            listObjectsRequest.setPrefix(targetFloder);
            ObjectListing objectListing =  ossClient.listObjects(listObjectsRequest);
            //遍历所有文件
            LOGGER.info(targetFloder + "目录下的文件有：");
            for (OSSObjectSummary objectSummary : objectListing.getObjectSummaries()) {
                String fileName = objectSummary.getKey().substring(9);
                LOGGER.info("文件的名字 ====>" + fileName);
                //获取下载的文件object
                OSSObject ossObject = ossClient.getObject(OSSClientConstants.BACKET_NAME, objectSummary.getKey());
                //读取ossObject的内容，即下载的文件
                is = ossObject.getObjectContent();
                //将每个要下载的文件加到zip压缩包里面
                zos.putNextEntry(new ZipEntry(fileName));
                
                //定义一个读取缓冲区
                byte[] buffer = new byte[1024];
                int readeBytes = 0;
                while ( (readeBytes = is.read(buffer)) != -1) {
                    zos.write(buffer, 0, readeBytes);
                }
                zos.flush();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            if ( zos != null){
                try {
                    zos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (checkedOutputStream != null){
                try {
                    checkedOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if ( fos != null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return zipFile;
    }

    /**
     * 删除OSS上的文件
     * @param ossClient
     * @param targetFloder
     * @return
     */
    public static boolean deleteFile(OSSClient ossClient, String targetFloder) {

        //构造ListObjectsRequest请求
        ListObjectsRequest listObjectsRequest = new ListObjectsRequest(OSSClientConstants.BACKET_NAME);
        //将列出此文件夹下的所有文件和目录
        listObjectsRequest.setPrefix(targetFloder);
        ObjectListing objectListing =  ossClient.listObjects(listObjectsRequest);
        //遍历删除所有文件（批量删除）
        LOGGER.info(targetFloder + "目录下被删除的文件有：");
        for (OSSObjectSummary objectSummary : objectListing.getObjectSummaries()) {
            String deleteFile = objectSummary.getKey();
            LOGGER.info("删除 ===>" + targetFloder + "===>下的文件===>" + deleteFile);
            ossClient.deleteObject(OSSClientConstants.BACKET_NAME, deleteFile);
        }
        return true;
    }

    /**
     * 获取文件存储的URL
     * @param ossClient
     * @param file
     * @return
     */
    public static String getFileUrl (OSSClient ossClient, String file) {
        //设置URL过期的时间为1年
        Date expiration = new Date(System.currentTimeMillis() +3600L * 1000 * 24 * 365 );
        URL url = ossClient.generatePresignedUrl(OSSClientConstants.BACKET_NAME, file, expiration);
        if (url != null) {
            return url.toString();
        }
        return null;
    }
}
