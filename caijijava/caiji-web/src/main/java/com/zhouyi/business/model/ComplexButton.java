package com.zhouyi.business.model;

public class ComplexButton extends Button{
	/**
	 * 微信菜单
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private Button[] sub_button;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Button[] getSub_button() {
		return sub_button;
	}
	public void setSub_button(Button[] sub_button) {
		this.sub_button = sub_button;
	}

	
	
}
