package com.zhouyi.business.core.model;

import java.io.Serializable;
import java.util.Date;

public class LedenCollectPhalange implements Serializable {
    private String pkId;

    private String ryjcxxcjbh;

    private String zjwZwzwdm;

    private String zjwZzhwqsqkdm;

    private String zjwTxysfsms;

    private String zjwTxzl;

    private String deletag;

    private String annex;

    private String createUserId;

    private Date createDatetime;

    private String updateUserId;

    private Date updateDatetime;

    private byte[] zjwTxsj;

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

    public String getZjwZwzwdm() {
        return zjwZwzwdm;
    }

    public void setZjwZwzwdm(String zjwZwzwdm) {
        this.zjwZwzwdm = zjwZwzwdm;
    }

    public String getZjwZzhwqsqkdm() {
        return zjwZzhwqsqkdm;
    }

    public void setZjwZzhwqsqkdm(String zjwZzhwqsqkdm) {
        this.zjwZzhwqsqkdm = zjwZzhwqsqkdm;
    }

    public String getZjwTxysfsms() {
        return zjwTxysfsms;
    }

    public void setZjwTxysfsms(String zjwTxysfsms) {
        this.zjwTxysfsms = zjwTxysfsms;
    }

    public String getZjwTxzl() {
        return zjwTxzl;
    }

    public void setZjwTxzl(String zjwTxzl) {
        this.zjwTxzl = zjwTxzl;
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

    public byte[] getZjwTxsj() {
        return zjwTxsj;
    }

    public void setZjwTxsj(byte[] zjwTxsj) {
        this.zjwTxsj = zjwTxsj;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", pkId=").append(pkId);
        sb.append(", ryjcxxcjbh=").append(ryjcxxcjbh);
        sb.append(", zjwZwzwdm=").append(zjwZwzwdm);
        sb.append(", zjwZzhwqsqkdm=").append(zjwZzhwqsqkdm);
        sb.append(", zjwTxysfsms=").append(zjwTxysfsms);
        sb.append(", zjwTxzl=").append(zjwTxzl);
        sb.append(", deletag=").append(deletag);
        sb.append(", annex=").append(annex);
        sb.append(", createUserId=").append(createUserId);
        sb.append(", createDatetime=").append(createDatetime);
        sb.append(", updateUserId=").append(updateUserId);
        sb.append(", updateDatetime=").append(updateDatetime);
        sb.append(", zjwTxsj=").append(zjwTxsj);
        sb.append("]");
        return sb.toString();
    }
}