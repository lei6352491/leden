package com.zhouyi.business.core.exception;

import com.zhouyi.business.core.common.ReturnCode;

public class BusinessException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int code;

	private ReturnCode returnCode;
	
	public BusinessException(int code,String message){
		super(message);
		this.code=code;
	}
	public BusinessException(ReturnCode returnCode){
		this.returnCode=returnCode;
	}


	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}


	public ReturnCode getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(ReturnCode returnCode) {
		this.returnCode = returnCode;
	}
}
