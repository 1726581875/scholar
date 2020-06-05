package org.scholat.provider.user.repository;


import org.scholat.provider.user.pojo.UserLogin;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * JpaRepository接口提高简单增删改查
 *
 */
public interface UserLoginRepository extends JpaRepository<UserLogin, Integer>{

}
