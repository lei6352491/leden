package com.zhouyi.business.core.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class LedenCollectBRecord implements Serializable {
    private String yhkxxid;

    private String yhkkh;

    private Date jyrq;

    private Date jysj;

    private BigDecimal sqje;

    private BigDecimal qtje;

    private String jyddXzqhdm;

    private String jyhbdm;

    private String shDwmc;

    private String jylxdm;

    private String yyjyjsq;

    private String jyddmc;

    private String jylxmc;

    private static final long serialVersionUID = 1L;

    private Long serialNumber;

    public String getJylxmc() {
        return jylxmc;
    }

    public void setJylxmc(String jylxmc) {
        this.jylxmc = jylxmc;
    }

    public String getJyddmc() {
        return jyddmc;
    }

    public void setJyddmc(String jyddmc) {
        this.jyddmc = jyddmc;
    }

    public Long getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Long serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getYhkxxid() {
        return yhkxxid;
    }

    public void setYhkxxid(String yhkxxid) {
        this.yhkxxid = yhkxxid;
    }

    public String getYhkkh() {
        return yhkkh;
    }

    public void setYhkkh(String yhkkh) {
        this.yhkkh = yhkkh;
    }

    public Date getJyrq() {
        return jyrq;
    }

    public void setJyrq(Date jyrq) {
        this.jyrq = jyrq;
    }

    public Date getJysj() {
        return jysj;
    }

    public void setJysj(Date jysj) {
        this.jysj = jysj;
    }

    public BigDecimal getSqje() {
        return sqje;
    }

    public void setSqje(BigDecimal sqje) {
        this.sqje = sqje;
    }

    public BigDecimal getQtje() {
        return qtje;
    }

    public void setQtje(BigDecimal qtje) {
        this.qtje = qtje;
    }

    public String getJyddXzqhdm() {
        return jyddXzqhdm;
    }

    public void setJyddXzqhdm(String jyddXzqhdm) {
        this.jyddXzqhdm = jyddXzqhdm;
    }

    public String getJyhbdm() {
        return jyhbdm;
    }

    public void setJyhbdm(String jyhbdm) {
        this.jyhbdm = jyhbdm;
    }

    public String getShDwmc() {
        return shDwmc;
    }

    public void setShDwmc(String shDwmc) {
        this.shDwmc = shDwmc;
    }

    public String getJylxdm() {
        return jylxdm;
    }

    public void setJylxdm(String jylxdm) {
        this.jylxdm = jylxdm;
    }

    public String getYyjyjsq() {
        return yyjyjsq;
    }

    public void setYyjyjsq(String yyjyjsq) {
        this.yyjyjsq = yyjyjsq;
    }

    @Override
    public String toString() {
        return "LedenCollectBRecord{" +
                "yhkxxid='" + yhkxxid + '\'' +
                ", yhkkh='" + yhkkh + '\'' +
                ", jyrq=" + jyrq +
                ", jysj=" + jysj +
                ", sqje=" + sqje +
                ", qtje=" + qtje +
                ", jyddXzqhdm='" + jyddXzqhdm + '\'' +
                ", jyhbdm='" + jyhbdm + '\'' +
                ", shDwmc='" + shDwmc + '\'' +
                ", jylxdm='" + jylxdm + '\'' +
                ", yyjyjsq='" + yyjyjsq + '\'' +
                ", serialNumber=" + serialNumber +
                '}';
    }
}