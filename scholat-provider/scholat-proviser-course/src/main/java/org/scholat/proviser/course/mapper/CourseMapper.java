package org.scholat.proviser.course.mapper;

import java.util.List;

import org.scholat.proviser.course.pojo.Course;

public interface CourseMapper {

	public Course findById(int courseId);
	
	public List<Course> findAll();
		
	public int updateSelection(Course course);
	
	public int deleteById(int courseId);
	
	public int insert(Course course);
	
}
