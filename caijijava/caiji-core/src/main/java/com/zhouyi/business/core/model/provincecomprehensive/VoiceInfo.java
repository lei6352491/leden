package com.zhouyi.business.core.model.provincecomprehensive;

import lombok.Data;

/**
 * @Author: first
 * @Date: 上午11:53 2019/10/31
 * @Description: 声纹
**/
@Data
public class VoiceInfo {

    /**
     * 发音方式 1念读，02自述03交谈
     */
    private String speakTypeCode;

    /**
     * ??
     */
    private String speakTypeName;


    private String languageTypeCode;


    private String languageTypeName;


    /**
     * 方言类别
     */
    private String dialectCode;


    private String dialectName;

    /**
     * 设备型号
     */
    private String deviceID;

    /**
     * 有效时长
     */
    private String invalidDuration;


    /**
     * 截幅比例
     */
    private String clipPercentage;


    /**
     * 信噪比
     */
    private String snrEst;


    /**
     * 平均能量
     */
    private String speechAvgEnergy;

    /**
     * 说话人数量
     */
    private String speakerCount;
}
