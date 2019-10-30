package com.zhouyi.business.core.common;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component  
@ConfigurationProperties(prefix="myConfig")
public class Config {
	
	private String downloadPath;
	private String uploadPath;
	private String aesPassword;
	private String website;
	private String wxAppId;
	private String wxAppSecret;
	private String wxMercialNo;
	private String templateCode;
	private String smsSignName;
	private String wxApiSecret;
	private String mockApiKey;
	private String mockApiSecret;
	private String wxDevAppId;
	private String wxDevAppSecret;
	private String wxDevMercialNo;
	
	private String notifyUrl;
	private String cardNotifyUrl;
	private String personNotifyUrl;
	
	
	
	public String getNotifyUrl() {
		return notifyUrl;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}

	public String getCardNotifyUrl() {
		return cardNotifyUrl;
	}

	public void setCardNotifyUrl(String cardNotifyUrl) {
		this.cardNotifyUrl = cardNotifyUrl;
	}

	public String getPersonNotifyUrl() {
		return personNotifyUrl;
	}

	public void setPersonNotifyUrl(String personNotifyUrl) {
		this.personNotifyUrl = personNotifyUrl;
	}

	public String getDownloadPath() {
		return downloadPath;
	}

	public void setDownloadPath(String downloadPath) {
		this.downloadPath = downloadPath;
	}

	public String getUploadPath() {
		return uploadPath;
	}

	public void setUploadPath(String uploadPath) {
		this.uploadPath = uploadPath;
	}

	public String getAesPassword() {
		return aesPassword;
	}

	public void setAesPassword(String aesPassword) {
		this.aesPassword = aesPassword;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getTemplateCode() {
		return templateCode;
	}

	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}

	public String getSmsSignName() {
		return smsSignName;
	}

	public void setSmsSignName(String smsSignName) {
		this.smsSignName = smsSignName;
	}

	public String getWxAppId() {
		return wxAppId;
	}

	public void setWxAppId(String wxAppId) {
		this.wxAppId = wxAppId;
	}

	public String getWxAppSecret() {
		return wxAppSecret;
	}

	public void setWxAppSecret(String wxAppSecret) {
		this.wxAppSecret = wxAppSecret;
	}

	public String getWxMercialNo() {
		return wxMercialNo;
	}

	public void setWxMercialNo(String wxMercialNo) {
		this.wxMercialNo = wxMercialNo;
	}

	public String getWxApiSecret() {
		return wxApiSecret;
	}

	public void setWxApiSecret(String wxApiSecret) {
		this.wxApiSecret = wxApiSecret;
	}

	public String getMockApiKey() {
		return mockApiKey;
	}

	public void setMockApiKey(String mockApiKey) {
		this.mockApiKey = mockApiKey;
	}

	public String getMockApiSecret() {
		return mockApiSecret;
	}

	public void setMockApiSecret(String mockApiSecret) {
		this.mockApiSecret = mockApiSecret;
	}

	public String getWxDevAppId() {
		return wxDevAppId;
	}

	public void setWxDevAppId(String wxDevAppId) {
		this.wxDevAppId = wxDevAppId;
	}

	public String getWxDevAppSecret() {
		return wxDevAppSecret;
	}

	public void setWxDevAppSecret(String wxDevAppSecret) {
		this.wxDevAppSecret = wxDevAppSecret;
	}

	public String getWxDevMercialNo() {
		return wxDevMercialNo;
	}

	public void setWxDevMercialNo(String wxDevMercialNo) {
		this.wxDevMercialNo = wxDevMercialNo;
	}

}
