package com.zhouyi.business.core.service;

import com.zhouyi.business.core.model.LedenCollectVoiceprint;
import com.zhouyi.business.core.model.Response;
import com.zhouyi.business.core.model.provincecomprehensive.pojo.StandardVoice;
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

    /**
     * 根据人员编号获取声纹信息
     * @param personCode
     * @return
     */
    LedenCollectVoiceprint getVoiceprintByPersonCode(String personCode);


    /**
     * 根据人员编号获取声纹
     * @param personCode
     * @return
     */
    StandardVoice getStandardVoByPersonCode(String personCode);


}
