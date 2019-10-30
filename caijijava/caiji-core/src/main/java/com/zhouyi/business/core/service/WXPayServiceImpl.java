package com.zhouyi.business.core.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.zhouyi.business.core.common.ReturnCode;
import com.zhouyi.business.core.exception.BusinessException;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.entity.StringEntity;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zhouyi.business.core.common.Config;
import com.zhouyi.business.core.common.WXConstant;
import com.zhouyi.business.core.dao.MemberInfoMapper;
import com.zhouyi.business.core.dao.PayOrderInfoMapper;
import com.zhouyi.business.core.model.CardOrderInfo;
import com.zhouyi.business.core.model.OrderInfo;
import com.zhouyi.business.core.model.PayOrderInfo;
import com.zhouyi.business.core.model.PayTypeEnum;
import com.zhouyi.business.core.model.TransactionInfo;
import com.zhouyi.business.core.utils.DateUtil;
import com.zhouyi.business.core.utils.HttpCall;
import com.zhouyi.business.core.utils.HttpUtil;
import com.zhouyi.business.core.utils.MD5Util;
import com.zhouyi.business.core.utils.WXUtil;
import com.zhouyi.business.core.vo.ResponseVo;
import com.zhouyi.business.core.vo.WXRechargeBackVo;
import com.zhouyi.business.core.vo.WXRechargeVo;

@Service
public class WXPayServiceImpl implements WXPayService {
	private static final Logger logger = LoggerFactory.getLogger(WXPayServiceImpl.class);
	
	private static final String REMIND_TO_USER="oZMcW0qJ_5qOu5pJgEcHiztrdMYM,oZMcW0uB1zNF4NvkfcHGKZXSdXsw,oZMcW0pGwGAGlYwGXOEkcFd3XH0c,oZMcW0mRxUKAMRt0uTEoDafjfGkg,oZMcW0g4mUYpMpeFE8mgQg5d9Hr8,oZMcW0uJ4vnMoLng76Gggvcq3psM";
	
	@Autowired
	private Config config;
	
	@Autowired
	private PayOrderInfoMapper payOrderInfoMapper;
	
	@Autowired
	private PersonAccService personAccService;
	
	@Autowired
	private MemberInfoMapper memberInfoMapper;
	
	@Autowired
	private CardService cardService;
	
	@Autowired
	private OrderService orderService;
	
	private String getTimeStamp() {
		return String.valueOf(System.currentTimeMillis() / 1000);
	}
	
	private String mapToXml(Map<String, String> params) {
		StringBuilder sb = new StringBuilder();
		sb.append("<xml>");
		
		for (Map.Entry<String, String> entry : params.entrySet()){
			if(StringUtils.isNotBlank(entry.getValue())) {
				sb.append("<" + entry.getKey() + ">" + entry.getValue() + "</" + entry.getKey() + ">");
			}
        }
		
		sb.append("</xml>");
		
		logger.info("mapToXml_params={}",sb.toString());
		
		return sb.toString();
	}
	
	private String generateSign(Map<String, String> params,int isApp) {
		String mercialSecret = config.getWxApiSecret();
		if(isApp==1){
			mercialSecret=config.getWxDevAppSecret();
		}
		List<String> plist = new ArrayList<String>();
		
		Map<String, String> sortMap = new TreeMap<String, String>(new MapKeyComparatorUtil());
		sortMap.putAll(params);
		
		for (Map.Entry<String, String> entry : sortMap.entrySet()){
			if(StringUtils.isNotBlank(entry.getValue())) {
				plist.add(entry.getKey() + "=" + entry.getValue());
			}
        }
		
		plist.add("key=" + mercialSecret);
		logger.info("generateSign_params={}",JSON.toJSONString(plist));
		try {
			return MD5Util.GetMD5Code(new String(StringUtils.join(plist, "&").getBytes("UTF-8"))).toUpperCase();
		} catch (Exception e) {
			logger.error("WXPayService.generateSign is error. {}", e);
			
			throw new BusinessException(ReturnCode.ERROR_01.getCode(), ReturnCode.ERROR_01.getMsg());
		}
	}
	
	private String generateSignStr(Map<String, String> params,int isApp) {
		String sign = generateSign(params,isApp);

		params.put("sign", sign);
		

		return mapToXml(params);
	}
	
