package com.zhouyi.business.core.service;

import com.zhouyi.business.core.common.ReturnCode;
import com.zhouyi.business.core.dao.LedenCollectPCallrecordsMapper;
import com.zhouyi.business.core.model.LedenCollectPCallrecords;
import com.zhouyi.business.core.model.Response;
import com.zhouyi.business.core.utils.InitializationPageUtils;
import com.zhouyi.business.core.utils.ResponseUtil;
import com.zhouyi.business.core.vo.LedenCollectPCallrecordsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LedenCollectPCallrecordsServiceImpl extends BaseServiceImpl<LedenCollectPCallrecords, LedenCollectPCallrecordsVo> implements LedenCollectPCallrecordsService{

    @Autowired
    private LedenCollectPCallrecordsMapper ledenCollectPCallrecordsMapper;

    @Override
    public Response selectDataById(LedenCollectPCallrecordsVo ledenCollectPCallrecordsVo) {
        new InitializationPageUtils<LedenCollectPCallrecordsVo>().initializationPage(ledenCollectPCallrecordsVo);
        List<LedenCollectPCallrecords> list = ledenCollectPCallrecordsMapper.selectDataById(ledenCollectPCallrecordsVo);
        Integer total = ledenCollectPCallrecordsMapper.selectDataByIdCount(ledenCollectPCallrecordsVo);
        Map<String,Object> map = new HashMap<>();
        map.put("list",list);
        map.put("total",total);
        return ResponseUtil.getResponseInfo(ReturnCode.SUCCESS,map);
    }
}
