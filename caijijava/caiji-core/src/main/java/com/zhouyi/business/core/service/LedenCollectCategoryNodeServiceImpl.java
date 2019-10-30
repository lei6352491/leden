package com.zhouyi.business.core.service;

import com.zhouyi.business.core.common.ReturnCode;
import com.zhouyi.business.core.dao.LedenCollectCategoryMapper;
import com.zhouyi.business.core.dao.LedenCollectCategoryNodeMapper;
import com.zhouyi.business.core.model.LedenCollectCategory;
import com.zhouyi.business.core.model.LedenCollectCategoryNode;
import com.zhouyi.business.core.model.Response;
import com.zhouyi.business.core.utils.ResponseUtil;
import com.zhouyi.business.core.vo.LedenCollectCategoryNodeVo;
import com.zhouyi.business.core.vo.LedenCollectCategoryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LedenCollectCategoryNodeServiceImpl extends
        BaseServiceImpl<LedenCollectCategoryNode, LedenCollectCategoryNodeVo>
        implements LedenCollectCategoryNodeService{

    @Autowired
    private LedenCollectCategoryNodeMapper ledenCollectCategoryNodeMapper;

    @Override
    public Response moveDown(LedenCollectCategoryNode ledenCollectCategoryNode) {
        LedenCollectCategoryNode ledenCollectCategoryNodeOne =
                ledenCollectCategoryNodeMapper.selectByPrimaryKey(ledenCollectCategoryNode.getPkId());

        Integer parseInt1 = Integer.parseInt(ledenCollectCategoryNodeOne.getNodeOrd());
        Integer parseInt2 = parseInt1 + 1;

        LedenCollectCategoryNodeVo ledenCollectCategoryNodeVo = new LedenCollectCategoryNodeVo();
        ledenCollectCategoryNodeVo.setCategoryId(ledenCollectCategoryNodeOne.getCategoryId());
        ledenCollectCategoryNodeVo.setUnitCode(ledenCollectCategoryNodeOne.getUnitCode());
        ledenCollectCategoryNodeVo.setNodeOrd(parseInt2.toString());
        ledenCollectCategoryNodeVo.setStartNo(1);
        ledenCollectCategoryNodeVo.setEndNo(10);
        List<LedenCollectCategoryNode> categoryNodes =
                ledenCollectCategoryNodeMapper.selectByModel(ledenCollectCategoryNodeVo);

        if (categoryNodes == null || categoryNodes.size() < 1){
            return ResponseUtil.returnError(ReturnCode.ERROR_10);
        }

        LedenCollectCategoryNode ledenCollectCategoryNodeTwo = categoryNodes.get(0);
        ledenCollectCategoryNodeOne.setNodeOrd(parseInt2.toString());
        ledenCollectCategoryNodeTwo.setNodeOrd(parseInt1.toString());

        ledenCollectCategoryNodeMapper.updateByPrimaryKeySelective(ledenCollectCategoryNodeOne);
        ledenCollectCategoryNodeMapper.updateByPrimaryKeySelective(ledenCollectCategoryNodeTwo);

        return ResponseUtil.getResponseInfo(true);
    }

    @Override
    public Response moveUpward(LedenCollectCategoryNode ledenCollectCategoryNode) {
        LedenCollectCategoryNode ledenCollectCategoryNodeOne =
                ledenCollectCategoryNodeMapper.selectByPrimaryKey(ledenCollectCategoryNode.getPkId());

        Integer parseInt1 = Integer.parseInt(ledenCollectCategoryNodeOne.getNodeOrd());
        Integer parseInt2 = parseInt1 - 1;

        if (parseInt1 <= 2){
            return ResponseUtil.returnError(ReturnCode.ERROR_10);
        }

        LedenCollectCategoryNodeVo ledenCollectCategoryNodeVo = new LedenCollectCategoryNodeVo();
        ledenCollectCategoryNodeVo.setCategoryId(ledenCollectCategoryNodeOne.getCategoryId());
        ledenCollectCategoryNodeVo.setUnitCode(ledenCollectCategoryNodeOne.getUnitCode());
        ledenCollectCategoryNodeVo.setNodeOrd(parseInt2.toString());
        ledenCollectCategoryNodeVo.setStartNo(1);
        ledenCollectCategoryNodeVo.setEndNo(10);
        List<LedenCollectCategoryNode> categoryNodes =
                ledenCollectCategoryNodeMapper.selectByModel(ledenCollectCategoryNodeVo);
        LedenCollectCategoryNode ledenCollectCategoryNodeTwo = categoryNodes.get(0);

        ledenCollectCategoryNodeOne.setNodeOrd(parseInt2.toString());
        ledenCollectCategoryNodeTwo.setNodeOrd(parseInt1.toString());

        ledenCollectCategoryNodeMapper.updateByPrimaryKeySelective(ledenCollectCategoryNodeOne);
        ledenCollectCategoryNodeMapper.updateByPrimaryKeySelective(ledenCollectCategoryNodeTwo);
        return ResponseUtil.getResponseInfo(true);
    }
}
