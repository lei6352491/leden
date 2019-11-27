package com.zhouyi.business.core.service;

import com.zhouyi.business.core.model.LedenCollectPerson;
import com.zhouyi.business.core.model.PersonResult;
import com.zhouyi.business.core.model.Response;
import com.zhouyi.business.core.model.months.MonthStatistical;
import com.zhouyi.business.core.model.provincecomprehensive.pojo.StandardPerson;
import com.zhouyi.business.core.vo.LedenConllectPersonVo2;

import java.util.List;
import java.util.Map;

/**
 * @author 李秸康
 * @ClassNmae: LedenCollectPersonService
 * @Description: 个人信息业务接口
 * @date 2019/7/1 15:29
 * @Version 1.0
 **/

public interface LedenCollectPersonService {


    /**
     * 将xml中的数据写入数据库
     * @param path
     * @return
     */
    Boolean insertCollectPersonByXml(String path);

    Response selectAllCJRXM();

    Response<Map<String,Object>> selectDataList(LedenConllectPersonVo2 ledenConllectPersonVo2);

    Response<PersonResult> selectPersonByPersonCode(String id);

    /**
     * 根据条件获取人员信息
     * @param maps
     * @return
     */
    LedenCollectPerson getLedenCollectPersonByConditions(Map<String,Object> maps);


    /**
     * 获取对接模型数据
     * @param personCode 标采的人员编号
     * @return
     */
    StandardPerson getStandardPerson(String personCode);


    /**
     * 根据人员编号更新人员信息
     * @param ledenCollectPerson
     */
    void updatePersonByUserCode(LedenCollectPerson ledenCollectPerson);


    /**
     * 获取本月采集数量
     * @return
     */
    List<MonthStatistical> getMonthStatistical();
}
