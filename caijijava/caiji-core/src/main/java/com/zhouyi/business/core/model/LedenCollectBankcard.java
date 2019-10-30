package com.zhouyi.business.core.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LedenCollectBankcard implements Serializable {
    private String pkid;

    private String wpbh;

    private String ryjcxxcjbh;

    private String yhkWpbzh;

    private String xm;

    private String yhkWpmc;

    private String yhklxdm;

    private Date zjYxqjzrq;

    private String fkdq;

    private String khyhmc;

    private String cyzjlxdm;

    private String zjhm;

    private String blxxJyqk;

    private String khhhbh;

    private Date khrq;

    private Date cxrq;

    private String fjsxid;

    private String yhklxmc;

    public String getYhklxmc() {
        return yhklxmc;
    }

    public void setYhklxmc(String yhklxmc) {
        this.yhklxmc = yhklxmc;
    }

    private static final long serialVersionUID = 1L;

    private Long serialNumber;

    private List list;

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public Long getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Long serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getPkid() {
        return pkid;
    }

    public void setPkid(String pkid) {
        this.pkid = pkid;
    }

    public String getWpbh() {
        return wpbh;
    }

    public void setWpbh(String wpbh) {
        this.wpbh = wpbh;
    }

    public String getRyjcxxcjbh() {
        return ryjcxxcjbh;
    }

    public void setRyjcxxcjbh(String ryjcxxcjbh) {
        this.ryjcxxcjbh = ryjcxxcjbh;
    }

    public String getYhkWpbzh() {
        return yhkWpbzh;
    }

    public void setYhkWpbzh(String yhkWpbzh) {
        this.yhkWpbzh = yhkWpbzh;
    }

    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }

    public String getYhkWpmc() {
        return yhkWpmc;
    }

    public void setYhkWpmc(String yhkWpmc) {
        this.yhkWpmc = yhkWpmc;
    }

    public String getYhklxdm() {
        return yhklxdm;
    }

    public void setYhklxdm(String yhklxdm) {
        this.yhklxdm = yhklxdm;
    }

    public Date getZjYxqjzrq() {
        return zjYxqjzrq;
    }

    public void setZjYxqjzrq(Date zjYxqjzrq) {
        this.zjYxqjzrq = zjYxqjzrq;
    }

    public String getFkdq() {
        return fkdq;
    }

    public void setFkdq(String fkdq) {
        this.fkdq = fkdq;
    }

    public String getKhyhmc() {
        return khyhmc;
    }

    public void setKhyhmc(String khyhmc) {
        this.khyhmc = khyhmc;
    }

    public String getCyzjlxdm() {
        return cyzjlxdm;
    }

    public void setCyzjlxdm(String cyzjlxdm) {
        this.cyzjlxdm = cyzjlxdm;
    }

    public String getZjhm() {
        return zjhm;
    }

    public void setZjhm(String zjhm) {
        this.zjhm = zjhm;
    }

    public String getBlxxJyqk() {
        return blxxJyqk;
    }

    public void setBlxxJyqk(String blxxJyqk) {
        this.blxxJyqk = blxxJyqk;
    }

    public String getKhhhbh() {
        return khhhbh;
    }

    public void setKhhhbh(String khhhbh) {
        this.khhhbh = khhhbh;
    }

    public Date getKhrq() {
        return khrq;
    }

    public void setKhrq(Date khrq) {
        this.khrq = khrq;
    }

    public Date getCxrq() {
        return cxrq;
    }

    public void setCxrq(Date cxrq) {
        this.cxrq = cxrq;
    }

    public String getFjsxid() {
        return fjsxid;
    }

    public void setFjsxid(String fjsxid) {
        this.fjsxid = fjsxid;
    }

    @Override
    public String toString() {
        return "LedenCollectBankcard{" +
                "pkid='" + pkid + '\'' +
                ", wpbh='" + wpbh + '\'' +
                ", ryjcxxcjbh='" + ryjcxxcjbh + '\'' +
                ", yhkWpbzh='" + yhkWpbzh + '\'' +
                ", xm='" + xm + '\'' +
                ", yhkWpmc='" + yhkWpmc + '\'' +
                ", yhklxdm='" + yhklxdm + '\'' +
                ", zjYxqjzrq=" + zjYxqjzrq +
                ", fkdq='" + fkdq + '\'' +
                ", khyhmc='" + khyhmc + '\'' +
                ", cyzjlxdm='" + cyzjlxdm + '\'' +
                ", zjhm='" + zjhm + '\'' +
                ", blxxJyqk='" + blxxJyqk + '\'' +
                ", khhhbh='" + khhhbh + '\'' +
                ", khrq=" + khrq +
                ", cxrq=" + cxrq +
                ", fjsxid='" + fjsxid + '\'' +
                ", serialNumber=" + serialNumber +
                ", list=" + list +
                '}';
    }
}