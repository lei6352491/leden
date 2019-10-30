package com.zhouyi.business.core.service;

import com.zhouyi.business.core.model.PersonResult;
import com.zhouyi.business.core.model.Response;
import com.zhouyi.business.core.vo.LedenConllectPersonVo2;

import java.util.Map;

/**
 * @author 李秸康
 * @ClassNmae: LedenCollectPersonService
 * @Description: 个人信息业务接口
 * @date 2019/7/1 15:29
 * @Version 1.0
 **/

public interface LedenCollectPersonService {


    /**
     * 将xml中的数据写入数据库
     * @param path
     * @return
     */
    Boolean insertCollectPersonByXml(String path);

    Response selectAllCJRXM();

    Response<Map<String,Object>> selectDataList(LedenConllectPersonVo2 ledenConllectPersonVo2);

    Response<PersonResult> selectPersonByPersonCode(String id);
}
