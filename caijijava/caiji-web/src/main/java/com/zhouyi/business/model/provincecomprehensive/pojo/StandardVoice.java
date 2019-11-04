package com.zhouyi.business.model.provincecomprehensive.pojo;

import com.zhouyi.business.core.model.LedenCollectVoiceprint;
import lombok.Data;

/**
 * @Author: first
 * @Date: 下午3:24 2019/11/4
 * @Description: 省综对接声纹类
**/
@Data
public class StandardVoice extends LedenCollectVoiceprint {

    /**
     * 发音方式中文（字典表）
     */
    private String speakTypeName;

    /**
     * 语种中文（中文表）
     */
    private String languageTypeName;

    /**
     * 方言名称
     */
    private String dialectName;




}
