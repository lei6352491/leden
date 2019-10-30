package com.zhouyi.business.core.vo;

import java.io.Serializable;

/**
 * 套餐表
 * 
 *
 */
public class PackageVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	private String packageName;
	private Integer classHours;
	private String createTime;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public Integer getClassHours() {
		return classHours;
	}
	public void setClassHours(Integer classHours) {
		this.classHours = classHours;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	
	
}
