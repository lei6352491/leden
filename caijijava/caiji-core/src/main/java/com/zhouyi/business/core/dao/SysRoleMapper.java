package com.zhouyi.business.core.dao;

import com.zhouyi.business.core.model.MenuTree;
import com.zhouyi.business.core.model.MenuTreeNode;
import com.zhouyi.business.core.model.SysRole;
import com.zhouyi.business.core.vo.SysRoleVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole,String>{
    int deleteByPrimaryKey(String pkId);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    SysRole selectByPrimaryKey(String pkId);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);

    int findTotal(SysRoleVo sysRoleVo);

    List<MenuTreeNode> selectMenuTreeByRoleId(List list);

    List<MenuTreeNode> selectMenuTreeNodeByRoleId(String roleId);

}