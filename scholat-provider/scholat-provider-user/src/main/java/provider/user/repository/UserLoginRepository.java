package provider.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import provider.user.entity.UserLogin;

/**
 * User表操作接口
 */
@Repository
public interface UserLoginRepository extends JpaRepository<UserLogin, Integer>{
        public UserLogin findByPasswordAndUserPhone(String Phone,String password);

}