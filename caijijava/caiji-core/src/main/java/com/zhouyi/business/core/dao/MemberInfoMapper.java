package com.zhouyi.business.core.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zhouyi.business.core.model.MemberInfo;
import com.zhouyi.business.core.vo.MemberVo;

@Mapper
public interface MemberInfoMapper extends BaseMapper<MemberInfo, Long>{
	public List<MemberVo> queryMemberList(Map<String,Object> params);
	
	public MemberVo getMemberDetail(@Param("userId") Long userId);
	
	public Integer getMemberCount(Map<String,Object> params);
	
	public void saveMemberLogin(@Param("userId") Long userId);
	
	public void openMember(@Param("userId") Long userId,@Param("userType") Integer userType);
	
	public Integer isRegisterRequirements(@Param("userId") Long userId);
	
	public MemberVo getMemberInfoByToken(@Param("token") String token);
}
