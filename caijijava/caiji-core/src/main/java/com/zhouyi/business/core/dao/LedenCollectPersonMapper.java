package com.zhouyi.business.core.dao;

import com.zhouyi.business.core.model.LedenCollectPerson;
import com.zhouyi.business.core.model.PersonResult;
import com.zhouyi.business.core.model.months.MonthStatistical;
import com.zhouyi.business.core.model.provincecomprehensive.pojo.StandardPerson;
import com.zhouyi.business.core.vo.LedenConllectPersonVo;
import com.zhouyi.business.core.vo.LedenConllectPersonVo2;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Mapper
public interface LedenCollectPersonMapper extends BaseMapper<LedenConllectPersonVo2,String>{
    int deleteByPrimaryKey(String ryjcxxcjbh);

    int insert(LedenCollectPerson record);

    int insertSelective(LedenCollectPerson record);

    LedenConllectPersonVo2 selectByPrimaryKey(String ryjcxxcjbh);

    int updateByPrimaryKeySelective(LedenCollectPerson record);

    int updateByPrimaryKeyWithBLOBs(LedenCollectPerson record);

    int updateByPrimaryKey(LedenCollectPerson record);

    int findCountByModel(LedenConllectPersonVo2 ledenConllectPersonVo2);

    List<LedenCollectPerson> listLedenCollectPersonByConditions(Map<String,Object> conditions);

    int getLedenCollectPersonCountByConditions(Map<String,Object> conditions);

    List<LedenCollectPerson> selectAllCJRXM();

    /**
     * 批量新增
     * @return
     */
    int insertLedenCollectPersons(LedenCollectPerson ledenCollectPerson);

    List<PersonResult> selectCollectPersonList(LedenConllectPersonVo2 ledenConllectPersonVo2);

    Integer selectCollectPersonListCount(LedenConllectPersonVo2 ledenConllectPersonVo2);

    PersonResult selectPersonByPersonCode(String id);

    /**
     * 根据条件筛选人员信息
     * @param conditions
     * @return
     */
    List<LedenCollectPerson> getLedenCollectPersonByConditions(Map<String,Object> conditions);


    /**
     * 封装上传对应实体类
     * @param person
     * @return
     */
    StandardPerson getStandardPerson(String person);


    /**
     * 根据人员编号修改人员信息
     * @param data
     * @return
     */
    int updatePersonByPersonCode(Map<String,Object> data);

    //获取时间段采集人数
    int selectCollectNumber(@Param("startDate")Date startDate,@Param("endDate")Date endDate,@Param("unitCode")String unitCode);


    /**
     * 根据指定月份查询数据
     * @param time
     * @return
     */
    List<MonthStatistical> getMonthStatistical(@Param("time") String time,@Param("unitCode")String unitCode);
}