package com.zhouyi.business.core.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户信息
 * 
 *
 */
public class NameCommentInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	private String comment;
	private Date createTime;
	private Long publisher;
	private Integer isShow;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public Integer getIsShow() {
		return isShow;
	}
	public void setIsShow(Integer isShow) {
		this.isShow = isShow;
	}
	public Long getPublisher() {
		return publisher;
	}
	public void setPublisher(Long publisher) {
		this.publisher = publisher;
	}
	
}
