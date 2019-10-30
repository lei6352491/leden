package com.zhouyi.business.core.dao;

import com.zhouyi.business.core.model.xinzhen.XZPerson;

import java.util.List;

/**
 * @author 李秸康
 * @ClassNmae: XZPersonMapper
 * @Description: TODO 接口 刑侦数据层接口
 * @date 2019/7/19 14:37
 * @Version 1.0
 **/
public interface XZPersonMapper {

    /**
     * 根据人员编号获取刑侦数据
     * @param rybh 人员编号
     * @return
     */
    List<XZPerson> getXZPersonInfoByRybh(String rybh);
}
