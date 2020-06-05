package org.scholat.proviser.course.service.impl;

import java.util.List;

import org.scholat.proviser.course.mapper.CourseMapper;
import org.scholat.proviser.course.pojo.Course;
import org.scholat.proviser.course.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class CourseServiceImpl implements CourseService{

	@Autowired
	private CourseMapper courseMapper;
	
	@Override
	public Course findById(int courseId) {		
		return courseMapper.findById(courseId);
	}

	@Override
	public List<Course> findAll() {		
		return courseMapper.findAll();
	}

	@Override
	public int updateSelection(Course course) {
		return courseMapper.updateSelection(course);
	}

	@Override
	public int deleteById(int courseId) {		
		return courseMapper.deleteById(courseId);
	}

	@Override
	public int insert(Course course) {		
		return courseMapper.insert(course);
	}

}
