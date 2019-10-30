package com.zhouyi.business.core.service;

import com.zhouyi.business.core.dao.SysMenuMapper;
import com.zhouyi.business.core.dao.SysRoleMapper;
import com.zhouyi.business.core.exception.BusinessException;
import com.zhouyi.business.core.model.ResponseRoleMenu;
import com.zhouyi.business.core.model.SysMenu;
import com.zhouyi.business.core.model.SysRole;
import com.zhouyi.business.core.model.SysRoleMenu;
import com.zhouyi.business.core.vo.SysMenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class SysMenuServiceImpl implements SysMenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    @Autowired
    private SysRoleMapper sysRoleMapper;

    private static final Integer ROW_NUMBER_ZERO = 0;

    @Override
    public ResponseRoleMenu findSysMenuById(String id) {
        SysMenu sysMenu = sysMenuMapper.selectByPrimaryKey(id);
        //查询该菜单下的角色
        List<SysRoleMenu> roleMenus = sysRoleMenuService.getSysRoleMenuByMenuId(id);
        Set<SysRole> menus = new HashSet<SysRole>();
        if (roleMenus != null){
            for (SysRoleMenu sysRoleMenu : roleMenus){
                SysRole sysRole = sysRoleMapper.selectByPrimaryKey(sysRoleMenu.getRoleId());
                menus.add(sysRole);
            }
        }
        ResponseRoleMenu responseRoleMenu = new ResponseRoleMenu();
        responseRoleMenu.getMenus().add(sysMenu);
        responseRoleMenu.setRoles(menus);
        return responseRoleMenu;
    }

    @Override
    public List<SysMenu> findSysMenuListBySysMenu(SysMenuVo sysMenuVo) {
        if (sysMenuVo.getPage() == null || sysMenuVo.getPage() < 1){
            sysMenuVo.setPage(1);
        }
        if (sysMenuVo.getSize() == null || sysMenuVo.getSize() < 1){
            sysMenuVo.setSize(20);
        }
        int stratNo = (sysMenuVo.getPage() - 1) * sysMenuVo.getSize() + 1;
        sysMenuVo.setStartNo(stratNo);
        int endNo = stratNo + sysMenuVo.getSize();
        sysMenuVo.setEndNo(endNo);

        List<SysMenu> sysMenus = sysMenuMapper.selectByModel(sysMenuVo);
        return sysMenus;
    }

    @Override
    @Transactional
    public void saveSysMenu(SysMenu sysMenu) {
        int rowNumber = 0;
        try{
            rowNumber = sysMenuMapper.insertSelective(sysMenu);
        }catch (RuntimeException e){
            e.fillInStackTrace();
            throw new BusinessException(505,"保存重复!");
        }

        if (rowNumber == ROW_NUMBER_ZERO){
            throw new BusinessException(501,"保存失败!");
        }
        //保存中间表信息...
    }

    @Override
    public void updateSysMenu(SysMenu sysMenu) {
        int rowNumber = sysMenuMapper.updateByPrimaryKeySelective(sysMenu);
        if (rowNumber == ROW_NUMBER_ZERO){
            throw new BusinessException(502,"更新失败!");
        }
        //更新中间表的信息...
    }

    @Override
    public void deleteSysMenu(String id) {
        SysMenu sysMenu = sysMenuMapper.selectByPrimaryKey(id);
        if (sysMenu != null){
            //删除该菜单下的中间表信息...
            sysRoleMenuService.deleteRoleMenuByMenuId(id);
            int rowNumber = sysMenuMapper.deleteByPrimaryKey(id);
            if (rowNumber == ROW_NUMBER_ZERO){
                throw new BusinessException(503,"删除失败!");
            }
        }
    }

    @Override
    public int findTotal(SysMenuVo sysMenuVo) {
        return sysMenuMapper.findTotal(sysMenuVo);
    }


}
