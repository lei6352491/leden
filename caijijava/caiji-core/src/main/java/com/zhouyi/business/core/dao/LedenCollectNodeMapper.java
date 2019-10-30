package com.zhouyi.business.core.dao;

import com.zhouyi.business.core.model.CollectNodeResult;
import com.zhouyi.business.core.model.LedenCollectNode;
import com.zhouyi.business.core.vo.CollectNodeVo;
import com.zhouyi.business.core.vo.LedenCollectNodeVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LedenCollectNodeMapper extends BuffBaseMapper<LedenCollectNode, LedenCollectNodeVo>{

    List<CollectNodeResult> selectCollectNodeListByCategoryOrUnit(CollectNodeVo collectNodeVo);

    List<CollectNodeResult> selectCollectNodeByUnitAndCategory(CollectNodeVo collectNodeVo);

    Integer selectCollectNodeListCount(CollectNodeVo collectNodeVo);

    Integer selectCollectNodeCountByUnitAndCategory(CollectNodeVo collectNodeVo);

}