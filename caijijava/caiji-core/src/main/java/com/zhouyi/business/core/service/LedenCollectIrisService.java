package com.zhouyi.business.core.service;

import com.zhouyi.business.core.model.Head;
import com.zhouyi.business.core.model.LedenCollectIris;
import com.zhouyi.business.core.model.Response;
import com.zhouyi.business.core.vo.LedenCollectIrisVo;

import java.util.List;
import java.util.Map;

public interface LedenCollectIrisService extends BaseService<LedenCollectIris, LedenCollectIrisVo> {

    Response<Object> saveMapToRepository(List list,String userUnitCode,String ryjcxxcjbh);

    Response<List<LedenCollectIris>> selectIrisByPersonCode(String id);
}
