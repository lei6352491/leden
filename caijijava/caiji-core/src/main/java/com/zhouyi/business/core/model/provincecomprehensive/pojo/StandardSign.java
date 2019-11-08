package com.zhouyi.business.core.model.provincecomprehensive.pojo;

import com.zhouyi.business.core.model.LedenCollectSign;
import lombok.Data;

/**
 * @Author: first
 * @Date: 下午3:16 2019/11/4
 * @Description: 省综对接特征点类
**/
@Data
public class StandardSign extends LedenCollectSign {

    /**
     * 图片名称
     */
    private String photo;


    /**
     * 体表标记代码（特殊特征表的特征名称）
     */
    private String signCode;

}
