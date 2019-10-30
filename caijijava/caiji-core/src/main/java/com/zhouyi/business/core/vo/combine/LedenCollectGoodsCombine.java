package com.zhouyi.business.core.vo.combine;

import com.zhouyi.business.core.vo.headvo.LedenCollectGoodsHeaderVo;
import com.zhouyi.business.core.vo.xml.LedenCollectGoodsXml;

import java.util.List;

/**
 * @author 李秸康
 * @ClassNmae: LedenCollectGoodsCombine
 * @Description: TODO
 * @date 2019/7/4 17:09
 * @Version 1.0
 **/
public class LedenCollectGoodsCombine {

    public LedenCollectGoodsHeaderVo head;

    public List<LedenCollectGoodsXml> data;

    public LedenCollectGoodsHeaderVo getHead() {
        return head;
    }

    public void setHead(LedenCollectGoodsHeaderVo head) {
        this.head = head;
    }

    public List<LedenCollectGoodsXml> getData() {
        return data;
    }

    public void setData(List<LedenCollectGoodsXml> data) {
        this.data = data;
    }
}
