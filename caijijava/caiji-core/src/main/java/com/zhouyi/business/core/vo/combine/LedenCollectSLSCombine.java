package com.zhouyi.business.core.vo.combine;

import com.zhouyi.business.core.vo.headvo.LedenCollectSLSHeaderVo;
import com.zhouyi.business.core.vo.xml.LedenCollectSLSXml;

/**
 * @author 李秸康
 * @ClassNmae: LedenCollectSLSCombine
 * @Description: 体貌/特征/足迹。。组合
 * @date 2019/7/3 8:48
 * @Version 1.0
 **/
public class LedenCollectSLSCombine {
    public LedenCollectSLSHeaderVo head;
    public LedenCollectSLSXml data;


    public LedenCollectSLSHeaderVo getHead() {
        return head;
    }

    public void setHead(LedenCollectSLSHeaderVo head) {
        this.head = head;
    }

    public LedenCollectSLSXml getData() {
        return data;
    }

    public void setData(LedenCollectSLSXml data) {
        this.data = data;
    }
}
