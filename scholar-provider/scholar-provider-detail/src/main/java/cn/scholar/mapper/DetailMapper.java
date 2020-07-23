package cn.scholar.mapper;

import cn.scholar.entity.UserDetail;

import java.util.List;

//@Mapper
public interface DetailMapper {

     public List<UserDetail> findAll();

     public int update(UserDetail detail);

     public int deleteById(int userId);

     public int insert(UserDetail detail);

     public UserDetail findById(int userId);


}
