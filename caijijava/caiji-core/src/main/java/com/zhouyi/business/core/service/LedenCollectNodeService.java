package com.zhouyi.business.core.service;

import com.zhouyi.business.core.model.LedenCollectNode;
import com.zhouyi.business.core.model.Response;
import com.zhouyi.business.core.vo.CollectNodeVo;
import com.zhouyi.business.core.vo.LedenCollectNodeVo;

public interface LedenCollectNodeService extends BaseService<LedenCollectNode, LedenCollectNodeVo> {

    Response selectCollectNodeListByCategoryOrUnit(CollectNodeVo collectNodeVo);
}
