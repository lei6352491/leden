package com.zhouyi.business.core.service;

import com.zhouyi.business.core.model.OrderInfo;
import com.zhouyi.business.core.model.TransactionInfo;
import com.zhouyi.business.core.vo.WXRechargeBackVo;
import com.zhouyi.business.core.vo.WXRechargeVo;

public interface WXPayService {
	public final String TEMPLATE_001="AaNjht-oTH81mltJn2X5d5jhLa_ehPAYFXqIu3PnNP0";
	/**
	 * 微信充值预下单
	 * @param WXOpenId	用户标识
	 * @param amount	金额，单位：分
	 * @param orderNo	订单流水号
	 * @param userIP	用户IP
	 * @return
	 */
	public WXRechargeVo payOrder(TransactionInfo orderInfo);
	
	/**
	 * 提现
	 * @param orderInfo
	 * @return
	 */
	public WXRechargeVo toWithdrawals(TransactionInfo orderInfo);
	
	/**
	 * 支付回调
	 * @param xml
	 * @return
	 */
	public WXRechargeBackVo payNotice(String xml);
	
	/**
	 * 支付回调
	 * @param xml
	 * @return
	 */
	public WXRechargeBackVo cardPayNotice(String xml);
	
	/**
	 * 人工支付回调
	 * @param xml
	 * @return
	 */
	public WXRechargeBackVo personPayNotice(String xml);
	
	/**
	 * 微信充值预下单
	 * @param WXOpenId	用户标识
	 * @param amount	金额，单位：分
	 * @param orderNo	订单流水号
	 * @param userIP	用户IP
	 * @return
	 */
	public WXRechargeVo payAppOrder(TransactionInfo orderInfo);
	
	/**
	 * 发送提醒公众号
	 * @param orderNo
	 */
	public void sendRemindMessage(OrderInfo orderInfo);
}
