package com.zhouyi.business.core.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zhouyi.business.core.model.OrderInfo;
import com.zhouyi.business.core.vo.OrderVo;

@Mapper
public interface OrderMapper extends BaseMapper<OrderInfo, Long>{
	public List<OrderVo> queryOrderList(Map<String,Object> params);
	
	public Integer getOrderCountByOwnerId(@Param("ownerId") Long ownerId);
	
	public List<Map<String,Object>> queryOrderNameList(@Param("orderId") Long orderId);
	
	public OrderInfo getLastOrderByUser(@Param("userId") Long userId);
	
	public Map<String, Object> getLastOrderDetailByUser(@Param("userId") Long userId);
	
	public void updateOrderUserByPhone(@Param("phone") String phone,@Param("userId") Long userId);
	
	public OrderInfo getObjectByOrderNo(@Param("orderNo") String orderNo);
	
	public Integer updateOrderStatus(@Param("orderStatus") Integer orderStatus,@Param("id") Long id);
}
