package com.zhouyi.business.core.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zhouyi.business.core.model.PayOrderInfo;

@Mapper
public interface PayOrderInfoMapper extends BaseMapper<PayOrderInfo, Long>{
	public List<PayOrderInfo> queryPayOrderList(Map<String,Object> params);
	
	public Integer getPayOrderCount(Map<String,Object> params);
	
	public void updateStatus(@Param("id") Long id, @Param("status") Integer status);
	
	public PayOrderInfo getPayOrderInfoByOrderNo(@Param("orderNo") String orderNo);
	
}
