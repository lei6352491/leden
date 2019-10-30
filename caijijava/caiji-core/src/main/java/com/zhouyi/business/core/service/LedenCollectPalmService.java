package com.zhouyi.business.core.service;

import com.zhouyi.business.core.model.LedenCollectPalm;
import com.zhouyi.business.core.model.Response;
import com.zhouyi.business.core.vo.LedenCollectPalmVo;

public interface LedenCollectPalmService extends BaseService<LedenCollectPalm, LedenCollectPalmVo> {

    Response selectPalmOrFourFingerByPersonCode(String id);

}
