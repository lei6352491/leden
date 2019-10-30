package com.zhouyi.business.core.model;

import java.io.Serializable;
import java.util.Date;

public class LedenCollectDna implements Serializable {
    private String pkId;

    private String ryjcxxcjbh;

    private String rydnabh;

    private String yblxdm;

    private String ybbzqk;

    private String sampleDes;

    private String cjzkbsdm;

    private String deletag;

    private String annex;

    private String yblxmc;

    private String createUserId;

    private Date createDatetime;

    private String updateUserId;

    private Date updateDatetime;

    private static final long serialVersionUID = 1L;

    public String getYblxmc() {
        return yblxmc;
    }

    public void setYblxmc(String yblxmc) {
        this.yblxmc = yblxmc;
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

    public String getRydnabh() {
        return rydnabh;
    }

    public void setRydnabh(String rydnabh) {
        this.rydnabh = rydnabh;
    }

    public String getYblxdm() {
        return yblxdm;
    }

    public void setYblxdm(String yblxdm) {
        this.yblxdm = yblxdm;
    }

    public String getYbbzqk() {
        return ybbzqk;
    }

    public void setYbbzqk(String ybbzqk) {
        this.ybbzqk = ybbzqk;
    }

    public String getSampleDes() {
        return sampleDes;
    }

    public void setSampleDes(String sampleDes) {
        this.sampleDes = sampleDes;
    }

    public String getCjzkbsdm() {
        return cjzkbsdm;
    }

    public void setCjzkbsdm(String cjzkbsdm) {
        this.cjzkbsdm = cjzkbsdm;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", pkId=").append(pkId);
        sb.append(", ryjcxxcjbh=").append(ryjcxxcjbh);
        sb.append(", rydnabh=").append(rydnabh);
        sb.append(", yblxdm=").append(yblxdm);
        sb.append(", ybbzqk=").append(ybbzqk);
        sb.append(", sampleDes=").append(sampleDes);
        sb.append(", cjzkbsdm=").append(cjzkbsdm);
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