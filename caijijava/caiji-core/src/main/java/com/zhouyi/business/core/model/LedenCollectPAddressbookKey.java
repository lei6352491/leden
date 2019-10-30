package com.zhouyi.business.core.model;

import java.io.Serializable;

public class LedenCollectPAddressbookKey implements Serializable {
    private String cjmbbh;

    private String zzxlmc;

    private static final long serialVersionUID = 1L;

    public String getCjmbbh() {
        return cjmbbh;
    }

    public void setCjmbbh(String cjmbbh) {
        this.cjmbbh = cjmbbh;
    }

    public String getZzxlmc() {
        return zzxlmc;
    }

    public void setZzxlmc(String zzxlmc) {
        this.zzxlmc = zzxlmc;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", cjmbbh=").append(cjmbbh);
        sb.append(", zzxlmc=").append(zzxlmc);
        sb.append("]");
        return sb.toString();
    }
}