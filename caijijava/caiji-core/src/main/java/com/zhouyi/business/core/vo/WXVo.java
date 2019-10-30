package com.zhouyi.business.core.vo;

import com.zhouyi.business.core.common.ReturnCode;
import com.zhouyi.business.core.exception.BusinessException;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class WXVo {
	
	private static final Logger logger = LoggerFactory.getLogger(WXVo.class);
	
	/** 返回状态码：SUCCESS */
	public static final String RETURN_CODE_SUCCESS = "SUCCESS";
	/** 返回状态码：FAIL */
	public static final String RETURN_CODE_FAIL = "FAIL";
	/** 业务结果：SUCCESS */
	public static final String RESULT_CODE_SUCCESS = "SUCCESS";
	/** 业务结果：FAIL */
	public static final String RESULT_CODE_FAIL = "FAIL";
	
	/** 返回微信操作码：SUCCESS */
	public static final String RETURN_WX_CODE_SUCCESS = "SUCCESS";
	/** 返回微信操作码：FAIL */
	public static final String RETURN_WX_CODE_FAIL = "FAIL";
	
	
	/** 返回微信结果：成功 */
	public static final String RETURN_WX_MSG_SUCCESS = "OK";
	/** 返回微信结果：成功 */
	public static final String RETURN_WX_MSG_FAIL = "操作失败";
	

	/** 返回状态码 */
	private String returnCode;
	/** 返回信息 */
	private String returnMsg;	
	

	// returnCode == SUCCESS
	/** 业务结果 */
	private String resultCode;
	/** 业务结果描述 */
	private String resultMsg;
	/** 错误代码 */
	private String errCode;
	/** 错误代码描述 */
	private String errCodeDes;
	
	
	// 返回微信结果
	/** 错误码 */
	private String returnWXCode;
	/** 错误结果 */
	private String returnWXMsg;
	
	
	public String getReturnCode() {
		return returnCode;
	}
	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}
	public String getReturnMsg() {
		return returnMsg;
	}
	public void setReturnMsg(String returnMsg) {
		this.returnMsg = returnMsg;
	}
	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	public String getResultMsg() {
		return resultMsg;
	}
	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}
	public String getErrCode() {
		return errCode;
	}
	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}
	public String getErrCodeDes() {
		return errCodeDes;
	}
	public void setErrCodeDes(String errCodeDes) {
		this.errCodeDes = errCodeDes;
	}
	public String getReturnWXCode() {
		return returnWXCode;
	}
	public void setReturnWXCode(String returnWXCode) {
		this.returnWXCode = returnWXCode;
	}
	public String getReturnWXMsg() {
		return returnWXMsg;
	}
	public void setReturnWXMsg(String returnWXMsg) {
		this.returnWXMsg = returnWXMsg;
	}
	/**
	 * xml解析
	 * @param data
	 */
	public void resolve(String data) {
		Document dom = null;
		try {
			dom = DocumentHelper.parseText(data);
		} catch (DocumentException e) {
			logger.error("WXVo resolve is error. " + data);
			
			throw new BusinessException(ReturnCode.ERROR_01.getCode(), ReturnCode.ERROR_01.getMsg());
		}
		
		Element root = dom.getRootElement();
		
		resolve(root);
	}
	
	/**
	 * xml.dom解析
	 * @param root
	 */
	public void resolve(Element root) {
		this.returnCode = root.element("return_code") == null ? null : root.element("return_code").getText();
		this.returnMsg = root.element("return_msg") == null ? null : root.element("return_msg").getText();
		
		if(StringUtils.isBlank(returnCode) || !returnCode.equals(RETURN_CODE_SUCCESS)) {
			return;
		}
		
		this.resultCode = root.element("result_code") == null ? null : root.element("result_code").getText();
		this.resultMsg = root.element("result_msg") == null ? null : root.element("result_msg").getText();
		this.errCode = root.element("err_code") == null ? null : root.element("err_code").getText();
		this.errCodeDes = root.element("err_code_des") == null ? null : root.element("err_code_des").getText();
	}
	
	/**
	 * 检查微信处理是否成功
	 * @return
	 */
	public boolean checkSuccess() {
		if(StringUtils.isBlank(this.returnCode) || StringUtils.isBlank(this.resultCode)) {
			return false;
		}
		
		if(!this.returnCode.equals(RETURN_CODE_SUCCESS) || !this.resultCode.equals(RESULT_CODE_SUCCESS)) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * 给予微信请求进行响应（支付回调）
	 * @return
	 */
	public String returnWX() {
		if(StringUtils.isBlank(returnWXCode)) {
			this.returnWXCode = RETURN_WX_CODE_FAIL;
			this.returnWXMsg = RETURN_WX_MSG_FAIL;
		}
		
		StringBuffer sb = new StringBuffer();
		
		sb.append("<xml>");
		sb.append("<return_code><![CDATA[" + this.returnWXCode + "]]></return_code>");
		sb.append("<return_msg><![CDATA[" + this.returnWXMsg + "]]></return_msg>");
		sb.append("</xml>");
		
		return sb.toString();
	}
	
	/**
	 * 微信返回结果错误描述
	 * @return
	 */
	public String errorMsg() {
		if(StringUtils.isBlank(returnCode)) {
			return RETURN_CODE_FAIL;
		}
		if(!returnCode.equals(RETURN_CODE_SUCCESS)) {
			return StringUtils.isBlank(returnMsg) ? returnCode : returnMsg;
		}
		if(StringUtils.isBlank(resultCode)) {
			return RETURN_CODE_FAIL;
		}
		if(!resultCode.equals(RESULT_CODE_SUCCESS)) {
			return StringUtils.isBlank(resultMsg) ? resultCode : resultMsg;
		}
		
		return RESULT_CODE_SUCCESS;
	}
	
	public static void main(String[] args) {
		WXVo wx = new WXVo();
//		wx.setReturnCode(RETURN_CODE_SUCCESS);
//		wx.setReturnMsg("ffff");
//		wx.setResultCode(RESULT_CODE_SUCCESS);
//		wx.setResultMsg("ddd");
//		System.out.println(wx.errorMsg());
		
		wx.setReturnWXCode(RETURN_WX_CODE_SUCCESS);
		wx.setReturnWXMsg(RETURN_WX_MSG_SUCCESS);
		System.out.println(wx.returnWX());
	}
	
}