package com.zhouyi.business.core.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zhouyi.business.core.model.CardOrderInfo;
import com.zhouyi.business.core.model.UserCardInfo;
import com.zhouyi.business.core.model.UserNames;

public interface CardService {
	
	public void addCard(CardOrderInfo data);
	
	public CardOrderInfo getCardOrderByNo(String orderNo);
	
/**
	 * 添加卡
	 * @param data*/


	public void addCardOrder(CardOrderInfo data);
	
	public void updateCardOrder(CardOrderInfo data);
	
/**
	 * 减少高分卡
	 * @param userId
	 * @return*/


	public Integer reduceUserCard(Long userId);
	
	public UserCardInfo getUserCardByUserId(Long userId);
	
	public List<UserNames> queryCardRecordList(Long userId);
}
