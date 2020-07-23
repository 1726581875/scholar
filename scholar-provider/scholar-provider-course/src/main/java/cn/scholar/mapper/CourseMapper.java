package cn.scholar.mapper;

import cn.scholar.dto.CourseDto;
import cn.scholar.entity.Course;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface CourseMapper {

	public CourseDto findById(int courseId);
	
	public List<Course> findAll();
		
	public int updateSelection(Course course);
	
	public int deleteById(int courseId);
	
	public int insert(Course course);

	public List<CourseDto> findByName(String courseName);

    public List<CourseDto> findByuserId(@Param("userId") int userId);

	
}
