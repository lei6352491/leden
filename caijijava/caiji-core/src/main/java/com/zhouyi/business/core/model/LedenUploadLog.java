package com.zhouyi.business.core.model;

import java.io.Serializable;
import java.util.Date;

public class LedenUploadLog implements Serializable {
    private String pkId;

    private String equipmentId;

    private String nodeSign;

    private String isEmpower;

    private String uploadIp;

    private String ryjcxxcjbh;

    private String uploadStatus;

    private String uploadResultInfo;

    private Date uploadDatetime;

    private static final long serialVersionUID = 1L;

    public String getPkId() {
        return pkId;
    }

    public void setPkId(String pkId) {
        this.pkId = pkId;
    }

    public String getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
    }

    public String getNodeSign() {
        return nodeSign;
    }

    public void setNodeSign(String nodeSign) {
        this.nodeSign = nodeSign;
    }

    public String getIsEmpower() {
        return isEmpower;
    }

    public void setIsEmpower(String isEmpower) {
        this.isEmpower = isEmpower;
    }

    public String getUploadIp() {
        return uploadIp;
    }

    public void setUploadIp(String uploadIp) {
        this.uploadIp = uploadIp;
    }

    public String getRyjcxxcjbh() {
        return ryjcxxcjbh;
    }

    public void setRyjcxxcjbh(String ryjcxxcjbh) {
        this.ryjcxxcjbh = ryjcxxcjbh;
    }

    public String getUploadStatus() {
        return uploadStatus;
    }

    public void setUploadStatus(String uploadStatus) {
        this.uploadStatus = uploadStatus;
    }

    public String getUploadResultInfo() {
        return uploadResultInfo;
    }

    public void setUploadResultInfo(String uploadResultInfo) {
        this.uploadResultInfo = uploadResultInfo;
    }

    public Date getUploadDatetime() {
        return uploadDatetime;
    }

    public void setUploadDatetime(Date uploadDatetime) {
        this.uploadDatetime = uploadDatetime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", pkId=").append(pkId);
        sb.append(", equipmentId=").append(equipmentId);
        sb.append(", nodeSign=").append(nodeSign);
        sb.append(", isEmpower=").append(isEmpower);
        sb.append(", uploadIp=").append(uploadIp);
        sb.append(", ryjcxxcjbh=").append(ryjcxxcjbh);
        sb.append(", uploadStatus=").append(uploadStatus);
        sb.append(", uploadResultInfo=").append(uploadResultInfo);
        sb.append(", uploadDatetime=").append(uploadDatetime);
        sb.append("]");
        return sb.toString();
    }
}