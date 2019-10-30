package com.zhouyi.business.core.service;

import com.zhouyi.business.core.model.LedenCollectHandwriting;
import com.zhouyi.business.core.model.Response;

/**
 * @author 李秸康
 * @ClassNmae: LedenCollectHandWritingService
 * @Description: 笔记接口
 * @date 2019/7/4 15:53
 * @Version 1.0
 **/
public interface LedenCollectHandWritingService {


    /**
     * 录入笔记数据
     * @param path
     * @return
     */
    boolean inputHandWirtingXml(String path);

    Response<LedenCollectHandwriting> selectHandWritingById(String id);
}
