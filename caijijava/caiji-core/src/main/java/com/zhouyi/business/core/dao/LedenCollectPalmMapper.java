package com.zhouyi.business.core.dao;

import com.zhouyi.business.core.model.LedenCollectPalm;
import com.zhouyi.business.core.vo.LedenCollectPalmVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LedenCollectPalmMapper extends
        BuffBaseMapper<LedenCollectPalm, LedenCollectPalmVo>{

    List<LedenCollectPalm> selectPalmFingerByPersonCode(String id);

    int deletePalmByPersonId(String personId);
}