package com.zhouyi.business.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zhouyi.business.core.model.UserInfo;

@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfo, Long>{
	public int updatePassword(UserInfo user);
	
	public UserInfo getUserInfo(@Param("userName") String userName,@Param("password") String password);
	
	public List<UserInfo> queryUserListByName(@Param("fullName") String fullName);
	
	public List<UserInfo> queryUserListByPhone(@Param("phone") String phone);
	
	public UserInfo getUserInfoByPhone(@Param("phone") String phone);
	
	public List<UserInfo> queryCoachListByPhone(@Param("phone") String phone);
}
