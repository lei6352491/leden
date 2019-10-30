package com.zhouyi.business.core.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 授权信息
 * 
 *
 */
@SuppressWarnings("serial")
public class MemberAuth implements Serializable {

	private Long id;
	private Long memberId;// 用户ID
	private Integer platformType;// 平台类型:1微信
	private Integer portalType;// 渠道类型：100-星意广告平台cifmedia
	private String openId;// 用户在渠道的openId
	private String nickname;// 昵称
	private String headImage;// 头像地址
	private String sex;// 性别
	private String city;// 城市
	private Date createTime;// 授权时间
	private String unionId;
	private Integer isAttention;
	private Date updateTime;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getMemberId() {
		return memberId;
	}
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	public Integer getPlatformType() {
		return platformType;
	}
	public void setPlatformType(Integer platformType) {
		this.platformType = platformType;
	}
	public Integer getPortalType() {
		return portalType;
	}
	public void setPortalType(Integer portalType) {
		this.portalType = portalType;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getHeadImage() {
		return headImage;
	}
	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getUnionId() {
		return unionId;
	}
	public void setUnionId(String unionId) {
		this.unionId = unionId;
	}
	public Integer getIsAttention() {
		return isAttention;
	}
	public void setIsAttention(Integer isAttention) {
		this.isAttention = isAttention;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
}
