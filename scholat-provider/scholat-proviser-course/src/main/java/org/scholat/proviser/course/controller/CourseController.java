package org.scholat.proviser.course.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.scholat.proviser.course.entity.Course;
import org.scholat.proviser.course.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/course-api")
@CrossOrigin
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
	
	@PostMapping("/all")
	public Object findAll(String courseName,String courseDesc){
		System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		Map<String,String> map = new HashMap<String,String>();
		map.put("courseName",courseName);
		map.put("courseDesc",courseDesc);
		return map;
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
