package org.scholat.proviser.course.service;

import java.util.List;

import org.scholat.proviser.course.pojo.Course;

public interface CourseService {

	public Course findById(int courseId);
	
	public List<Course> findAll();
		
	public int updateSelection(Course course);
	
	public int deleteById(int courseId);
	
	public int insert(Course course);
	
}
