package com.zhouyi.business.core.service;

import com.zhouyi.business.core.common.ReturnCode;
import com.zhouyi.business.core.dao.BuffBaseMapper;
import com.zhouyi.business.core.dao.LedenCollectNodeMapper;
import com.zhouyi.business.core.model.CollectNodeResult;
import com.zhouyi.business.core.model.LedenCollectNode;
import com.zhouyi.business.core.model.Response;
import com.zhouyi.business.core.utils.ResponseUtil;
import com.zhouyi.business.core.vo.CollectNodeVo;
import com.zhouyi.business.core.vo.LedenCollectNodeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LedenCollectNodeServiceImpl extends BaseServiceImpl<LedenCollectNode, LedenCollectNodeVo>
                                implements LedenCollectNodeService {

    @Autowired
    private LedenCollectNodeMapper ledenCollectNodeMapper;

    @Override
    public Response selectCollectNodeListByCategoryOrUnit(CollectNodeVo collectNodeVo) {
        initialPage(collectNodeVo);
        List<CollectNodeResult> list = ledenCollectNodeMapper.selectCollectNodeByUnitAndCategory(collectNodeVo);
        Integer total = ledenCollectNodeMapper.selectCollectNodeCountByUnitAndCategory(collectNodeVo);
        Map<String,Object> map = new HashMap<>();
        map.put("list",list);
        map.put("total",total);
        return ResponseUtil.getResponseInfo(ReturnCode.SUCCESS,map);
    }

    //初始化分页参数
    CollectNodeVo initialPage(CollectNodeVo collectNodeVo){
        if (collectNodeVo == null)
            collectNodeVo = new CollectNodeVo();
        if (collectNodeVo.getPage() == null || collectNodeVo.getPage() < 1)
            collectNodeVo.setPage(1);
        if (collectNodeVo.getSize() == null || collectNodeVo.getSize() < 1)
            collectNodeVo.setSize(20);
        collectNodeVo.setStartNo((collectNodeVo.getPage() - 1) * collectNodeVo.getSize() + 1);
        collectNodeVo.setEndNo(collectNodeVo.getStartNo() + collectNodeVo.getSize());
        return collectNodeVo;
    }
}
