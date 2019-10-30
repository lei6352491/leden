package com.zhouyi.business.core.dao;

import com.zhouyi.business.core.model.SysUser;
import com.zhouyi.business.core.vo.SysUserVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface SysUserMapper {
    int deleteByPrimaryKey(String userCode);

    int insert(SysUser record);

    int insertSelective(SysUserVo record);

    SysUser selectByPrimaryKey(String userCode);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

    /**
     * 通过用户名查询用户信息
     * @param userAccount
     * @return
     */
    SysUser getSysUserByUserAccount(String userAccount);

    /**
     * 根据用户账户信息查询用户
      * @param userAccount
     * @return
     */
    SysUser getSysUserByUserAccountReal(String userAccount);


    /**s
     * 根据条件查询用户信息
     * @param conditions
     * @return
     */
    List<SysUser> listSysUserByConditions(Map<String,Object> conditions);


    /**
     * 根据条件查询用户总数
     * @param conditions
     * @return
     */
    int getSysUserCountByConditions(Map<String,Object> conditions);


    /**
     * 获取账户的数量
     * @param userAccount
     * @return
     */
    int getSysUserAccountCount(String userAccount);

    /**
     * 根据用户编码获取用户信息
      * @param userCode
     * @return
     */
    SysUser getSysUserByUserCode(String userCode);
}