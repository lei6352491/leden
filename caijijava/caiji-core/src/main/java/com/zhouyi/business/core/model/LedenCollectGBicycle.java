package com.zhouyi.business.core.model;

import java.io.Serializable;

public class LedenCollectGBicycle implements Serializable {
    private String wpbh;

    private String djh;

    private String cjh;

    private String zxcclpzh;

    private String gyh;

    private static final long serialVersionUID = 1L;

    public String getWpbh() {
        return wpbh;
    }

    public void setWpbh(String wpbh) {
        this.wpbh = wpbh;
    }

    public String getDjh() {
        return djh;
    }

    public void setDjh(String djh) {
        this.djh = djh;
    }

    public String getCjh() {
        return cjh;
    }

    public void setCjh(String cjh) {
        this.cjh = cjh;
    }

    public String getZxcclpzh() {
        return zxcclpzh;
    }

    public void setZxcclpzh(String zxcclpzh) {
        this.zxcclpzh = zxcclpzh;
    }

    public String getGyh() {
        return gyh;
    }

    public void setGyh(String gyh) {
        this.gyh = gyh;
    }

    @Override
    public String toString() {
        return "LedenCollectGBicycle{" +
                "wpbh='" + wpbh + '\'' +
                ", djh='" + djh + '\'' +
                ", cjh='" + cjh + '\'' +
                ", zxcclpzh='" + zxcclpzh + '\'' +
                ", gyh='" + gyh + '\'' +
                '}';
    }
}