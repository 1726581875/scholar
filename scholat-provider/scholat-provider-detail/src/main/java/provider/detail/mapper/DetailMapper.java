package provider.detail.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import provider.detail.entity.UserDetail;

@Mapper
public interface DetailMapper {
    public List<UserDetail> findAll();

   public int update(UserDetail detail);

     public int deleteById(int userId);

    public int insert(UserDetail detail);

     public UserDetail findById(int userId);


}
