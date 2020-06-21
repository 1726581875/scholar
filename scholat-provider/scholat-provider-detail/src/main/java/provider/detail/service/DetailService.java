package provider.detail.service;

import provider.detail.entity.UserDetail;

import java.util.List;


public interface DetailService {
    //插入详情
    public int insertDetail(UserDetail detail);
    //更新详情
    public int updateDetail(UserDetail detail);
   //查询详情根据用户id
    public UserDetail findDetail(int userId);
    //删除详情by用户id
    public int deleteDetailByUserId(int userId);
    //查询所有详情
    public List<UserDetail> findAll();
}
