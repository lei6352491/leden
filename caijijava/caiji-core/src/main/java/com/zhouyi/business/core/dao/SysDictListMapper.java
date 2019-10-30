package com.zhouyi.business.core.dao;

import com.zhouyi.business.core.model.SysDictList;
import com.zhouyi.business.core.model.SysRole;
import com.zhouyi.business.core.vo.SysDictListVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysDictListMapper extends BaseMapper<SysDictList,String>{

    int deleteByPrimaryKey(String pkId);

    int insert(SysDictList record);

    int insertSelective(SysDictList record);

    SysDictList selectByPrimaryKey(String pkId);

    List<SysDictList> findSysDictListById(String code);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);

    List<SysDictList> testList(SysDictListVo sysDictListVo);

    int findTotal(SysDictListVo sysDictListVo);

    List<SysDictList> findDictByBean(SysDictList sysDictList);
}