package com.zhouyi.business.core.service;

import com.zhouyi.business.core.model.Head;
import com.zhouyi.business.core.model.LedenCollectDrugtest;
import com.zhouyi.business.core.model.Response;
import com.zhouyi.business.core.vo.LedenCollectDrugtestVo;

import java.util.List;
import java.util.Map;

public interface LedenCollectDrugtestService
        extends BaseService<LedenCollectDrugtest, LedenCollectDrugtestVo> {

    Response<Object> saveMapToRepository(List list,String userUnitCode);

}
