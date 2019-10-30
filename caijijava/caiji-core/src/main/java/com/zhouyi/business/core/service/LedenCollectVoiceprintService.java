package com.zhouyi.business.core.service;

import com.zhouyi.business.core.model.LedenCollectVoiceprint;
import com.zhouyi.business.core.model.Response;
import com.zhouyi.business.core.vo.LedenCollectVoiceprintVo;
import org.apache.http.util.ByteArrayBuffer;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public interface LedenCollectVoiceprintService
        extends BaseService<LedenCollectVoiceprint, LedenCollectVoiceprintVo>{

    Response<Object> saveMapToRepository(List list, String userUnitCode);

    Response<LedenCollectVoiceprint> selectVoicePrintByPersonCode(String id);

    InputStream selectVoicePrintByPersonCodeBlob(String id) throws IOException;

    InputStream selectVoicePrintByPersonCodeBlobSWSJ(String id) throws IOException;


}
