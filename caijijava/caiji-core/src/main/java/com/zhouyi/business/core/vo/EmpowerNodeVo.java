package com.zhouyi.business.core.vo;

import com.zhouyi.business.core.model.LedenCollectNode;

/**
 * @author 李秸康
 * @ClassNmae: EmpowerNodeVo
 * @Description: 授权节点
 * @date 2019/7/5 17:16
 * @Version 1.0
 **/
public class EmpowerNodeVo {


    private String nodeName; //节点

    private int value=0; //值

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
