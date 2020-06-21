package provider.detail.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import provider.detail.entity.UserDetail;
import provider.detail.mapper.DetailMapper;
import provider.detail.service.DetailService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class Detailmpl implements DetailService{

    @Resource
    private DetailMapper detailMapper;

    @Override
    public List<UserDetail> findAll() {
        return detailMapper.findAll();
    }

    @Override
    public int insertDetail(UserDetail detail) {
        return detailMapper.insert(detail);
    }

    @Override
    public int updateDetail(UserDetail detail) {
        return detailMapper.update(detail);
    }

    @Override
    public UserDetail findDetail(int userId) {
        return detailMapper.findById(userId);
    }

    @Override
    public int deleteDetailByUserId(int userId) {
       return detailMapper.deleteById(userId);
    }

    /*  @Override
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
    }*/

}
