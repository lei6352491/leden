package com.zhouyi.business.core.vo;

import com.zhouyi.business.core.model.MenuTreeNode;

import java.util.ArrayList;
import java.util.List;

public class ResponseMenuTree {

    private List<MenuTreeNode> menuTreeNodes = new ArrayList<>();

    public List<MenuTreeNode> getMenuTreeNodes() {
        return menuTreeNodes;
    }

    public void setMenuTreeNodes(List<MenuTreeNode> menuTreeNodes) {
        this.menuTreeNodes = menuTreeNodes;
    }
}
