package com.zhouyi.business.core.service;

import com.zhouyi.business.core.model.PageData;
import com.zhouyi.business.core.model.SysUserTransfer;

import java.util.Map;

/**
 * 用户调离接口
 */
public interface SysUserTransferService {

    /**
     * 根据记录编码删除调离记录
     * @param transferCode
     * @return
     */
    Boolean removeByKey(String transferCode);

    /**
     * 完整添加用户调离记录
     * @param sysUserTransfer
     * @return
     */
    Boolean addSysUserTransfer(SysUserTransfer sysUserTransfer);

    /**
     * 选择性添加用户调离记录
     * @param sysUserTransfer
     * @return
     */
    Boolean addSysUserTransferSelective(SysUserTransfer sysUserTransfer);

    /**
     * 根据记录编码查询用户调离记录
     * @param transferCode
     * @return
     */
    SysUserTransfer searchSysUserTransfer(String transferCode);


    /**
     * 完整修改用户调离记录
     * @param sysUserTransfer
     * @return
     */
    Boolean modifySysUserTransfer(SysUserTransfer sysUserTransfer);


    /**
     * 选择性修改用户调离记录
     * @param sysUserTransfer
     * @return
     */
    Boolean modifySysUserTransferSelective(SysUserTransfer sysUserTransfer);


    /**
     * 分页获取用户调离业务记录
     * @param conditions
     * @return
     */
    PageData<SysUserTransfer> searchSysUserTransferPage(Map<String,Object> conditions);

}
