package com.zhouyi.business.core.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zhouyi.business.core.model.UserNames;

@Mapper
public interface UserNamesMapper extends BaseMapper<UserNames, Long>{
	public List<UserNames> queryNameResultList(@Param("userId") Long userId,@Param("pStart") int pStart,@Param("pSize") int pSize);
	
	public Map<String,String> getUserNamesById(@Param("id") Long id);
	
	public List<UserNames> queryCollectionNameList(@Param("userId") Long userId,@Param("pStart") int pStart,@Param("pSize") int pSize);
	
	public Integer getCollectionCountByOwnerId(@Param("userId") Long userId);
	
	public void addTransferNum(@Param("id") Long id);
	
	public List<UserNames> queryNameResultListByRequireId(@Param("requireId") Long requireId,@Param("userId") Long userId);
	
	public List<UserNames> queryCardRecordList(@Param("userId") Long userId);
}
