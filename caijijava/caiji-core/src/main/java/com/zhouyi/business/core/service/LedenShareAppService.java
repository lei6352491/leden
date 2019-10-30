package com.zhouyi.business.core.service;


import com.zhouyi.business.core.model.LedenShareApp;
import com.zhouyi.business.core.model.PageData;
import com.zhouyi.business.core.model.ShareAppDetailDto;
import com.zhouyi.business.core.vo.LedenShareAppVo;

import java.util.List;
import java.util.Map;

/**
 * @author 李秸康
 * @ClassNmae: LedenShareAppService
 * @Description: 共享数据 接口
 * @date 2019/6/28 8:51
 * @Version 1.0
 **/

public interface LedenShareAppService {

    boolean addShareAppInfo(LedenShareApp ledenShareApp);

    boolean removeShareAppInfo(String appId);

    boolean modifyShareAppInfo(LedenShareApp ledenShareApp);

    PageData<LedenShareApp> listAllShareApp(Map<String,Object> condition);

    LedenShareApp getLedenShareAppById(String appId);

    List<LedenShareApp> listAllShareApps(Map<String,Object> condition);

    /**
     * 获取共享信息详情
     * @param pkId
     * @return
     */
    ShareAppDetailDto getShareAppInfoDetail(String pkId);
}

