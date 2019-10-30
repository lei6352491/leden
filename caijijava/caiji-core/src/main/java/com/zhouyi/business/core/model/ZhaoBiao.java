package com.zhouyi.business.core.model;


/**
 * @author 李秸康
 * @ClassNmae: ZhaoBiao
 * @Description: TODO 招标实体
 * @date 2019/7/15 11:36
 * @Version 1.0
 **/
public class ZhaoBiao {
    private String title;
    private String region;
    private String dateZ;
    private String text;
    private String keyword;
    private String url;
    private String downUrl;
    private String ggType;
    private String cmd;
    

    public String getCmd() {
		return cmd;
	}

	public void setCmd(String cmd) {
		this.cmd = cmd;
	}

	public String getDownUrl() {
        return downUrl;
    }

    public void setDownUrl(String downUrl) {
        this.downUrl = downUrl;
    }

    public String getGgType() {
        return ggType;
    }

    public void setGgType(String ggType) {
        this.ggType = ggType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getDateZ() {
        return dateZ;
    }

    public void setDateZ(String dateZ) {
        this.dateZ = dateZ;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
