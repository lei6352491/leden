package com.zhouyi.business.core.service;

import com.zhouyi.business.core.model.PageData;
import com.zhouyi.business.core.model.SysLogOperation;

import java.util.Map;

/**
 * 系统操作日志接口
 */
public interface SysLogOperationService {

    /**
     * 删除系统日志
     * @param pkId
     * @return
     */
    boolean removeSysLogOperationById(String pkId);

    /**
     * 添加系统日志
     * @param sysLogOperation
     * @return
     */
    boolean addSysLogOperation(SysLogOperation sysLogOperation);

    /**
     * 选择性添加系统日志
     * @param sysLogOperation
     * @return
     */
    boolean addSysLogOperationSelective(SysLogOperation sysLogOperation);

    /**
     * 根据主键id查询系统日志
     * @param pkId
     * @return
     */
    SysLogOperation searchSysLogOperation(String pkId);

    /**
     * 修改系统日志信息
     * @param sysLogOperation
     * @return
     */
    boolean modifySysLogOperation(SysLogOperation sysLogOperation);

    /**
     * 选择性修改系统日志
     * @param sysLogOperation
     * @return
     */
    boolean modifySysLogOperationSelective(SysLogOperation sysLogOperation);


    /**
     * 获取系统操作日志分页对象
     * @param conditions
     * @return
     */
    PageData<SysLogOperation> searchSysLogOperationPage(Map<String,Object> conditions);
}
