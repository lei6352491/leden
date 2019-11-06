package com.zhouyi.business.core.dao;

import com.zhouyi.business.core.model.LedenCollectPerson;
import com.zhouyi.business.core.model.PersonResult;
import com.zhouyi.business.core.vo.LedenConllectPersonVo;
import com.zhouyi.business.core.vo.LedenConllectPersonVo2;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
}