package com.zhouyi.business.core.common;;

public class WXConstant {

	// 公众号支付
	/** 公众号ID */
	//public static final String APP_ID = "wx88d9d563c795de7e";
	//public static final String APP_ID =CommonConstant.WECHAT_CIFMEDIA_APP_ID;
	/** 商户ID */
	//public static final String MCH_ID = "1380514502";
	//public static final String MCH_ID = CommonConstant.WECHAT_APP_COMMERCIAL_NO;
	
	/** 公众号SECRET */
	//public static final String SECRET = "8eb7b9c9428de8e7d4a9e2979ea44162";
	
	//public static final String SECRET =CommonConstant.WECHAT_APP_COMMERCIAL_SECRET;
//	// APP支付
//	/** 公众号ID */
//	public static final String APP_ID = "wxffb219bd115cee93";
//	/** 商户ID */
//	public static final String MCH_ID = "1366705302";
//	/** 公众号SECRET */
//	public static final String SECRET = "9b7112e30840d71ec621c9961ea8f12b";
	
	
	/** 校验用户姓名选项：不校验真实姓名 */
	public static final String CHECK_NAME = "NO_CHECK";
	
	/** 证书格式 */
	public static final String CERT_FORMAT = "PKCS12";
	/** 证书格式 */
//	public static final String CERT_ADDR = "/com/cifmedia/framework/pay/core/ssl/apiclient_cert.p12";
	public static final String CERT_ADDR = "/data/programs/jar/8060/zhouyi-wx/apiclient_cert.p12";
	public static final String APP_CERT_ADDR = "/data/programs/jar/8060/zhouyi-wx/appCert/apiclient_cert.p12";
	
	/** 交易类型：公众号支付 */
	public static final String TRADE_TYPE_JSAPI = "JSAPI";
	public static final String TRADE_TYPE_APPAPI = "APP";
	public static final String TRADE_TYPE_NATIVE = "NATIVE";
	/** 通知地址，支付回调 */
	//public static final String NOTIFY_URL = "https://www.zhouyilive.com/apitest/order/payNotice";
	//public static final String CARD_NOTIFY_URL = "https://www.zhouyilive.com/apitest/order/cardPayNotice";
	//public static final String PERSON_NOTIFY_URL = "https://www.zhouyilive.com/apitest/order/personPayNotice";
	
	
	
	/** 微信支付地址（充值） */
	public static final String TO_RECHARGE_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
	/** 微信查询支付地址（充值查询） */
	public static final String TO_SELECT_RECHARGE_URL = "https://api.mch.weixin.qq.com/pay/orderquery";
	/** 微信提现 */
	public static final String TO_WITHDRAW = "https://api.mch.weixin.qq.com/mmpaymkttransfers/promotion/transfers";
	/** 微信提现查询 */
	public static final String TO_SELECT_WITHDRAW = "https://api.mch.weixin.qq.com/mmpaymkttransfers/gettransferinfo";
	/** 微信下载对账文件 */
	public static final String TO_DOWNLOAD_SETTLEFILE = "https://api.mch.weixin.qq.com/pay/downloadbill";
	
	/** 微信提现 */
	public static final String SEND_REDPACKET = "https://api.mch.weixin.qq.com/mmpaymkttransfers/sendredpack";
	
}
