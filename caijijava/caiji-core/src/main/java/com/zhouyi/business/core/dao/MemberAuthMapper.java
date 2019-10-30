package com.zhouyi.business.core.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zhouyi.business.core.model.MemberAuth;

@Mapper
public interface MemberAuthMapper extends BaseMapper<MemberAuth, Long> {
	

	public MemberAuth getAuthInfoByOpenId(@Param("openId") String openId,@Param("portalType") Integer portalType);
	
	
}
