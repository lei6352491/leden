package com.zhouyi.business.dto;

public class BaseDto extends PageDto{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** id */
//    private Integer userType;
    private String nickName;
    private String keyword;
    private Long userId;
    
//	public Integer getUserType() {
//		return userType;
//	}
//	public void setUserType(Integer userType) {
//		this.userType = userType;
//	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
    
	
}