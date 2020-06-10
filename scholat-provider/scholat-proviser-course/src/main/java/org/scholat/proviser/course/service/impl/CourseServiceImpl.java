package org.scholat.proviser.course.service.impl;

import java.util.List;

import org.scholat.common.constant.MyConstant;
import org.scholat.proviser.course.dto.CourseDto;
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
	public int deleteById(int courseId) {		
		return courseMapper.deleteById(courseId);
	}

	@Override
	public int insert(Course course) {		
		return courseMapper.insert(course);
	}

	@Override
	public List<CourseDto> findByPage(int page) {
		return courseMapper.findByPage(MyConstant.PAGE_SIZE * (page-1),MyConstant.PAGE_SIZE);
	}





}
