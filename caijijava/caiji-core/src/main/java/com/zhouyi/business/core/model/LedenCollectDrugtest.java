package com.zhouyi.business.core.model;

import java.io.Serializable;
import java.util.Date;

public class LedenCollectDrugtest implements Serializable {
    private String pkId;

    private String ryjcxxcjbh;

    private String xdjcyb;

    private String xdjcfs;

    private String xdjcjg;

    private String annex;

    private String createUserId;

    private Date createDatetime;

    private String updateUserId;

    private Date updateDatetime;

    private byte[] xdjctp;

    private String xdzp;

    private static final long serialVersionUID = 1L;

    private Long serialNumber;

    public String getXdzp() {
        return xdzp;
    }

    public void setXdzp(String xdzp) {
        this.xdzp = xdzp;
    }

    public Long getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Long serialNumber) {
        this.serialNumber = serialNumber;
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

    public String getXdjcyb() {
        return xdjcyb;
    }

    public void setXdjcyb(String xdjcyb) {
        this.xdjcyb = xdjcyb;
    }

    public String getXdjcfs() {
        return xdjcfs;
    }

    public void setXdjcfs(String xdjcfs) {
        this.xdjcfs = xdjcfs;
    }

    public String getXdjcjg() {
        return xdjcjg;
    }

    public void setXdjcjg(String xdjcjg) {
        this.xdjcjg = xdjcjg;
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

    public byte[] getXdjctp() {
        return xdjctp;
    }

    public void setXdjctp(byte[] xdjctp) {
        this.xdjctp = xdjctp;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", pkId=").append(pkId);
        sb.append(", ryjcxxcjbh=").append(ryjcxxcjbh);
        sb.append(", xdjcyb=").append(xdjcyb);
        sb.append(", xdjcfs=").append(xdjcfs);
        sb.append(", xdjcjg=").append(xdjcjg);
        sb.append(", annex=").append(annex);
        sb.append(", createUserId=").append(createUserId);
        sb.append(", createDatetime=").append(createDatetime);
        sb.append(", updateUserId=").append(updateUserId);
        sb.append(", updateDatetime=").append(updateDatetime);
        sb.append(", xdjctp=").append(xdjctp);
        sb.append("]");
        return sb.toString();
    }
}