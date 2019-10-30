package com.zhouyi.business.core.vo;

import org.apache.http.HttpStatus;


public class ResponseVo {

	public int status;
	public String data;
	
	public ResponseVo() {
		status = HttpStatus.SC_INTERNAL_SERVER_ERROR;
		data = "";
	}
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
	public boolean isOk() {
		return this.status == HttpStatus.SC_OK;
	}
}
