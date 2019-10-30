package com.zhouyi.business.core.dao;

import com.zhouyi.business.core.model.SysMenu;
import com.zhouyi.business.core.vo.SysMenuVo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysMenuMapper extends BaseMapper<SysMenu,String>{
    int deleteByPrimaryKey(String menuCode);

    int insert(SysMenu record);

    int insertSelective(SysMenu record);

    SysMenu selectByPrimaryKey(String menuCode);

    int updateByPrimaryKeySelective(SysMenu record);

    int updateByPrimaryKey(SysMenu record);

    int findTotal(SysMenuVo sysMenuVo);
}