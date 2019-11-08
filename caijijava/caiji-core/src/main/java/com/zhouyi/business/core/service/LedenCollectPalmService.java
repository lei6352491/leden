package com.zhouyi.business.core.service;

import com.zhouyi.business.core.model.LedenCollectPalm;
import com.zhouyi.business.core.model.Response;
import com.zhouyi.business.core.vo.LedenCollectPalmVo;

import java.util.List;

public interface LedenCollectPalmService extends BaseService<LedenCollectPalm, LedenCollectPalmVo> {

    Response selectPalmOrFourFingerByPersonCode(String id);

    /**
     * 根据人员编号获取掌纹信息
     * @param personCode
     * @return
     */
    List<LedenCollectPalm> listPalmsByPersonCode(String personCode);

}
