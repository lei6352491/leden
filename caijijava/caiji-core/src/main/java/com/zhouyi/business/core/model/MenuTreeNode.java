package com.zhouyi.business.core.model;

import java.util.ArrayList;
import java.util.List;

public class MenuTreeNode {

    private String pkId;

    private String menuCode;

    private String menuName;

    private String upperMenuCode;

    private Integer isLeaf;

    private String iconLocation;

    private String menuAction;

    private boolean boo = false;

    public String getMenuAction() {
        return menuAction;
    }

    public void setMenuAction(String menuAction) {
        this.menuAction = menuAction;
    }

    public String getIconLocation() {
        return iconLocation;
    }

    public void setIconLocation(String iconLocation) {
        this.iconLocation = iconLocation;
    }

    public Integer getIsLeaf() {
        return isLeaf;
    }

    public void setIsLeaf(Integer isLeaf) {
        this.isLeaf = isLeaf;
    }

    private List<MenuTreeNode> menuTreeNodes = new ArrayList<>();

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

    public String getUpperMenuCode() {
        return upperMenuCode;
    }

    public void setUpperMenuCode(String upperMenuCode) {
        this.upperMenuCode = upperMenuCode;
    }

    public List<MenuTreeNode> getMenuTreeNodes() {
        return menuTreeNodes;
    }

    public void setMenuTreeNodes(List<MenuTreeNode> menuTreeNodes) {
        this.menuTreeNodes = menuTreeNodes;
    }

    public boolean isBoo() {
        return boo;
    }

    public void setBoo(boolean boo) {
        this.boo = boo;
    }

    public String getPkId() {
        return pkId;
    }

    public void setPkId(String pkId) {
        this.pkId = pkId;
    }
}
