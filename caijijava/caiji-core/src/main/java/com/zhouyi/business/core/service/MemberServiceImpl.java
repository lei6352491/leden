package com.zhouyi.business.core.service;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.zhouyi.business.core.common.Config;
import com.zhouyi.business.core.common.WeChatUserInfo;
import com.zhouyi.business.core.dao.FornameRequirementsMapper;
import com.zhouyi.business.core.dao.MemberAuthMapper;
import com.zhouyi.business.core.dao.MemberInfoMapper;
import com.zhouyi.business.core.dao.OrderMapper;
import com.zhouyi.business.core.dao.PersonAccInfoMapper;
import com.zhouyi.business.core.dao.UserCardMapper;
import com.zhouyi.business.core.dao.UserGiftMapper;
import com.zhouyi.business.core.dao.UserNamesMapper;
import com.zhouyi.business.core.model.FornameRequirements;
import com.zhouyi.business.core.model.MemberAuth;
import com.zhouyi.business.core.model.MemberInfo;
import com.zhouyi.business.core.model.PersonAccInfo;
import com.zhouyi.business.core.model.UserNames;
import com.zhouyi.business.core.utils.WXUtil;
import com.zhouyi.business.core.vo.MemberVo;

@Service
public class MemberServiceImpl implements MemberService{

