package com.zhouyi.business.core.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 起名批次信息
 * 
 *
 */
@SuppressWarnings("serial")
public class FornameBatchInfo implements Serializable {

	private Long id;
	private Integer buildNum;// 平台类型:1微信
	private Long createBy;// 城市
	private Date createTime;// 授权时间
	private String orderNo;
	private String nameList;
	private String filePath;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public Integer getBuildNum() {
		return buildNum;
	}
	public void setBuileNum(Integer buildNum) {
		this.buildNum = buildNum;
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
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getNameList() {
		return nameList;
	}
	public void setNameList(String nameList) {
		this.nameList = nameList;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public void setBuildNum(Integer buildNum) {
		this.buildNum = buildNum;
	}

	
}
