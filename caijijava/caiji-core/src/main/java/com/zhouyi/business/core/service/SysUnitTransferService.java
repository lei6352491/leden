package com.zhouyi.business.core.service;

import com.zhouyi.business.core.model.PageData;
import com.zhouyi.business.core.model.SysUnitTransfer;

import java.util.Map;

/**
 * 系统部门转换服务接口
 */
public interface SysUnitTransferService {

    /**
     * 根据transferCode移除调度记录
     * @param transferCode
     * @return
     */
    Boolean removeByPrimaryKey(String transferCode);

    /**
     * 完整添加纪录
     * @param sysUnitTransfer
     * @return
     */
    Boolean addSysUnitTransfer(SysUnitTransfer sysUnitTransfer);

    /**
     * 选择性添加转换记录
     * @param sysUnitTransfer
     * @return
     */
    Boolean addSysUnitTransferSelective(SysUnitTransfer sysUnitTransfer);


    /**
     * 根据转换记录字符获取转换信息
     * @param transferCode
     * @return
     */
    SysUnitTransfer searchTransferByPrimaryKey(String transferCode);


    /**
     * 选择性修改转换信息
     * @param record
     * @return
     */
    Boolean modifyByPrimaryKeySelective(SysUnitTransfer record);


    /**
     * 完整修改转换记录
     * @param sysUnitTransfer
     * @return
     */
    Boolean modifyTransfer(SysUnitTransfer sysUnitTransfer);


    /**
     * 根据条件分页获取部门调动记录
     * @param conditions
     * @return
     */
    PageData<SysUnitTransfer> searchUnitTransfersPage(Map<String,Object> conditions);
}
