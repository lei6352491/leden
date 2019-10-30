package com.zhouyi.business.core.service;

import com.zhouyi.business.core.model.LedenCollectCategoryNode;
import com.zhouyi.business.core.model.Response;
import com.zhouyi.business.core.vo.LedenCollectCategoryNodeVo;

public interface LedenCollectCategoryNodeService extends
        BaseService<LedenCollectCategoryNode, LedenCollectCategoryNodeVo> {

    Response moveDown(LedenCollectCategoryNode ledenCollectCategoryNode);

    Response moveUpward(LedenCollectCategoryNode ledenCollectCategoryNode);
}
