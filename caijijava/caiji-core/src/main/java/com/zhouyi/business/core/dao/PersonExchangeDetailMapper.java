package com.zhouyi.business.core.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zhouyi.business.core.model.PersonExchangeDetail;

@Mapper
public interface PersonExchangeDetailMapper extends BaseMapper<PersonExchangeDetail, Long>{
	public PersonExchangeDetail getPersonExchangeDetailByNo(@Param("serialNo") Long serialNo,@Param("operateType") String operateType);
}
