package com.zhouyi.business.core.model;

import java.io.Serializable;
import java.util.Date;

public class LedenCollectGPhoto implements Serializable {
    private String xxbh;

    private String wpbh;

    private String dzwjmc;

    private String dzwjzy;

    private String dzwjgs;

    private String dzwjdx;

    private String dzwjwz;

    private Date dzwjcjsjRqsj;

    private byte[] dzwjnr;

    private String base64Img;

    public String getBase64Img() {
        return base64Img;
    }

    public void setBase64Img(String base64Img) {
        this.base64Img = base64Img;
    }

    private static final long serialVersionUID = 1L;

    public String getXxbh() {
        return xxbh;
    }

    public void setXxbh(String xxbh) {
        this.xxbh = xxbh;
    }

    public String getWpbh() {
        return wpbh;
    }

    public void setWpbh(String wpbh) {
        this.wpbh = wpbh;
    }

    public String getDzwjmc() {
        return dzwjmc;
    }

    public void setDzwjmc(String dzwjmc) {
        this.dzwjmc = dzwjmc;
    }

    public String getDzwjzy() {
        return dzwjzy;
    }

    public void setDzwjzy(String dzwjzy) {
        this.dzwjzy = dzwjzy;
    }

    public String getDzwjgs() {
        return dzwjgs;
    }

    public void setDzwjgs(String dzwjgs) {
        this.dzwjgs = dzwjgs;
    }

    public String getDzwjdx() {
        return dzwjdx;
    }

    public void setDzwjdx(String dzwjdx) {
        this.dzwjdx = dzwjdx;
    }

    public String getDzwjwz() {
        return dzwjwz;
    }

    public void setDzwjwz(String dzwjwz) {
        this.dzwjwz = dzwjwz;
    }

    public Date getDzwjcjsjRqsj() {
        return dzwjcjsjRqsj;
    }

    public void setDzwjcjsjRqsj(Date dzwjcjsjRqsj) {
        this.dzwjcjsjRqsj = dzwjcjsjRqsj;
    }

    public byte[] getDzwjnr() {
        return dzwjnr;
    }

    public void setDzwjnr(byte[] dzwjnr) {
        this.dzwjnr = dzwjnr;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", xxbh=").append(xxbh);
        sb.append(", wpbh=").append(wpbh);
        sb.append(", dzwjmc=").append(dzwjmc);
        sb.append(", dzwjzy=").append(dzwjzy);
        sb.append(", dzwjgs=").append(dzwjgs);
        sb.append(", dzwjdx=").append(dzwjdx);
        sb.append(", dzwjwz=").append(dzwjwz);
        sb.append(", dzwjcjsjRqsj=").append(dzwjcjsjRqsj);
        sb.append(", dzwjnr=").append(dzwjnr);
        sb.append("]");
        return sb.toString();
    }



}