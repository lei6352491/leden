package com.zhouyi.business.core.dao;

import com.zhouyi.business.core.model.SysBaseConf;
import com.zhouyi.business.core.vo.SysBaseConfVo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysBaseConfMapper extends BaseMapper<SysBaseConf,String>{

    int deleteByPrimaryKey(String pkId);

    int insert(SysBaseConf record);

    int insertSelective(SysBaseConf record);

    SysBaseConf selectByPrimaryKey(String pkId);

    int updateByPrimaryKeySelective(SysBaseConf record);

    int updateByPrimaryKey(SysBaseConf record);

    int findTotal(SysBaseConfVo sysBaseConfVo);
}