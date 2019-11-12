package com.zhouyi.business.core.service;

import com.zhouyi.business.core.model.LedenCollectPortrait;
import com.zhouyi.business.core.model.PortraitResult;
import com.zhouyi.business.core.model.Response;
import com.zhouyi.business.core.vo.LedenCollectPortraitVo;

import java.util.List;

public interface LedenCollectPortraitService extends BaseService<LedenCollectPortrait, LedenCollectPortraitVo> {

    /**
     * 读取xml存数据
     * @param path
     * @return
     */
    Boolean insertPortraitByXml(String path);

    Response<PortraitResult> selectPortraitByPersonCode(String id);

    /**
     * 根据人员编号获取人像信息
     * @param personCode
     * @return
     */
    List<LedenCollectPortrait> listPortraitsByPersonCode(String personCode);
}
