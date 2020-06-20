package org.scholat.privider.task.teacherTask.controller;

        import com.aliyun.oss.OSSClient;
        import com.aliyun.oss.model.OSSObject;
        import org.scholat.common.aliyunOSS.OSSClientConstants;
        import org.scholat.common.aliyunOSS.OSSUtil;
        import org.scholat.common.utils.ResultUtil;
        import org.springframework.stereotype.Controller;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RequestMethod;
        import org.springframework.web.bind.annotation.ResponseBody;

        import java.io.*;

/**
 * @author yrk
 * @date 2020/6/15 - 20:12
 */
@Controller
public class TestController {

    private static OSSClient ossClient = OSSUtil.getOSSClient();
    private String folder = OSSClientConstants.HOMEWORKFOLDER + "1/" + "17外包2班/test.txt";
    InputStream is = null;
    OutputStream os = null;
    @RequestMapping(value = "/downLoad", method = RequestMethod.POST)
    @ResponseBody
    public Object downLoad() {
        try {
            is = OSSUtil.downloadFile(ossClient, folder);
            os = new FileOutputStream(new File("E:/uploadTemp/"));
            byte[] buffer = new byte[1024];
            while ( (is.read(buffer) != -1)){
                os.write(buffer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (is != null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return ResultUtil.success("下载成功！");
    }

    /**
     * 测试获取homework/1/17外包2/目录下的所有文件信息
     * @param args
     */
    public static void main(String[] args) {
        /*ListObjectsRequest listObjectsRequest = new ListObjectsRequest(OSSClientConstants.BACKET_NAME);
        listObjectsRequest.setPrefix(OSSClientConstants.HOMEWORKFOLDER+"1/"+"17外包2班/");
        ObjectListing listing = ossClient.listObjects(listObjectsRequest);
        System.out.println("遍历所有的文件：");
        for (OSSObjectSummary objectSummary : listing.getObjectSummaries()) {
            System.out.println(objectSummary.getKey());
        }*/

        InputStream is = null;
        OutputStream os = null;
        BufferedReader reader = null;
        FileWriter fileWriter = null;
        BufferedWriter writer = null;
        OutputStreamWriter streamWriter = null;
        try {
            // ossObject包含文件所在的存储空间名称、文件名称、文件元信息以及一个输入流。
            OSSObject ossObject = ossClient.getObject(OSSClientConstants.BACKET_NAME, "resourse/教师名字/1/CentOS 7 下使用 Firewall.md");
           is = ossObject.getObjectContent();
            /* os =new FileOutputStream(new File("e:/myTest.md"));
            byte[] buffer = new byte[1024];
            while (is.read(buffer) !=  -1){
                os.write(buffer);
            }*/

            reader = new BufferedReader(new InputStreamReader(is,"utf-8"));
            streamWriter = new OutputStreamWriter(new FileOutputStream("e:/Firewall.md"),"utf-8");
            writer = new BufferedWriter(streamWriter);
            while (true) {
                String line = reader.readLine();
                System.out.println("line = " + line);
                /*if (line == null) {
                    break;
                }
                writer.write(line);
                writer.newLine();
                writer.flush();*/
            }

            //System.err.println("----------------Ok------------");
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
             /*   is.close();
                os.close();*/
                writer.close();
                streamWriter.close();
                reader.close();
                ossClient.shutdown();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
