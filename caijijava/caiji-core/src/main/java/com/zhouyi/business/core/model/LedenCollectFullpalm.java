package com.zhouyi.business.core.model;

import java.io.Serializable;
import java.util.Date;

public class LedenCollectFullpalm implements Serializable {
    private String pkId;

    private String ryjcxxcjbh;

    private String qzZhwzhwdm;

    private String qzZzhwqsqkdm;

    private String qztxysffms;

    private String qzTxzl;

    private String deletag;

    private String annex;

    private String createUserId;

    private Date createDatetime;

    private String updateUserId;

    private Date updateDatetime;

    private byte[] qzTxsj;

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

    public String getQzZhwzhwdm() {
        return qzZhwzhwdm;
    }

    public void setQzZhwzhwdm(String qzZhwzhwdm) {
        this.qzZhwzhwdm = qzZhwzhwdm;
    }

    public String getQzZzhwqsqkdm() {
        return qzZzhwqsqkdm;
    }

    public void setQzZzhwqsqkdm(String qzZzhwqsqkdm) {
        this.qzZzhwqsqkdm = qzZzhwqsqkdm;
    }

    public String getqztxysffms() {
        return qztxysffms;
    }

    public void setqztxysffms(String qztxysffms) {
        this.qztxysffms = qztxysffms;
    }

    public String getQzTxzl() {
        return qzTxzl;
    }

    public void setQzTxzl(String qzTxzl) {
        this.qzTxzl = qzTxzl;
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

    public byte[] getQzTxsj() {
        return qzTxsj;
    }

    public void setQzTxsj(byte[] qzTxsj) {
        this.qzTxsj = qzTxsj;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", pkId=").append(pkId);
        sb.append(", ryjcxxcjbh=").append(ryjcxxcjbh);
        sb.append(", qzZhwzhwdm=").append(qzZhwzhwdm);
        sb.append(", qzZzhwqsqkdm=").append(qzZzhwqsqkdm);
        sb.append(", qztxysffms=").append(qztxysffms);
        sb.append(", qzTxzl=").append(qzTxzl);
        sb.append(", deletag=").append(deletag);
        sb.append(", annex=").append(annex);
        sb.append(", createUserId=").append(createUserId);
        sb.append(", createDatetime=").append(createDatetime);
        sb.append(", updateUserId=").append(updateUserId);
        sb.append(", updateDatetime=").append(updateDatetime);
        sb.append(", qzTxsj=").append(qzTxsj);
        sb.append("]");
        return sb.toString();
    }
}