package com.zhouyi.business.core.service;

import com.zhouyi.business.core.dao.SysRoleMenuMapper;
import com.zhouyi.business.core.exception.BusinessException;
import com.zhouyi.business.core.model.SysRoleMenu;
import com.zhouyi.business.core.vo.SysRoleMenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SysRoleMenuServiceImpl implements SysRoleMenuService {

    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    private static final Integer ROW_NUMBER_ZERO = 0;

    @Override
    public SysRoleMenu findSysRoleMenuById(String id) {
        return sysRoleMenuMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<SysRoleMenu> findSysRoleMenuListBySysRoleMenu(SysRoleMenuVo sysRoleMenuVo) {
        if (sysRoleMenuVo.getPage() == null || sysRoleMenuVo.getPage() < 1){
            sysRoleMenuVo.setPage(1);
        }
        if (sysRoleMenuVo.getSize() == null || sysRoleMenuVo.getSize() < 1){
            sysRoleMenuVo.setSize(20);
        }
        int stratNo = (sysRoleMenuVo.getPage() - 1) * sysRoleMenuVo.getSize() + 1;
        sysRoleMenuVo.setStartNo(stratNo);
        int endNo = stratNo + sysRoleMenuVo.getSize();
        sysRoleMenuVo.setEndNo(endNo);

        List<SysRoleMenu> roleMenuList = sysRoleMenuMapper.selectByModel(sysRoleMenuVo);
        return roleMenuList;
    }

    @Override
    @Transactional
    public void saveSysRoleMenu(SysRoleMenu sysRoleMenu) {
        int rowNumber = 0;
        try{
            rowNumber = sysRoleMenuMapper.insertSelective(sysRoleMenu);
        }catch (RuntimeException e){
            e.fillInStackTrace();
            throw new BusinessException(505,"保存重复!");
        }
        if (rowNumber == ROW_NUMBER_ZERO){
            throw new BusinessException(501,"保存失败!");
        }
    }

    @Override
    @Transactional
    public void updateSysRoleMenu(SysRoleMenu sysRoleMenu) {
        int rowNumber = sysRoleMenuMapper.updateByPrimaryKeySelective(sysRoleMenu);
        if (rowNumber == ROW_NUMBER_ZERO){
            throw new BusinessException(502,"更新失败!");
        }
    }

    @Override
    @Transactional
    public void deleteSysRoleMenu(String id) {
        SysRoleMenu sysRoleMenu = sysRoleMenuMapper.selectByPrimaryKey(id);
        if (sysRoleMenu != null){
            int rowNumber = sysRoleMenuMapper.deleteByPrimaryKey(id);
            if (rowNumber == ROW_NUMBER_ZERO){
                throw new BusinessException(503,"删除失败!");
            }
        }
    }

    /**
     * 根据角色id删除中间表信息
     * */
    @Override
    @Transactional
    public void deleteRoleMenuByRoleId(String roleId){
        sysRoleMenuMapper.deleteRoleMenuByRoleId(roleId);
    }

    /**
     * 根据菜单id删除中间表信息
     * */
    @Override
    @Transactional
    public void deleteRoleMenuByMenuId(String menuId){
        sysRoleMenuMapper.deleteRoleMenuByMenuId(menuId);
    }

    /**
     * 根据角色id查询中间表信息
     * */
    @Override
    public List<SysRoleMenu> getSysRoleMenuByRoleId(String roleId){
        return sysRoleMenuMapper.getSysRoleMenuByRoleId(roleId);
    }

    /**
     * 根据菜单id查询中间表信息
     * */
    @Override
    public List<SysRoleMenu> getSysRoleMenuByMenuId(String menuId) {
        return sysRoleMenuMapper.getSysRoleMenuByMenuId(menuId);
    }

    @Override
    public int findTotal(SysRoleMenuVo sysRoleMenuVo) {
        return sysRoleMenuMapper.findTotal(sysRoleMenuVo);
    }
}
