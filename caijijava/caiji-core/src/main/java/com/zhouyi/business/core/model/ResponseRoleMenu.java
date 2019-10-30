package com.zhouyi.business.core.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class ResponseRoleMenu implements Serializable {

    private Set<SysRole> roles = new HashSet<SysRole>();

    private Set<SysMenu> menus = new HashSet<SysMenu>();

    public Set<SysRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<SysRole> roles) {
        this.roles = roles;
    }

    public Set<SysMenu> getMenus() {
        return menus;
    }

    public void setMenus(Set<SysMenu> menus) {
        this.menus = menus;
    }
}
