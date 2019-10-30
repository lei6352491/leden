package com.zhouyi.business.core.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class LedenCollectGCurrency implements Serializable {
    private String wpbh;

    private String hbzldm;

    private BigDecimal hbmz;

    private String jldw;

    private static final long serialVersionUID = 1L;

    public String getWpbh() {
        return wpbh;
    }

    public void setWpbh(String wpbh) {
        this.wpbh = wpbh;
    }

    public String getHbzldm() {
        return hbzldm;
    }

    public void setHbzldm(String hbzldm) {
        this.hbzldm = hbzldm;
    }

    public BigDecimal getHbmz() {
        return hbmz;
    }

    public void setHbmz(BigDecimal hbmz) {
        this.hbmz = hbmz;
    }

    public String getJldw() {
        return jldw;
    }

    public void setJldw(String jldw) {
        this.jldw = jldw;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", wpbh=").append(wpbh);
        sb.append(", hbzldm=").append(hbzldm);
        sb.append(", hbmz=").append(hbmz);
        sb.append(", jldw=").append(jldw);
        sb.append("]");
        return sb.toString();
    }
}