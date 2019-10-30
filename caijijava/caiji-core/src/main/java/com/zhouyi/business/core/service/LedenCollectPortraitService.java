package com.zhouyi.business.core.service;

import com.zhouyi.business.core.model.LedenCollectPortrait;
import com.zhouyi.business.core.model.PortraitResult;
import com.zhouyi.business.core.model.Response;
import com.zhouyi.business.core.vo.LedenCollectPortraitVo;

public interface LedenCollectPortraitService extends BaseService<LedenCollectPortrait, LedenCollectPortraitVo> {

    /**
     * 读取xml存数据
     * @param path
     * @return
     */
    Boolean insertPortraitByXml(String path);

    Response<PortraitResult> selectPortraitByPersonCode(String id);
}
