package com.zhouyi.business.model;

import java.io.Serializable;
import java.util.List;

public class PictInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String Title;

    private String PicUrl;

    private String Url;

    private String Description;

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getPicUrl() {
		return PicUrl;
	}

	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}

	public String getUrl() {
		return Url;
	}

	public void setUrl(String url) {
		Url = url;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

}
