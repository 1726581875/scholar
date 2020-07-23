package cn.scholar.service.impl;

import cn.scholar.dto.CourseDto;
import cn.scholar.entity.Course;
import cn.scholar.mapper.CourseMapper;
import cn.scholar.mapper.CourseUserMapper;
import cn.scholar.service.CourseService;
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
