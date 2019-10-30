package com.zhouyi.business.core.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class LedenShareEmpowers implements Serializable {
    private String appId = "";

    private String pkId = "";

    private String shareType = "";

    private String nodeSign = "";

    private String deletag = "";

    private String annex = "";

    private String createUserId = "";

    private Date createDatetime;

    private String updateUserId = "";

    private Date updateDatetime;

    private boolean deletag2;

    private boolean recive = false; //接收

    private boolean issue = false;  //分发

    private boolean multiple = false; //综合


    public boolean isRecive() {
        return recive;
    }

    public void setRecive(boolean recive) {
        this.recive = recive;
    }

    public boolean isIssue() {
        return issue;
    }

    public void setIssue(boolean issue) {
        this.issue = issue;
    }

    public boolean isMultiple() {
        return multiple;
    }

    public void setMultiple(boolean multiple) {
        this.multiple = multiple;
    }

    public boolean isDeletag2() {
        return deletag2;
    }

    public void setDeletag2(boolean deletag2) {
        if (deletag2 == false)
            setDeletag("1");
        else if (deletag2 == true)
            setDeletag("0");
        this.deletag2 = deletag2;
    }

    private static final long serialVersionUID = 1L;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getPkId() {
        return pkId;
    }

    public void setPkId(String pkId) {
        this.pkId = pkId;
    }

    public String getShareType() {
        return shareType;
    }

    public void setShareType(String shareType) {
        this.shareType = shareType;
    }

    public String getNodeSign() {
        return nodeSign;
    }

    public void setNodeSign(String nodeSign) {
        this.nodeSign = nodeSign;
    }

    public String getDeletag() {
        return deletag;
    }

    public void setDeletag(String deletag) {
        this.deletag = deletag;
    }

    public String getAnnex() {
        return annex;
    }

    public void setAnnex(String annex) {
        this.annex = annex;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }


    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }

    public Date getUpdateDatetime() {
        return updateDatetime;
    }

    public void setCreateDatetime() {
        this.createDatetime = new Date();
    }

    public void setUpdateDatetime() {
        this.updateDatetime = new Date();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", appId=").append(appId);
        sb.append(", pkId=").append(pkId);
        sb.append(", shareType=").append(shareType);
        sb.append(", nodeSign=").append(nodeSign);
        sb.append(", deletag=").append(deletag);
        sb.append(", annex=").append(annex);
        sb.append(", createUserId=").append(createUserId);
        sb.append(", createDatetime=").append(createDatetime);
        sb.append(", updateUserId=").append(updateUserId);
        sb.append(", updateDatetime=").append(updateDatetime);
        sb.append("]");
        return sb.toString();
    }
}