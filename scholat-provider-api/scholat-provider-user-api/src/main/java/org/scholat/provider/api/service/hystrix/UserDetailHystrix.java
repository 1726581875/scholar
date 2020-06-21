package org.scholat.provider.api.service.hystrix;

import org.scholat.common.pojo.UserDetailInfo;
import org.scholat.provider.api.service.UserDetailServiceApi;
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
