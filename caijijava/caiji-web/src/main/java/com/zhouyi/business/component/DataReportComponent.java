package com.zhouyi.business.component;

import com.zhouyi.business.core.utils.HttpUtil;
import com.zhouyi.business.core.utils.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * @Author: first
 * @Date: 下午10:34 2019/11/4
 * @Description: 数据上报组件
**/
@Component
public class DataReportComponent {

    private static final Logger logger= LoggerFactory.getLogger(DataReportComponent.class);

    @Value("${provinceComprehensive.ip}")
    private String provinceIp;
    @Value("${provinceComprehensive.port}")
    private String provincePort;
    @Value("${provinceComprehensive.interfaces.dataNumber}")
    private String dataNumberAddress;


    /**
     * 生成采集数据编号
     * @param unitCode 单位代码
     * @return 采集数据编号(相当远人员编号)
     */
    public String generaterDataNumber(String unitCode) throws Exception {
        StringBuffer urlBuffer=new StringBuffer("http://");
        urlBuffer.append(provinceIp);
        urlBuffer.append(":");
        urlBuffer.append(provincePort);
        urlBuffer.append(dataNumberAddress);

        HttpUtil.sendPost(urlBuffer.toString(),new HashMap<String,String>(){{put("unitCode",unitCode);}});
        return null;
    }

}
