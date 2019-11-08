package com.zhouyi.business.core.service;

import com.zhouyi.business.core.common.ReturnCode;
import com.zhouyi.business.core.dao.LedenCollectFourfingerMapper;
import com.zhouyi.business.core.dao.LedenCollectPalmMapper;
import com.zhouyi.business.core.model.LedenCollectFourfinger;
import com.zhouyi.business.core.model.LedenCollectPalm;
import com.zhouyi.business.core.model.Response;
import com.zhouyi.business.core.utils.ResponseUtil;
import com.zhouyi.business.core.vo.LedenCollectPalmVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Encoder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class LedenCollectPalmServiceImpl
        extends BaseServiceImpl<LedenCollectPalm, LedenCollectPalmVo>
        implements LedenCollectPalmService {

    @Autowired
    private LedenCollectPalmMapper ledenCollectPalmMapper;

    @Autowired
    private LedenCollectFourfingerMapper ledenCollectFourfingerMapper;

    /**
     * 根据人员编号查询掌纹与四指指纹信息
     */
    @Override
    public Response selectPalmOrFourFingerByPersonCode(String id) {
        //获取掌纹信息
        List<LedenCollectPalm> palms = ledenCollectPalmMapper.selectPalmFingerByPersonCode(id);
        //获取四指指纹信息
        //List<LedenCollectFourfinger> fourfingers = ledenCollectFourfingerMapper.selectFourFingerByPersonCode(id);
        if (palms.size() <= 0)
            return ResponseUtil.returnError(ReturnCode.ERROR_05);
        //把数据添加到集合中
        List list = new ArrayList<>();
        //BASE64Encoder base64Encoder = new BASE64Encoder();

        //加密图片信息
        palms.stream().forEach(s -> {
                /*if ("0".equals(s.getZhwZzhwqsqkdm())){
                    String encode = base64Encoder.encode(s.getZhwTxsj());
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("data:image/png;base64,");
                    stringBuilder.append(encode);
                    s.setZzwzp(stringBuilder.toString());
                    s.setZhwTxsj(null);
                }*/
            if (s.getZhwTxsj() != null) {
                s.setZzwzp(new String(s.getZhwTxsj()));
                s.setZhwTxsj(null);
            }
        });

        /*if (fourfingers.size() > 0){
            //加密图片信息
            fourfingers.stream().forEach( s -> {
                *//*if ("0".equals(s.getSlzZzhwqsqkdm())){
                    String encode = base64Encoder.encode(s.getSlzTxsj());
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("data:image/png;base64,");
                    stringBuilder.append(encode);
                    s.setCzwzp(stringBuilder.toString());
                    s.setSlzTxsj(null);
                }*//*
                if (s.getSlzTxsj() != null){
                    s.setCzwzp(new String(s.getSlzTxsj()));
                    s.setSlzTxsj(null);
                }
            });
        }*/
        list.add(palms);
        //list.add(fourfingers);
        return ResponseUtil.getResponseInfo(ReturnCode.SUCCESS, list);
    }

    @Override
    public List<LedenCollectPalm> listPalmsByPersonCode(String personCode) {
        return ledenCollectPalmMapper.listPalmByConditions(new HashMap<String,Object>(1){{put("ryjcxxcjbh",personCode);}});
    }
}
