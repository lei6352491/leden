package com.zhouyi.business.core.service;

import com.zhouyi.business.core.model.PageData;
import com.zhouyi.business.core.model.SysLogData;

import java.util.Map;

/**
 * 数据日志接口信息
 */
public interface SysLogDataService {

    /**
     * 移除数据日志
     * @param pkId
     * @return
     */
    boolean removeSysLogData(String pkId);

    /**
     * 添加数据日志
     * @param sysLogData
     * @return
     */
    boolean addSysLogData(SysLogData sysLogData);

    /**
     * 选择性添加数据日志
     * @param sysLogData
     * @return
     */
    boolean addSysLogDataSelective(SysLogData sysLogData);

    /**
     * 根据主键查询数据日志
     * @param pkId
     * @return
     */
    SysLogData searchSysLogDataByPkId(String pkId);

    /**
     * 修改数据日志
     * @param sysLogData
     * @return
     */
    boolean modifySysLogData(SysLogData sysLogData);

    /**
     * 选择性修改数据日志
     * @param sysLogData
     * @return
     */
    boolean modifySysLogDataSelective(SysLogData sysLogData);


    /**
     * 获取数据日志对象
     * @param conditions
     * @return
     */
    PageData<SysLogData> searchSysLogDataPage(Map<String,Object> conditions);
}
