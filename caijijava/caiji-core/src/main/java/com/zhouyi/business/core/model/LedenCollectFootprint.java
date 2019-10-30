package com.zhouyi.business.core.model;

import java.io.Serializable;
import java.util.Date;

public class LedenCollectFootprint implements Serializable {
    private String pkId;

    private String ryjcxxcjbh;

    private String zjbwdm;

    private String zjlxdm;

    private String xdhwdm;

    private String xydm;

    private String xxzldf;

    private String deletag;

    private String annex;

    private String createUserId;

    private Date createDatetime;

    private String updateUserId;

    private Date updateDatetime;

    private byte[] zjsj;

    private String zjzp;

    private String zjbwmc;

    private String zjlxmc;

    private String creagteName;

    private String updateName;

    private static final long serialVersionUID = 1L;

    public String getCreagteName() {
        return creagteName;
    }

    public void setCreagteName(String creagteName) {
        this.creagteName = creagteName;
    }

    public String getUpdateName() {
        return updateName;
    }

    public void setUpdateName(String updateName) {
        this.updateName = updateName;
    }

    public String getZjbwmc() {
        return zjbwmc;
    }

    public void setZjbwmc(String zjbwmc) {
        this.zjbwmc = zjbwmc;
    }

    public String getZjlxmc() {
        return zjlxmc;
    }

    public void setZjlxmc(String zjlxmc) {
        this.zjlxmc = zjlxmc;
    }

    public String getZjzp() {
        return zjzp;
    }

    public void setZjzp(String zjzp) {
        this.zjzp = zjzp;
    }

    public String getPkId() {
        return pkId;
    }

    public void setPkId(String pkId) {
        this.pkId = pkId;
    }

    public String getRyjcxxcjbh() {
        return ryjcxxcjbh;
    }

    public void setRyjcxxcjbh(String ryjcxxcjbh) {
        this.ryjcxxcjbh = ryjcxxcjbh;
    }

    public String getZjbwdm() {
        return zjbwdm;
    }

    public void setZjbwdm(String zjbwdm) {
        this.zjbwdm = zjbwdm;
    }

    public String getZjlxdm() {
        return zjlxdm;
    }

    public void setZjlxdm(String zjlxdm) {
        this.zjlxdm = zjlxdm;
    }

    public String getXdhwdm() {
        return xdhwdm;
    }

    public void setXdhwdm(String xdhwdm) {
        this.xdhwdm = xdhwdm;
    }

    public String getXydm() {
        return xydm;
    }

    public void setXydm(String xydm) {
        this.xydm = xydm;
    }

    public String getXxzldf() {
        return xxzldf;
    }

    public void setXxzldf(String xxzldf) {
        this.xxzldf = xxzldf;
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

    public byte[] getZjsj() {
        return zjsj;
    }

    public void setZjsj(byte[] zjsj) {
        this.zjsj = zjsj;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", pkId=").append(pkId);
        sb.append(", ryjcxxcjbh=").append(ryjcxxcjbh);
        sb.append(", zjbwdm=").append(zjbwdm);
        sb.append(", zjlxdm=").append(zjlxdm);
        sb.append(", xdhwdm=").append(xdhwdm);
        sb.append(", xydm=").append(xydm);
        sb.append(", xxzldf=").append(xxzldf);
        sb.append(", deletag=").append(deletag);
        sb.append(", annex=").append(annex);
        sb.append(", createUserId=").append(createUserId);
        sb.append(", createDatetime=").append(createDatetime);
        sb.append(", updateUserId=").append(updateUserId);
        sb.append(", updateDatetime=").append(updateDatetime);
        sb.append(", zjsj=").append(zjsj);
        sb.append("]");
        return sb.toString();
    }
}