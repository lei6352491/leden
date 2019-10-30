package com.zhouyi.business.core.model;

import java.io.Serializable;
import java.util.Date;

public class LedenCollectSign implements Serializable {
    private String pkId;

    private String tstzMc;

    private String ryjcxxcjbh;

    private String tstzBw;

    private String tstzFw;

    private String tstzSl;

    private String tstzCc;

    private String tstzYs;

    private String deletag;

    private String annex;

    private String tstzmc;
    private String tstzbwmc;
    private String tstzfwmc;
    private String tstzslmc;
    private String tstzysmc;

    private String createUserId;

    private Date createDatetime;

    private String updateUserId;

    private Date updateDatetime;

    private byte[] tstzZp;

    private String tzzp;

    public String getTzzp() {
        return tzzp;
    }

    public void setTzzp(String tzzp) {
        this.tzzp = tzzp;
    }

    private static final long serialVersionUID = 1L;

    public String getTstzmc() {
        return tstzmc;
    }

    public void setTstzmc(String tstzmc) {
        this.tstzmc = tstzmc;
    }

    public String getTstzbwmc() {
        return tstzbwmc;
    }

    public void setTstzbwmc(String tstzbwmc) {
        this.tstzbwmc = tstzbwmc;
    }

    public String getTstzfwmc() {
        return tstzfwmc;
    }

    public void setTstzfwmc(String tstzfwmc) {
        this.tstzfwmc = tstzfwmc;
    }

    public String getTstzslmc() {
        return tstzslmc;
    }

    public void setTstzslmc(String tstzslmc) {
        this.tstzslmc = tstzslmc;
    }

    public String getTstzysmc() {
        return tstzysmc;
    }

    public void setTstzysmc(String tstzysmc) {
        this.tstzysmc = tstzysmc;
    }

    public String getPkId() {
        return pkId;
    }

    public void setPkId(String pkId) {
        this.pkId = pkId;
    }

    public String getTstzMc() {
        return tstzMc;
    }

    public void setTstzMc(String tstzMc) {
        this.tstzMc = tstzMc;
    }

    public String getRyjcxxcjbh() {
        return ryjcxxcjbh;
    }

    public void setRyjcxxcjbh(String ryjcxxcjbh) {
        this.ryjcxxcjbh = ryjcxxcjbh;
    }

    public String getTstzBw() {
        return tstzBw;
    }

    public void setTstzBw(String tstzBw) {
        this.tstzBw = tstzBw;
    }

    public String getTstzFw() {
        return tstzFw;
    }

    public void setTstzFw(String tstzFw) {
        this.tstzFw = tstzFw;
    }

    public String getTstzSl() {
        return tstzSl;
    }

    public void setTstzSl(String tstzSl) {
        this.tstzSl = tstzSl;
    }

    public String getTstzCc() {
        return tstzCc;
    }

    public void setTstzCc(String tstzCc) {
        this.tstzCc = tstzCc;
    }

    public String getTstzYs() {
        return tstzYs;
    }

    public void setTstzYs(String tstzYs) {
        this.tstzYs = tstzYs;
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

    public byte[] getTstzZp() {
        return tstzZp;
    }

    public void setTstzZp(byte[] tstzZp) {
        this.tstzZp = tstzZp;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", pkId=").append(pkId);
        sb.append(", tstzMc=").append(tstzMc);
        sb.append(", ryjcxxcjbh=").append(ryjcxxcjbh);
        sb.append(", tstzBw=").append(tstzBw);
        sb.append(", tstzFw=").append(tstzFw);
        sb.append(", tstzSl=").append(tstzSl);
        sb.append(", tstzCc=").append(tstzCc);
        sb.append(", tstzYs=").append(tstzYs);
        sb.append(", deletag=").append(deletag);
        sb.append(", annex=").append(annex);
        sb.append(", createUserId=").append(createUserId);
        sb.append(", createDatetime=").append(createDatetime);
        sb.append(", updateUserId=").append(updateUserId);
        sb.append(", updateDatetime=").append(updateDatetime);
        sb.append(", tstzZp=").append(tstzZp);
        sb.append("]");
        return sb.toString();
    }
}