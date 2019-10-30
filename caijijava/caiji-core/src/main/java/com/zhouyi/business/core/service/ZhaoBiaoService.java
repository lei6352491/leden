package com.zhouyi.business.core.service;

import com.zhouyi.business.core.model.ZhaoBiao;

import java.util.List;

/**
 * @author 李秸康
 * @ClassNmae: ZhaoBiaoService
 * @Description: TODO 招标业务接口
 * @date 2019/7/15 12:45
 * @Version 1.0
 **/
public interface ZhaoBiaoService {
    /**
     * 根据标题查询招标信息
     * @param title
     * @return
     */
    List<ZhaoBiao> searchZhaoBiaoInfoBytitle(String title);
}
