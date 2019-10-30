package com.zhouyi.business.core.service;

import com.zhouyi.business.core.model.SysRoleMenu;
import com.zhouyi.business.core.vo.SysRoleMenuVo;

import java.util.List;

public interface SysRoleMenuService {

    SysRoleMenu findSysRoleMenuById(String id);

    List<SysRoleMenu> findSysRoleMenuListBySysRoleMenu(SysRoleMenuVo sysRoleMenuVo);

    void saveSysRoleMenu(SysRoleMenu sysRoleMenu);

    void updateSysRoleMenu(SysRoleMenu sysRoleMenu);

    void deleteSysRoleMenu(String id);

    void deleteRoleMenuByRoleId(String roleId);

    void deleteRoleMenuByMenuId(String menuId);

    List<SysRoleMenu> getSysRoleMenuByRoleId(String roleId);

    List<SysRoleMenu> getSysRoleMenuByMenuId(String menuId);

    int findTotal(SysRoleMenuVo sysRoleMenuVo);
}
