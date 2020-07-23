package cn.scholar.teacherTask.controller;

import cn.scholar.common.aliyunOSS.OSSUtil;
import cn.scholar.teacherTask.service.IhomeworkZipDownLoadService;
import com.aliyun.oss.OSSClient;
import com.sun.deploy.net.URLEncoder;
import cn.scholar.common.aliyunOSS.OSSClientConstants;
import cn.scholar.common.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @author yrk
 * @date 2020/6/16 - 10:35
 */
@Controller
@CrossOrigin(allowedHeaders = "*",allowCredentials = "true") //允许跨域请求
public class HomeworkZipDownLoadController {

    private static Logger LOGGER = LoggerFactory.getLogger(HomeworkZipDownLoadController.class);
    private OSSClient ossClient = OSSUtil.getOSSClient();
    @Autowired
    private IhomeworkZipDownLoadService ihomeworkZipDownLoadService;

    @GetMapping("/homeworkZipDownLoad")
    @ResponseBody
    public Object homeworkZipDownLoad(@RequestParam("taskId") String taskId, @RequestParam("className") String className, HttpServletRequest request, HttpServletResponse response) {

        FileInputStream fis = null;
        BufferedInputStream buff = null;
        BufferedOutputStream out = null;
        File  zipFile = null;

        //下载的地址路径
        String downLoadFloder = OSSClientConstants.HOMEWORKFOLDER + "1" + "/" + className + "/" + taskId + "/";
        //下载后的压缩包名
        String zipName = taskId + className + ".zip";

        try {
            //创建一个临时文件,用于压缩
            File TempFile = File.createTempFile(taskId + className , ".zip");
            //返回的zip临时压缩文件，最后要将其删除
            zipFile = ihomeworkZipDownLoadService.homeworkZipDownLoad(ossClient, downLoadFloder, TempFile);

            //解决下载压缩文件名中文乱码问题
            String userAgent = request.getHeader("User-Agent"); //获取浏览器名（IE/Chome/firefox）
            if(userAgent.contains("Mozilla")||userAgent.contains("Trident")) { //针对IE或IE为内核的浏览器
                zipName = URLEncoder.encode(zipName,"UTF-8");
            }else {  //谷歌控制版本
                zipName = new String(zipName.getBytes("UTF-8"),"ISO-8859-1");
            }

            //设置响应信息
            response.reset();
            response.setContentType("application/x-rar-compressed");
            response.setHeader("Location", zipName);
            response.setHeader("Cache-Control", "max-age=0");
            response.setHeader("Content-Disposition", "attachment; filename=" + zipName);

            fis = new FileInputStream(zipFile);
            buff = new BufferedInputStream(fis);
            out = new BufferedOutputStream(response.getOutputStream());

            byte[] buffer = new byte[1024];

            int readLen = 0;
            while (readLen < zipFile.length()) {
                int readBytes = buff.read(buffer, 0, 1024);
                readLen += readBytes;
                out.write(buffer, 0, readBytes);
            }
        } catch (IOException e) {
            LOGGER.error("===> 下载失败！<===", e.getMessage());
            return ResultUtil.fail("下载失败！");
        } finally {

                if (out != null) {
                    try {
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

             if (ossClient != null) {
                 ossClient.shutdown();
             }

            // 删除临时文件
            if ( zipFile.exists() && zipFile != null) {
                zipFile.delete();
            }
        }
        return ResultUtil.success("下载成功！");
    }
}