	private boolean checkSign(String data) {
		Document dom = null;
		try {
			dom = DocumentHelper.parseText(data);
		} catch (DocumentException e) {
			logger.error("WXPayService.checkSign is error. " + data);
			
			throw new BusinessException(ReturnCode.ERROR_01.getCode(), ReturnCode.ERROR_01.getMsg());
		}
		
		Element root = dom.getRootElement();
		
		// 如果没有sign则不用验证签名
		if(root.element("sign") == null) {
			return true;
		}
		
		
		// 验证签名
		Map<String, String> params = new TreeMap<String, String>();
		Iterator ite = root.elements().iterator();
		Element element;
				
		while (ite.hasNext()) {
			element = (Element)ite.next();
			params.put(element.getName(), element.getText());
		}
		
//		logger.info("WXPayService.checkSign params info:" + JSON.toJSONString(params));
		
		String sign = params.get("sign");
		params.remove("sign");
		int isApp=0;
		if(params.get("trade_type").equals("APP")){
			isApp=1;
		}
		String _sign = generateSign(params,isApp);
		
		if(!sign.equals(_sign)) {
			return false;
		}
		
		return true;
	}
	
	private String sendHttpWXService(String url, Map<String, String> params,int isApp) {
		// 参数签名，并xml转Str
		String paramsStr = generateSignStr(params,isApp);
		
		ResponseVo respVo = null;
		try {
			respVo = HttpUtil.sendPost(url, new StringEntity(paramsStr,"UTF-8"));
		} catch (Exception e) {
			logger.error("sendHttpWXService_error:" + paramsStr, e);
			
			throw new BusinessException(ReturnCode.ERROR_01.getCode(), ReturnCode.ERROR_01.getMsg());
		}
		
		
		// 分析返回参数
		if(!respVo.isOk()) {
			logger.error("WXPayService.sendWXService.sendPost on response is error. " + paramsStr);
			
			throw new BusinessException(ReturnCode.ERROR_01.getCode(), ReturnCode.ERROR_01.getMsg());
		}
		
		//如果获取对账记录不延签
		boolean isSettle = url.equals(WXConstant.TO_DOWNLOAD_SETTLEFILE); 
		if(isSettle) {
			return respVo.getData();
		}
		
		if(!checkSign(respVo.getData())) {
			logger.error("WXPayService.sendWXService.checkSign is false. " + respVo.getData());
			
			throw new BusinessException(ReturnCode.ERROR_01.getCode(), ReturnCode.ERROR_01.getMsg());
		}
		
		
		return respVo.getData();
	}
	
	@Override
	public WXRechargeVo payOrder(TransactionInfo wxInfo) {
		// 参数组装，并排序
		Map<String, String> params = new TreeMap<String, String>(new MapKeyComparatorUtil());
		String mercialNo = config.getWxMercialNo();
		String appId = config.getWxAppId();
		params.put("appid", appId);					
		params.put("mch_id", mercialNo);
		params.put("nonce_str", RandomStringUtils.randomAlphanumeric(32));
		if(wxInfo.getBusinessType()!=null && wxInfo.getBusinessType()==PayTypeEnum.TYPE_106.getType()){
			params.put("notify_url", config.getCardNotifyUrl());
		}else if(wxInfo.getBusinessType()!=null && wxInfo.getBusinessType()==PayTypeEnum.TYPE_107.getType()){
			params.put("notify_url", config.getPersonNotifyUrl());
		}else{
			params.put("notify_url", config.getNotifyUrl());
		}
		params.put("trade_type", WXConstant.TRADE_TYPE_JSAPI);
		params.put("time_expire", DateUtil.getTimeExpireByMinute(30));
		
		params.put("body", wxInfo.getRemark());
		params.put("total_fee", wxInfo.getAmount().toString());
		params.put("spbill_create_ip", wxInfo.getUserIp());
		params.put("out_trade_no", wxInfo.getOrderNo());
		params.put("openid", wxInfo.getWxOpenid());
		//屏蔽信用卡套现
		params.put("limit_pay", "no_credit");
		
		logger.info("recharge_params={}",JSON.toJSONString(params));
		// 调用微信
		String data = sendHttpWXService(WXConstant.TO_RECHARGE_URL, params,0);
		logger.info("recharge_params_data={}",data);
		WXRechargeVo wxvo = new WXRechargeVo();
		
		wxvo.resolve(data);
		
		if(!wxvo.checkSuccess()) {
			return wxvo;
		}
		
		
		// 为前端准备参数
		Map<String, String> returnMap = new TreeMap<String, String>(new MapKeyComparatorUtil());
		
		returnMap.put("appId", appId);
		returnMap.put("timeStamp", getTimeStamp());
		returnMap.put("nonceStr", RandomStringUtils.randomAlphanumeric(32));
		returnMap.put("package", "prepay_id=" + wxvo.getPrepayId());
		returnMap.put("signType", "MD5");
		
		returnMap.put("paySign", generateSign(returnMap,0));
		
		wxvo.setAppId(returnMap.get("appId"));
		wxvo.setTimeStamp(returnMap.get("timeStamp"));
		wxvo.setNonceStr(returnMap.get("nonceStr"));
		wxvo.setSignType(returnMap.get("signType"));
		wxvo.setPaySign(returnMap.get("paySign"));
		wxvo.setPackageValue(returnMap.get("package"));
		wxvo.setOrderNo(wxInfo.getOrderNo());
		
		return wxvo;
	}

	
	/**
	 * 比较器
	 * @author Administrator
	 *
	 */
	class MapKeyComparatorUtil implements Comparator<String>{  
	    public int compare(String str1, String str2) {  
	        return str1.compareTo(str2);  
	    }  
	}


