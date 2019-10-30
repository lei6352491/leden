package com.zhouyi.business.core.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhouyi.business.core.dao.CardOrderMapper;
import com.zhouyi.business.core.dao.UserCardMapper;
import com.zhouyi.business.core.dao.UserNamesMapper;
import com.zhouyi.business.core.model.CardOrderInfo;
import com.zhouyi.business.core.model.UserCardInfo;
import com.zhouyi.business.core.model.UserNames;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    private CardOrderMapper cardOrderMapper;

    @Autowired
	private OrderNoService orderNoService;
	
	@Autowired
	private UserCardMapper userCardMapper;
	
	@Autowired
	private UserNamesMapper userNamesMapper;
	
	@Override
	public void addCard(CardOrderInfo data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CardOrderInfo getCardOrderByNo(String orderNo) {
		// TODO Auto-generated method stub
		return cardOrderMapper.getCardOrderByNo(orderNo);
	}

	@Override
	public void addCardOrder(CardOrderInfo data) {
		// TODO Auto-generated method stub
		Long orderNo=orderNoService.getOrderNo();
		data.setOrderNo(orderNo.toString());
		cardOrderMapper.insertSelective(data);
	}


	@Transactional
	@Override
	public void updateCardOrder(CardOrderInfo data) {
		// TODO Auto-generated method stub
		Date now=new Date();
		data.setUpdateTime(now);
		cardOrderMapper.updateByPrimaryKeySelective(data);
		
		Integer count=0;
		//解锁高分卡
		if(data.getUnLock()!=null && data.getUnLock()==1){
			count=userCardMapper.unlockUserCard(data.getUserId());
		}else{
			//购买高分卡
			count=userCardMapper.addUserCard(data.getUserId(), data.getNum());
		}
		if(count==null || count==0){
			UserCardInfo uci=new UserCardInfo();
			uci.setTotalNum(data.getNum());
			uci.setCreateTime(now);
			uci.setUpdateTime(now);
			uci.setUserId(data.getUserId());
			uci.setUnLock(data.getUnLock());
			userCardMapper.insertAndReturnId(uci);
		}
	}

	@Override
	public Integer reduceUserCard(Long userId) {
		// TODO Auto-generated method stub
		return userCardMapper.reduceUserCard(userId);
	}

	@Override
	public UserCardInfo getUserCardByUserId(Long userId) {
		// TODO Auto-generated method stub
		return userCardMapper.getUserCardByUserId(userId);
	}

	@Override
	public List<UserNames> queryCardRecordList(Long userId) {
		// TODO Auto-generated method stub
		return userNamesMapper.queryCardRecordList(userId);
	}
}
