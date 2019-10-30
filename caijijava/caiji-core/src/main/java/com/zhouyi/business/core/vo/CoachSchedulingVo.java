package com.zhouyi.business.core.vo;

import java.io.Serializable;

public class CoachSchedulingVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long coachId;
	private Integer localMonthHours;
	private Integer lastMonthHours;
	private String fullName;
	private String phone;
	private String headImage;
	private Integer workHours;
	private String createMonth;
	
	public Long getCoachId() {
		return coachId;
	}
	public void setCoachId(Long coachId) {
		this.coachId = coachId;
	}
	public Integer getLocalMonthHours() {
		return localMonthHours;
	}
	public void setLocalMonthHours(Integer localMonthHours) {
		this.localMonthHours = localMonthHours;
	}
	public Integer getLastMonthHours() {
		return lastMonthHours;
	}
	public void setLastMonthHours(Integer lastMonthHours) {
		this.lastMonthHours = lastMonthHours;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getHeadImage() {
		return headImage;
	}
	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}
	public Integer getWorkHours() {
		return workHours;
	}
	public void setWorkHours(Integer workHours) {
		this.workHours = workHours;
	}
	public String getCreateMonth() {
		return createMonth;
	}
	public void setCreateMonth(String createMonth) {
		this.createMonth = createMonth;
	}
	
}
