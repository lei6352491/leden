package com.zhouyi.business.core.vo;

import java.io.Serializable;

public class UserDetailVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long userId;
	private String userName;
	private String fullName;
	private Integer userType;
	private String phone;
	private Integer isForbidden;
	private String departCode;
	private String dutyCode;
	private String birthdate;
	private String registerDate;
	private String classifyName;
	private String outlet;
	private Integer isInner;
	private String systemType;
	private String undertake;
	private String branchOffice;
	private String region;
	private String businessOffice;
	private Long browseNum;
	private Integer loginNum;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
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
	public Integer getIsForbidden() {
		return isForbidden;
	}
	public void setIsForbidden(Integer isForbidden) {
		this.isForbidden = isForbidden;
	}
	public String getDepartCode() {
		return departCode;
	}
	public void setDepartCode(String departCode) {
		this.departCode = departCode;
	}
	public String getDutyCode() {
		return dutyCode;
	}
	public void setDutyCode(String dutyCode) {
		this.dutyCode = dutyCode;
	}
	public String getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public String getOutlet() {
		return outlet;
	}
	public void setOutlet(String outlet) {
		this.outlet = outlet;
	}
	public Integer getIsInner() {
		return isInner;
	}
	public void setIsInner(Integer isInner) {
		this.isInner = isInner;
	}
	public String getSystemType() {
		return systemType;
	}
	public void setSystemType(String systemType) {
		this.systemType = systemType;
	}
	public String getUndertake() {
		return undertake;
	}
	public void setUndertake(String undertake) {
		this.undertake = undertake;
	}

	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getBusinessOffice() {
		return businessOffice;
	}
	public void setBusinessOffice(String businessOffice) {
		this.businessOffice = businessOffice;
	}
	public String getBranchOffice() {
		return branchOffice;
	}
	public void setBranchOffice(String branchOffice) {
		this.branchOffice = branchOffice;
	}
	public String getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}
	public String getClassifyName() {
		return classifyName;
	}
	public void setClassifyName(String classifyName) {
		this.classifyName = classifyName;
	}
	public Long getBrowseNum() {
		return browseNum==null?0:browseNum;
	}
	public void setBrowseNum(Long browseNum) {
		this.browseNum = browseNum;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Integer getLoginNum() {
		return loginNum==null?0:loginNum;
	}
	public void setLoginNum(Integer loginNum) {
		this.loginNum = loginNum;
	}

	
}
