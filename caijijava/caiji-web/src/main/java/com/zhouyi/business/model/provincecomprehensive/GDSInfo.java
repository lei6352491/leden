package com.zhouyi.business.model.provincecomprehensive;

import lombok.Data;

import java.util.List;

/**
 * @Author: first
 * @Date: 上午11:25 2019/10/31
 * @Description: 物品信息
**/
@Data
public class GDSInfo {
    private String name;

    private String type;

    /**
     * 物品电子串号
     */
    private String ESN;

    private String colorCode;

    private String color;

    private String factory;

    private String field;

    private String phone;

    private String remark;

    private List<ImageInfo> imageInfos;





    @Data
    public class ImageInfo{
        /**
         * 文件名
         */
       private String image;

        /**
         * 图片备注
         */
       private String imageRemark;
    }


}
