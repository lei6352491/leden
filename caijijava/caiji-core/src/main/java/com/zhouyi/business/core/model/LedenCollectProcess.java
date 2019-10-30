package com.zhouyi.business.core.model;

import java.io.Serializable;
import java.util.Date;

public class LedenCollectProcess implements Serializable {
    private String pkId;

    private String ryjcxxcjbh;

    private Short collectStatus;

    private Date collectDate;

    private Short ord;

    private String collectNodeId;

    private Short isSkip;

    private String skipReason;

    private String skipUserId;

    private Date skipDatetimetime;

    private String createUserId;

    private Date createDatetime;

    private String updateUserId;

    private Date updateDatetime;

    private static final long serialVersionUID = 1L;

    public String getPkId() {
        return pkId;
    }

    public void setPkId(String pkId) {
        this.pkId = pkId;
    }

    public String getRyjcxxcjbh() {
        return ryjcxxcjbh;
    }

    public void setRyjcxxcjbh(String ryjcxxcjbh) {
        this.ryjcxxcjbh = ryjcxxcjbh;
    }

    public Short getCollectStatus() {
        return collectStatus;
    }

    public void setCollectStatus(Short collectStatus) {
        this.collectStatus = collectStatus;
    }

    public Date getCollectDate() {
        return collectDate;
    }

    public void setCollectDate(Date collectDate) {
        this.collectDate = collectDate;
    }

    public Short getOrd() {
        return ord;
    }

    public void setOrd(Short ord) {
        this.ord = ord;
    }

    public String getCollectNodeId() {
        return collectNodeId;
    }

    public void setCollectNodeId(String collectNodeId) {
        this.collectNodeId = collectNodeId;
    }

    public Short getIsSkip() {
        return isSkip;
    }

    public void setIsSkip(Short isSkip) {
        this.isSkip = isSkip;
    }

    public String getSkipReason() {
        return skipReason;
    }

    public void setSkipReason(String skipReason) {
        this.skipReason = skipReason;
    }

    public String getSkipUserId() {
        return skipUserId;
    }

    public void setSkipUserId(String skipUserId) {
        this.skipUserId = skipUserId;
    }

    public Date getSkipDatetimetime() {
        return skipDatetimetime;
    }

    public void setSkipDatetimetime(Date skipDatetimetime) {
        this.skipDatetimetime = skipDatetimetime;
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

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
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

    public void setUpdateDatetime(Date updateDatetime) {
        this.updateDatetime = updateDatetime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", pkId=").append(pkId);
        sb.append(", ryjcxxcjbh=").append(ryjcxxcjbh);
        sb.append(", collectStatus=").append(collectStatus);
        sb.append(", collectDate=").append(collectDate);
        sb.append(", ord=").append(ord);
        sb.append(", collectNodeId=").append(collectNodeId);
        sb.append(", isSkip=").append(isSkip);
        sb.append(", skipReason=").append(skipReason);
        sb.append(", skipUserId=").append(skipUserId);
        sb.append(", skipDatetimetime=").append(skipDatetimetime);
        sb.append(", createUserId=").append(createUserId);
        sb.append(", createDatetime=").append(createDatetime);
        sb.append(", updateUserId=").append(updateUserId);
        sb.append(", updateDatetime=").append(updateDatetime);
        sb.append("]");
        return sb.toString();
    }
}