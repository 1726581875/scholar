package org.scholat.proviser.course.controller;

import java.util.List;

import org.scholat.provider.api.service.UserDetailServiceApi;
import org.scholat.proviser.course.pojo.Course;
import org.scholat.proviser.course.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/course-api")
public class CourseController {
	
	@Autowired
	private CourseService courseService;
		
	//@Autowired
	//private UserDetailServiceApi userServiceApi;
	//
	//@GetMapping("/user/{userId}")
	//public Object getUser(@PathVariable Integer userId){
	//	return userServiceApi.findUserDetail(userId);
	//}
	
	@GetMapping("/all")
	public List<Course> findAll(){
		return courseService.findAll();
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
