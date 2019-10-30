package com.zhouyi.business.core.service;

import com.zhouyi.business.core.model.*;
import com.zhouyi.business.core.vo.SysRoleVo;

import java.util.List;

public interface SysRoleService {

    Response findSysRoleById(String id);

    List<SysRole> findSysRoleListBySysRole(SysRoleVo sysRoleVo);

    void saveSysRole(SysRole sysRole);

    void updateSysRole(SysRole sysRole);

    void deleteSysRole(String id);

    int findTotal(SysRoleVo sysRoleVo);

    MenuTreeNode selectMenuTreeByUserCode(String userCode);

    MenuTreeNode selectMenuTreeByRoleId(String roleId);

    List<MenuTreeNode> selectMenuTreeTableByRoleId(String roleId);

    Response updateRoleMenu(RoleMenuRequest roleMenuRequest);

    Response saveRoleMenu(RoleMenuRequest roleMenuRequest);
}
