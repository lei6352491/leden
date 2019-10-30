package com.zhouyi.business.core.dao;

import com.zhouyi.business.core.model.LedenCollectDna;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LedenCollectDnaMapper extends BaseMapper<LedenCollectDna,String>{

    List<LedenCollectDna> selectDataByPersonCode(String id);

    /**
     * 根据采集人员编号删除dna信息
     * @param personId
     * @return
     */
    int deteteDnaByPersonId(String personId);

}