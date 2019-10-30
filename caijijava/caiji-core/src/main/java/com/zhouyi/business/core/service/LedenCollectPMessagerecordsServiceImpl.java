package com.zhouyi.business.core.service;

import com.zhouyi.business.core.common.ReturnCode;
import com.zhouyi.business.core.dao.LedenCollectPMessagerecordsMapper;
import com.zhouyi.business.core.model.LedenCollectPMessagerecords;
import com.zhouyi.business.core.model.Response;
import com.zhouyi.business.core.utils.InitializationPageUtils;
import com.zhouyi.business.core.utils.ResponseUtil;
import com.zhouyi.business.core.vo.LedenCollectPMessagerecordsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LedenCollectPMessagerecordsServiceImpl
        extends BaseServiceImpl<LedenCollectPMessagerecords, LedenCollectPMessagerecordsVo>
        implements LedenCollectPMessagerecordsService{

    @Autowired
    private LedenCollectPMessagerecordsMapper ledenCollectPMessagerecordsMapper;

    @Override
    public Response selectDataById(LedenCollectPMessagerecordsVo ledenCollectPMessagerecordsVo) {
        new InitializationPageUtils<>().initializationPage(ledenCollectPMessagerecordsVo);
        List<LedenCollectPMessagerecords> list = ledenCollectPMessagerecordsMapper.selectDataById(ledenCollectPMessagerecordsVo);
        Integer total = ledenCollectPMessagerecordsMapper.selectDataByIdCount(ledenCollectPMessagerecordsVo);
        Map<String,Object> map = new HashMap<>();
        map.put("list",list);
        map.put("total",total);
        return ResponseUtil.getResponseInfo(ReturnCode.SUCCESS,map);
    }
}
