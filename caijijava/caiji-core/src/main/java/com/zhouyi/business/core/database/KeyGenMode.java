package com.zhouyi.business.core.database;


public enum KeyGenMode {
	/** 数据库ID自增 */
	AUTO_INCREMENT(0),
	/** MYCAT 序列自增 */
	MYCAT(1);
	
	private int code;
	
	private KeyGenMode(int code) {
		this.code = code;
	}

	/**
	 * @return the code
	 */
	public int getCode() {
		return code;
	}
	
	@Override
	public String toString() {
		return Integer.toString(code);
	}

}
