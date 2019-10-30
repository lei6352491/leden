package com.zhouyi.business.core.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhouyi.business.core.dao.UserGiftMapper;
import com.zhouyi.business.core.vo.UserGiftVo;

@Service
public class UserGiftServiceImpl implements UserGiftService{

	@Autowired
	private UserGiftMapper userGiftMapper;

	@Override
	public List<UserGiftVo> queryUserGiftList(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return userGiftMapper.queryAllUserGiftList(params);
	}

	@Override
	public Map<Long,Integer> getUserGiftCount(Long userId) {
		// TODO Auto-generated method stub
		Map<Long,Integer> data=new HashMap<Long,Integer>();
		List<Map<String,Object>> list=userGiftMapper.getProductUserGiftCount(userId);
		for(Map<String,Object> obj:list){
			data.put(Long.valueOf(obj.get("productId").toString()), Integer.valueOf(obj.get("num").toString()));
		}
		return data;
	}

	@Override
	public List<Map<String, Object>> queryProductUserGiftList(Long userId) {
		// TODO Auto-generated method stub
		return userGiftMapper.queryProductUserGiftList(userId);
	}

	
	
}
