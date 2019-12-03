package com.zhouyi.business.core.model.xinzhen;

import lombok.Data;

/**
 * @Author: first
 * @Date: 下午2:28 2019/12/1
 * @Description: 虹膜接收服务实体
**/
@Data
public class IrisReceive extends IrisCommons{

    /**
     * 被采集人的人员类别
     */
    private String bcjrRylb;


    /**
     * 签发机关
     */
    private String bcjrZjqfjg;


    /**
     * 有效期限
     */
    private String bcjrZjyxqx;


    /**
     * 采集备注
     */
    private String cjbz;

}

