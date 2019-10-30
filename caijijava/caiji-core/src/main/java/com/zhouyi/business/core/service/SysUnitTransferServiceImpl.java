package com.zhouyi.business.core.service;

import com.zhouyi.business.core.dao.SysUnitTransferMapper;
import com.zhouyi.business.core.model.PageData;
import com.zhouyi.business.core.model.SysUnitTransfer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author 李秸康
 * @ClassNmae: SysUnitTransferServiceImpl
 * @Description: TODO
 * @date 2019/6/20 17:00
 * @Version 1.0
 **/
@Service
public class SysUnitTransferServiceImpl implements SysUnitTransferService {

    @Autowired
    private SysUnitTransferMapper sysUnitTransferMapper;


    /**
     * 根据记录字符删除记录
     * @param transferCode
     * @return
     */
    @Override
    public Boolean removeByPrimaryKey(String transferCode) {
        return sysUnitTransferMapper.deleteByPrimaryKey(transferCode)==1?true:false;
    }

    /**
     * 完整添加记录数据
     * @param sysUnitTransfer
     * @return
     */
    @Override
    public Boolean addSysUnitTransfer(SysUnitTransfer sysUnitTransfer) {
        return sysUnitTransferMapper.insert(sysUnitTransfer)==1?true:false;
    }

    /**
     * 选择性添加记录字段信息
     * @param sysUnitTransfer
     * @return
     */
    @Override
    public Boolean addSysUnitTransferSelective(SysUnitTransfer sysUnitTransfer) {
        return sysUnitTransferMapper.insertSelective(sysUnitTransfer)==1?true:false;
    }


    /**
     * 根据记录id获取转换记录信息
     * @param transferCode
     * @return
     */
    @Override
    public SysUnitTransfer searchTransferByPrimaryKey(String transferCode) {
        return sysUnitTransferMapper.selectByPrimaryKey(transferCode);
    }

    /**
     * 选择性修改记录信息
     * @param record
     * @return
     */
    @Override
    public Boolean modifyByPrimaryKeySelective(SysUnitTransfer record) {
        return sysUnitTransferMapper.updateByPrimaryKeySelective(record)==1?true:false;
    }

    /**
     * 完全修改记录信息
     * @param sysUnitTransfer
     * @return
     */
    @Override
    public Boolean modifyTransfer(SysUnitTransfer sysUnitTransfer) {
        return sysUnitTransferMapper.updateByPrimaryKey(sysUnitTransfer)==1?true:false;
    }

    /**
     * 根据条件分页获取部门调动记录
     * @param conditions
     * @return
     */
    @Override
    public PageData<SysUnitTransfer> searchUnitTransfersPage(Map<String, Object> conditions) {
        List<SysUnitTransfer> sysUnitTransferList=sysUnitTransferMapper.listUnitTransfersByConditions(conditions);
        Integer totalCount=sysUnitTransferMapper.getUnitTransfersCountByConditions(conditions);
        PageData<SysUnitTransfer> pageData=new PageData<>(sysUnitTransferList,totalCount,(int)conditions.get("pSize"));
        return pageData;
    }
}
