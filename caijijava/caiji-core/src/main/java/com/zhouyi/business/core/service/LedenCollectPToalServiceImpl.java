package com.zhouyi.business.core.service;

import com.zhouyi.business.core.common.ReturnCode;
import com.zhouyi.business.core.dao.LedenCollectPTotalMapper;
import com.zhouyi.business.core.model.LedenCollectPTotal;
import com.zhouyi.business.core.model.Response;
import com.zhouyi.business.core.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 杜承旭
 * @ClassNmae: LedenCollectPToalServiceImpl
 * @Description: TODO
 * @date 2019/9/4 9:59
 * @Version 1.0
 **/

@Service
public class LedenCollectPToalServiceImpl implements LedenCollectPToalService{

    @Autowired
    private LedenCollectPTotalMapper ledenCollectPTotalMapper;

    @Override
    public Response selectDataByPersonCode(String id) {
        List<LedenCollectPTotal> ledenCollectPTotals = ledenCollectPTotalMapper.selectDataByPersonCode(id);
        return ResponseUtil.getResponseInfo(ReturnCode.SUCCESS,ledenCollectPTotals);
    }
}
