package com.zhouyi.business.core.dao;

import com.zhouyi.business.core.model.PersonResult;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zhouyi.business.core.model.PersonAccInfo;

@Mapper
public interface PersonAccInfoMapper extends BaseMapper<PersonAccInfo, Long>{
	public PersonAccInfo getSubaccByUserId(@Param("userId") Long userId);
	
	public int addAvaBalance(@Param("totalBalance") Integer totalBalance,@Param("accNo") String accNo);
	
	public int subAvaBalance(@Param("totalBalance") Integer totalBalance,@Param("accNo") String accNo);

}
