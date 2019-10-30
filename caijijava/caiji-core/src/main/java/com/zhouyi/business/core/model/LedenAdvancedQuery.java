package com.zhouyi.business.core.model;

import java.io.Serializable;
import java.util.Date;

public class LedenAdvancedQuery implements Serializable {
    private String pkId;

    private String colletCategoryName;

    private String ryjcxxcjbh;

    private String xm;

    private String gmsfhm;

    private String xbdm;

    private String rydnabh;

    private String xdjcjg;

    private String ajlbdm;

    private String cjdwdm;

    private String cjdwmc;

    private Date cjsj;

    private String status;

    private String schedule;

    private String deletag;

    private static final long serialVersionUID = 1L;

    public String getPkId() {
        return pkId;
    }

    public void setPkId(String pkId) {
        this.pkId = pkId;
    }

    public String getColletCategoryName() {
        return colletCategoryName;
    }

    public void setColletCategoryName(String colletCategoryName) {
        this.colletCategoryName = colletCategoryName;
    }

    public String getRyjcxxcjbh() {
        return ryjcxxcjbh;
    }

    public void setRyjcxxcjbh(String ryjcxxcjbh) {
        this.ryjcxxcjbh = ryjcxxcjbh;
    }

    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }

    public String getGmsfhm() {
        return gmsfhm;
    }

    public void setGmsfhm(String gmsfhm) {
        this.gmsfhm = gmsfhm;
    }

    public String getXbdm() {
        return xbdm;
    }

    public void setXbdm(String xbdm) {
        this.xbdm = xbdm;
    }

    public String getRydnabh() {
        return rydnabh;
    }

    public void setRydnabh(String rydnabh) {
        this.rydnabh = rydnabh;
    }

    public String getXdjcjg() {
        return xdjcjg;
    }

    public void setXdjcjg(String xdjcjg) {
        this.xdjcjg = xdjcjg;
    }

    public String getAjlbdm() {
        return ajlbdm;
    }

    public void setAjlbdm(String ajlbdm) {
        this.ajlbdm = ajlbdm;
    }

    public String getCjdwdm() {
        return cjdwdm;
    }

    public void setCjdwdm(String cjdwdm) {
        this.cjdwdm = cjdwdm;
    }

    public String getCjdwmc() {
        return cjdwmc;
    }

    public void setCjdwmc(String cjdwmc) {
        this.cjdwmc = cjdwmc;
    }

    public Date getCjsj() {
        return cjsj;
    }

    public void setCjsj(Date cjsj) {
        this.cjsj = cjsj;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getDeletag() {
        return deletag;
    }

    public void setDeletag(String deletag) {
        this.deletag = deletag;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", pkId=").append(pkId);
        sb.append(", colletCategoryName=").append(colletCategoryName);
        sb.append(", ryjcxxcjbh=").append(ryjcxxcjbh);
        sb.append(", xm=").append(xm);
        sb.append(", gmsfhm=").append(gmsfhm);
        sb.append(", xbdm=").append(xbdm);
        sb.append(", rydnabh=").append(rydnabh);
        sb.append(", xdjcjg=").append(xdjcjg);
        sb.append(", ajlbdm=").append(ajlbdm);
        sb.append(", cjdwdm=").append(cjdwdm);
        sb.append(", cjdwmc=").append(cjdwmc);
        sb.append(", cjsj=").append(cjsj);
        sb.append(", status=").append(status);
        sb.append(", schedule=").append(schedule);
        sb.append(", deletag=").append(deletag);
        sb.append("]");
        return sb.toString();
    }
}