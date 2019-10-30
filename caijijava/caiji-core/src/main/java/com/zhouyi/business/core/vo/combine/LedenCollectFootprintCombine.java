package com.zhouyi.business.core.vo.combine;

import com.zhouyi.business.core.vo.headvo.LedenCollectFootprintHeader;
import com.zhouyi.business.core.vo.xml.LedenCollectFootprintXml;

import java.util.List;

/**
 * @author 李秸康
 * @ClassNmae: LedenCollectFootprintCombine
 * @Description:  足迹xml组合信息
 * @date 2019/7/4 14:20
 * @Version 1.0
 **/
public class LedenCollectFootprintCombine {

    public LedenCollectFootprintHeader head;

    public List<LedenCollectFootprintXml> data;


    public LedenCollectFootprintHeader getHead() {
        return head;
    }

    public void setHead(LedenCollectFootprintHeader head) {
        this.head = head;
    }

    public List<LedenCollectFootprintXml> getData() {
        return data;
    }

    public void setData(List<LedenCollectFootprintXml> data) {
        this.data = data;
    }
}
