package com.zhouyi.business.core.dao;

import com.zhouyi.business.core.model.LedenCollectPTotal;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface LedenCollectPTotalMapper {
    int deleteByPrimaryKey(String pkId);

    int insert(LedenCollectPTotal record);

    int insertSelective(LedenCollectPTotal record);

    LedenCollectPTotal selectByPrimaryKey(String pkId);

    int updateByPrimaryKeySelective(LedenCollectPTotal record);

    int updateByPrimaryKey(LedenCollectPTotal record);

    /**
     * 插入多行total
     * @param totals
     * @return
     */
    int insertTotals(List<LedenCollectPTotal> totals);

    /**
     * 根据人员编号查询手机采集总记录信息
     * */
    List<LedenCollectPTotal> selectDataByPersonCode(String id);

    /**
     * 根据人员编号删除手机各项信息总数
     * @param personId
     * @return
     */
    int deletePtotalByPersonId(String personId);
}