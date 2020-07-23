package cn.scholar.controller;

import cn.scholar.question.service.api.NoticeServiceApi;
import cn.scholar.entity.Course;
import cn.scholar.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/course-api")
@CrossOrigin(allowCredentials = "true",allowedHeaders = "*")
public class CourseController {
	
	@Autowired
	private CourseService courseService;

	@Autowired
	private NoticeServiceApi noticeServiceApi;

	//@Autowired
	//private UserDetailServiceApi userServiceApi;
	//
	//@GetMapping("/user/{userId}")
	//public Object getUser(@PathVariable Integer userId){
	//	return userServiceApi.findUserDetail(userId);
	//}

	/**
	 * 测试
	 * @return
	 */
	@GetMapping("/send")
	public Object findAll(){
	//	noticeServiceApi.sendMessageToCourseUser( 1,1,"交作业11111啦啦啦啦");
		noticeServiceApi.sendMessageToOne(3,1,"hello 很高兴认识你");
		return "ok";
	}

//	@GetMapping("/find/{courseId}")
//	public Course findCourseById(@PathVariable Integer courseId){
//		return courseService.findById(courseId);
//	}

	@PostMapping("/update")
    public int updateCourse(Course course){
		return courseService.updateSelection(course);
	}


	@PostMapping("/delete")
	public int deleteCourseById(Integer courseId){
		return courseService.deleteById(courseId);
	}

	@PostMapping("/insert")
    public int insertCourse(Course course){
		return courseService.insert(course);
	}
	
}
