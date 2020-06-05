package org.scholat.provider.user.service;

import java.util.List;

import org.scholat.provider.user.pojo.UserDetail;

public interface UserDetailService {

	public UserDetail findById(int userId);
	
	public UserDetail updateSelected(UserDetail user);
		
	public void deleteById(int userId);
	
	public List<UserDetail> findAll();
	
	
}
