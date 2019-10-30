package com.zhouyi.business.core.dao;

import com.zhouyi.business.core.model.LedenCollectVoiceprint;
import com.zhouyi.business.core.model.LedenCollectVoiceprintWithBLOBs;
import com.zhouyi.business.core.vo.LedenCollectVoiceprintVo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LedenCollectVoiceprintMapper
        extends BuffBaseMapper<LedenCollectVoiceprint, LedenCollectVoiceprintVo>{

    LedenCollectVoiceprint selectVoicePrintByPersonCode(String id);

    LedenCollectVoiceprint selectVoicePrintByPersonCodeBlob(String id);

    LedenCollectVoiceprint selectVoicePrintByPersonCodeBlobSWSJ(String id);

    int deleteVoiceprintByPersonId(String personId);
}