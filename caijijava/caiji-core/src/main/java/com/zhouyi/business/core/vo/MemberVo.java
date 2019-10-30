package com.zhouyi.business.core.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户信息
 * 
 *
 */
public class MemberVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Date updateTime;
	private Date createTime;
	private Long updateBy;
	private String sex;
	private Integer isForbidden;
	private String nickName;
	private Integer isDeleted;
	private Integer userType;
	private String phone;
	private String headImage;
	private Date loginTime;
	private String portalType;
	private String openId;
	private String unionId;
	private Integer unLock;
	private Integer cardNum;
	private Integer usedNum;
	
	public MemberVo(){
		this.unLock=0;
		this.cardNum=0;
		this.usedNum=0;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Long getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(Long updateBy) {
		this.updateBy = updateBy;
	}

	public Integer getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
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

	public String getHeadImage() {
		return headImage;
	}
	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Integer getIsForbidden() {
		return isForbidden;
	}
	public void setIsForbidden(Integer isForbidden) {
		this.isForbidden = isForbidden;
	}
	public Date getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	public String getPortalType() {
		return portalType;
	}
	public void setPortalType(String portalType) {
		this.portalType = portalType;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getUnionId() {
		return unionId;
	}
	public void setUnionId(String unionId) {
		this.unionId = unionId;
	}
	public Integer getUnLock() {
		return unLock==null?0:unLock;
	}
	public void setUnLock(Integer unLock) {
		this.unLock = unLock;
	}
	public Integer getCardNum() {
		return cardNum==null?0:cardNum;
	}
	public void setCardNum(Integer cardNum) {
		this.cardNum = cardNum;
	}

	public Integer getUsedNum() {
		return usedNum;
	}

	public void setUsedNum(Integer usedNum) {
		this.usedNum = usedNum;
	}
	
	
}
