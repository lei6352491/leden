package com.zhouyi.business.core.model;

import java.io.Serializable;
import java.util.Date;

public class FornameRequirements implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private Integer operateType;
    private String familyName;
    private String birthday;
    private Integer sex;
    private Integer nameType;
    private String homePlace;
    private String middleName;
    private String lastName;
    private String limitName;
    private Integer sysRecommend;
    private String midFiveElements;
    private String lastFiveElements;
    private String threeTalents;
    private Integer isTaboo;
    private Integer isPunching;
    private Integer fiveLuckyNum;
    private Integer threeLuckyNum;
    private Double latitude;
    private Double longitude;
    private Long userId;      //创建者
    private Date createTime;    //创建日期
    private String orderNo;
    private String phone;
    private String remark;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getOperateType() {
		return operateType;
	}
	public void setOperateType(Integer operateType) {
		this.operateType = operateType;
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
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getLimitName() {
		return limitName;
	}
	public void setLimitName(String limitName) {
		this.limitName = limitName;
	}
	public Integer getSysRecommend() {
		return sysRecommend;
	}
	public void setSysRecommend(Integer sysRecommend) {
		this.sysRecommend = sysRecommend;
	}
	public String getMidFiveElements() {
		return midFiveElements;
	}
	public void setMidFiveElements(String midFiveElements) {
		this.midFiveElements = midFiveElements;
	}
	public String getLastFiveElements() {
		return lastFiveElements;
	}
	public void setLastFiveElements(String lastFiveElements) {
		this.lastFiveElements = lastFiveElements;
	}
	public String getThreeTalents() {
		return threeTalents;
	}
	public void setThreeTalents(String threeTalents) {
		this.threeTalents = threeTalents;
	}
	public Integer getIsTaboo() {
		return isTaboo;
	}
	public void setIsTaboo(Integer isTaboo) {
		this.isTaboo = isTaboo;
	}
	public Integer getIsPunching() {
		return isPunching;
	}
	public void setIsPunching(Integer isPunching) {
		this.isPunching = isPunching;
	}
	public Integer getFiveLuckyNum() {
		return fiveLuckyNum;
	}
	public void setFiveLuckyNum(Integer fiveLuckyNum) {
		this.fiveLuckyNum = fiveLuckyNum;
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
	public Integer getThreeLuckyNum() {
		return threeLuckyNum;
	}
	public void setThreeLuckyNum(Integer threeLuckyNum) {
		this.threeLuckyNum = threeLuckyNum;
	}
	public Integer getNameType() {
		return nameType;
	}
	public void setNameType(Integer nameType) {
		this.nameType = nameType;
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
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
