package com.zhouyi.business.core.model;

import java.util.ArrayList;
import java.util.List;

public class MenuTree  {

    private String roleId;

    private String roleName;

    private List<MenuTreeNode> menuTreeNodes = new ArrayList<>();

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<MenuTreeNode> getMenuTreeNodes() {
        return menuTreeNodes;
    }

    public void setMenuTreeNodes(List<MenuTreeNode> menuTreeNodes) {
        this.menuTreeNodes = menuTreeNodes;
    }


}
