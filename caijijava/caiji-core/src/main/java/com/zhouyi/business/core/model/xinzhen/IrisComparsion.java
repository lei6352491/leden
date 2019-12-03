package com.zhouyi.business.core.model.xinzhen;

import lombok.Data;

/**
 * @Author: first
 * @Date: 下午2:32 2019/12/1
 * @Description: 虹膜比对实体
**/
@Data
public class IrisComparsion extends IrisCommons{

    /**
     * 签发机关
     */
    private String bcjrQfjg;


    /**
     * 有效日期
     */
    private String bcjrYxqx;


}
