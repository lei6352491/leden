package com.zhouyi.business.core.dao;

import com.zhouyi.business.core.model.LedenUploadLog;
import com.zhouyi.business.core.model.LedenUploadPacket;
import com.zhouyi.business.core.vo.LedenUploadLogVo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LedenUploadLogMapper extends BuffBaseMapper<LedenUploadLog, LedenUploadLogVo>{

    LedenUploadLog selectLedenUploadLogByNewTime(String equipmentId);

    int insertUploadLog(LedenUploadLog ledenUploadLog);

}