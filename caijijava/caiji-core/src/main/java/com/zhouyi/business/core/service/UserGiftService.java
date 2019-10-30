package com.zhouyi.business.core.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zhouyi.business.core.vo.UserGiftVo;

public interface UserGiftService {
	public List<UserGiftVo> queryUserGiftList(Map<String,Object> params);
	public Map<Long,Integer> getUserGiftCount(Long userId);
	
	public List<Map<String,Object>> queryProductUserGiftList(Long userId);
}
