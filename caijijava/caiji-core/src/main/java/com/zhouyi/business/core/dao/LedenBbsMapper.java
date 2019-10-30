package com.zhouyi.business.core.dao;

import com.zhouyi.business.core.model.LedenBbs;
import com.zhouyi.business.core.vo.BbsVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface LedenBbsMapper {
    int deleteByPrimaryKey(String pkId);

    int insert(BbsVo record);

    int insertSelective(BbsVo record);

    LedenBbs selectByPrimaryKey(String pkId);

    int updateByPrimaryKeySelective(BbsVo record);

    int updateByPrimaryKey(BbsVo record);

    List<LedenBbs> listLedenBbsByConditions(Map<String,Object> conditions);

    int getLedenBbsCountByConditions(Map<String,Object> conditions);

    List<LedenBbs> selectBbsByDate();
}