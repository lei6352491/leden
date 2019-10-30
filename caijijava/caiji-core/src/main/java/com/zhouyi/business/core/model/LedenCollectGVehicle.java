package com.zhouyi.business.core.model;

import java.io.Serializable;

public class LedenCollectGVehicle implements Serializable {
    private String wpbh;

    private String jdchpzldm;

    private String clpzh;

    private String vin;

    private String fdjh;

    private static final long serialVersionUID = 1L;

    public String getWpbh() {
        return wpbh;
    }

    public void setWpbh(String wpbh) {
        this.wpbh = wpbh;
    }

    public String getJdchpzldm() {
        return jdchpzldm;
    }

    public void setJdchpzldm(String jdchpzldm) {
        this.jdchpzldm = jdchpzldm;
    }

    public String getClpzh() {
        return clpzh;
    }

    public void setClpzh(String clpzh) {
        this.clpzh = clpzh;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getFdjh() {
        return fdjh;
    }

    public void setFdjh(String fdjh) {
        this.fdjh = fdjh;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", wpbh=").append(wpbh);
        sb.append(", jdchpzldm=").append(jdchpzldm);
        sb.append(", clpzh=").append(clpzh);
        sb.append(", vin=").append(vin);
        sb.append(", fdjh=").append(fdjh);
        sb.append("]");
        return sb.toString();
    }
}