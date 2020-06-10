package org.scholat.proviser.course.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.scholat.proviser.course.dto.CourseDto;
import org.scholat.proviser.course.pojo.Course;
@Mapper
public interface CourseMapper {

	public CourseDto findById(int courseId);
	
	public List<Course> findAll();
		
	public int updateSelection(Course course);
	
	public int deleteById(int courseId);
	
	public int insert(Course course);

	public List<CourseDto> findByName(String courseName);

    public List<CourseDto> findByPage(@Param("begin") int begin , @Param("size") int size);
	
}
