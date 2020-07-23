package cn.scholar.user.service.api.hystrix;

import cn.scholar.common.pojo.UserDetailInfo;
import cn.scholar.user.service.api.UserDetailServiceApi;
import org.springframework.stereotype.Component;

@Component
public class UserDetailHystrix implements UserDetailServiceApi{

	@Override
	public UserDetailInfo findUserDetail(int userId) {

		return UserDetailInfo.invalid();
	}

	@Override
	public void delete(int userId) {		
		
	}

	
	
}
