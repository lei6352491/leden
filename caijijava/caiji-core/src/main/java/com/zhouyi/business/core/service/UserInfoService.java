package com.zhouyi.business.core.service;

import java.util.List;

import com.zhouyi.business.core.model.UserInfo;

public interface UserInfoService {
	public UserInfo getUserInfo(String userName,String password);
	
	public void saveUser(UserInfo user);
	
	public void updateUser(UserInfo user);
	
	public int updatePassword(UserInfo user);
	
	public UserInfo getUserInfoById(Long id);
	
	public List<UserInfo> queryUserListByName(String fullName);
	
	public List<UserInfo> queryUserListByPhone(String phone);
	
	public UserInfo getUserInfoByPhone(String phone);
	
	public List<UserInfo> queryCoachListByPhone(String phone);
	
}
