
package com.zhouyi.business.core.service;

import com.zhouyi.business.core.common.ReturnCode;
import com.zhouyi.business.core.dao.LedenShareAppMapper;
import com.zhouyi.business.core.model.LedenShareApp;
import com.zhouyi.business.core.model.PageData;
import com.zhouyi.business.core.model.Response;
import com.zhouyi.business.core.model.ShareAppDetailDto;
import com.zhouyi.business.core.utils.MapUtils;
import com.zhouyi.business.core.utils.ResponseUtil;
import com.zhouyi.business.core.vo.LedenShareAppVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;


/**
 * @author 李秸康
 * @ClassNmae: LedenShareAppServiceImpl
 * @Description: 共享数据接口实现
 * @date 2019/6/28 14:47
 * @Version 1.0
 **/

@Service
public class LedenShareAppServiceImpl implements LedenShareAppService{

    @Autowired
    private LedenShareAppMapper ledenShareAppMapper;


    @Override
    public boolean addShareAppInfo(LedenShareApp ledenShareApp) {
        ledenShareApp.setCreateDatetime();
        ledenShareApp.setAppId(UUID.randomUUID().toString());
        return ledenShareAppMapper.insertSelective(ledenShareApp)==1?true:false;
    }

    @Override
    public boolean removeShareAppInfo(String appId) {
        return ledenShareAppMapper.deleteByPrimaryKey(appId)==1?true:false;
    }

    @Override
    public boolean modifyShareAppInfo(LedenShareApp ledenShareApp) {
        ledenShareApp.setUpdateDatetime();
        return ledenShareAppMapper.updateByPrimaryKeySelective(ledenShareApp)==1?true:false;
    }

    @Override
    public PageData<LedenShareApp> listAllShareApp(Map<String,Object> conditions) {
       List<LedenShareApp> shareApps=ledenShareAppMapper.listDataByConditions(conditions);
       int totalCount=ledenShareAppMapper.getDataCountByConditions(conditions);
       return new PageData<>(shareApps,totalCount,(int)conditions.get("pSize"));
    }

    @Override
    public LedenShareApp getLedenShareAppById(String appId) {
        return ledenShareAppMapper.selectByPrimaryKey(appId);
    }

    @Override
    public List<LedenShareApp> listAllShareApps(Map<String, Object> condition) {
        return ledenShareAppMapper.listDataByConditions(condition);
    }

    @Override
    public ShareAppDetailDto getShareAppInfoDetail(String pkId) {

        return ledenShareAppMapper.getLedenShareAppById(pkId);
    }


}

