package org.scholat.provider.user.service.impl;

import java.util.List;

import org.scholat.provider.user.pojo.UserDetail;
import org.scholat.provider.user.repository.UserDetailReporitory;
import org.scholat.provider.user.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserDetailServiceImpl implements UserDetailService{

	@Autowired
	private UserDetailReporitory userRepository;
	
	@Override
	public UserDetail findById(int userId) {		
		return userRepository.findById(userId).orElse(null);
	}

	@Override
	public UserDetail updateSelected(UserDetail user) {	
		UserDetail detail = findById(user.getUserId());
		if(detail == null)  log.info("用户id：{},不存在，更新失败",user.getUserId());
		// BeanUtils.copyProperties(user, detail, getNullPropertyNames(user));
		if(user.getUserAge() != null) detail.setUserAge(user.getUserAge());
		if(user.getUserField() != null) detail.setUserField(user.getUserField());
		if(user.getUserImage() != null) detail.setUserImage(user.getUserImage());
		if(user.getUserMajor() != null) detail.setUserMajor(user.getUserMajor());
		if(user.getUserName() != null) detail.setUserName(user.getUserName());
		if(user.getUserSchool() != null) detail.setUserSchool(user.getUserSchool());
		if(user.getUserSex() != null) detail.setUserSex(user.getUserSex());
		if(user.getUserType() != null) detail.setUserType(user.getUserType());
		if(user.getDescription() != null) detail.setDescription(user.getDescription());
		
		return userRepository.save(detail);
	}

	@Override
	public void deleteById(int userId) {		
		userRepository.deleteById(userId);		
	}

	@Override
	public List<UserDetail> findAll() {
		return userRepository.findAll();
	}



}
