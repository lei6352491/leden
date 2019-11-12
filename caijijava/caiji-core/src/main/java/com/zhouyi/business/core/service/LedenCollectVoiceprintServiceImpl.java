package com.zhouyi.business.core.service;


import com.zhouyi.business.core.common.ReturnCode;
import com.zhouyi.business.core.dao.LedenCollectVoiceprintMapper;
import com.zhouyi.business.core.exception.ExceptionCast;
import com.zhouyi.business.core.model.LedenCollectVoiceprint;
import com.zhouyi.business.core.model.Response;
import com.zhouyi.business.core.model.provincecomprehensive.pojo.StandardVoice;
import com.zhouyi.business.core.utils.ResponseUtil;
import com.zhouyi.business.core.vo.LedenCollectVoiceprintVo;

import io.netty.handler.codec.base64.Base64Decoder;
import io.netty.handler.codec.base64.Base64Dialect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.misc.BASE64Decoder;

import java.io.*;
import java.util.*;

@Service
public class LedenCollectVoiceprintServiceImpl
        extends BaseServiceImpl<LedenCollectVoiceprint, LedenCollectVoiceprintVo>
        implements LedenCollectVoiceprintService {

    @Autowired
    private LedenCollectVoiceprintMapper ledenCollectVoiceprintMapper;

    @Override
    @Transactional
    public Response<Object> saveMapToRepository(List list, String userUnitCode) {
        for (Object object : list) {
            if (list == null) {
                return ResponseUtil.returnError(ReturnCode.ERROR_14);
            }
            LedenCollectVoiceprint ledenCollectVoiceprint = (LedenCollectVoiceprint) object;
            ledenCollectVoiceprint.setPkId(UUID.randomUUID().toString().replace("-", ""));
            //删除原有数据
            ledenCollectVoiceprintMapper.deleteVoiceprintByPersonId(ledenCollectVoiceprint.getRyjcxxcjbh());
            this.saveData(ledenCollectVoiceprint);
        }
        return ResponseUtil.getResponseInfo(true);
    }

    //根据人员编号获取声纹信息
    @Override
    public Response<LedenCollectVoiceprint> selectVoicePrintByPersonCode(String id) {
        LedenCollectVoiceprint ledenCollectVoiceprint = ledenCollectVoiceprintMapper.selectVoicePrintByPersonCode(id);
        return ResponseUtil.getResponseInfo(ReturnCode.SUCCESS, ledenCollectVoiceprint);
    }

    @Override
    public InputStream selectVoicePrintByPersonCodeBlob(String id) throws IOException {
        LedenCollectVoiceprint ledenCollectVoiceprint = ledenCollectVoiceprintMapper.selectVoicePrintByPersonCodeBlob(id);
        if (ledenCollectVoiceprint == null) {
            ExceptionCast.cast(ResponseUtil.returnError(ReturnCode.ERROR_05));
        }
        //解密音频数据
        String base64Ypsj = new String(ledenCollectVoiceprint.getYpsj());
        byte[] bytes = new BASE64Decoder().decodeBuffer(base64Ypsj);
        ledenCollectVoiceprint.setYpsj(bytes);

        InputStream inputStream = null;
        if (ledenCollectVoiceprint.getYpsj() != null) {
            byte[] ypsj = ledenCollectVoiceprint.getYpsj();
            inputStream = new ByteArrayInputStream(ypsj);
            return inputStream;
        }
        return inputStream;
    }

    @Override
    public InputStream selectVoicePrintByPersonCodeBlobSWSJ(String id) throws IOException {
        LedenCollectVoiceprint ledenCollectVoiceprint = ledenCollectVoiceprintMapper.selectVoicePrintByPersonCodeBlobSWSJ(id);
        if (ledenCollectVoiceprint == null) {
            ExceptionCast.cast(ResponseUtil.returnError(ReturnCode.ERROR_05));
        }
        //解密音频数据
        String base64Swsj = new String(ledenCollectVoiceprint.getSwsj());
        byte[] bytes = new BASE64Decoder().decodeBuffer(base64Swsj);
        ledenCollectVoiceprint.setSwsj(bytes);

        InputStream inputStream = null;
        if (ledenCollectVoiceprint.getSwsj() != null) {
            byte[] swsj = ledenCollectVoiceprint.getSwsj();
            inputStream = new ByteArrayInputStream(swsj);
            return inputStream;
        }
        return inputStream;
    }

    @Override
    public LedenCollectVoiceprint getVoiceprintByPersonCode(String personCode) {
        LedenCollectVoiceprint ledenCollectVoiceprint = ledenCollectVoiceprintMapper.selectVoicePrintByPersonCode(personCode);
        LedenCollectVoiceprint ledenCollectVoiceprint1 = ledenCollectVoiceprintMapper.selectVoicePrintByPersonCodeBlob(personCode);
        ledenCollectVoiceprint.setYpsj(ledenCollectVoiceprint1.getYpsj());
        return ledenCollectVoiceprint;
    }


    @Override
    public StandardVoice getStandardVoByPersonCode(String personCode) {
        return ledenCollectVoiceprintMapper.getVoiceByPersonCode(personCode);
    }


}
