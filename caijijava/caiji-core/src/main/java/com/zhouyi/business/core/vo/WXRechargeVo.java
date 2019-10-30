package com.zhouyi.business.core.vo;

import com.zhouyi.business.core.common.ReturnCode;
import com.zhouyi.business.core.exception.BusinessException;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WXRechargeVo extends WXVo {
	
	private static final Logger logger = LoggerFactory.getLogger(WXRechargeVo.class);
	

	/** 交易类型 */
	private String tradeType;
	/** 预支付交易会话标识 */
	private String prepayId;
	/** 二维码链接 */
	private String codeUrl;
	
	
	// 前端所需要用到的参数，与微信返回无关
	/** 商户公众号 */
	private String appId;
	/** 时间戳 */
	private String timeStamp;
	/** 随机串 */
	private String nonceStr;
	/** 微信签名方式 */
	private String signType;
	/** 微信签名 */
	private String paySign;
	/**  */
	private String packageValue;
	/** 商户流水号 */
	private String orderNo;
	
	private String mercialNo;
	
	/*
	 * APP坑，微信文档参数大小写与实际参数大小写不一致
	 * 
	 * Map<String, String> map = new HashMap<>();
	 * map.put("appid", WeChatConstant.APP_ID);
	 * map.put("noncestr", RandomStringUtils.randomAlphanumeric(32));
	 * map.put("package", "Sign=WXPay");
	 * map.put("partnerid", WeChatConstant.APP_MCH_ID);
	 * map.put("prepayid", prepayId);
	 * map.put("timestamp", String.valueOf((new Date().getTime())));
	 * String sign = MD5Util.getSign(map, WeChatConstant.SALT);
	 * map.put("sign", sign);
	 */
	
	
	public String getTradeType() {
		return tradeType;
	}
	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}
	public String getPrepayId() {
		return prepayId;
	}
	public void setPrepayId(String prepayId) {
		this.prepayId = prepayId;
	}
	public String getCodeUrl() {
		return codeUrl;
	}
	public void setCodeUrl(String codeUrl) {
		this.codeUrl = codeUrl;
	}
	

	
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getPackageValue() {
		return packageValue;
	}
	public void setPackageValue(String packageValue) {
		this.packageValue = packageValue;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getNonceStr() {
		return nonceStr;
	}
	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}
	public String getSignType() {
		return signType;
	}
	public void setSignType(String signType) {
		this.signType = signType;
	}
	public String getPaySign() {
		return paySign;
	}
	public void setPaySign(String paySign) {
		this.paySign = paySign;
	}
	
	
	public void resolve(String data) {
		Document dom = null;
		try {
			dom = DocumentHelper.parseText(data);
		} catch (DocumentException e) {
			logger.error("WXRechargeVo resolve is error. " + data);
			
			throw new BusinessException(ReturnCode.ERROR_01.getCode(), ReturnCode.ERROR_01.getMsg());
		}
		
		Element root = dom.getRootElement();
		
		super.resolve(root);
		if(!super.checkSuccess()) {
			return ;
		}
		
		
		this.tradeType = root.element("trade_type") == null ? null : root.element("trade_type").getText();
		this.prepayId = root.element("prepay_id") == null ? null : root.element("prepay_id").getText();
		this.codeUrl = root.element("code_url") == null ? null : root.element("code_url").getText();
//		this.paySign = root.element("sign") == null ? null : root.element("sign").getText();
	}
	
	public String getMercialNo() {
		return mercialNo;
	}
	
	public void setMercialNo(String mercialNo) {
		this.mercialNo = mercialNo;
	}
	
}
