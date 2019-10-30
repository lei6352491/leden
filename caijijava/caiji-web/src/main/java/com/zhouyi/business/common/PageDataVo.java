package com.zhouyi.business.common;

import java.util.List;

public class PageDataVo<T> {
	private List<T> dataList;
	private int totalCount;
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
	
	
}
