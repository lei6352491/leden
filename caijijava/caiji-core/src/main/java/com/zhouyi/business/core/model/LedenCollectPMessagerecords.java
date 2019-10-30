package com.zhouyi.business.core.model;

import java.io.Serializable;
import java.util.Date;

public class LedenCollectPMessagerecords implements Serializable {
    private String cjmbbh;

    private String yddh;

    private String gxrYddh;

    private String gxrXm;

    private String bdsjbs;

    private Date dxsfsj;

    private String dxJyqk;

    private String ckzt;

    private String dxccwz;

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

    public String getBdsjbs() {
        return bdsjbs;
    }

    public void setBdsjbs(String bdsjbs) {
        this.bdsjbs = bdsjbs;
    }

    public Date getDxsfsj() {
        return dxsfsj;
    }

    public void setDxsfsj(Date dxsfsj) {
        this.dxsfsj = dxsfsj;
    }

    public String getDxJyqk() {
        return dxJyqk;
    }

    public void setDxJyqk(String dxJyqk) {
        this.dxJyqk = dxJyqk;
    }

    public String getCkzt() {
        return ckzt;
    }

    public void setCkzt(String ckzt) {
        this.ckzt = ckzt;
    }

    public String getDxccwz() {
        return dxccwz;
    }

    public void setDxccwz(String dxccwz) {
        this.dxccwz = dxccwz;
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
        sb.append(", bdsjbs=").append(bdsjbs);
        sb.append(", dxsfsj=").append(dxsfsj);
        sb.append(", dxJyqk=").append(dxJyqk);
        sb.append(", ckzt=").append(ckzt);
        sb.append(", dxccwz=").append(dxccwz);
        sb.append(", smxsz=").append(smxsz);
        sb.append(", ljzt=").append(ljzt);
        sb.append(", fjsxid=").append(fjsxid);
        sb.append(", xxscPdbz=").append(xxscPdbz);
        sb.append(", xxscClsj=").append(xxscClsj);
        sb.append("]");
        return sb.toString();
    }
}