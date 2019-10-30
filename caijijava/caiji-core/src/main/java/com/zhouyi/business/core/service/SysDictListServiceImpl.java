package com.zhouyi.business.core.service;

import com.zhouyi.business.core.common.ReturnCode;
import com.zhouyi.business.core.dao.SysDictListMapper;
import com.zhouyi.business.core.exception.BusinessException;
import com.zhouyi.business.core.exception.ExceptionCast;
import com.zhouyi.business.core.model.SysDictList;
import com.zhouyi.business.core.utils.ResponseUtil;
import com.zhouyi.business.core.vo.SysDictListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SysDictListServiceImpl implements SysDictListService{
    
    @Autowired
    private SysDictListMapper sysDictListMapper;

    @Override
    public List<SysDictList> findSysDictListById(String id) {
        List<SysDictList> sysDictList = sysDictListMapper.findSysDictListById(id);
        return sysDictList;
    }

    @Override
    public List<SysDictList> findSysDiceListByModel(SysDictListVo sysDictListVo) {
        if (sysDictListVo.getPage() == null || sysDictListVo.getPage() < 1){
            sysDictListVo.setPage(1);
        }
        if (sysDictListVo.getSize() == null || sysDictListVo.getSize() < 1){
            sysDictListVo.setSize(20);
        }
        int stratNo = (sysDictListVo.getPage() - 1) * sysDictListVo.getSize() + 1;
        sysDictListVo.setStartNo(stratNo);
        int endNo = stratNo + sysDictListVo.getSize();
        sysDictListVo.setEndNo(endNo);

        List<SysDictList> sysDictLists = sysDictListMapper.selectByModel(sysDictListVo);
        return sysDictLists;
    }

    @Override
    @Transactional
    public void saveSysDictList(SysDictList sysDictList) {
        try{
            sysDictListMapper.insertSelective(sysDictList);
        }catch (Exception e){
            ExceptionCast.cast(ResponseUtil.getResponseInfo(false));
        }
    }

    @Override
    @Transactional
    public void updateSysDictList(SysDictList sysDictList) {
        try{
            sysDictListMapper.updateByPrimaryKeySelective(sysDictList);
        }catch (Exception e){
            ExceptionCast.cast(ResponseUtil.getResponseInfo(false));
        }
    }

    @Override
    @Transactional
    public void deleteSysDictList(String id) {
        List<SysDictList> dictList = this.findSysDictListById(id);
        if (dictList == null){
            ExceptionCast.cast(ResponseUtil.returnError(ReturnCode.ERROR_05));
        }
        if (dictList != null){
            try{
                sysDictListMapper.deleteByPrimaryKey(id);
            }catch (Exception e){
                ExceptionCast.cast(ResponseUtil.getResponseInfo(false));
            }
        }
    }

    @Override
    @Transactional
    public List<SysDictList> testList(SysDictListVo sysDictListVo) {
        List<SysDictList> sysDictLists = sysDictListMapper.testList(sysDictListVo);
        return sysDictLists;
    }

    @Override
    public int findTotal(SysDictListVo sysDictListVo) {
        return sysDictListMapper.findTotal(sysDictListVo);
    }
}
