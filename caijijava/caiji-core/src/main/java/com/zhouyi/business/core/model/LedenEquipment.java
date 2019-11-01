package com.zhouyi.business.core.model;

import java.io.Serializable;
import java.util.Date;

public class LedenEquipment implements Serializable {
    private String pkId;

    private String equipmentCode;

    private String chamberType;

    private String equipmentName;

    private String factoryNum;

    private String equipmentVersion;

    private String manufacturer;

    private Date manufacturerDate;

    private String equipmentIp;

    private String equipmentMac;

    private String status;

    private String unitCode;

    private String deleteFlag;

    private String annex;

    private String createUserId;

    private Date createDatetime;

    private String updateUserId;

    private Date updateDatetime;

    private String unitName;

    private String provincialEquipmentCode;

    public String getProvincialEquipmentCode() {
        return provincialEquipmentCode;
    }

    public void setProvincialEquipmentCode(String provincialEquipmentCode) {
        this.provincialEquipmentCode = provincialEquipmentCode;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    private static final long serialVersionUID = 1L;

    public String getPkId() {
        return pkId;
    }

    public void setPkId(String pkId) {
        this.pkId = pkId;
    }

    public String getEquipmentCode() {
        return equipmentCode;
    }

    public void setEquipmentCode(String equipmentCode) {
        this.equipmentCode = equipmentCode;
    }

    public String getChamberType() {
        return chamberType;
    }

    public void setChamberType(String chamberType) {
        this.chamberType = chamberType;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public String getFactoryNum() {
        return factoryNum;
    }

    public void setFactoryNum(String factoryNum) {
        this.factoryNum = factoryNum;
    }

    public String getEquipmentVersion() {
        return equipmentVersion;
    }

    public void setEquipmentVersion(String equipmentVersion) {
        this.equipmentVersion = equipmentVersion;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Date getManufacturerDate() {
        return manufacturerDate;
    }

    public void setManufacturerDate(Date manufacturerDate) {
        this.manufacturerDate = manufacturerDate;
    }

    public String getEquipmentIp() {
        return equipmentIp;
    }

    public void setEquipmentIp(String equipmentIp) {
        this.equipmentIp = equipmentIp;
    }

    public String getEquipmentMac() {
        return equipmentMac;
    }

    public void setEquipmentMac(String equipmentMac) {
        this.equipmentMac = equipmentMac;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
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
        sb.append(", equipmentCode=").append(equipmentCode);
        sb.append(", chamberType=").append(chamberType);
        sb.append(", equipmentName=").append(equipmentName);
        sb.append(", factoryNum=").append(factoryNum);
        sb.append(", equipmentVersion=").append(equipmentVersion);
        sb.append(", manufacturer=").append(manufacturer);
        sb.append(", manufacturerDate=").append(manufacturerDate);
        sb.append(", equipmentIp=").append(equipmentIp);
        sb.append(", equipmentMac=").append(equipmentMac);
        sb.append(", status=").append(status);
        sb.append(", unitCode=").append(unitCode);
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