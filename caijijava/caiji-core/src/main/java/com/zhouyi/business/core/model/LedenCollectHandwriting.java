package com.zhouyi.business.core.model;

import java.io.Serializable;
import java.util.Date;

public class LedenCollectHandwriting implements Serializable {
    private String pkId;

    private String ryjcxxcjbh;

    private String bjwjgsdm;

    private String bjfldm;

    private String deletag;

    private String annex;

    private String createUserId;

    private Date createDatetime;

    private String updateUserId;

    private Date updateDatetime;

    private byte[] bjsj;

    private String bjzp;

    private static final long serialVersionUID = 1L;

    public String getBjzp() {
        return bjzp;
    }

    public void setBjzp(String bjzp) {
        this.bjzp = bjzp;
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

    public String getBjwjgsdm() {
        return bjwjgsdm;
    }

    public void setBjwjgsdm(String bjwjgsdm) {
        this.bjwjgsdm = bjwjgsdm;
    }

    public String getBjfldm() {
        return bjfldm;
    }

    public void setBjfldm(String bjfldm) {
        this.bjfldm = bjfldm;
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

    public byte[] getBjsj() {
        return bjsj;
    }

    public void setBjsj(byte[] bjsj) {
        this.bjsj = bjsj;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", pkId=").append(pkId);
        sb.append(", ryjcxxcjbh=").append(ryjcxxcjbh);
        sb.append(", bjwjgsdm=").append(bjwjgsdm);
        sb.append(", bjfldm=").append(bjfldm);
        sb.append(", deletag=").append(deletag);
        sb.append(", annex=").append(annex);
        sb.append(", createUserId=").append(createUserId);
        sb.append(", createDatetime=").append(createDatetime);
        sb.append(", updateUserId=").append(updateUserId);
        sb.append(", updateDatetime=").append(updateDatetime);
        sb.append(", bjsj=").append(bjsj);
        sb.append("]");
        return sb.toString();
    }
}