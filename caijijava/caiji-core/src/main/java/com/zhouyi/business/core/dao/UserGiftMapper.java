package com.zhouyi.business.core.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zhouyi.business.core.model.UserGiftInfo;
import com.zhouyi.business.core.vo.UserGiftVo;

@Mapper
public interface UserGiftMapper extends BaseMapper<UserGiftInfo, Long>{
	public List<UserGiftVo> queryAllUserGiftList(Map<String,Object> params);
	public List<Map<String,Object>> getProductUserGiftCount(@Param("userId") Long userId);
	
	public List<Map<String,Object>> queryProductUserGiftList(@Param("userId") Long userId);
	
	public Integer getGiftCountByOwnerId(@Param("userId") Long userId);
}
