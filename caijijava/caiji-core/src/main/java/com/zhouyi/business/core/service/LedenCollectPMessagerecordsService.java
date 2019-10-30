package com.zhouyi.business.core.service;

import com.zhouyi.business.core.model.LedenCollectPMessagerecords;
import com.zhouyi.business.core.model.Response;
import com.zhouyi.business.core.vo.LedenCollectPMessagerecordsVo;

public interface LedenCollectPMessagerecordsService extends BaseService<LedenCollectPMessagerecords, LedenCollectPMessagerecordsVo> {
    Response selectDataById(LedenCollectPMessagerecordsVo ledenCollectPMessagerecordsVo);
}
