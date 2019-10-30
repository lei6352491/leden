package com.zhouyi.business.core.service;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.zhouyi.business.core.common.WeChatUserInfo;
import com.zhouyi.business.core.model.FornameRequirements;
import com.zhouyi.business.core.model.MemberAuth;
import com.zhouyi.business.core.model.MemberInfo;
import com.zhouyi.business.core.model.UserNames;
import com.zhouyi.business.core.vo.MemberVo;

public interface MemberService {
	public List<MemberVo> queryMemberList(Map<String,Object> params);
	
	public Integer getMemberCount(Map<String,Object> params);
	
	public void updateMember(MemberInfo data);
	
	public MemberVo getMemberDetail(Long id);
	
	public MemberAuth getAuthInfoByOpenId(String openId,Integer portalType);
	
	public void saveMemberInfo(MemberInfo data,WeChatUserInfo weChatInfo);
	
	public MemberInfo getObjectById(Long id);
	
	public void saveMemberLogin(Long userId);
	
	public Long addFornameRequirements(FornameRequirements data);
	
	public Map<String,Integer> getUserStatData(Long userId);
	
	public List<UserNames> addUserNames(JSONObject list,Long userId,Long requireId,Long batchId,Integer usedCard);
	
	public List<UserNames> queryNameResultList(Long userId,int pStart,int pSize);
	
	public Map<String,String> getUserNamesById(Long id);
	
	public void collectionName(Long id,int operateType);
	
	public List<UserNames> queryCollectionName(Long userId,int pStart,int pSize);
	
	public void addTransferNum(Long id);
	
	public List<UserNames> queryNameResultListByRequireId(Long requireId,Long userId);
	
	public MemberInfo bindingUser(String openId,String phone);
	
	public MemberInfo bindingUserById(Long userId,String phone);
	
	public Integer isRegisterRequirements(Long userId);
	
	public MemberVo getMemberInfoByToken(String token);
	
	public void updateMemberToken(Long id,String token);
}
