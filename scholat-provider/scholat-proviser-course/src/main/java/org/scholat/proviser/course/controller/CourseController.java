package org.scholat.proviser.course.controller;

import java.util.List;

import org.scholat.provider.api.service.UserDetailServiceApi;
import org.scholat.proviser.course.pojo.Course;
import org.scholat.proviser.course.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CourseController {
	
	@Autowired
	private CourseService courseService;
		
	@Autowired
	private UserDetailServiceApi userServiceApi;
	
	@GetMapping("/user/{userId}")
	public Object getUser(@PathVariable Integer userId){		
		return userServiceApi.findUserDetail(userId);
	}
	
	@GetMapping("/courses")
	public List<Course> findAll(){
		return courseService.findAll();
	}
	
	
}
