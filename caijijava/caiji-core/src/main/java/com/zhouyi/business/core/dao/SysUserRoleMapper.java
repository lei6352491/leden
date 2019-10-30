package com.zhouyi.business.core.dao;

import com.zhouyi.business.core.model.SysUserRole;
import com.zhouyi.business.core.vo.SysUserRoleVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysUserRoleMapper extends BuffBaseMapper<SysUserRole, SysUserRoleVo>{
    List<String> getRoleIdByUserCode(@Param("userCode") String userCode, @Param("status") String status);

    int deleteRoleByUserId(String userId);

}