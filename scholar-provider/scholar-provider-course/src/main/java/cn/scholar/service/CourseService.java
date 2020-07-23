package cn.scholar.service;

import java.util.List;

import cn.scholar.dto.CourseDto;
import cn.scholar.entity.Course;

public interface CourseService {

	public CourseDto findById(int courseId);

	public List<CourseDto> findByName(String courceName);
	
	public List<Course> findAll();
		
	public int updateSelection(Course course);
	
	public int deleteById(int courseId);
	
	public int insert(Course course);

	public List<CourseDto> findByuserId(int userId);
	
}
