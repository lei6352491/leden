package com.zhouyi.business.core.service;

import com.zhouyi.business.core.model.LedenCollectDna;
import com.zhouyi.business.core.model.Response;

import java.util.List;

/**
 * @author 李秸康
 * @ClassNmae: LedenCollectDNAService
 * @Description:  dna 接口
 * @date 2019/7/4 13:48
 * @Version 1.0
 **/
public interface LedenCollectDNAService {

    /**
     * 通过xml录入信息
     * @param path
     * @return
     */
    Boolean inputDNSByXml(String path);

    Response<List<LedenCollectDna>> selectDataByPersonCode(String id);
}
