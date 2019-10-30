package com.zhouyi.business.core.service;

public interface PersonAccService {
	public void incomeBalance(Long userId,int amount,Long serialNo);
	public void deduBalance(Long userId,int amount,Long serialNo);
}
