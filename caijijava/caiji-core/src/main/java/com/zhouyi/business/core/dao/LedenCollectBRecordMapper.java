package com.zhouyi.business.core.dao;


import com.zhouyi.business.core.model.LedenCollectBRecord;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LedenCollectBRecordMapper extends BaseMapper<LedenCollectBRecord,String> {
    List<LedenCollectBRecord> selectBRecordByBankcard(String id);
}