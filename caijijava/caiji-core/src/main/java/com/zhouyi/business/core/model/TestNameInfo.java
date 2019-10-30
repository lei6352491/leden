package com.zhouyi.business.core.model;

import java.io.Serializable;
import java.util.Date;

public class TestNameInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String familyName;
    private String birthday;
    private Integer sex;
    private String homePlace;
    private Double latitude;
    private Double longitude;
    private Long userId;      //创建者
    private Date createTime;    //创建日期
    private Integer isDouble;
    private String combine;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getFamilyName() {
		return familyName;
	}
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getHomePlace() {
		return homePlace;
	}
	public void setHomePlace(String homePlace) {
		this.homePlace = homePlace;
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

	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public Integer getIsDouble() {
		return isDouble;
	}
	public void setIsDouble(Integer isDouble) {
		this.isDouble = isDouble;
	}
	public String getCombine() {
		return combine;
	}
	public void setCombine(String combine) {
		this.combine = combine;
	}
	
}
