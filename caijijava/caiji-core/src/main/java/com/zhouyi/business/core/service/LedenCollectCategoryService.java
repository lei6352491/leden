package com.zhouyi.business.core.service;

import com.zhouyi.business.core.model.CollectIncomplete;
import com.zhouyi.business.core.model.LedenCollectCategory;
import com.zhouyi.business.core.model.Response;
import com.zhouyi.business.core.vo.CollectIncompleteVo;
import com.zhouyi.business.core.vo.LedenCollectCategoryVo;

public interface LedenCollectCategoryService extends
            BaseService<LedenCollectCategory, LedenCollectCategoryVo> {

    Response selectCollectIncomplete(CollectIncompleteVo collectIncompleteVo);
}
