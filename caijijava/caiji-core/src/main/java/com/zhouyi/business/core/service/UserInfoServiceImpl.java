package com.zhouyi.business.core.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhouyi.business.core.dao.UserInfoMapper;
import com.zhouyi.business.core.model.UserInfo;

@Service
public class UserInfoServiceImpl implements UserInfoService{

	@Autowired
	private UserInfoMapper userInfoMapper;

	
	@Override
	public UserInfo getUserInfo(String userName, String password) {
		// TODO Auto-generated method stub
		return userInfoMapper.getUserInfo(userName, password);
	}


	@Override
	public void saveUser(UserInfo user) {
		Date now=new Date();
		user.setIsDeleted(0);
		user.setCreateTime(now);
		user.setUpdateTime(now);
		userInfoMapper.insertAndReturnId(user);
		
	}


	@Override
	public void updateUser(UserInfo user) {
		user.setUpdateTime(new Date());
		userInfoMapper.updateByPrimaryKeySelective(user);
		
	}

	@Override
	public int updatePassword(UserInfo user) {
		return userInfoMapper.updatePassword(user);
		
	}

	@Override
	public UserInfo getUserInfoById(Long id) {
		// TODO Auto-generated method stub
		return userInfoMapper.selectByPrimaryKey(id);
	}


	@Override
	public List<UserInfo> queryUserListByName(String fullName) {
		// TODO Auto-generated method stub
		return userInfoMapper.queryUserListByName(fullName);
	}
	
	@Override
	public List<UserInfo> queryUserListByPhone(String phone) {
		// TODO Auto-generated method stub
		return userInfoMapper.queryUserListByPhone(phone);
	}


	@Override
	public UserInfo getUserInfoByPhone(String phone) {
		// TODO Auto-generated method stub
		return userInfoMapper.getUserInfoByPhone(phone);
	}


	@Override
	public List<UserInfo> queryCoachListByPhone(String phone) {
		// TODO Auto-generated method stub
		return userInfoMapper.queryCoachListByPhone(phone);
	}
}
