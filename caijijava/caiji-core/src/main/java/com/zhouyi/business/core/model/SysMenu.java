package com.zhouyi.business.core.model;

import java.io.Serializable;

public class SysMenu implements Serializable {
    private String menuCode;

    private String menuName;

    private String menuAction;

    private String deleteFlag;

    private String iconLocation;

    private String upperMenuCode;

    private Short ord;

    private static final long serialVersionUID = 1L;

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuAction() {
        return menuAction;
    }

    public void setMenuAction(String menuAction) {
        this.menuAction = menuAction;
    }

    public String getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public String getIconLocation() {
        return iconLocation;
    }

    public void setIconLocation(String iconLocation) {
        this.iconLocation = iconLocation;
    }

    public String getUpperMenuCode() {
        return upperMenuCode;
    }

    public void setUpperMenuCode(String upperMenuCode) {
        this.upperMenuCode = upperMenuCode;
    }

    public Short getOrd() {
        return ord;
    }

    public void setOrd(Short ord) {
        this.ord = ord;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", menuCode=").append(menuCode);
        sb.append(", menuName=").append(menuName);
        sb.append(", menuAction=").append(menuAction);
        sb.append(", deleteFlag=").append(deleteFlag);
        sb.append(", iconLocation=").append(iconLocation);
        sb.append(", upperMenuCode=").append(upperMenuCode);
        sb.append(", ord=").append(ord);
        sb.append("]");
        return sb.toString();
    }
}