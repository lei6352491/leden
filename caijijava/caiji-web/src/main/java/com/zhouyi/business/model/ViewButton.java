package com.zhouyi.business.model;

public class ViewButton extends Button{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String type;//菜单类型
	private String url;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	
}
