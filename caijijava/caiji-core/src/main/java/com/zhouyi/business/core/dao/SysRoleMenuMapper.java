package com.zhouyi.business.core.dao;

import com.zhouyi.business.core.model.SysRoleMenu;
import com.zhouyi.business.core.vo.SysRoleMenuVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenu,String>{
    int deleteByPrimaryKey(String pkId);

    int deleteRoleMenuByRoleId(String roleId);

    int deleteRoleMenuByMenuId(String menuId);

    int insert(SysRoleMenu record);

    int insertSelective(SysRoleMenu record);

    SysRoleMenu selectByPrimaryKey(String pkId);

    int updateByPrimaryKeySelective(SysRoleMenu record);

    int updateByPrimaryKey(SysRoleMenu record);

    List<SysRoleMenu> getSysRoleMenuByRoleId(String roleId);

    List<SysRoleMenu> getSysRoleMenuByMenuId(String menuId);

    int findTotal(SysRoleMenuVo sysRoleMenuVo);
}