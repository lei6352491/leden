package com.zhouyi.business.core.service;

import com.zhouyi.business.core.dao.SysUserTransferMapper;
import com.zhouyi.business.core.model.PageData;
import com.zhouyi.business.core.model.SysUserTransfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author 李秸康
 * @ClassNmae: SysUserTransferServiceImpl
 * @Description: 用户调离记录接口实现
 * @date 2019/6/20 17:24
 * @Version 1.0
 **/
@Service
public class SysUserTransferServiceImpl implements SysUserTransferService {

    @Autowired
    private SysUserTransferMapper sysUserTransferMapper;


    /**
     * 移除调离记录
     * @param transferCode
     * @return
     */
    @Override
    public Boolean removeByKey(String transferCode) {
        return sysUserTransferMapper.deleteByPrimaryKey(transferCode)==1?true:false;
    }

    /**
     * 完整添加调离记录
     * @param sysUserTransfer
     * @return
     */
    @Override
    public Boolean addSysUserTransfer(SysUserTransfer sysUserTransfer) {
        return sysUserTransferMapper.insert(sysUserTransfer)==1?true:false;
    }

    /**
     * 选择性添加调离记录
     * @param sysUserTransfer
     * @return
     */
    @Override
    public Boolean addSysUserTransferSelective(SysUserTransfer sysUserTransfer) {
        return sysUserTransferMapper.insertSelective(sysUserTransfer)==1?true:false;
    }

    /**
     * 根据调离编码查询调离记录
     * @param transferCode
     * @return
     */
    @Override
    public SysUserTransfer searchSysUserTransfer(String transferCode) {
        return sysUserTransferMapper.selectByPrimaryKey(transferCode);
    }

    /**
     * 完整修改调离记录
     * @param sysUserTransfer
     * @return
     */
    @Override
    public Boolean modifySysUserTransfer(SysUserTransfer sysUserTransfer) {
        return sysUserTransferMapper.updateByPrimaryKey(sysUserTransfer)==1?true:false;
    }

    /**
     * 选择性修改调离记录
     * @param sysUserTransfer
     * @return
     */
    @Override
    public Boolean modifySysUserTransferSelective(SysUserTransfer sysUserTransfer) {
        return sysUserTransferMapper.updateByPrimaryKeySelective(sysUserTransfer)==1?true:false;
    }

    /**
     * 分页查询用户调离记录业务
     * @param conditions
     * @return
     */
    @Override
    public PageData<SysUserTransfer> searchSysUserTransferPage(Map<String, Object> conditions) {
        List<SysUserTransfer> list=sysUserTransferMapper.listSysUserTransfersByConditions(conditions);
        Integer totalCount=sysUserTransferMapper.getSysUserTransferCountByConditions(conditions);
        PageData<SysUserTransfer> pageData=new PageData<>(list,totalCount,(int)conditions.get("pSize"));
        return pageData;
    }
}
