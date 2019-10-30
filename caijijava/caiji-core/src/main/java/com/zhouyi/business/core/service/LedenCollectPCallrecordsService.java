package com.zhouyi.business.core.service;

import com.zhouyi.business.core.model.LedenCollectPCallrecords;
import com.zhouyi.business.core.model.Response;
import com.zhouyi.business.core.vo.LedenCollectPCallrecordsVo;

public interface LedenCollectPCallrecordsService extends BaseService<LedenCollectPCallrecords, LedenCollectPCallrecordsVo> {
    Response selectDataById(LedenCollectPCallrecordsVo ledenCollectPCallrecordsVo);
}
