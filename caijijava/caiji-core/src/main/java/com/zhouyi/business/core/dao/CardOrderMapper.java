package com.zhouyi.business.core.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zhouyi.business.core.model.CardOrderInfo;

@Mapper
public interface CardOrderMapper extends BaseMapper<CardOrderInfo, Long>{
	public CardOrderInfo getCardOrderByNo(@Param("orderNo") String orderNo);
}
