package com.zhouyi.business.core.dao;

import com.zhouyi.business.core.model.LedenCollectFootprint;
import com.zhouyi.business.core.vo.LedenCollectFootprintSearchVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface LedenCollectFootprintMapper extends BaseMapper<LedenCollectFootprintSearchVo,String>{


    /**
     * 插入数据集合
     * @param list
     * @return
     */
    int insertFootPrints(List<LedenCollectFootprint> list);

    LedenCollectFootprint selectDataById(String id);

    /**
     * 根据人员编号删除足记信息
     * @param personId
     * @return
     */
    int deleteFootPrintByPersonId(String personId);

    /**
     * 根据条件筛选足记信息
     * @param conditions
     * @return
     */
    List<LedenCollectFootprint> listFootPrintByConditions(Map<String,Object> conditions);
}