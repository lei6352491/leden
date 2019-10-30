package com.zhouyi.business.core.service;

import com.zhouyi.business.core.model.LedenEquipmentPlugIn;
import com.zhouyi.business.core.model.Response;
import com.zhouyi.business.core.vo.LedenEquipmentPlugInVo;

/**
 * @author 杜承旭
 * @ClassNmae: LedenEquipmentPlugInService
 * @Description: TODO
 * @date 2019/9/19 14:06
 * @Version 1.0
 **/
public interface LedenEquipmentPlugInService
        extends BaseService<LedenEquipmentPlugIn, LedenEquipmentPlugInVo> {

    Response<LedenEquipmentPlugIn> selectDataByNodeCode(String nodeCode);
}
