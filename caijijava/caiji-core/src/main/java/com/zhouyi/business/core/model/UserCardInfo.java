package com.zhouyi.business.core.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 高分卡订单信息
 * 
 *
 */
public class UserCardInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Long userId;
	private Integer totalNum;
	private Date updateTime;
	private Date createTime;
	private Integer unLock;
	private Integer usedNum;
	
	public UserCardInfo(){
		this.unLock=0;
		this.usedNum=0;
		this.totalNum=0;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
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
	public Integer getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}

	public Integer getUsedNum() {
		return usedNum;
	}
	public void setUsedNum(Integer usedNum) {
		this.usedNum = usedNum;
	}
	public Integer getUnLock() {
		return unLock;
	}
	public void setUnLock(Integer unLock) {
		this.unLock = unLock;
	}
	
}
