package com.zhouyi.business.core.model;

import java.io.Serializable;
import java.util.Date;

public class LedenCollectGCard implements Serializable {
    private String wpbh;

    private String kh;

    private String fkdwGjhdqdm;

    private String fkdwDwmc;

    private Date fksjRqsj;

    private Date yxqKssj;

    private Date yxqJssj;

    private static final long serialVersionUID = 1L;

    public String getWpbh() {
        return wpbh;
    }

    public void setWpbh(String wpbh) {
        this.wpbh = wpbh;
    }

    public String getKh() {
        return kh;
    }

    public void setKh(String kh) {
        this.kh = kh;
    }

    public String getFkdwGjhdqdm() {
        return fkdwGjhdqdm;
    }

    public void setFkdwGjhdqdm(String fkdwGjhdqdm) {
        this.fkdwGjhdqdm = fkdwGjhdqdm;
    }

    public String getFkdwDwmc() {
        return fkdwDwmc;
    }

    public void setFkdwDwmc(String fkdwDwmc) {
        this.fkdwDwmc = fkdwDwmc;
    }

    public Date getFksjRqsj() {
        return fksjRqsj;
    }

    public void setFksjRqsj(Date fksjRqsj) {
        this.fksjRqsj = fksjRqsj;
    }

    public Date getYxqKssj() {
        return yxqKssj;
    }

    public void setYxqKssj(Date yxqKssj) {
        this.yxqKssj = yxqKssj;
    }

    public Date getYxqJssj() {
        return yxqJssj;
    }

    public void setYxqJssj(Date yxqJssj) {
        this.yxqJssj = yxqJssj;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", wpbh=").append(wpbh);
        sb.append(", kh=").append(kh);
        sb.append(", fkdwGjhdqdm=").append(fkdwGjhdqdm);
        sb.append(", fkdwDwmc=").append(fkdwDwmc);
        sb.append(", fksjRqsj=").append(fksjRqsj);
        sb.append(", yxqKssj=").append(yxqKssj);
        sb.append(", yxqJssj=").append(yxqJssj);
        sb.append("]");
        return sb.toString();
    }
}