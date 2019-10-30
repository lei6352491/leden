package com.zhouyi.business.core.model;

import java.io.Serializable;
import java.util.Date;

public class LedenEquipmentPlugIn implements Serializable {
    private String pkId;

    private String nodeCode;

    private String manufacturerName;

    private String manufacturerCode;

    private String plugInClassid;

    private String plugInVersion;

    private String deleteFlag;

    private String annex;

    private String createUserId;

    private Date createDatetime;

    private String updateUserId;

    private Date updateDatetime;

    private static final long serialVersionUID = 1L;

    public String getPkId() {
        return pkId;
    }

    public void setPkId(String pkId) {
        this.pkId = pkId;
    }

    public String getNodeCode() {
        return nodeCode;
    }

    public void setNodeCode(String nodeCode) {
        this.nodeCode = nodeCode;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    public String getManufacturerCode() {
        return manufacturerCode;
    }

    public void setManufacturerCode(String manufacturerCode) {
        this.manufacturerCode = manufacturerCode;
    }

    public String getPlugInClassid() {
        return plugInClassid;
    }

    public void setPlugInClassid(String plugInClassid) {
        this.plugInClassid = plugInClassid;
    }

    public String getPlugInVersion() {
        return plugInVersion;
    }

    public void setPlugInVersion(String plugInVersion) {
        this.plugInVersion = plugInVersion;
    }

    public String getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag;
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
        sb.append(", pkId=").append(pkId);
        sb.append(", nodeCode=").append(nodeCode);
        sb.append(", manufacturerName=").append(manufacturerName);
        sb.append(", manufacturerCode=").append(manufacturerCode);
        sb.append(", plugInClassid=").append(plugInClassid);
        sb.append(", plugInVersion=").append(plugInVersion);
        sb.append(", deleteFlag=").append(deleteFlag);
        sb.append(", annex=").append(annex);
        sb.append(", createUserId=").append(createUserId);
        sb.append(", createDatetime=").append(createDatetime);
        sb.append(", updateUserId=").append(updateUserId);
        sb.append(", updateDatetime=").append(updateDatetime);
        sb.append("]");
        return sb.toString();
    }
}