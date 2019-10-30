package com.zhouyi.business.core.service;

import com.zhouyi.business.core.model.LedenCollectPAddressbook;
import com.zhouyi.business.core.model.Response;
import com.zhouyi.business.core.vo.LedenCollectPAddressbookVo;

public interface LedenCollectPAddressbookService extends BaseService<LedenCollectPAddressbook, LedenCollectPAddressbookVo> {
    Response findDataListById(LedenCollectPAddressbookVo ledenCollectPAddressbookVo);
}
