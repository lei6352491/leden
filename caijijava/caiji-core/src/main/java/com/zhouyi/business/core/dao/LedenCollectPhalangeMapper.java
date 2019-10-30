package com.zhouyi.business.core.dao;

import com.zhouyi.business.core.model.LedenCollectPhalange;
import com.zhouyi.business.core.vo.LedenCollectPhalangeVo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LedenCollectPhalangeMapper extends
        BuffBaseMapper<LedenCollectPhalange, LedenCollectPhalangeVo>{

    int deletePhalangeByPersonId(String personId);
}