package com.zhouyi.business.core.dao;

import com.zhouyi.business.core.model.LedenCollectPCallrecords;
import com.zhouyi.business.core.vo.LedenCollectPCallrecordsVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LedenCollectPCallrecordsMapper extends BuffBaseMapper<LedenCollectPCallrecords, LedenCollectPCallrecordsVo>{

    /**
     * 添加多条通话记录信息
     * @param callrecords
     * @return
     */
    int insertCallRecords(List<LedenCollectPCallrecords> callrecords);

    List<LedenCollectPCallrecords> selectDataById(LedenCollectPCallrecordsVo ledenCollectPCallrecordsVo);

    Integer selectDataByIdCount(LedenCollectPCallrecordsVo ledenCollectPCallrecordsVo);

    /**
     * 删除目标id的通话记录
     * @param targetId
     * @return
     */
    int deleteCallRecordsByTargetId(String targetId);
}