	@Override
	public WXRechargeVo toWithdrawals(TransactionInfo orderInfo) {
		// 参数组装，并排序
		Map<String, String> params = new TreeMap<String, String>(new MapKeyComparatorUtil());
		String appId = config.getWxAppId();
		String mercialNo = config.getWxMercialNo();
		params.put("mch_appid", appId);					
		params.put("mchid", mercialNo);
		params.put("nonce_str", RandomStringUtils.randomAlphanumeric(32));
		
		params.put("check_name", WXConstant.CHECK_NAME);
		
		params.put("partner_trade_no", orderInfo.getOrderNo());
		params.put("openid", orderInfo.getWxOpenid());
		params.put("amount", orderInfo.getAmount().toString());
		String desc="账户提现";
		params.put("desc", desc);
		params.put("spbill_create_ip", orderInfo.getUserIp());
		
		// 参数签名，并xml转Str
		String paramsStr = generateSignStr(params,0);
		ResponseVo respVo = null;
		try {
			logger.info("withdraw_params={}",paramsStr);
			respVo = HttpUtil.sendPostSSL(WXConstant.TO_WITHDRAW, new StringEntity(paramsStr,"UTF-8"), mercialNo);
			logger.info("withdraw_params_data={}",respVo);
		} catch (Exception e) {
			logger.error("toWithdrawals_error=", e);
			
			throw new BusinessException(ReturnCode.ERROR_01.getCode(), ReturnCode.ERROR_01.getMsg());
		}

		
		// 验证返回结果
		if(!checkSign(respVo.getData())) {
			logger.error("toWithdrawals_checkSign_err={}",respVo.getData());
			
			throw new BusinessException(ReturnCode.ERROR_01.getCode(), ReturnCode.ERROR_01.getMsg());
		}
		
		
		// 分析返回参数
		if(!respVo.isOk()) {
			logger.error("toWithdrawals_sendPost_error={}",paramsStr);
			
			throw new BusinessException(ReturnCode.ERROR_01.getCode(), ReturnCode.ERROR_01.getMsg());
		}
		logger.info("withdraw_params={}-{}",orderInfo.getWxOpenid(),JSON.toJSONString(respVo));
		WXRechargeVo wxvo = new WXRechargeVo();
		
		wxvo.resolve(respVo.getData());
		
		return wxvo;
	}

	@Override
	@Transactional
	public WXRechargeBackVo payNotice(String xml) {
		logger.info("payNotice_params={}",xml);
		WXRechargeBackVo wXRechargeBackVo=this.rechargeBack(xml);
		boolean checkResult=wXRechargeBackVo.checkSuccess();
		if(!checkResult){
			logger.info("payNotice_result={}",wXRechargeBackVo.returnWX());
			return wXRechargeBackVo;
		}
		try{
			String orderNo=wXRechargeBackVo.getOutTradeNo();

			PayOrderInfo payPersonOrder=payOrderInfoMapper.getPayOrderInfoByOrderNo(orderNo);
			if(payPersonOrder.getStatus()==0){
				payPersonOrder.setChannelSerialNum(wXRechargeBackVo.getTransactionId());
				payPersonOrder.setUpdateTime(new Date());
				payPersonOrder.setStatus(1);
				payOrderInfoMapper.updateByPrimaryKeySelective(payPersonOrder);
				
				if(payPersonOrder.getType()==PayTypeEnum.TYPE_102.getType()){
					memberInfoMapper.openMember(payPersonOrder.getUserId(), 1);
					wXRechargeBackVo.setUserId(payPersonOrder.getUserId());
				}
			}
		}catch(Exception e){
			logger.error("payNotice_err=",wXRechargeBackVo.getOutTradeNo(),e);
			throw new BusinessException(ReturnCode.ERROR_01.getCode(), "回调失败");
		}
		return wXRechargeBackVo;
	}
	
