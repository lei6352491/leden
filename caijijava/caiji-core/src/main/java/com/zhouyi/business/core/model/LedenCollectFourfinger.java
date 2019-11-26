package com.zhouyi.business.core.model;

import java.io.Serializable;
import java.util.Date;

public class LedenCollectFourfinger implements Serializable {
    private String pkId;

    private String ryjcxxcjbh;

    private String slzZwzwdm;

    private String slzZzhwqsqkdm;

    private String slzTxysffms;

    private String slzTxzl;

    private String deletag;

    private String annex;

    private String createUserId;

    private Date createDatetime;

    private String updateUserId;

    private Date updateDatetime;

    private byte[] slzTxsj;

    private String czwzp;

    private String zwwz;

    private String zwqsqk;

    private static final long serialVersionUID = 1L;

    public String getCzwzp() {
        return czwzp;
    }

    public void setCzwzp(String czwzp) {
        this.czwzp = czwzp;
    }

    public String getZwwz() {
        return zwwz;
    }

    public void setZwwz(String zwwz) {
        this.zwwz = zwwz;
    }

    public String getZwqsqk() {
        return zwqsqk;
    }

    public void setZwqsqk(String zwqsqk) {
        this.zwqsqk = zwqsqk;
    }

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

    public String getSlzZwzwdm() {
        return slzZwzwdm;
    }

    public void setSlzZwzwdm(String slzZwzwdm) {
        this.slzZwzwdm = slzZwzwdm;
    }

    public String getSlzZzhwqsqkdm() {
        return slzZzhwqsqkdm;
    }

    public void setSlzZzhwqsqkdm(String slzZzhwqsqkdm) {
        this.slzZzhwqsqkdm = slzZzhwqsqkdm;
    }

    public String getslzTxysffms() {
        return slzTxysffms;
    }

    public void setslzTxysffms(String slzTxysffms) {
        this.slzTxysffms = slzTxysffms;
    }

    public String getSlzTxzl() {
        return slzTxzl;
    }

    public void setSlzTxzl(String slzTxzl) {
        this.slzTxzl = slzTxzl;
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

    public byte[] getSlzTxsj() {
        return slzTxsj;
    }

    public void setSlzTxsj(byte[] slzTxsj) {
        this.slzTxsj = slzTxsj;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", pkId=").append(pkId);
        sb.append(", ryjcxxcjbh=").append(ryjcxxcjbh);
        sb.append(", slzZwzwdm=").append(slzZwzwdm);
        sb.append(", slzZzhwqsqkdm=").append(slzZzhwqsqkdm);
        sb.append(", slzTxysffms=").append(slzTxysffms);
        sb.append(", slzTxzl=").append(slzTxzl);
        sb.append(", deletag=").append(deletag);
        sb.append(", annex=").append(annex);
        sb.append(", createUserId=").append(createUserId);
        sb.append(", createDatetime=").append(createDatetime);
        sb.append(", updateUserId=").append(updateUserId);
        sb.append(", updateDatetime=").append(updateDatetime);
        sb.append(", slzTxsj=").append(slzTxsj);
        sb.append("]");
        return sb.toString();
    }
}