package com.zhouyi.business.model;

import java.util.Date;

import com.zhouyi.business.core.vo.MemberVo;

public class LoginData {
	private Date expiresTime;
	private String accessToken;
	private MemberVo member;
	private Integer isOnOther;
	
	public Date getExpiresTime() {
		return expiresTime;
	}
	public void setExpiresTime(Date expiresTime) {
		this.expiresTime = expiresTime;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public Integer getIsOnOther() {
		return isOnOther;
	}
	public void setIsOnOther(Integer isOnOther) {
		this.isOnOther = isOnOther;
	}
	public MemberVo getMember() {
		return member;
	}
	public void setMember(MemberVo member) {
		this.member = member;
	}
	
}
