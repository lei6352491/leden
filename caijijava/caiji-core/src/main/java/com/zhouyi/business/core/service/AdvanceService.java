package com.zhouyi.business.core.service;

import com.zhouyi.business.core.model.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.util.List;
import java.util.Map;

/**
 * @author 李秸康
 * @ClassNmae: AdvanceService
 * @Description: TODO 高级查询接口
 * @date 2019/7/25 15:39
 * @Version 1.0
 **/
public interface AdvanceService {


    /**
     * 根据条件高级查询
     * @param map
     * @return
     */
    Map advanceQuery(Map<String,Object> map);

    Response selectAllCollect(AdvanceSearchVo advanceSearchVo);

    HSSFWorkbook selectDataByIdList(RequestList requestList);
}
