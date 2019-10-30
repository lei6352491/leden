package com.zhouyi.business.core.service;

import com.zhouyi.business.core.common.ReturnCode;
import com.zhouyi.business.core.dao.LedenEquipmentPlugInMapper;
import com.zhouyi.business.core.model.LedenEquipmentPlugIn;
import com.zhouyi.business.core.model.Response;
import com.zhouyi.business.core.utils.ResponseUtil;
import com.zhouyi.business.core.vo.LedenEquipmentPlugInVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 杜承旭
 * @ClassNmae: LedenEquipmentPlugInServiceImpl
 * @Description: TODO
 * @date 2019/9/19 14:07
 * @Version 1.0
 **/

@Service
public class LedenEquipmentPlugInServiceImpl
        extends BaseServiceImpl<LedenEquipmentPlugIn, LedenEquipmentPlugInVo>
        implements LedenEquipmentPlugInService{

    @Autowired
    private LedenEquipmentPlugInMapper ledenEquipmentPlugInMapper;

    @Override
    public Response<LedenEquipmentPlugIn> selectDataByNodeCode(String nodeCode) {
        List<LedenEquipmentPlugIn> list = ledenEquipmentPlugInMapper.selectDataByNodeCode(nodeCode);
        return ResponseUtil.getResponseInfo(ReturnCode.SUCCESS,list);
    }
}
