package com.zhouyi.business.core.service.iris;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zhouyi.business.core.dao.LedenCollectIrisMapper;
import com.zhouyi.business.core.model.xinzhen.*;
import com.zhouyi.business.core.utils.HttpUtil;
import com.zhouyi.business.core.utils.MathUtil;
import com.zhouyi.business.core.vo.ResponseVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: first
 * @Date: 下午4:32 2019/12/9
 * @Description: 虹膜对接实现
**/
@Service
public class IrisButtServiceImpl implements IrisButtService{

    @Autowired
    private LedenCollectIrisMapper ledenCollectIrisMapper;
    @Autowired
    private IdSecretVersion idSecretVersion;

    @Value("${criminal-investigation.interfaces.iris_collect_interface}")
    private String irisCollectInterface;

    @Value("${criminal-investigation.interfaces.iris_examine_interface}")
    private String irisExamineInterface;

    @Value("${criminal-investigation.interfaces.iris_check_interface}")
    private String irisCheckInterface;

    @Value("${criminal-investigation.interfaces.getCodeMessage_interface}")
    private String getCodeMessageInterface;


    @Override
    public IrisReceive irisCollect(String rybh) {
        IrisReceive irisReceive = ledenCollectIrisMapper.irisCollectSearch(rybh);
        BeanUtils.copyProperties(idSecretVersion,irisReceive);
        setRequestId(irisReceive,irisReceive.getUser_dept());
        //调用接口

        try {
            HttpUtil.sendPostByJson(irisCollectInterface,JSON.parseObject(JSONObject.toJSONString(irisReceive),Map.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return irisReceive;
    }

    @Override
    public IrisCommons irisExamine(String rybh) {
        IrisCommons irisCommons = ledenCollectIrisMapper.selectCommons(rybh);
        setRequestId(irisCommons,irisCommons.getUser_dept());
        try {
            HttpUtil.sendPostByform(irisExamineInterface,JSON.parseObject(JSONObject.toJSONString(irisCommons),Map.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return irisCommons;
    }

    @Override
    public IrisComparsion irisCheck(String rybh) {
        IrisComparsion irisComparsion = ledenCollectIrisMapper.irisComparsionSearch(rybh);
        setRequestId(irisComparsion,irisComparsion.getUser_dept());

        try {
            HttpUtil.sendPostByform(irisCheckInterface,JSON.parseObject(JSONObject.toJSONString(irisComparsion),Map.class));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return irisComparsion;
    }


    @Override
    public String getCodeMessage(String codeType,String pId) {

        ResponseVo responseVo = null;
        try {
            responseVo = HttpUtil.sendPostByform(getCodeMessageInterface, new HashMap(4) {{
                put("client_id", idSecretVersion.getClient_id());
                put("client_secret", idSecretVersion.getClient_secret());
                put("codeType", codeType);
                put("pId", pId);
            }});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseVo.getData();
    }

    private void setRequestId(IrisCommons irisCommons,String unitCode){
        irisCommons.setRequest_id(generatorRequestId(irisCommons.getSbcsdm(),unitCode));
    }

    /**
     * 生成请求id
     * @param equipmentVendorCode
     * @return
     */
    private String generatorRequestId(String equipmentVendorCode,String unitCode){
        StringBuffer requestId=new StringBuffer("HMCJ-");
        requestId.append(equipmentVendorCode).append(new SimpleDateFormat("yyyyMMdd").format(new Date()))
                .append(MathUtil.generateRandomCode(8));
        return requestId.toString();

    }
}
