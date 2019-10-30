package com.zhouyi.business.core.dao;

import com.zhouyi.business.core.model.LedenCollectPMessagerecords;
import com.zhouyi.business.core.vo.LedenCollectPMessagerecordsVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LedenCollectPMessagerecordsMapper extends BuffBaseMapper<LedenCollectPMessagerecords, LedenCollectPMessagerecordsVo>{

    /**
     * 插入多条短信记录
      * @param messagerecords
     * @return
     */
    int insertMessageRecords(List<LedenCollectPMessagerecords> messagerecords);

    List<LedenCollectPMessagerecords> selectDataById(LedenCollectPMessagerecordsVo ledenCollectPMessagerecordsVo);

    Integer selectDataByIdCount(LedenCollectPMessagerecordsVo ledenCollectPMessagerecordsVo);
}