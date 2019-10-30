package com.zhouyi.business.core.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhouyi.business.core.dao.CardOrderMapper;
import com.zhouyi.business.core.dao.FornameBatchInfoMapper;
import com.zhouyi.business.core.dao.FornameRequirementsMapper;
import com.zhouyi.business.core.dao.OrderMapper;
import com.zhouyi.business.core.dao.UserCardMapper;
import com.zhouyi.business.core.model.FornameRequirements;
import com.zhouyi.business.core.model.OrderInfo;
import com.zhouyi.business.core.vo.OrderVo;

@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	private OrderMapper orderMapper;

	@Autowired
	private FornameBatchInfoMapper fornameBatchInfoMapper;
	
	@Autowired
	private FornameRequirementsMapper fornameRequirementsMapper;

	@Autowired
	private CardOrderMapper cardOrderMapper;
	
	@Autowired
	private OrderNoService orderNoService;
	
	@Autowired
	private UserCardMapper userCardMapper;
	
	@Override
	public List<OrderVo> queryOrderList(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return orderMapper.queryOrderList(params);
	}


	@Override
	public Integer getOrderCountByOwnerId(Long ownerId) {
		// TODO Auto-generated method stub
		return orderMapper.getOrderCountByOwnerId(ownerId);
	}


	@Override
	public List<Map<String, Object>> queryOrderNameList(Long orderId) {
		// TODO Auto-generated method stub
		return orderMapper.queryOrderNameList(orderId);
	}


	@Override
	public OrderInfo getObjectById(Long id) {
		// TODO Auto-generated method stub
		return orderMapper.selectByPrimaryKey(id);
	}


	@Override
	public OrderInfo getLastOrderByUser(Long userId) {
		// TODO Auto-generated method stub
		return orderMapper.getLastOrderByUser(userId);
	}


	@Override
	public Map<String, Object> getLastOrderDetailByUser(Long userId) {
		// TODO Auto-generated method stub
		return orderMapper.getLastOrderDetailByUser(userId);
	}


	@Override
	public List<Map<String, Object>> getFornameBatchList(String orderNo) {
		// TODO Auto-generated method stub
		return fornameBatchInfoMapper.getFornameBatchList(orderNo);
	}

	@Override
	public void updateOrderUserByPhone(String phone, Long userId) {
		// TODO Auto-generated method stub
		orderMapper.updateOrderUserByPhone(phone, userId);
	}


	@Override
	public FornameRequirements getFornameRequirementsByOrderNo(String orderNo) {
		// TODO Auto-generated method stub
		return fornameRequirementsMapper.getFornameRequirementsByOrderNo(orderNo);
	}


	@Override
	public void addOrder(OrderInfo orderInfo) {
		// TODO Auto-generated method stub
		orderMapper.insertAndReturnId(orderInfo);
	}


	@Override
	public OrderInfo getObjectByOrderNo(String orderNo) {
		// TODO Auto-generated method stub
		return orderMapper.getObjectByOrderNo(orderNo);
	}


	@Override
	public Integer updateOrderStatus(Integer orderStatus, Long id) {
		// TODO Auto-generated method stub
		return orderMapper.updateOrderStatus(orderStatus, id);
	}


	@Override
	public Integer updateOrderInfo(OrderInfo order) {
		// TODO Auto-generated method stub
		return orderMapper.updateByPrimaryKeySelective(order);
	}
	
}
