package com.zhouyi.business.core.vo.combine;

import com.zhouyi.business.core.vo.headvo.LedenCollectDNAHeaderVo;
import com.zhouyi.business.core.vo.xml.LedenCollectDNAXml;

/**
 * @author 李秸康
 * @ClassNmae: LedenCollectDNACombine
 * @Description: DNA信息组合
 * @date 2019/7/4 13:43
 * @Version 1.0
 **/
public class LedenCollectDNACombine {

    public LedenCollectDNAHeaderVo head;
    public LedenCollectDNAXml data;

    public LedenCollectDNAHeaderVo getHead() {
        return head;
    }

    public void setHead(LedenCollectDNAHeaderVo head) {
        this.head = head;
    }

    public LedenCollectDNAXml getData() {
        return data;
    }

    public void setData(LedenCollectDNAXml data) {
        this.data = data;
    }
}
