package com.zhouyi.business.dto;

import java.io.Serializable;

public class OrderDto extends PageDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long requireId;
	private Long orderId;

	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public Long getRequireId() {
		return requireId;
	}
	public void setRequireId(Long requireId) {
		this.requireId = requireId;
	}
	
	
	
}
