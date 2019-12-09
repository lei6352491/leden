package com.zhouyi.business.core.model.xinzhen;

import lombok.Data;
import lombok.ToString;

/**
 * @Author: first
 * @Date: 下午2:28 2019/12/1
 * @Description: 虹膜接收服务实体
**/
@Data
@ToString(callSuper = true)
public class IrisReceive extends IrisCommonsSecond{

    /**
     * 被采集人的人员类别
     */
    private String bcjr_rylb;


    /**
     * 签发机关
     */
    private String bcjr_zjqfjg;


    /**
     * 有效期限
     */
    private String bcjr_zjyxqx;


    /**
     * 采集备注
     */
    private String cjbz;

}

