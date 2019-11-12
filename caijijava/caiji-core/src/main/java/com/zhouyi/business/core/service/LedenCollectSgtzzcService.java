package com.zhouyi.business.core.service;

import com.zhouyi.business.core.model.LedenCollectSgtzzc;
import com.zhouyi.business.core.vo.LedenCollectSgtzzcVo;

public interface LedenCollectSgtzzcService extends
        BaseService<LedenCollectSgtzzc, LedenCollectSgtzzcVo> {


    /**
     * 根据人员编号查询体貌信息
     * @param personCode
     * @return
     */
    LedenCollectSgtzzc getSgtzzcByPersonCode(String personCode);
}
