package com.zhouyi.business.core.vo;

import java.io.Serializable;

public class UserPackageVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	private Long userId;
	private Integer classHours;
	private String packageName;
	private Integer usedHours;
	private Integer remainHours;
	
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
	public Integer getClassHours() {
		return classHours;
	}
	public void setClassHours(Integer classHours) {
		this.classHours = classHours;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public Integer getUsedHours() {
		return usedHours;
	}
	public void setUsedHours(Integer usedHours) {
		this.usedHours = usedHours;
	}
	public Integer getRemainHours() {
		return remainHours;
	}
	public void setRemainHours(Integer remainHours) {
		this.remainHours = remainHours;
	}
	
}
