package com.zhouyi.business.core.model;

import java.io.Serializable;
import java.util.Date;

public class LedenCollectPalm implements Serializable {
    private String pkId;

    private String ryjcxxcjbh;

    private String zhwzhwdm;

    private String zhwZzhwqsqkdm;

    private String zhwTxysfsms;

    private String zhwTxzl;

    private String deletag;

    private String annex;

    private String createUserId;

    private Date createDatetime;

    private String updateUserId;

    private Date updateDatetime;

    private byte[] zhwTxsj;

    private String zzwzp;

    private String zwwz;

    private String zwqsqk;

    private static final long serialVersionUID = 1L;

    public String getZzwzp() {
        return zzwzp;
    }

    public void setZzwzp(String zzwzp) {
        this.zzwzp = zzwzp;
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

    public String getZhwzhwdm() {
        return zhwzhwdm;
    }

    public void setZhwzhwdm(String zhwzhwdm) {
        this.zhwzhwdm = zhwzhwdm;
    }

    public String getZhwZzhwqsqkdm() {
        return zhwZzhwqsqkdm;
    }

    public void setZhwZzhwqsqkdm(String zhwZzhwqsqkdm) {
        this.zhwZzhwqsqkdm = zhwZzhwqsqkdm;
    }

    public String getZhwTxysfsms() {
        return zhwTxysfsms;
    }

    public void setZhwTxysfsms(String zhwTxysfsms) {
        this.zhwTxysfsms = zhwTxysfsms;
    }

    public String getZhwTxzl() {
        return zhwTxzl;
    }

    public void setZhwTxzl(String zhwTxzl) {
        this.zhwTxzl = zhwTxzl;
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

    public byte[] getZhwTxsj() {
        return zhwTxsj;
    }

    public void setZhwTxsj(byte[] zhwTxsj) {
        this.zhwTxsj = zhwTxsj;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", pkId=").append(pkId);
        sb.append(", ryjcxxcjbh=").append(ryjcxxcjbh);
        sb.append(", zhwzhwdm=").append(zhwzhwdm);
        sb.append(", zhwZzhwqsqkdm=").append(zhwZzhwqsqkdm);
        sb.append(", zhwTxysfsms=").append(zhwTxysfsms);
        sb.append(", zhwTxzl=").append(zhwTxzl);
        sb.append(", deletag=").append(deletag);
        sb.append(", annex=").append(annex);
        sb.append(", createUserId=").append(createUserId);
        sb.append(", createDatetime=").append(createDatetime);
        sb.append(", updateUserId=").append(updateUserId);
        sb.append(", updateDatetime=").append(updateDatetime);
        sb.append(", zhwTxsj=").append(zhwTxsj);
        sb.append("]");
        return sb.toString();
    }
}