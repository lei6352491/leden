package com.zhouyi.business.core.model;

import java.io.Serializable;
import java.util.Date;

public class UserNames implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private Long userId;
    private Integer isView;
    private Integer isShare;
    private Integer isFavorite;
    private String nameDetail;
    private String surname;
    private String surnamePinyin;
    private String combine;
    private String combinePinyin;
    private String combineDescript;
    private String sancai;
    private Integer score;
    private Long requireId;
    private Long createBy;      //创建者
    private Date createTime;    //创建日期
    private Long batchId;
    private Integer usedCard;
    
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Integer getIsView() {
		return isView;
	}
	public void setIsView(Integer isView) {
		this.isView = isView;
	}
	public Integer getIsShare() {
		return isShare;
	}
	public void setIsShare(Integer isShare) {
		this.isShare = isShare;
	}
	public Integer getIsFavorite() {
		return isFavorite;
	}
	public void setIsFavorite(Integer isFavorite) {
		this.isFavorite = isFavorite;
	}
	public String getNameDetail() {
		return nameDetail;
	}
	public void setNameDetail(String nameDetail) {
		this.nameDetail = nameDetail;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getSurnamePinyin() {
		return surnamePinyin;
	}
	public void setSurnamePinyin(String surnamePinyin) {
		this.surnamePinyin = surnamePinyin;
	}
	public String getCombine() {
		return combine;
	}
	public void setCombine(String combine) {
		this.combine = combine;
	}
	public String getCombinePinyin() {
		return combinePinyin;
	}
	public void setCombinePinyin(String combinePinyin) {
		this.combinePinyin = combinePinyin;
	}
	public String getCombineDescript() {
		return combineDescript;
	}
	public void setCombineDescript(String combineDescript) {
		this.combineDescript = combineDescript;
	}
	public String getSancai() {
		return sancai;
	}
	public void setSancai(String sancai) {
		this.sancai = sancai;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public Long getRequireId() {
		return requireId;
	}
	public void setRequireId(Long requireId) {
		this.requireId = requireId;
	}
	public Long getCreateBy() {
		return createBy;
	}
	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getBatchId() {
		return batchId;
	}
	public void setBatchId(Long batchId) {
		this.batchId = batchId;
	}
	public Integer getUsedCard() {
		return usedCard;
	}
	public void setUsedCard(Integer usedCard) {
		this.usedCard = usedCard;
	}
    
}
