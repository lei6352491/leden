package com.zhouyi.business.core.service;

import java.util.List;
import java.util.Map;

import com.zhouyi.business.core.model.PayOrderInfo;

public interface PayOrderService {
	public List<PayOrderInfo> queryPayOrderList(Map<String,Object> params);
	public Integer getPayOrderCount(Map<String,Object> params);
	
	public void addPayOrder(PayOrderInfo data);
	
	public void updateStatus(Long id,Integer status);
}
