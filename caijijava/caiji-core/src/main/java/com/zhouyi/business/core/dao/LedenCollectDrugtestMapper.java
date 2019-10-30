package com.zhouyi.business.core.dao;

import com.zhouyi.business.core.model.LedenCollectDrugtest;
import com.zhouyi.business.core.vo.LedenCollectDrugtestVo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LedenCollectDrugtestMapper extends BuffBaseMapper<LedenCollectDrugtest, LedenCollectDrugtestVo>{
    /**
     * 删除制定人员的吸毒信息
     * @param personId
     * @return
     */
    int deleteDrugtestByPersonId(String personId);
}