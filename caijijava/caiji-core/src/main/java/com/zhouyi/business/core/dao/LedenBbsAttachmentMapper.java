package com.zhouyi.business.core.dao;

import com.zhouyi.business.core.model.LedenBbsAttachment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface LedenBbsAttachmentMapper {
    int deleteByPrimaryKey(String pkId);

    int insert(LedenBbsAttachment record);

    int insertSelective(LedenBbsAttachment record);

    LedenBbsAttachment selectByPrimaryKey(String pkId);

    int updateByPrimaryKeySelective(LedenBbsAttachment record);

    int updateByPrimaryKey(LedenBbsAttachment record);

    List<LedenBbsAttachment> listBbsAttachmentByConditions(Map<String,Object> conditions);

    int getBbsAttachmentCountByConditions(Map<String,Object> conditions);

    /**
     * 上传多个附件
     * @param records
     * @return
     */
    int insertMulti(List<LedenBbsAttachment> records);
}