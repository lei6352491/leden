package com.zhouyi.business.core.model;

import java.io.Serializable;

public class LedenCollectGMobile implements Serializable {
    private String wpbh;

    private String iccid;

    private String imei;

    private String imsi;

    private String dhhm;

    private static final long serialVersionUID = 1L;

    public String getWpbh() {
        return wpbh;
    }

    public void setWpbh(String wpbh) {
        this.wpbh = wpbh;
    }

    public String getIccid() {
        return iccid;
    }

    public void setIccid(String iccid) {
        this.iccid = iccid;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getImsi() {
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    public String getDhhm() {
        return dhhm;
    }

    public void setDhhm(String dhhm) {
        this.dhhm = dhhm;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", wpbh=").append(wpbh);
        sb.append(", iccid=").append(iccid);
        sb.append(", imei=").append(imei);
        sb.append(", imsi=").append(imsi);
        sb.append(", dhhm=").append(dhhm);
        sb.append("]");
        return sb.toString();
    }
}