package org.scholat.proviser.course.controller.client;

import cn.scholat.service.NoticeServiceApi;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.scholat.common.constant.MyConstant;
import org.scholat.common.message.enums.CommonEnum;
import org.scholat.common.message.enums.CourseEnum;
import org.scholat.common.message.exception.CourseException;
import org.scholat.common.utils.CheckUtil;
import org.scholat.common.utils.MyFileUtil;
import org.scholat.common.utils.ResultUtil;
import org.scholat.proviser.course.dto.CourseDto;
import org.scholat.proviser.course.entity.Course;
import org.scholat.proviser.course.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

import static org.scholat.common.utils.ResultUtil.success;

@CrossOrigin
@RestController
@Slf4j
public class CourseControllerClient {

    @Autowired
    private CourseService courseService;

    @Autowired
    private NoticeServiceApi noticeServiceApi;



    @GetMapping("/find/{courseId}")
    public Object findCourse(@PathVariable Integer courseId){
        log.info("accept =====> courseId={}",courseId);
        CourseDto courseDto = courseService.findById(courseId);
        if(courseDto == null){
            ResultUtil.fail(CourseEnum.COURSE_NOT_EXIST);
        }
        return  ResultUtil.success(courseDto);
    }



    @PostMapping("/delete")
    public Object deleteCourse(Integer courseId){
        log.info("accept =====> courseId={}",courseId);
        int m = courseService.deleteById(courseId);

        return m == 1 ? ResultUtil.success() : ResultUtil.fail("删除课程失败");
    }


    /**
     * 查找方法
     * 可以通过id查找，或者课程名字查找
     * @param search 查找的词
     * @return
     */

    @GetMapping("/find")
    public Object findCourseByIdOrName(@RequestParam String search,
                                       @RequestParam(value="page",defaultValue = "1") Integer page){
        //使用分页插件分页，设置页面大小和第几页
        PageHelper.startPage(page,MyConstant.PAGE_SIZE);

        List<CourseDto> coursedtoList = null;
        if(CheckUtil.isNumber(search)){//如果是纯数字，调用按id查找
            coursedtoList = new ArrayList<>();
            CourseDto course = courseService.findById(Integer.parseInt(search));
            coursedtoList.add(course);
        }else{//否则根据名字查
            coursedtoList = courseService.findByName(search);
        }
        PageInfo<CourseDto> pageInfo = new PageInfo<CourseDto>(coursedtoList);
        return success(pageInfo);
    }

    /**
     * 根据教师查找自己开设的课程
     * @param page
     * @return
     */
    @GetMapping("/user/{userId}/all")
    public Object findCourseByPage(@PathVariable int userId ,
                                   @RequestParam(value="page",defaultValue = "1") int page){
        //使用分页插件分页，设置页面大小和第几页
        PageHelper.startPage(page,MyConstant.PAGE_SIZE,"course_id DESC");
        List<CourseDto> courseDtoList = courseService.findByuserId(userId);
        PageInfo<CourseDto> pageInfo = new PageInfo<CourseDto>(courseDtoList);

        return success(pageInfo);
    }


    /**
     * 新增课程
     * @param file 课程图片
     * @param course 课程信息
     * @return
     */
    @PostMapping(value = "/insert")
    public Object addCourse(@RequestParam(value = "fileName",required = false) MultipartFile file , Course course) {
         //。。。省略参数校验

        String fileName = MyConstant.DEFAULT_IMAGE;//先给个默认图片，如果用户没传图片给个默认图片
        if (file != null) {//如果不为空，说明用户有上传图片
            fileName = MyFileUtil.uploadImage(file);//调用工具类上传图片方法，并获取返回的新文件名
        }
        course.setCourseImage(MyConstant.IMAGE_PRE + fileName);//设置存入数据库的文件名
        int m = courseService.insert(course);
        return (m == 1) ? ResultUtil.success() : ResultUtil.fail("课程创建失败");
    }

    /**
     * 更新课程的方法
     * 表单一起提交，可能需要更新图片
     * @param file
     * @param course
     * @return
     */
    @PostMapping(value = "/update")
    public Object updateCourse(@RequestParam(value = "fileName",required = false) MultipartFile file , Course course) {
        //参数校验
        if(course.getCourseId() == null){//如果不传id
            throw new CourseException(CommonEnum.PARAMETER_ERROR);//抛出异常
        }

        //判断是否有传来图片
        if (file != null) {//不为空，表示需要修改图片
            CourseDto courseDto = courseService.findById(course.getCourseId());
            //1.删除服务器原来图片
            if(courseDto != null) {
                String oldPath = courseDto.getCourseImage();
                String oldName = oldPath.substring(oldPath.lastIndexOf("/") + 1,oldPath.length());
                log.info("oldName============>{}",oldName);
                if(!oldPath.equals(MyConstant.DEFAULT_IMAGE)){//如果不是默认图片（default.png）
                    MyFileUtil.dropFile(MyConstant.IMAGE_PATH + oldName);//删除
                }
            }
            //2.把新图片保存到服务器
            String newName = MyFileUtil.uploadImage(file);//保存新图片，并获取新名字
            course.setCourseImage(MyConstant.IMAGE_PRE + newName);//设置更新课程的新路径
        }
            //3.更新课程
        int m = courseService.updateSelection(course);
        return m==1 ? ResultUtil.success() : ResultUtil.fail(CourseEnum.COURSE_UPDATE_FAIL);
    }






}
