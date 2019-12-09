package com.zhouyi.business.core.service.iris;

import com.zhouyi.business.core.model.xinzhen.IrisCommons;
import com.zhouyi.business.core.model.xinzhen.IrisComparsion;
import com.zhouyi.business.core.model.xinzhen.IrisReceive;

/**
 * @Author: first
 * @Date: 下午4:03 2019/12/9
 * @Description: 虹膜对接服务
**/
public interface IrisButtService {

    /**
     * 虹膜采集
     * @return
     */
    IrisReceive irisCollect(String rybh);

    /**
     * 虹膜检查
     * @return
     */
    IrisCommons irisExamine(String rybh);


    /**
     * 虹膜核验
     * @return
     */
    IrisComparsion irisCheck(String rybh);
}
