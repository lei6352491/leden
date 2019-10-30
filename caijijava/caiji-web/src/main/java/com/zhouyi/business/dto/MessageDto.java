package com.zhouyi.business.dto;

public class MessageDto extends PageDto{

	private static final long serialVersionUID = -7466758037340567431L;
	
	private Integer type;
	private Long id;
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
	
}
