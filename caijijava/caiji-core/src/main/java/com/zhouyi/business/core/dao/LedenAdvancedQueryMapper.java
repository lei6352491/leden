package com.zhouyi.business.core.dao;

import com.zhouyi.business.core.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
@Mapper
public interface LedenAdvancedQueryMapper extends BaseMapper<LedenAdvancedQuery,String>{


    /**
     * 根据条件高级查询
     * @param conditions
     * @return
     */
    List<LedenAdvancedQuery> ledenAdvancedQueryUnionVersion(Map<String,Object> conditions);

    int getDataCountByConditions(Map<String,Object> conditions);

    List<AdvancedQueryResult> selectAllCollect(AdvanceSearchVo advanceSearchVo);

    int selectAllCollectCount(AdvanceSearchVo advanceSearchVo);

    List<PersonResult> selectDataByIdList(List<String> list);
}