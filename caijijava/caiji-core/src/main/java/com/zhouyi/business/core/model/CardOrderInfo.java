package com.zhouyi.business.core.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 高分卡订单信息
 * 
 *
 */
public class CardOrderInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String orderNo;
	private Integer amount;
	private Integer payType;
	private Integer orderStatus;
	private Long userId;
	private Date payTime;
	private Date updateTime;
	private Date createTime;
	private Long updateBy;
	private Integer num;
	private Integer refundStatus;
	private Integer unLock;
	private String channelSerialNum;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Integer getPayType() {
		return payType;
	}
	public void setPayType(Integer payType) {
		this.payType = payType;
	}
	public Integer getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Date getPayTime() {
		return payTime;
	}
	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Long getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(Long updateBy) {
		this.updateBy = updateBy;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public Integer getRefundStatus() {
		return refundStatus;
	}
	public void setRefundStatus(Integer refundStatus) {
		this.refundStatus = refundStatus;
	}
	public Integer getUnLock() {
		return unLock;
	}
	public void setUnLock(Integer unLock) {
		this.unLock = unLock;
	}
	public String getChannelSerialNum() {
		return channelSerialNum;
	}
	public void setChannelSerialNum(String channelSerialNum) {
		this.channelSerialNum = channelSerialNum;
	}

	
}
