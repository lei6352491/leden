package com.zhouyi.business.core.vo;

import java.io.Serializable;

public class UserVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	private String taobaoId;
	private Integer isDeleted;
	private String fullName;
	private Integer userType;
	private String phone;
	private String licenseUrl;
	private String description;
	private Integer sex;
	private String headImage;
	private Integer remainHours;
	private Integer confirmAppointNum;
	private Integer confirmDoneNum;
	private Integer appointNum;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTaobaoId() {
		return taobaoId;
	}
	public void setTaobaoId(String taobaoId) {
		this.taobaoId = taobaoId;
	}
	public Integer getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public Integer getUserType() {
		return userType;
	}
	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getLicenseUrl() {
		return licenseUrl;
	}
	public void setLicenseUrl(String licenseUrl) {
		this.licenseUrl = licenseUrl;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getHeadImage() {
		return headImage;
	}
	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}
	public Integer getRemainHours() {
		return remainHours;
	}
	public void setRemainHours(Integer remainHours) {
		this.remainHours = remainHours;
	}
	public Integer getConfirmAppointNum() {
		return confirmAppointNum;
	}
	public void setConfirmAppointNum(Integer confirmAppointNum) {
		this.confirmAppointNum = confirmAppointNum;
	}
	public Integer getConfirmDoneNum() {
		return confirmDoneNum;
	}
	public void setConfirmDoneNum(Integer confirmDoneNum) {
		this.confirmDoneNum = confirmDoneNum;
	}
	public Integer getAppointNum() {
		return appointNum;
	}
	public void setAppointNum(Integer appointNum) {
		this.appointNum = appointNum;
	}
	
}
