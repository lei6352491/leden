package com.zhouyi.business.core.service;

import com.zhouyi.business.core.common.ReturnCode;
import com.zhouyi.business.core.dao.SysBaseConfMapper;
import com.zhouyi.business.core.exception.BusinessException;
import com.zhouyi.business.core.exception.ExceptionCast;
import com.zhouyi.business.core.model.SysBaseConf;
import com.zhouyi.business.core.utils.ResponseUtil;
import com.zhouyi.business.core.vo.SysBaseConfVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysBaseConfServiceImpl implements SysBaseConfService {

    @Autowired
    private SysBaseConfMapper sysBaseConfMapper;

    private static final Integer ROW_NUMBER_ZERO = 0;

    @Override
    public SysBaseConf findSysBaseConfById(String id) {
        SysBaseConf sysBaseConf = sysBaseConfMapper.selectByPrimaryKey(id);
        return sysBaseConf;
    }

    @Override
    public List<SysBaseConf> findSysBaseConfByModel(SysBaseConfVo sysBaseConfVo) {
        if (sysBaseConfVo.getPage() == null || sysBaseConfVo.getPage() < 1){
            sysBaseConfVo.setPage(1);
        }
        if (sysBaseConfVo.getSize() == null || sysBaseConfVo.getSize() < 1){
            sysBaseConfVo.setSize(20);
        }
        int stratNo = (sysBaseConfVo.getPage() - 1) * sysBaseConfVo.getSize() + 1;
        sysBaseConfVo.setStartNo(stratNo);
        int endNo = stratNo + sysBaseConfVo.getSize();
        sysBaseConfVo.setEndNo(endNo);

        List<SysBaseConf> baseConfs = sysBaseConfMapper.selectByModel(sysBaseConfVo);
        return baseConfs;
    }

    @Override
    public void saveSysBaseConf(SysBaseConf sysBaseConf) {
        if (sysBaseConf == null || StringUtils.isEmpty(sysBaseConf.getPkId())){
            ExceptionCast.cast(ResponseUtil.returnError(ReturnCode.ERROR_02));
        }
        try{
            sysBaseConfMapper.insertSelective(sysBaseConf);
        }catch (Exception e){
            e.printStackTrace();
            ExceptionCast.cast(ResponseUtil.returnError(ReturnCode.ERROR_01));
        }
    }

    @Override
    public void updateSysBaseConf(SysBaseConf sysBaseConf) {
        if (sysBaseConf == null || StringUtils.isEmpty(sysBaseConf.getPkId())){
            ExceptionCast.cast(ResponseUtil.returnError(ReturnCode.ERROR_02));
        }
        try{
            sysBaseConfMapper.updateByPrimaryKeySelective(sysBaseConf);
        }catch (Exception e){
            e.printStackTrace();
            ExceptionCast.cast(ResponseUtil.returnError(ReturnCode.ERROR_01));
        }
    }

    @Override
    public void deleteSysBaseConfById(String id) {
        SysBaseConf baseConf = this.findSysBaseConfById(id);
        if (baseConf == null){
            throw new BusinessException(503,"删除失败!");
        }
        int rowNumber = sysBaseConfMapper.deleteByPrimaryKey(id);
        if (rowNumber == ROW_NUMBER_ZERO){
            throw new BusinessException(503,"删除失败!");
        }
    }

    @Override
    public int findTotal(SysBaseConfVo sysBaseConfVo) {
        return sysBaseConfMapper.findTotal(sysBaseConfVo);
    }
}
