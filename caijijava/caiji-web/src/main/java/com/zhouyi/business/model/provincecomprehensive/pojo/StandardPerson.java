package com.zhouyi.business.model.provincecomprehensive.pojo;

import com.zhouyi.business.core.model.LedenCollectPerson;
import lombok.Data;

/**
 * @Author: first
 * @Date: 下午2:54 2019/11/4
 * @Description: 和综采对接的人员信息类
**/
@Data
public class StandardPerson extends LedenCollectPerson {
    /**
     * 指纹编号(人员编号去掉R)
     */
    private String zwbh;

    /**
     * 采集信息原因(字典表中数据)
      */
    private String cjxxyy;

    /**
     * 采集点代码（省综返回的代码）
     */
    private String cjddm;


    /**
     * 身份证照片名称
     */
    private String idCardPhoto;


    /**
     * 户籍地的详情地址
     */
    private String hjdxzqh;

    /**
     * 现住地行政区划
     */
    private String xzdxzqh;
}
