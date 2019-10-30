package com.zhouyi.business.core.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 登录记录信息
 * 
 *
 */
@SuppressWarnings("serial")
public class MemberLoginInfo implements Serializable {

	private Long id;
	private Long userId;// 用户ID
	private Date createTime;// 授权时间
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
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
}