	private static final Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);

	@Autowired
	private MemberInfoMapper memberMapper;

	@Autowired
	private MemberAuthMapper memberAuthMapper;

	@Autowired
	private FornameRequirementsMapper fornameRequirementsMapper;

	@Autowired
	private PersonAccInfoMapper personAccInfoMapper;

	@Autowired
	private OrderMapper orderMapper;

	@Autowired
	private UserGiftMapper userGiftMapper;

	@Autowired
	private UserNamesMapper userNamesMapper;

	@Autowired
	private UserCardMapper userCardMapper;

	@Autowired
	private Config config;

	@Override
	public List<MemberVo> queryMemberList(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return memberMapper.queryMemberList(params);
	}

	@Override
	public Integer getMemberCount(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return memberMapper.getMemberCount(params);
	}

	@Override
	public void updateMember(MemberInfo data) {
		// TODO Auto-generated method stub
		memberMapper.updateByPrimaryKeySelective(data);
	}

	@Override
	public MemberVo getMemberDetail(Long id) {
		// TODO Auto-generated method stub
		return memberMapper.getMemberDetail(id);
	}

	@Override
	public MemberAuth getAuthInfoByOpenId(String openId, Integer portalType) {
		// TODO Auto-generated method stub
		return memberAuthMapper.getAuthInfoByOpenId(openId, portalType);
	}

	@Transactional
	@Override
	public void saveMemberInfo(MemberInfo user,WeChatUserInfo weChatInfo) {
		// TODO Auto-generated method stub
		Date now=new Date();
		user.setCreateTime(now);
		user.setUpdateTime(now);
		user.setLoginTime(now);
		memberMapper.insertSelective(user);
		MemberAuth authInfo=new MemberAuth();
		authInfo.setMemberId(user.getId());
		authInfo.setPlatformType(1);
		authInfo.setPortalType(user.getPortalType());
		authInfo.setOpenId(weChatInfo.getOpenid());
		authInfo.setNickname(weChatInfo.getNickname());
		authInfo.setHeadImage(weChatInfo.getHeadimgurl());
		authInfo.setSex(weChatInfo.getSex());
		authInfo.setCreateTime(now);
		authInfo.setUpdateTime(now);
		authInfo.setUnionId(weChatInfo.getUnionId());
		authInfo.setCity(weChatInfo.getCity());
		memberAuthMapper.insertSelective(authInfo);

		memberMapper.saveMemberLogin(user.getId());

		PersonAccInfo acc=new PersonAccInfo();
		acc.setAccNo(this.getAccNo(user.getId()));
		acc.setCreateTime(now);
		acc.setUserId(user.getId());
		personAccInfoMapper.insertSelective(acc);
	}

	@Override
	public MemberInfo getObjectById(Long id) {
		// TODO Auto-generated method stub
		return memberMapper.selectByPrimaryKey(id);
	}

	@Override
	public void saveMemberLogin(Long userId) {
		// TODO Auto-generated method stub
		memberMapper.saveMemberLogin(userId);
	}

	@Override
	public Long addFornameRequirements(FornameRequirements data) {
		// TODO Auto-generated method stub
		fornameRequirementsMapper.insertSelective(data);
		return data.getId();
	}


	private String getAccNo(Long userId){
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHH");
		Date d=new Date();
		DecimalFormat df = new DecimalFormat("00000000000");
		String uid = df.format(userId);
		return sdf.format(d)+uid;
	}

	@Override
	public Map<String, Integer> getUserStatData(Long userId) {
		Map<String,Integer> data=new HashMap<String,Integer>();
		int orderNum=orderMapper.getOrderCountByOwnerId(userId);
//		int giftNum=userGiftMapper.getGiftCountByOwnerId(userId);
		int collectNum=userNamesMapper.getCollectionCountByOwnerId(userId);
		data.put("orderNum", orderNum);
//		data.put("giftNum", giftNum);
		data.put("collectNum", collectNum);
		return data;
	}

	@Override
	public List<UserNames> addUserNames(JSONObject list,Long userId,Long requireId,Long batchId,Integer usedCard) {

		List<UserNames> unList=new ArrayList<UserNames>();
		try{

			Set<String> keys=list.keySet();
			for(String k:keys){
				JSONObject obj=list.getJSONObject(k);
				UserNames un=new UserNames();
				un.setUserId(userId);
				un.setCombine(obj.getString("combine"));
				un.setCombineDescript(obj.getString("combine_char1_description"));
				un.setCombinePinyin(obj.getString("combine_char1_pinyin"));
				un.setIsFavorite(0);
				un.setIsShare(0);
				un.setIsView(0);
				un.setNameDetail(obj.toJSONString());
				un.setRequireId(requireId);
				un.setSurname(obj.getString("surname"));
				un.setSurnamePinyin(obj.getString("surname_pinyin"));
				un.setCreateTime(new Date());
				un.setScore(0);
				un.setBatchId(batchId);
				un.setUsedCard(usedCard);
				unList.add(un);
			}
			if(unList.size()>0){
				userNamesMapper.insertBatch(unList);
			}
		}catch(Exception e){
			logger.error("addUserNames_err=",e);
		}
		return unList;
	}

	@Override
	public List<UserNames> queryNameResultList(Long userId, int pStart, int pSize) {
		List<UserNames> list=userNamesMapper.queryNameResultList(userId, pStart, pSize);
		return list;
	}

	@Override
	public Map<String,String> getUserNamesById(Long id) {
		// TODO Auto-generated method stub
		return userNamesMapper.getUserNamesById(id);
	}

	@Override
	public void collectionName(Long id, int operateType) {
		// TODO Auto-generated method stub
		UserNames un=new UserNames();
		un.setId(id);
		un.setIsFavorite(operateType);
		userNamesMapper.updateByPrimaryKeySelective(un);
	}

	@Override
	public List<UserNames> queryCollectionName(Long userId,int pStart,int pSize) {
		// TODO Auto-generated method stub
		return userNamesMapper.queryCollectionNameList(userId, pStart, pSize);
	}

	@Override
	public void addTransferNum(Long userId) {
		// TODO Auto-generated method stub
		userNamesMapper.addTransferNum(userId);
	}

	@Override
	public List<UserNames> queryNameResultListByRequireId(Long requireId,Long userId) {
		// TODO Auto-generated method stub
		return userNamesMapper.queryNameResultListByRequireId(requireId, userId);
	}

	@Override
	@Transactional
	public MemberInfo bindingUser(String openId, String phone) {
		MemberAuth auth=this.getAuthInfoByOpenId(openId, 101);
		if(auth==null){
			WeChatUserInfo weChatInfo=WXUtil.getWeChatDetail(openId, config.getWxAppId(), config.getWxApiSecret());
			if(weChatInfo==null || weChatInfo.getOpenid()==null){
				logger.info("bindingUser_result={}-{}",openId,phone);
				return null;
			}
			MemberInfo user=new MemberInfo();
			String sex=(weChatInfo.getSex()==null || weChatInfo.getSex().equals("2"))?"0":weChatInfo.getSex();
			user.setSex(Integer.parseInt(sex));
			user.setUnionId(weChatInfo.getUnionId());
			user.setUserType(0);
			user.setPortalType(101);

			this.saveMemberInfo(user, weChatInfo);
			return user;
		}else{
			MemberInfo mem=memberMapper.selectByPrimaryKey(auth.getMemberId());
			if(mem.getPhone()==null || mem.getPhone().equals("")){
				mem.setPhone(phone);
				memberMapper.updateByPrimaryKeySelective(mem);
			}
			return mem;
		}
	}

	@Override
	public MemberInfo bindingUserById(Long userId, String phone) {
		MemberInfo mem=memberMapper.selectByPrimaryKey(userId);
		if(mem.getPhone()==null || mem.getPhone().equals("")){
			mem.setPhone(phone);
			memberMapper.updateByPrimaryKeySelective(mem);
		}
		return mem;
	}

	@Override
	public Integer isRegisterRequirements(Long userId) {
		// TODO Auto-generated method stub
		return memberMapper.isRegisterRequirements(userId);
	}

	@Override
	public MemberVo getMemberInfoByToken(String token) {
		// TODO Auto-generated method stub
		return memberMapper.getMemberInfoByToken(token);
	}

	@Override
	public void updateMemberToken(Long id, String token) {
		// TODO Auto-generated method stub
		MemberInfo mi=new MemberInfo();
		mi.setId(id);
		mi.setToken(token);
		memberMapper.updateByPrimaryKeySelective(mi);
	}

}
