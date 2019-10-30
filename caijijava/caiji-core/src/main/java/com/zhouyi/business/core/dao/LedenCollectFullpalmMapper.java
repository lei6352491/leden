package com.zhouyi.business.core.dao;

import com.zhouyi.business.core.model.LedenCollectFullpalm;
import com.zhouyi.business.core.vo.LedenCollectFullpalmVo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LedenCollectFullpalmMapper extends
        BuffBaseMapper<LedenCollectFullpalm, LedenCollectFullpalmVo>{


    int deleteFullPalmByPersonId(String personId);

}