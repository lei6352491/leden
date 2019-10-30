package com.zhouyi.business.core.service;

import com.zhouyi.business.core.dao.SysLogDataMapper;
import com.zhouyi.business.core.model.PageData;
import com.zhouyi.business.core.model.SysLogData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author 李秸康
 * @ClassNmae: SysLogDataServiceImpl
 * @Description: 数据日志接口实现
 * @date 2019/6/21 10:28
 * @Version 1.0
 **/
@Service
public class SysLogDataServiceImpl implements SysLogDataService {

    @Autowired
    private SysLogDataMapper sysLogDataMapper;

    /**
     * 删除数据日志
     * @param pkId
     * @return
     */
    @Override
    public boolean removeSysLogData(String pkId) {
        return sysLogDataMapper.deleteByPrimaryKey(pkId)==1?true:false;
    }

    /**
     * 添加数据日志
     * @param sysLogData
     * @return
     */
    @Override
    public boolean addSysLogData(SysLogData sysLogData) {
        return sysLogDataMapper.insert(sysLogData)==1?true:false;
    }

    /**
     * 选择性添加数据日志
     * @param sysLogData
     * @return
     */
    @Override
    public boolean addSysLogDataSelective(SysLogData sysLogData) {
        return sysLogDataMapper.insertSelective(sysLogData)==1?true:false;
    }

    /**
     * 根据主键id查询数据日志
     * @param pkId
     * @return
     */
    @Override
    public SysLogData searchSysLogDataByPkId(String pkId) {
        return sysLogDataMapper.selectByPrimaryKey(pkId);
    }

    /**
     * 修改数据日志
     * @param sysLogData
     * @return
     */
    @Override
    public boolean modifySysLogData(SysLogData sysLogData) {
        return sysLogDataMapper.updateByPrimaryKey(sysLogData)==1?true:false;
    }

    /**
     * 选择性修改数据日志
     * @param sysLogData
     * @return
     */
    @Override
    public boolean modifySysLogDataSelective(SysLogData sysLogData) {
        return sysLogDataMapper.updateByPrimaryKeySelective(sysLogData)==1?true:false;
    }

    /**
     * 分页获取数据日志
     * @param conditions
     * @return
     */
    @Override
    public PageData<SysLogData> searchSysLogDataPage(Map<String, Object> conditions) {
        List<SysLogData> list=sysLogDataMapper.listSysLogDataByConditions(conditions);
        Integer totalCount=sysLogDataMapper.getSysLogDataCountByConditions(conditions);
        PageData<SysLogData> pageData=new PageData<>(list,totalCount,(int)conditions.get("pSize"));
        return pageData;
    }
}
