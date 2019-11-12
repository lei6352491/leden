package com.zhouyi.business.core.model;

import java.io.Serializable;
import java.util.Date;

public class LedenCollectIris implements Serializable {
    private String pkId;

    private String cjbh;

    private String qzcjbz;

    private String hmcjsbxhdm;

    private String hmcjhs;

    private String sbbh;

    private String sbcsdm;

    private String ryjcxxcjbh;

    private String hmywdm;

    private String hmqsqkdm;

    private String xxzldf;

    private String deletag;

    private String annex;

    private String createUserId;

    private Date createDatetime;

    private String updateUserId;

    private Date updateDatetime;

    private byte[] hmsj;

    private String hmzp;



    private static final long serialVersionUID = 1L;

    private Long serialNumber;

    public String getHmzp() {
        return hmzp;
    }

    public String getCjbh() {
        return cjbh;
    }

    public void setCjbh(String cjbh) {
        this.cjbh = cjbh;
    }

    public String getQzcjbz() {
        return qzcjbz;
    }

    public void setQzcjbz(String qzcjbz) {
        this.qzcjbz = qzcjbz;
    }

    public String getHmcjsbxhdm() {
        return hmcjsbxhdm;
    }

    public void setHmcjsbxhdm(String hmcjsbxhdm) {
        this.hmcjsbxhdm = hmcjsbxhdm;
    }

    public String getHmcjhs() {
        return hmcjhs;
    }

    public void setHmcjhs(String hmcjhs) {
        this.hmcjhs = hmcjhs;
    }

    public String getSbbh() {
        return sbbh;
    }

    public void setSbbh(String sbbh) {
        this.sbbh = sbbh;
    }

    public String getSbcsdm() {
        return sbcsdm;
    }

    public void setSbcsdm(String sbcsdm) {
        this.sbcsdm = sbcsdm;
    }

    public void setHmzp(String hmzp) {
        this.hmzp = hmzp;
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

    public String getHmywdm() {
        return hmywdm;
    }

    public void setHmywdm(String hmywdm) {
        this.hmywdm = hmywdm;
    }

    public String getHmqsqkdm() {
        return hmqsqkdm;
    }

    public void setHmqsqkdm(String hmqsqkdm) {
        this.hmqsqkdm = hmqsqkdm;
    }

    public String getXxzldf() {
        return xxzldf;
    }

    public void setXxzldf(String xxzldf) {
        this.xxzldf = xxzldf;
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

    public byte[] getHmsj() {
        return hmsj;
    }

    public void setHmsj(byte[] hmsj) {
        this.hmsj = hmsj;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", pkId=").append(pkId);
        sb.append(", ryjcxxcjbh=").append(ryjcxxcjbh);
        sb.append(", hmywdm=").append(hmywdm);
        sb.append(", hmqsqkdm=").append(hmqsqkdm);
        sb.append(", xxzldf=").append(xxzldf);
        sb.append(", deletag=").append(deletag);
        sb.append(", annex=").append(annex);
        sb.append(", createUserId=").append(createUserId);
        sb.append(", createDatetime=").append(createDatetime);
        sb.append(", updateUserId=").append(updateUserId);
        sb.append(", updateDatetime=").append(updateDatetime);
        sb.append(", hmsj=").append(hmsj);
        sb.append("]");
        return sb.toString();
    }
}