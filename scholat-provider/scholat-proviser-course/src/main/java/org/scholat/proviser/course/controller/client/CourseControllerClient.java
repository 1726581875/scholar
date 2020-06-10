package org.scholat.proviser.course.controller.client;

import static org.scholat.common.utils.ResultUtil.*;


import lombok.extern.slf4j.Slf4j;
import org.scholat.common.constant.MyConstant;
import org.scholat.common.message.enums.CommonEnum;
import org.scholat.common.message.enums.CourseEnum;
import org.scholat.common.message.exception.CommonException;
import org.scholat.common.utils.CheckUtil;
import org.scholat.common.utils.MyFileUtil;
import org.scholat.common.utils.ResultUtil;
import org.scholat.proviser.course.dto.CourseDto;
import org.scholat.proviser.course.pojo.Course;
import org.scholat.proviser.course.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@CrossOrigin
@RestController
@Slf4j
public class CourseControllerClient {

    @Autowired
    private CourseService courseService;

//    @PostMapping("/update")
//    public Object updateCourse(Course course){
//        int n = courseService.updateSelection(course);
//        return (n == 0) ? fail(CourseEnum.COURSE_UPDATE_FAIL) : success();
//    }

    @GetMapping("/find")
    public Object findCourseByIdOrName(@RequestParam String search){
        CourseDto course = null;
        if(CheckUtil.isNumber(search)){
            course = courseService.findById(Integer.parseInt(search));
        }else{
            List<CourseDto> coursedto = courseService.findByName(search);
            return success(coursedto);
        }
        return success(course);
    }


    @GetMapping("/find/page/{page}")
    public Object findCourseByPage(@PathVariable int page){
        return success(courseService.findByPage(page));
    }


    /**
     * 新增课程
     * @param file
     * @param course
     * @return
     */
    @PostMapping(value = "/insert")
    public Object addCourse(@RequestParam(value = "fileName",required = false) MultipartFile file , Course course) {
         //。。。省略参数校验

        String fileName = MyConstant.FILE_NAME;//先给个默认图片，如果用户没传图片给个默认图片
        if (file != null) {//如果不为空，说明用户有上传图片
            fileName = MyFileUtil.uploadImage(file);//调用工具类上传图片方法，并获取返回的新文件名
        }
        course.setCourseImage(MyConstant.IMAGE_PRE + fileName);//设置存入数据库的文件名
        int m = courseService.insert(course);
        return (m == 1) ? ResultUtil.success() : ResultUtil.fail("课程创建失败");
    }

    /**
     * 更新课程
     * @param file
     * @param course
     * @return
     */
    @PostMapping(value = "/update")
    public Object updateCourse(@RequestParam(value = "fileName",required = false) MultipartFile file , Course course) {

        //。。。省略参数校验

        CourseDto courseDto = courseService.findById(course.getCourseId());
        if(courseDto != null) {
            String oldName = courseDto.getCourseImage();
        }
        if (file != null) {//需要修改图片
            String newName = MyFileUtil.uploadImage(file);//保存新图片，并获取新名字
            course.setCourseImage(MyConstant.IMAGE_PRE + newName);//设置存入数据库的文件名
        }

        int m = courseService.updateSelection(course);
        return m==1 ? ResultUtil.success() : ResultUtil.fail(CourseEnum.COURSE_UPDATE_FAIL);
    }



}
