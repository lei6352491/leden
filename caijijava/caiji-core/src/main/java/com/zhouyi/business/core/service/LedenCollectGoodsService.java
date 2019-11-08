package com.zhouyi.business.core.service;

import com.zhouyi.business.core.model.LedenCollectGoods;
import com.zhouyi.business.core.model.PageData;
import com.zhouyi.business.core.model.provincecomprehensive.pojo.StandardGoods;

import java.util.List;

/**
 * @author 李秸康
 * @ClassNmae: LedenCollectGoodsService
 * @Description: 随声物品 接口
 * @date 2019/7/5 8:24
 * @Version 1.0
 **/
public interface LedenCollectGoodsService {


    /**
     * 从xml文件中读取数据
     * @param path
     * @return
     */
    boolean inputGoodsByXml(String path);


    /**
     * 获取人员所有物品
     * @param personId
     * @return
     */
    PageData<LedenCollectGoods> goods(String personId, String pNo, String pSize);

    /**
     * 获取物品信息
     * @param wpbh
     * @return
     */
    LedenCollectGoods good(String wpbh);


    /**
     * 根据人员编号获取物品信息
     * @param personCode
     * @return
     */
    List<StandardGoods> listGoodsByPersonCode(String personCode);
}
