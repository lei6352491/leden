package com.zhouyi.business.core.dao;


import com.zhouyi.business.core.model.CollectIncomplete;
import com.zhouyi.business.core.model.LedenCollectCategory;
import com.zhouyi.business.core.vo.CollectIncompleteVo;
import com.zhouyi.business.core.vo.LedenCollectCategoryVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LedenCollectCategoryMapper extends
        BuffBaseMapper<LedenCollectCategory, LedenCollectCategoryVo> {

    List<CollectIncomplete> selectCollectIncomplete(CollectIncompleteVo collectIncompleteVo);

    Integer selectCollectIncompleteCount(CollectIncompleteVo collectIncompleteVo);
}