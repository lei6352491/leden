package com.zhouyi.business.core.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

public class LedenCollectVoiceprint implements Serializable {
    private String pkId;

    private String ryjcxxcjbh;

    private String qbypSc;

    private String yxypSc;

    private String xzb;

    private String nlz;

    private String xxzldf;

    private String lyyzdm;

    private String lysb;

    private String fyfsdm;

    private String xddm;

    private String hyfydm;

    private String deletag;

    private String annex;

    private byte[] ypsj;

    private byte[] swsj;

    private String createUserId;

    private Date createDatetime;

    private String updateUserId;

    private Date updateDatetime;

    //字典项
    private String lyyzmc;
    private String fyfsmc;
    private String xdmc;
    private String hyfymc;

    public String getLyyzmc() {
        return lyyzmc;
    }

    public void setLyyzmc(String lyyzmc) {
        this.lyyzmc = lyyzmc;
    }

    public String getFyfsmc() {
        return fyfsmc;
    }

    public void setFyfsmc(String fyfsmc) {
        this.fyfsmc = fyfsmc;
    }

    public String getXdmc() {
        return xdmc;
    }

    public void setXdmc(String xdmc) {
        this.xdmc = xdmc;
    }

    public String getHyfymc() {
        return hyfymc;
    }

    public void setHyfymc(String hyfymc) {
        this.hyfymc = hyfymc;
    }

    private static final long serialVersionUID = 1L;

    private Long serialNumber;

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

    public String getQbypSc() {
        return qbypSc;
    }

    public void setQbypSc(String qbypSc) {
        this.qbypSc = qbypSc;
    }

    public String getYxypSc() {
        return yxypSc;
    }

    public void setYxypSc(String yxypSc) {
        this.yxypSc = yxypSc;
    }

    public String getXzb() {
        return xzb;
    }

    public void setXzb(String xzb) {
        this.xzb = xzb;
    }

    public String getNlz() {
        return nlz;
    }

    public void setNlz(String nlz) {
        this.nlz = nlz;
    }

    public String getXxzldf() {
        return xxzldf;
    }

    public void setXxzldf(String xxzldf) {
        this.xxzldf = xxzldf;
    }

    public String getLyyzdm() {
        return lyyzdm;
    }

    public void setLyyzdm(String lyyzdm) {
        this.lyyzdm = lyyzdm;
    }

    public String getLysb() {
        return lysb;
    }

    public void setLysb(String lysb) {
        this.lysb = lysb;
    }

    public String getFyfsdm() {
        return fyfsdm;
    }

    public void setFyfsdm(String fyfsdm) {
        this.fyfsdm = fyfsdm;
    }

    public String getXddm() {
        return xddm;
    }

    public void setXddm(String xddm) {
        this.xddm = xddm;
    }

    public String getHyfydm() {
        return hyfydm;
    }

    public void setHyfydm(String hyfydm) {
        this.hyfydm = hyfydm;
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

    public byte[] getYpsj() {
        return ypsj;
    }

    public void setYpsj(byte[] ypsj) {
        this.ypsj = ypsj;
    }

    public byte[] getSwsj() {
        return swsj;
    }

    public void setSwsj(byte[] swsj) {
        this.swsj = swsj;
    }

    @Override
    public String toString() {
        return "LedenCollectVoiceprint{" +
                "pkId='" + pkId + '\'' +
                ", ryjcxxcjbh='" + ryjcxxcjbh + '\'' +
                ", qbypSc='" + qbypSc + '\'' +
                ", yxypSc='" + yxypSc + '\'' +
                ", xzb='" + xzb + '\'' +
                ", nlz='" + nlz + '\'' +
                ", xxzldf='" + xxzldf + '\'' +
                ", lyyzdm='" + lyyzdm + '\'' +
                ", lysb='" + lysb + '\'' +
                ", fyfsdm='" + fyfsdm + '\'' +
                ", xddm='" + xddm + '\'' +
                ", hyfydm='" + hyfydm + '\'' +
                ", deletag='" + deletag + '\'' +
                ", annex='" + annex + '\'' +
                ", ypsj=" + Arrays.toString(ypsj) +
                ", swsj=" + Arrays.toString(swsj) +
                ", createUserId='" + createUserId + '\'' +
                ", createDatetime=" + createDatetime +
                ", updateUserId='" + updateUserId + '\'' +
                ", updateDatetime=" + updateDatetime +
                ", serialNumber=" + serialNumber +
                '}';
    }
}