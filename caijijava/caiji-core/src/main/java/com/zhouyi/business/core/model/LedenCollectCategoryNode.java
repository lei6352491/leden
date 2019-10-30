package com.zhouyi.business.core.model;

import java.io.Serializable;

public class LedenCollectCategoryNode implements Serializable {
    private String pkId;

    private String categoryId;

    private String nodeCode;

    private String unitCode;

    private String isSkip;

    private String nodeOrd;

    private String userCode;

    private String PlugInId;

    private String equipmentCode;

    private static final long serialVersionUID = 1L;

    public String getPkId() {
        return pkId;
    }

    public void setPkId(String pkId) {
        this.pkId = pkId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getNodeCode() {
        return nodeCode;
    }

    public void setNodeCode(String nodeCode) {
        this.nodeCode = nodeCode;
    }

    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    public String getIsSkip() {
        return isSkip;
    }

    public void setIsSkip(String isSkip) {
        this.isSkip = isSkip;
    }

    public String getNodeOrd() {
        return nodeOrd;
    }

    public void setNodeOrd(String nodeOrd) {
        this.nodeOrd = nodeOrd;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getPlugInId() {
        return PlugInId;
    }

    public void setPlugInId(String plugInId) {
        PlugInId = plugInId;
    }

    public String getEquipmentCode() {
        return equipmentCode;
    }

    public void setEquipmentCode(String equipmentCode) {
        this.equipmentCode = equipmentCode;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", pkId=").append(pkId);
        sb.append(", categoryId=").append(categoryId);
        sb.append(", nodeCode=").append(nodeCode);
        sb.append(", unitCode=").append(unitCode);
        sb.append(", isSkip=").append(isSkip);
        sb.append(", nodeOrd=").append(nodeOrd);
        sb.append("]");
        return sb.toString();
    }
}