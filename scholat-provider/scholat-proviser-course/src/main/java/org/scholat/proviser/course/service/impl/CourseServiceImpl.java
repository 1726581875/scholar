package org.scholat.proviser.course.service.impl;

import org.scholat.proviser.course.dto.CourseDto;
import org.scholat.proviser.course.entity.Course;
import org.scholat.proviser.course.mapper.CourseMapper;
import org.scholat.proviser.course.mapper.CourseUserMapper;
import org.scholat.proviser.course.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class CourseServiceImpl implements CourseService{

	@Autowired
	private CourseMapper courseMapper;
	@Autowired
	private CourseUserMapper courseUserMapper;
	
	@Override
	public CourseDto findById(int courseId) {
		return courseMapper.findById(courseId);
	}

	@Override
	public List<CourseDto> findByName(String courseName) {
		return courseMapper.findByName(courseName);
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
	@Transactional
	public int deleteById(int courseId) {
		courseUserMapper.deleteUserByCourseId(courseId);
		return courseMapper.deleteById(courseId);
	}

	@Override
	public int insert(Course course) {		
		return courseMapper.insert(course);
	}

	@Override
	public List<CourseDto> findByuserId(int userId) {
		return courseMapper.findByuserId(userId);
	}



}
