package com.zhouyi.business.core.service;


import com.zhouyi.business.core.model.LedenCollectFourfinger;
import com.zhouyi.business.core.model.Response;
import com.zhouyi.business.core.vo.LedenCollectFourfingerVo;

public interface LedenCollectFourfingerService extends
        BaseService<LedenCollectFourfinger, LedenCollectFourfingerVo>{

    Response findFourFingerByPersonCode(String personCode);
}
