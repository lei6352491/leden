package com.zhouyi.business.core.service;

import com.zhouyi.business.core.model.LedenCollectFootprint;
import com.zhouyi.business.core.model.Response;
import com.zhouyi.business.core.vo.LedenCollectFootprintSearchVo;
import com.zhouyi.business.core.vo.LedenCollectFootprintVo;

import java.util.List;

/**
 * @author 李秸康
 * @ClassNmae: LedenCollectFootprintService
 * @Description: 足迹业务 接口
 * @date 2019/7/4 14:29
 * @Version 1.0
 **/
public interface LedenCollectFootprintService {

    /**
     * 录入xml中的足迹苏剧
     * @param path
     * @return
     */
    Boolean inputFootprintByXml(String path);


    /**
     * 查询人员的足迹信息
     * @param RYJCXXCJBH
     * @return
     */
    List<LedenCollectFootprintSearchVo> listFoots(String RYJCXXCJBH,String pNo,String pSize);

    /**
     * 根据主键查询足迹信息
     * */
    Response<LedenCollectFootprint> getFootprint(String id);
}
