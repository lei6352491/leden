package com.zhouyi.business.core.service;

import com.zhouyi.business.core.common.ReturnCode;
import com.zhouyi.business.core.dao.LedenCollectPAddressbookMapper;
import com.zhouyi.business.core.model.LedenCollectPAddressbook;
import com.zhouyi.business.core.model.Response;
import com.zhouyi.business.core.utils.InitializationPageUtils;
import com.zhouyi.business.core.utils.ResponseUtil;
import com.zhouyi.business.core.vo.LedenCollectPAddressbookVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LedenCollectPAddressbookServiceImpl
        extends BaseServiceImpl<LedenCollectPAddressbook, LedenCollectPAddressbookVo>
        implements LedenCollectPAddressbookService{

    @Autowired
    private LedenCollectPAddressbookMapper ledenCollectPAddressbookMapper;

    @Override
    public Response findDataListById(LedenCollectPAddressbookVo ledenCollectPAddressbookVo) {
        new InitializationPageUtils<LedenCollectPAddressbookVo>().initializationPage(ledenCollectPAddressbookVo);
        List<LedenCollectPAddressbook> list = ledenCollectPAddressbookMapper.findDataListById(ledenCollectPAddressbookVo);
        Integer total = ledenCollectPAddressbookMapper.findDataListByIdCount(ledenCollectPAddressbookVo);
        Map<String,Object> map = new HashMap();
        map.put("list",list);
        map.put("total",total);
        return ResponseUtil.getResponseInfo(ReturnCode.SUCCESS,map);
    }
}
