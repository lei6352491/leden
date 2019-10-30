package com.zhouyi.business.core.vo.combine;

import com.zhouyi.business.core.vo.headvo.LedenCollectHandWritingHeaderVo;
import com.zhouyi.business.core.vo.xml.LedenCollectHandWritingXml;

/**
 * @author 李秸康
 * @ClassNmae: LedenCollectHandWritingCombine
 * @Description: 笔记组合
 * @date 2019/7/4 16:06
 * @Version 1.0
 **/
public class LedenCollectHandWritingCombine {

    public LedenCollectHandWritingHeaderVo head;

    public LedenCollectHandWritingXml data;


    public LedenCollectHandWritingHeaderVo getHead() {
        return head;
    }

    public void setHead(LedenCollectHandWritingHeaderVo head) {
        this.head = head;
    }

    public LedenCollectHandWritingXml getData() {
        return data;
    }

    public void setData(LedenCollectHandWritingXml data) {
        this.data = data;
    }
}
