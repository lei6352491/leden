package com.zhouyi.business.core.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhouyi.business.core.dao.PayOrderInfoMapper;
import com.zhouyi.business.core.model.PayOrderInfo;

@Service
public class PayOrderServiceImpl implements PayOrderService{

	@Autowired
	private PayOrderInfoMapper orderMapper;

	@Autowired
	private OrderNoService orderNoService;

	@Override
	public List<PayOrderInfo> queryPayOrderList(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return orderMapper.queryPayOrderList(params);
	}


	@Override
	public Integer getPayOrderCount(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return orderMapper.getPayOrderCount(params);
	}


	@Override
	public void addPayOrder(PayOrderInfo data) {
		Date now=new Date();
		// TODO Auto-generated method stub
		data.setCreateTime(now);
		data.setUpdateTime(now);
		Long orderNo=orderNoService.getOrderNo();
		data.setId(orderNo);
		if(data.getOrderNo()==null){
			data.setOrderNo(orderNo.toString());
		}
		orderMapper.insertSelective(data);
	}


	@Override
	public void updateStatus(Long id, Integer status) {
		// TODO Auto-generated method stub
		orderMapper.updateStatus(id, status);
	}

	
	
}
