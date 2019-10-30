package com.zhouyi.business.core.service;

import com.zhouyi.business.core.exception.BusinessException;
import com.zhouyi.business.core.model.PageData;
import com.zhouyi.business.core.model.SysUser;
import com.zhouyi.business.core.vo.SysUserVo;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 系统用户接口
 */
public interface SysUserService {

    /**
     * 删除系统用户
     * @param userCode 用户编码
     * @return
     */
    Boolean deleteByPrimaryKey(String userCode);

    /**
     * 完整新增用户信息
     * @param sysUser
     * @return
     */
    Boolean insert(SysUser sysUser);

    /**
     * 根据用户编码查询用户信息
     * @param userCode
     * @return
     */
    SysUser searchSysUser(String userCode);

    /**
     * 完整修改用户信息
     * @param sysUser
     * @return
     */
    Boolean modifySysUser(SysUser sysUser);

    /**
     * 选择性修改用户信息
     * @param sysUserVo
     * @return
     */
    Boolean modifySysUserSelective(SysUserVo sysUserVo) throws Exception;

    /**
     * 用户登陆业务
     * @param userAccount
     * @param userPassword
     * @return
     */
    SysUser login(String userAccount, String userPassword, HttpServletResponse response,String equipmentMac) throws BusinessException;


    /**
     * 根据条件分页查询系统用户信息
     * @param conditions
     * @return
     */
    PageData<SysUser> searchSysUserPage(Map<String,Object> conditions);


    /**
     * 用户注册业务
     * @param sysUserVo
     * @return
     */
    Boolean sysUserRegister(SysUserVo sysUserVo) throws Exception;


    /**
     * 用户账户查重
     * @param userAccount
     * @return
     */
    boolean checkUserAccount(String userAccount);


    /**
     * 用户密码检验
     * @param userAccount
     * @param userPassword
     * @return
     */
    boolean checkUserPassword(String userAccount,String userPassword);
}
