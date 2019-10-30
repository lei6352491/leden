package com.zhouyi.business.core.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zhouyi.business.core.model.UserCardInfo;

@Mapper
public interface UserCardMapper extends BaseMapper<UserCardInfo, Long>{
	public Integer addUserCard(@Param("userId") Long userId,@Param("num") Integer num);
	
	public Integer unlockUserCard(@Param("userId") Long userId);
	
	public Integer reduceUserCard(@Param("userId") Long userId);
	
	public UserCardInfo getUserCardByUserId(@Param("userId") Long userId);
}
