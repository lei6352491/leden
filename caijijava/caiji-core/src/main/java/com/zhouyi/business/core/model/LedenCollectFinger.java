package com.zhouyi.business.core.model;

import java.io.Serializable;
import java.util.Date;

public class LedenCollectFinger implements Serializable {
    private String pkId;

    private String ryjcxxcjbh;

    private String zwzwdm;

    private String zzhwqsqkdm;

    private String zwTxysffms;

    private String zwTxzl;

    private String deletag;

    private String annex;

    private String createUserId;

    private Date createDatetime;

    private String updateUserId;

    private Date updateDatetime;

    private String zwwz;

    private String zwqsqk;

    private byte[] zwTxsj;

    private String zwzp;

    private Integer typeCode;

    private static final long serialVersionUID = 1L;

    public Integer getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(Integer typeCode) {
        this.typeCode = typeCode;
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

    public String getZwzp() {
        return zwzp;
    }

    public void setZwzp(String zwzp) {
        this.zwzp = zwzp;
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

    public String getZwzwdm() {
        return zwzwdm;
    }

    public void setZwzwdm(String zwzwdm) {
        this.zwzwdm = zwzwdm;
    }

    public String getZzhwqsqkdm() {
        return zzhwqsqkdm;
    }

    public void setZzhwqsqkdm(String zzhwqsqkdm) {
        this.zzhwqsqkdm = zzhwqsqkdm;
    }

    public String getZwTxysffms() {
        return zwTxysffms;
    }

    public void setZwTxysffms(String zwTxysffms) {
        this.zwTxysffms = zwTxysffms;
    }

    public String getZwTxzl() {
        return zwTxzl;
    }

    public void setZwTxzl(String zwTxzl) {
        this.zwTxzl = zwTxzl;
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

    public byte[] getZwTxsj() {
        return zwTxsj;
    }

    public void setZwTxsj(byte[] zwTxsj) {
        this.zwTxsj = zwTxsj;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", pkId=").append(pkId);
        sb.append(", ryjcxxcjbh=").append(ryjcxxcjbh);
        sb.append(", zwzwdm=").append(zwzwdm);
        sb.append(", zzhwqsqkdm=").append(zzhwqsqkdm);
        sb.append(", zwTxysffms=").append(zwTxysffms);
        sb.append(", zwTxzl=").append(zwTxzl);
        sb.append(", deletag=").append(deletag);
        sb.append(", annex=").append(annex);
        sb.append(", createUserId=").append(createUserId);
        sb.append(", createDatetime=").append(createDatetime);
        sb.append(", updateUserId=").append(updateUserId);
        sb.append(", updateDatetime=").append(updateDatetime);
        sb.append(", zwTxsj=").append(zwTxsj);
        sb.append("]");
        return sb.toString();
    }
}