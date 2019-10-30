package com.zhouyi.business.model;

import java.io.Serializable;

public class Menu implements Serializable{
	/**
	 * 微信菜单
	 */
	private static final long serialVersionUID = 1L;
	private Button[] button;

	public Button[] getButton() {
		return button;
	}

	public void setButton(Button[] button) {
		this.button = button;
	}
	
	
}
