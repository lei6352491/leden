package com.zhouyi.business.core.model;

import java.io.Serializable;
import java.util.Date;

public class LedenCollectPCallrecords implements Serializable {
    private String cjmbbh;

    private String yddh;

    private String gxrYddh;

    private String gxrXm;

    private String thzt;

    private String bdsjbs;

    private Date kssj;

    private Date jssj;

    private Integer thsc;

    private String smxsz;

    private String ljzt;

    private String fjsxid;

    private String xxscPdbz;

    private Date xxscClsj;

    private static final long serialVersionUID = 1L;

    public String getCjmbbh() {
        return cjmbbh;
    }

    public void setCjmbbh(String cjmbbh) {
        this.cjmbbh = cjmbbh;
    }

    public String getYddh() {
        return yddh;
    }

    public void setYddh(String yddh) {
        this.yddh = yddh;
    }

    public String getGxrYddh() {
        return gxrYddh;
    }

    public void setGxrYddh(String gxrYddh) {
        this.gxrYddh = gxrYddh;
    }

    public String getGxrXm() {
        return gxrXm;
    }

    public void setGxrXm(String gxrXm) {
        this.gxrXm = gxrXm;
    }

    public String getThzt() {
        return thzt;
    }

    public void setThzt(String thzt) {
        this.thzt = thzt;
    }

    public String getBdsjbs() {
        return bdsjbs;
    }

    public void setBdsjbs(String bdsjbs) {
        this.bdsjbs = bdsjbs;
    }

    public Date getKssj() {
        return kssj;
    }

    public void setKssj(Date kssj) {
        this.kssj = kssj;
    }

    public Date getJssj() {
        return jssj;
    }

    public void setJssj(Date jssj) {
        this.jssj = jssj;
    }

    public Integer getThsc() {
        return thsc;
    }

    public void setThsc(Integer thsc) {
        this.thsc = thsc;
    }

    public String getSmxsz() {
        return smxsz;
    }

    public void setSmxsz(String smxsz) {
        this.smxsz = smxsz;
    }

    public String getLjzt() {
        return ljzt;
    }

    public void setLjzt(String ljzt) {
        this.ljzt = ljzt;
    }

    public String getFjsxid() {
        return fjsxid;
    }

    public void setFjsxid(String fjsxid) {
        this.fjsxid = fjsxid;
    }

    public String getXxscPdbz() {
        return xxscPdbz;
    }

    public void setXxscPdbz(String xxscPdbz) {
        this.xxscPdbz = xxscPdbz;
    }

    public Date getXxscClsj() {
        return xxscClsj;
    }

    public void setXxscClsj(Date xxscClsj) {
        this.xxscClsj = xxscClsj;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", cjmbbh=").append(cjmbbh);
        sb.append(", yddh=").append(yddh);
        sb.append(", gxrYddh=").append(gxrYddh);
        sb.append(", gxrXm=").append(gxrXm);
        sb.append(", thzt=").append(thzt);
        sb.append(", bdsjbs=").append(bdsjbs);
        sb.append(", kssj=").append(kssj);
        sb.append(", jssj=").append(jssj);
        sb.append(", thsc=").append(thsc);
        sb.append(", smxsz=").append(smxsz);
        sb.append(", ljzt=").append(ljzt);
        sb.append(", fjsxid=").append(fjsxid);
        sb.append(", xxscPdbz=").append(xxscPdbz);
        sb.append(", xxscClsj=").append(xxscClsj);
        sb.append("]");
        return sb.toString();
    }
}