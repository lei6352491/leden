package com.zhouyi.business.core.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class LedenCollectGoods<T> implements Serializable {
    private String wpbh;

    private String ryjcxxcjbh;

    private String xdwpWpmc;

    private String xdwpWplxdm;

    private String wpbzh;

    private String wptzms;

    private BigDecimal wpjzrmby;

    private String wpysdm;

    private String wpysdmbcms;

    private BigDecimal wpsl;

    private BigDecimal wpzl;

    private Date wpgzsjRqsj;

    private BigDecimal objectType;

    private String deletag;

    private String annex;

    private String wpysmc;

    private String createUserId;

    private Date createDatetime;

    private String updateUserId;

    private Date updateDatetime;

    private List<LedenCollectGPhoto> gPhotos;

    private T objectTypeModel;

    public String getWpysmc() {
        return wpysmc;
    }

    public void setWpysmc(String wpysmc) {
        this.wpysmc = wpysmc;
    }

    public T getObjectTypeModel() {
        return objectTypeModel;
    }

    public void setObjectTypeModel(T objectTypeModel) {
        this.objectTypeModel = objectTypeModel;
    }

    public List<LedenCollectGPhoto> getgPhotos() {
        return gPhotos;
    }

    private String wplbmc;

    public String getWplbmc() {
        return wplbmc;
    }

    public void setWplbmc(String wplbmc) {
        this.wplbmc = wplbmc;
    }

    public void setgPhotos(List<LedenCollectGPhoto> gPhotos) {
        this.gPhotos = gPhotos;
    }

    private static final long serialVersionUID = 1L;

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

    public String getXdwpWpmc() {
        return xdwpWpmc;
    }

    public void setXdwpWpmc(String xdwpWpmc) {
        this.xdwpWpmc = xdwpWpmc;
    }

    public String getXdwpWplxdm() {
        return xdwpWplxdm;
    }

    public void setXdwpWplxdm(String xdwpWplxdm) {
        this.xdwpWplxdm = xdwpWplxdm;
    }

    public String getWpbzh() {
        return wpbzh;
    }

    public void setWpbzh(String wpbzh) {
        this.wpbzh = wpbzh;
    }

    public String getWptzms() {
        return wptzms;
    }

    public void setWptzms(String wptzms) {
        this.wptzms = wptzms;
    }

    public BigDecimal getWpjzrmby() {
        return wpjzrmby;
    }

    public void setWpjzrmby(BigDecimal wpjzrmby) {
        this.wpjzrmby = wpjzrmby;
    }

    public String getWpysdm() {
        return wpysdm;
    }

    public void setWpysdm(String wpysdm) {
        this.wpysdm = wpysdm;
    }

    public String getWpysdmbcms() {
        return wpysdmbcms;
    }

    public void setWpysdmbcms(String wpysdmbcms) {
        this.wpysdmbcms = wpysdmbcms;
    }

    public BigDecimal getWpsl() {
        return wpsl;
    }

    public void setWpsl(BigDecimal wpsl) {
        this.wpsl = wpsl;
    }

    public BigDecimal getWpzl() {
        return wpzl;
    }

    public void setWpzl(BigDecimal wpzl) {
        this.wpzl = wpzl;
    }

    public Date getWpgzsjRqsj() {
        return wpgzsjRqsj;
    }

    public void setWpgzsjRqsj(Date wpgzsjRqsj) {
        this.wpgzsjRqsj = wpgzsjRqsj;
    }

    public BigDecimal getObjectType() {
        return objectType;
    }

    public void setObjectType(BigDecimal objectType) {
        this.objectType = objectType;
    }

    public String getDeletag() {
        return deletag;
    }

    public void setDeletag(String deletag) {
        this.deletag = deletag;
    }

    public String getAnnex() {
        return annex;
    }

    public void setAnnex(String annex) {
        this.annex = annex;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }

    public Date getUpdateDatetime() {
        return updateDatetime;
    }

    public void setUpdateDatetime(Date updateDatetime) {
        this.updateDatetime = updateDatetime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", wpbh=").append(wpbh);
        sb.append(", ryjcxxcjbh=").append(ryjcxxcjbh);
        sb.append(", xdwpWpmc=").append(xdwpWpmc);
        sb.append(", xdwpWplxdm=").append(xdwpWplxdm);
        sb.append(", wpbzh=").append(wpbzh);
        sb.append(", wptzms=").append(wptzms);
        sb.append(", wpjzrmby=").append(wpjzrmby);
        sb.append(", wpysdm=").append(wpysdm);
        sb.append(", wpysdmbcms=").append(wpysdmbcms);
        sb.append(", wpsl=").append(wpsl);
        sb.append(", wpzl=").append(wpzl);
        sb.append(", wpgzsjRqsj=").append(wpgzsjRqsj);
        sb.append(", objectType=").append(objectType);
        sb.append(", deletag=").append(deletag);
        sb.append(", annex=").append(annex);
        sb.append(", createUserId=").append(createUserId);
        sb.append(", createDatetime=").append(createDatetime);
        sb.append(", updateUserId=").append(updateUserId);
        sb.append(", updateDatetime=").append(updateDatetime);
        sb.append("]");
        return sb.toString();
    }
}