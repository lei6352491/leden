package com.zhouyi.business.core.model;

import java.util.List;

public class PageData<T> {
	private List<T> dataList;
	private int totalCount;
	private int totalPage;
	private int pSize;
	public List<T> getDataList() {
		return dataList;
	}
	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getTotalPage() {
		this.totalPage = (totalCount  +  pSize  - 1) / pSize;  
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getpSize() {
		return pSize;
	}
	public void setpSize(int pSize) {
		this.pSize = pSize;
	}


	public PageData() {
	}

	public PageData(List<T> dataList, int totalCount, int pSize) {
		this.dataList = dataList;
		this.totalCount = totalCount;
		this.pSize = pSize;
	}
}
