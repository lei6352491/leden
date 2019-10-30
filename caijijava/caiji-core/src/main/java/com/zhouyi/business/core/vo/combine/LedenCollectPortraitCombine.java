package com.zhouyi.business.core.vo.combine;

import com.zhouyi.business.core.vo.headvo.LedenCollectPortraitHeaderVo;
import com.zhouyi.business.core.vo.xml.LedenCollectPortraitXml;

import java.util.LinkedList;
import java.util.List;

/**
 * @author 李秸康
 * @ClassNmae: LedenCollectPortraitCombine
 * @Description: 肖像信息组合（头部+数据集）
 * @date 2019/7/2 10:55
 * @Version 1.0
 **/
public class LedenCollectPortraitCombine {

    public LinkedList<LedenCollectPortraitXml> data;
    public LedenCollectPortraitHeaderVo head;

    public LinkedList<LedenCollectPortraitXml> getData() {
        return data;
    }

    public void setData(LinkedList<LedenCollectPortraitXml> data) {
        this.data = data;
    }

    public LedenCollectPortraitHeaderVo getHead() {
        return head;
    }

    public void setHead(LedenCollectPortraitHeaderVo head) {
        this.head = head;
    }
}
