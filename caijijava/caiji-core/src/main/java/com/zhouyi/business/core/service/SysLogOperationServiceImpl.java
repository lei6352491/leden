package com.zhouyi.business.core.service;

import com.zhouyi.business.core.dao.SysLogOperationMapper;
import com.zhouyi.business.core.model.PageData;
import com.zhouyi.business.core.model.SysLogOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author 李秸康
 * @ClassNmae: SysLogOperationServiceImpl
 * @Description: 系统日志业务接口实现
 * @date 2019/6/21 10:38
 * @Version 1.0
 **/
@Service
public class SysLogOperationServiceImpl implements SysLogOperationService{

    @Autowired
    private SysLogOperationMapper sysLogOperationMapper;


    /**
     * 移除系统日志记录
     * @param pkId
     * @return
     */
    @Override
    public boolean removeSysLogOperationById(String pkId) {
        return sysLogOperationMapper.deleteByPrimaryKey(pkId)==1?true:false;
    }

    /**
     * 添加系统日志记录
     * @param sysLogOperation
     * @return
     */
    @Override
    public boolean addSysLogOperation(SysLogOperation sysLogOperation) {
        return sysLogOperationMapper.insert(sysLogOperation)==1?true:false;
    }


    /**
     * 选择性添加系统日志记录
     * @param sysLogOperation
     * @return
     */
    @Override
    public boolean addSysLogOperationSelective(SysLogOperation sysLogOperation) {
        return sysLogOperationMapper.insertSelective(sysLogOperation)==1?true:false;
    }

    /**
     * 查询系统日志记录信息
     * @param pkId
     * @return
     */
    @Override
    public SysLogOperation searchSysLogOperation(String pkId) {
        return sysLogOperationMapper.selectByPrimaryKey(pkId);
    }

    /**
     * 修改系统日志记录
     * @param sysLogOperation
     * @return
     */
    @Override
    public boolean modifySysLogOperation(SysLogOperation sysLogOperation) {
        return sysLogOperationMapper.updateByPrimaryKey(sysLogOperation)==1?true:false;
    }

    /**
     * 选择性修改系统日志信息
     * @param sysLogOperation
     * @return
     */
    @Override
    public boolean modifySysLogOperationSelective(SysLogOperation sysLogOperation) {
        return sysLogOperationMapper.updateByPrimaryKeySelective(sysLogOperation)==1?true:false;
    }

    /**
     * 获取系统日志操作分页对象
     * @param conditions
     * @return
     */
    @Override
    public PageData<SysLogOperation> searchSysLogOperationPage(Map<String, Object> conditions) {
        List<SysLogOperation> list=sysLogOperationMapper.listSysLogOperationByConditions(conditions);
        Integer totalCount=sysLogOperationMapper.getSysLogOperationCountByConditions(conditions);
        PageData<SysLogOperation> pageData=new PageData<>(list,totalCount,(int)conditions.get("pSize"));
        return pageData;
    }
}
