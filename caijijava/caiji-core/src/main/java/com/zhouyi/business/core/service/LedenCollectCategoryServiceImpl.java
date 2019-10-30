package com.zhouyi.business.core.service;

import com.zhouyi.business.core.common.ReturnCode;
import com.zhouyi.business.core.dao.BuffBaseMapper;
import com.zhouyi.business.core.dao.LedenCollectCategoryMapper;
import com.zhouyi.business.core.exception.ExceptionCast;
import com.zhouyi.business.core.model.CollectIncomplete;
import com.zhouyi.business.core.model.LedenCollectCategory;
import com.zhouyi.business.core.model.Response;
import com.zhouyi.business.core.utils.ResponseUtil;
import com.zhouyi.business.core.vo.CollectIncompleteVo;
import com.zhouyi.business.core.vo.LedenCollectCategoryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class LedenCollectCategoryServiceImpl extends
        BaseServiceImpl<LedenCollectCategory, LedenCollectCategoryVo>
        implements LedenCollectCategoryService{

    @Autowired
    private LedenCollectCategoryMapper ledenCollectCategoryMapper;

    @Override
    public Response selectCollectIncomplete(CollectIncompleteVo collectIncompleteVo) {
        HashMap<String, Object> resultMap = new HashMap<>();
        InitializationPage(collectIncompleteVo);
        //设置分页
        collectIncompleteVo.setStartNo((collectIncompleteVo.getPage() - 1) * collectIncompleteVo.getSize() + 1);
        collectIncompleteVo.setEndNo(collectIncompleteVo.getStartNo() + collectIncompleteVo.getSize());

        List<CollectIncomplete> list = null;
        try{
            list = ledenCollectCategoryMapper.selectCollectIncomplete(collectIncompleteVo);
            Integer total = ledenCollectCategoryMapper.selectCollectIncompleteCount(collectIncompleteVo);
            if (list != null){
                resultMap.put("total",total);
                resultMap.put("list",list);
            }
        }catch (Exception e){
            e.printStackTrace();
            ExceptionCast.cast(ResponseUtil.returnError(ReturnCode.ERROR_01));
        }
        return ResponseUtil.getResponseInfo(ReturnCode.SUCCESS,resultMap);
    }

    //初始化分页参数
    private CollectIncompleteVo InitializationPage(CollectIncompleteVo collectIncompleteVo){
        if (collectIncompleteVo == null){
            collectIncompleteVo = new CollectIncompleteVo();
        }
        if (collectIncompleteVo.getPage() == null || collectIncompleteVo.getPage() < 1 ){
            collectIncompleteVo.setPage(1);
        }
        if (collectIncompleteVo.getSize() == null || collectIncompleteVo.getSize() < 1){
            collectIncompleteVo.setSize(20);
        }
        return collectIncompleteVo;
    }
}