	private WXRechargeBackVo rechargeBack(String data) {
		Map<String, String> params = new TreeMap<String, String>();
		Document dom = null;
		try {
			dom = DocumentHelper.parseText(data);
		} catch (DocumentException e) {
			logger.error("WXSelectRechargeVo resolve is error. " + data);
			
			throw new BusinessException(ReturnCode.ERROR_01.getCode(), ReturnCode.ERROR_01.getMsg());
		}
		
		Element root = dom.getRootElement();
		Iterator ite = root.elements().iterator();
		Element element;
				
		while (ite.hasNext()) {
			element = (Element)ite.next();
			params.put(element.getName(), element.getText());
		}
		
		logger.info("WXPayService.rechargeBack params info:" + JSON.toJSONString(params));
		
		String sign = params.get("sign");
		params.remove("sign");
		int isApp=0;
		if(params.get("trade_type").equals("APP")){
			isApp=1;
		}
		String _sign = generateSign(params,isApp);
		
		WXRechargeBackVo wxvo = new WXRechargeBackVo();
		
		if(!sign.equals(_sign)) {
			return wxvo;
		}
		
		wxvo.resolve(data);
		
		wxvo.setReturnWXCode(WXRechargeBackVo.RETURN_WX_CODE_SUCCESS);
		wxvo.setReturnWXMsg(WXRechargeBackVo.RETURN_WX_MSG_SUCCESS);
		
		return wxvo;
	}

	/**
	 * 购买卡回调
	 */
	@Override
	public WXRechargeBackVo cardPayNotice(String xml) {
		logger.info("payNotice_params={}",xml);
		WXRechargeBackVo wXRechargeBackVo=this.rechargeBack(xml);
		boolean checkResult=wXRechargeBackVo.checkSuccess();
		if(!checkResult){
			logger.info("payNotice_result={}",wXRechargeBackVo.returnWX());
			return wXRechargeBackVo;
		}
		try{
			String orderNo=wXRechargeBackVo.getOutTradeNo();
			CardOrderInfo order=cardService.getCardOrderByNo(orderNo);
			if(order.getOrderStatus()==0){
				order.setOrderStatus(1);
				order.setChannelSerialNum(wXRechargeBackVo.getTransactionId());
				cardService.updateCardOrder(order);
			}
		}catch(Exception e){
			logger.error("payNotice_err=",wXRechargeBackVo.getOutTradeNo(),e);
			throw new BusinessException(ReturnCode.ERROR_01.getCode(), "回调失败");
		}
		return wXRechargeBackVo;
	}

	@Override
	public WXRechargeBackVo personPayNotice(String xml) {
		logger.info("personPayNotice_params={}",xml);
		WXRechargeBackVo wXRechargeBackVo=this.rechargeBack(xml);
		boolean checkResult=wXRechargeBackVo.checkSuccess();
		if(!checkResult){
			logger.info("personPayNotice_result={}",wXRechargeBackVo.returnWX());
			return wXRechargeBackVo;
		}
		try{
			String orderNo=wXRechargeBackVo.getOutTradeNo();
			OrderInfo order=orderService.getObjectByOrderNo(orderNo);
			if(order.getOrderStatus()==0){
				order.setChannelSerialNum(wXRechargeBackVo.getTransactionId());
				OrderInfo data=new OrderInfo();
				data.setId(order.getId());
				data.setOrderStatus(1);
				data.setChannelSerialNum(wXRechargeBackVo.getTransactionId());
				Integer count=orderService.updateOrderInfo(data);
				logger.info("personPayNotice_count={}",count);
				this.sendRemindMessage(order);
			}
		}catch(Exception e){
			logger.error("personPayNotice_err=",wXRechargeBackVo.getOutTradeNo(),e);
			throw new BusinessException(ReturnCode.ERROR_01.getCode(), "回调失败");
		}
		return wXRechargeBackVo;
	}

