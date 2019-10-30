package com.zhouyi.business.core.service;

import com.zhouyi.business.core.model.ResponseRoleMenu;
import com.zhouyi.business.core.model.SysMenu;
import com.zhouyi.business.core.vo.SysMenuVo;

import java.util.List;

public interface SysMenuService {

    ResponseRoleMenu findSysMenuById(String id);

    List<SysMenu> findSysMenuListBySysMenu(SysMenuVo sysMenuVo);

    void saveSysMenu(SysMenu sysMenu);

    void updateSysMenu(SysMenu sysMenu);

    void deleteSysMenu(String id);

    int findTotal(SysMenuVo sysMenuVo);

}
