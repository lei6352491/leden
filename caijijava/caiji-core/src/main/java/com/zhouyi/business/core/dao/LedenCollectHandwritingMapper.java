package com.zhouyi.business.core.dao;


import com.zhouyi.business.core.model.LedenCollectHandwriting;
import com.zhouyi.business.core.model.Response;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LedenCollectHandwritingMapper extends BaseMapper<LedenCollectHandwriting,String> {

    /**
     * 批量新增处理
     * @param list
     * @return
     */
    int insertHandwritings(List<LedenCollectHandwriting> list);

    List<LedenCollectHandwriting> selectDataByPersonCode(String id);

    int deleteHandWritingByPerson(String personId);
}