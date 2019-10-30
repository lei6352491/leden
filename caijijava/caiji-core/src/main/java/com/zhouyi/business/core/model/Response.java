package com.zhouyi.business.core.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.ToString;

@ApiModel(value = "响应数据对象模型")
@ToString
public class Response<T> {
	@ApiModelProperty(value = "状态编码",example = "200")
	private int code;
	@ApiModelProperty(value = "附带得文字信息")
	private String msg;
	@ApiModelProperty(value = "相应的数据模型")
	private T data;
	
	public Response(){
		this(0,null);
	}
	public Response(int code,String msg){
		this.code=code;
		this.msg=msg;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	
	
}
