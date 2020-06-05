package org.scholat.provider.user.repository;

import org.scholat.provider.user.pojo.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author xmz
 *用户详情repository
 */
public interface UserDetailReporitory extends JpaRepository<UserDetail, Integer>{

}
