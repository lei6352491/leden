package com.zhouyi.business.model;

import java.io.Serializable;
import java.util.List;

public class TextMessage implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String ToUserName;

    private String FromUserName;

    private Long CreateTime;

    private String MsgType;

    private String Content;

    private String MsgId;
    
    private List<PictInfo> Articles;
    private Integer ArticleCount;
    
    private ImageMessage Image;
    
	public String getToUserName() {
		return ToUserName;
	}

	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}

	public String getFromUserName() {
		return FromUserName;
	}

	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}

	public Long getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(Long createTime) {
		CreateTime = createTime;
	}

	public String getMsgType() {
		return MsgType;
	}

	public void setMsgType(String msgType) {
		MsgType = msgType;
	}

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

	public String getMsgId() {
		return MsgId;
	}

	public void setMsgId(String msgId) {
		MsgId = msgId;
	}

	public List<PictInfo> getArticles() {
		return Articles;
	}

	public void setArticles(List<PictInfo> articles) {
		Articles = articles;
	}

	public Integer getArticleCount() {
		return ArticleCount;
	}

	public void setArticleCount(Integer articleCount) {
		ArticleCount = articleCount;
	}

	public ImageMessage getImage() {
		return Image;
	}

	public void setImage(ImageMessage image) {
		Image = image;
	}

	
}
