package com.zhouyi.business.core.model;

import java.io.Serializable;
import java.util.Date;

public class LedenCollectPTerminal implements Serializable {
    private String cjmbbh;

    private String ryjcxxcjbh;

    private String dxbh;

    private String wpmc;

    private String imeiWpbzh;

    private String macdz;

    private String lyMacdz;

    private String zzsDwmc;

    private String wpxh;

    private String wptzms;

    private String fjsxid;

    private String deletag;

    private String annex;

    private String createUserId;

    private Date createDatetime;

    private String updateUserId;

    private Date updateDatetime;

    private static final long serialVersionUID = 1L;

    public String getCjmbbh() {
        return cjmbbh;
    }

    public void setCjmbbh(String cjmbbh) {
        this.cjmbbh = cjmbbh;
    }

    public String getRyjcxxcjbh() {
        return ryjcxxcjbh;
    }

    public void setRyjcxxcjbh(String ryjcxxcjbh) {
        this.ryjcxxcjbh = ryjcxxcjbh;
    }

    public String getDxbh() {
        return dxbh;
    }

    public void setDxbh(String dxbh) {
        this.dxbh = dxbh;
    }

    public String getWpmc() {
        return wpmc;
    }

    public void setWpmc(String wpmc) {
        this.wpmc = wpmc;
    }

    public String getImeiWpbzh() {
        return imeiWpbzh;
    }

    public void setImeiWpbzh(String imeiWpbzh) {
        this.imeiWpbzh = imeiWpbzh;
    }

    public String getMacdz() {
        return macdz;
    }

    public void setMacdz(String macdz) {
        this.macdz = macdz;
    }

    public String getLyMacdz() {
        return lyMacdz;
    }

    public void setLyMacdz(String lyMacdz) {
        this.lyMacdz = lyMacdz;
    }

    public String getZzsDwmc() {
        return zzsDwmc;
    }

    public void setZzsDwmc(String zzsDwmc) {
        this.zzsDwmc = zzsDwmc;
    }

    public String getWpxh() {
        return wpxh;
    }

    public void setWpxh(String wpxh) {
        this.wpxh = wpxh;
    }

    public String getWptzms() {
        return wptzms;
    }

    public void setWptzms(String wptzms) {
        this.wptzms = wptzms;
    }

    public String getFjsxid() {
        return fjsxid;
    }

    public void setFjsxid(String fjsxid) {
        this.fjsxid = fjsxid;
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
        sb.append(", cjmbbh=").append(cjmbbh);
        sb.append(", ryjcxxcjbh=").append(ryjcxxcjbh);
        sb.append(", dxbh=").append(dxbh);
        sb.append(", wpmc=").append(wpmc);
        sb.append(", imeiWpbzh=").append(imeiWpbzh);
        sb.append(", macdz=").append(macdz);
        sb.append(", lyMacdz=").append(lyMacdz);
        sb.append(", zzsDwmc=").append(zzsDwmc);
        sb.append(", wpxh=").append(wpxh);
        sb.append(", wptzms=").append(wptzms);
        sb.append(", fjsxid=").append(fjsxid);
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