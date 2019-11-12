package com.zhouyi.business.core.model.provincecomprehensive;

import lombok.Data;

/**
 * @Author: first
 * @Date: 上午11:38 2019/10/31
 * @Description: 体貌特征
**/
@Data
public class BodySignInfo {
    /**
     * 体貌部位代码
     */
    private String positionCode;


    /**
     * 体毛方位代码
     */
    private String OrientationCode;


    /**
     * 数量代码
     */
    private String amountCode;


    /**
     * 体貌标记代码
     */
    private String signCode;

    private String remark;

    /**
     * 文件名(必填)
     */
    private String photo;
}