	@Override
	public WXRechargeVo payAppOrder(TransactionInfo wxInfo) {
		// 参数组装，并排序
		Map<String, String> params = new TreeMap<String, String>(new MapKeyComparatorUtil());
		String mercialNo = config.getWxDevMercialNo();
		String appId = config.getWxDevAppId();
		params.put("appid", appId);
		params.put("mch_id", mercialNo);
		params.put("nonce_str", RandomStringUtils.randomAlphanumeric(32));
		if(wxInfo.getBusinessType()!=null && wxInfo.getBusinessType()==PayTypeEnum.TYPE_106.getType()){
			params.put("notify_url", config.getCardNotifyUrl());
		}else if(wxInfo.getBusinessType()!=null && wxInfo.getBusinessType()==PayTypeEnum.TYPE_107.getType()){
			params.put("notify_url", config.getPersonNotifyUrl());
		}else{
			params.put("notify_url", config.getNotifyUrl());
		}
		params.put("trade_type", WXConstant.TRADE_TYPE_APPAPI);
		params.put("time_expire", DateUtil.getTimeExpireByMinute(30));
		
		params.put("body", wxInfo.getRemark());
		params.put("total_fee", wxInfo.getAmount().toString());
		params.put("spbill_create_ip", wxInfo.getUserIp());
		params.put("out_trade_no", wxInfo.getOrderNo());
//		params.put("openid", wxInfo.getWxOpenid());
		//屏蔽信用卡套现
		params.put("limit_pay", "no_credit");
		
		logger.info("recharge_params={}",JSON.toJSONString(params));
		// 调用微信
		String data = sendHttpWXService(WXConstant.TO_RECHARGE_URL, params,1);
		logger.info("recharge_params_data={}",data);
		WXRechargeVo wxvo = new WXRechargeVo();
		
		wxvo.resolve(data);
		
		if(!wxvo.checkSuccess()) {
			return wxvo;
		}
		
		
		// 为前端准备参数
		Map<String, String> returnMap = new TreeMap<String, String>(new MapKeyComparatorUtil());
		
		returnMap.put("appid", appId);
		returnMap.put("partnerid", mercialNo);
		returnMap.put("prepayid", wxvo.getPrepayId());
		returnMap.put("timestamp", getTimeStamp());
		returnMap.put("noncestr", RandomStringUtils.randomAlphanumeric(32));
		returnMap.put("package", "Sign=WXPay");
		returnMap.put("paySign", generateSign(returnMap,1));
		
		wxvo.setAppId(appId);
		wxvo.setTimeStamp(returnMap.get("timestamp"));
		wxvo.setNonceStr(returnMap.get("noncestr"));
		wxvo.setSignType("MD5");
		wxvo.setPaySign(returnMap.get("paySign"));
		wxvo.setPackageValue(returnMap.get("package"));
		wxvo.setOrderNo(wxInfo.getOrderNo());
		wxvo.setMercialNo(mercialNo);
		return wxvo;
	}

	@Async
	@Override
	public void sendRemindMessage(OrderInfo orderInfo) {
		// TODO Auto-generated method stub
		String[] toUsers=REMIND_TO_USER.split(",");
		for(String toUser:toUsers){
			sendUserRemindMessage(toUser,orderInfo.getOrderNo(),orderInfo.getCreateTime());
		}
	}
	
	private void sendUserRemindMessage(String toUser,String orderNo,Date orderTime){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Map<String,Object> data=new HashMap<String,Object>();
		Map<String,String> first=new HashMap<String,String>();
		first.put("value", "客户下单了");
		Map<String,String> keyword1=new HashMap<String,String>();
		keyword1.put("value", "人工起名服务");
		Map<String,String> keyword2=new HashMap<String,String>();
		keyword2.put("value", sdf.format(orderTime));
		Map<String,String> keyword3=new HashMap<String,String>();
		keyword3.put("value", orderNo);
		Map<String,String> remark=new HashMap<String,String>();
		remark.put("value", "请点击详情查看订单明细。");
		data.put("first", first);
		data.put("keyword1", keyword1);
		data.put("keyword2", keyword2);
		data.put("keyword3", keyword3);
		data.put("remark", remark);
		HttpCall httpCall = new HttpCall();
		String token=WXUtil.getAccessToken(config.getWxAppId(), config.getWxAppSecret());
        String tmpurl = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+ token;
        JSONObject json = new JSONObject();
        json.put("touser", toUser);
        json.put("template_id", WXPayService.TEMPLATE_001);
        json.put("url", "");
        json.put("data", data);
        try{
            String result = (String)httpCall.httpPostCall(tmpurl, json.toString());
            logger.info("sendUserRemindMessage_result={}",result);
            
         }catch(Exception e){
            e.printStackTrace();
        }
	}
}
