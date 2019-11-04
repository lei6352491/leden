package com.zhouyi.business.model.provincecomprehensive.pojo;

import com.zhouyi.business.core.model.LedenCollectGoods;
import lombok.Data;

import java.util.List;

/**
 * @Author: first
 * @Date: 下午3:07 2019/11/4
 * @Description: 随身物品省宗对接类
**/
@Data
public class StandardGoods extends LedenCollectGoods {

    private List<ImagesInfo> imagesInfos;


    @Data
    public class ImagesInfo{

        private String image;

        private String remark;

    }
}
