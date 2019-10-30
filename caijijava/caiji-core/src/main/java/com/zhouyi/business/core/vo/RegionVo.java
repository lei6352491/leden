package com.zhouyi.business.core.vo;

import java.io.Serializable;

public class RegionVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	private String areaName;
	private Integer areaType;
	private String branchOffice;
	private Long parentId;
	private String businessOffice;
	private Integer nextNum;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public Integer getAreaType() {
		return areaType;
	}
	public void setAreaType(Integer areaType) {
		this.areaType = areaType;
	}
	public String getBranchOffice() {
		return branchOffice;
	}
	public void setBranchOffice(String branchOffice) {
		this.branchOffice = branchOffice;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public String getBusinessOffice() {
		return businessOffice;
	}
	public void setBusinessOffice(String businessOffice) {
		this.businessOffice = businessOffice;
	}
	public Integer getNextNum() {
		return nextNum;
	}
	public void setNextNum(Integer nextNum) {
		this.nextNum = nextNum;
	}
	
}
