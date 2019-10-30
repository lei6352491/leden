package com.zhouyi.business.core.vo;

import com.zhouyi.business.core.common.ReturnCode;
import com.zhouyi.business.core.exception.BusinessException;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WXRechargeBackVo extends WXVo {
	
	private static final Logger logger = LoggerFactory.getLogger(WXRechargeBackVo.class);
	
	/** 设备号 */
	private String deviceInfo;
	/** 用户标识 */
	private String openid;
	/** 是否关注公众账号 */
	private String isSubscribe;
	/** 交易类型 */
	private String tradeType;
	/** 付款银行 */
	private String bankType;
	/** 订单金额，单位：分 */
	private int totalFee;
	/** 应结订单金额:应结订单金额=订单金额-非充值代金券金额，应结订单金额<=订单金额。 */
	private int settlementTotalFee;
	/** 货币种类 */
	private String feeType;
	/** 现金支付金额 */
	private int cashFee;
	/** 现金支付货币类型 */
	private String cashFeeType;
	/** 代金券金额 */
	private int couponFee;
	/** 代金券使用数量 */
	private int couponCount;
	/** 代金券类型 */
	private String couponType$n;
	/** 代金券ID */
	private String couponId$n;
	/** 单个代金券支付金额 */
	private String couponFee$n;
	/** 微信支付订单号 */
	private String transactionId;
	/** 商户订单号 */
	private String outTradeNo;
	/** 附加数据 */
	private String attach;
	/** 支付完成时间 */
	private String timeEnd;
	
	private Long userId;
	
	public String getDeviceInfo() {
		return deviceInfo;
	}

	public void setDeviceInfo(String deviceInfo) {
		this.deviceInfo = deviceInfo;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getIsSubscribe() {
		return isSubscribe;
	}

	public void setIsSubscribe(String isSubscribe) {
		this.isSubscribe = isSubscribe;
	}

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public String getBankType() {
		return bankType;
	}

	public void setBankType(String bankType) {
		this.bankType = bankType;
	}

	public int getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(int totalFee) {
		this.totalFee = totalFee;
	}

	public int getSettlementTotalFee() {
		return settlementTotalFee;
	}

	public void setSettlementTotalFee(int settlementTotalFee) {
		this.settlementTotalFee = settlementTotalFee;
	}

	public String getFeeType() {
		return feeType;
	}

	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}

	public int getCashFee() {
		return cashFee;
	}

	public void setCashFee(int cashFee) {
		this.cashFee = cashFee;
	}

	public String getCashFeeType() {
		return cashFeeType;
	}

	public void setCashFeeType(String cashFeeType) {
		this.cashFeeType = cashFeeType;
	}

	public int getCouponFee() {
		return couponFee;
	}

	public void setCouponFee(int couponFee) {
		this.couponFee = couponFee;
	}

	public int getCouponCount() {
		return couponCount;
	}

	public void setCouponCount(int couponCount) {
		this.couponCount = couponCount;
	}

	public String getCouponType$n() {
		return couponType$n;
	}

	public void setCouponType$n(String couponType$n) {
		this.couponType$n = couponType$n;
	}

	public String getCouponId$n() {
		return couponId$n;
	}

	public void setCouponId$n(String couponId$n) {
		this.couponId$n = couponId$n;
	}

	public String getCouponFee$n() {
		return couponFee$n;
	}

	public void setCouponFee$n(String couponFee$n) {
		this.couponFee$n = couponFee$n;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}

	public String getTimeEnd() {
		return timeEnd;
	}

	public void setTimeEnd(String timeEnd) {
		this.timeEnd = timeEnd;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public void resolve(String data) {
		Document dom = null;
		try {
			dom = DocumentHelper.parseText(data);
		} catch (DocumentException e) {
			logger.error("WXRechargeBackVo resolve is error. " + data);
			
			throw new BusinessException(ReturnCode.ERROR_01.getCode(), ReturnCode.ERROR_01.getMsg());
		}
		
		Element root = dom.getRootElement();
		
		super.resolve(root);
		if(!super.checkSuccess()) {
			return ;
		}
		
		
		this.deviceInfo = root.element("device_info") == null ? null : root.element("device_info").getText();
		this.openid = root.element("openid") == null ? null : root.element("openid").getText();
		this.isSubscribe = root.element("is_subscribe") == null ? null : root.element("is_subscribe").getText();
		this.tradeType = root.element("trade_type") == null ? null : root.element("trade_type").getText();
		this.bankType = root.element("bank_type") == null ? null : root.element("bank_type").getText();
		this.totalFee = root.element("total_fee") == null ? 0 : Integer.parseInt(root.element("total_fee").getText());
		this.settlementTotalFee = root.element("settlement_total_fee") == null ? 0 : Integer.parseInt(root.element("settlement_total_fee").getText());
		this.feeType = root.element("fee_type") == null ? null : root.element("fee_type").getText();
		this.cashFee = root.element("cash_fee") == null ? 0 : Integer.parseInt(root.element("cash_fee").getText());
		this.cashFeeType = root.element("cash_fee_type") == null ? null : root.element("cash_fee_type").getText();
		this.couponFee = root.element("coupon_fee") == null ? 0 : Integer.parseInt(root.element("coupon_fee").getText());
		this.couponCount = root.element("coupon_count") == null ? 0 : Integer.parseInt(root.element("coupon_count").getText());
		this.transactionId = root.element("transaction_id") == null ? null : root.element("transaction_id").getText();
		this.outTradeNo = root.element("out_trade_no") == null ? null : root.element("out_trade_no").getText();
		this.attach = root.element("attach") == null ? null : root.element("attach").getText();
		this.timeEnd = root.element("time_end") == null ? null : root.element("time_end").getText();
	}
}
