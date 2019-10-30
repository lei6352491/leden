package com.zhouyi.business.core.model;

import java.io.Serializable;
import java.util.Date;

public class SysLogData implements Serializable {
    private String pkId;

    private String operateType;

    private String objectId;

    private String addreIp;

    private String userId;

    private String modifyBefore;

    private String modifyAfter;

    private Date createDatetime;

    private String userName;

    private String unitName;

    private String menuName;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    private static final long serialVersionUID = 1L;

    public String getPkId() {
        return pkId;
    }

    public void setPkId(String pkId) {
        this.pkId = pkId;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getAddreIp() {
        return addreIp;
    }

    public void setAddreIp(String addreIp) {
        this.addreIp = addreIp;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getModifyBefore() {
        return modifyBefore;
    }

    public void setModifyBefore(String modifyBefore) {
        this.modifyBefore = modifyBefore;
    }

    public String getModifyAfter() {
        return modifyAfter;
    }

    public void setModifyAfter(String modifyAfter) {
        this.modifyAfter = modifyAfter;
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", pkId=").append(pkId);
        sb.append(", operateType=").append(operateType);
        sb.append(", objectId=").append(objectId);
        sb.append(", addreIp=").append(addreIp);
        sb.append(", userId=").append(userId);
        sb.append(", modifyBefore=").append(modifyBefore);
        sb.append(", modifyAfter=").append(modifyAfter);
        sb.append(", createDatetime=").append(createDatetime);
        sb.append("]");
        return sb.toString();
    }
}