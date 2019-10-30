package com.zhouyi.business.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
@ApiModel(value = "分页模型")
public class PageDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "页码",example = "1")
	private int pNo;
	@ApiModelProperty(value = "页面大小",example = "3")
	private int pSize;
	@ApiModelProperty(value = "可不传",hidden = true)
	private Long nextId;
	@ApiModelProperty(value = "可不传",hidden = true)
	private String accessToken;
	@ApiModelProperty(value = "排序方式",notes = "ASC/DESC",example = "ASC")
	private String sort="ASC";


	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public PageDto(){
		this.pSize=10;
	}
	public int getpNo() {
		return pNo;
	}


	public void setpNo(int pNo) {
		this.pNo = pNo;
	}
	public int getpSize() {
		return pSize;
	}
	public void setpSize(int pSize) {
		pSize=(pSize==0?10:pSize);
		this.pSize = pSize;
	}
	public Long getNextId() {
		return nextId;
	}
	public void setNextId(Long nextId) {
		this.nextId = nextId;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	
	
}
