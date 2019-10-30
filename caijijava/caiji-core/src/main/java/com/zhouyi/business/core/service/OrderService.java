package com.zhouyi.business.core.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zhouyi.business.core.model.FornameRequirements;
import com.zhouyi.business.core.model.OrderInfo;
import com.zhouyi.business.core.vo.OrderVo;

public interface OrderService {
	public List<OrderVo> queryOrderList(Map<String,Object> params);
	public Integer getOrderCountByOwnerId(Long ownerId);
	
	public List<Map<String,Object>> queryOrderNameList(Long orderId);
	
	public OrderInfo getObjectById(Long id);
	
	public OrderInfo getLastOrderByUser(Long userId);
	
	public Map<String,Object> getLastOrderDetailByUser(Long userId);
	
	public List<Map<String,Object>> getFornameBatchList(String orderNo);
	
	public void updateOrderUserByPhone(String phone,Long userId);
	
	/**
	 * 查询起名需求
	 * @param orderNo
	 * @return
	 */
	public FornameRequirements getFornameRequirementsByOrderNo(String orderNo);
	
	public void addOrder(OrderInfo orderInfo);
	
	public OrderInfo getObjectByOrderNo(String orderNo);
	
	public Integer updateOrderStatus(Integer orderStatus,@Param("id") Long id);
	
	public Integer updateOrderInfo(OrderInfo order);
	
}
