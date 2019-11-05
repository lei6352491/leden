package com.zhouyi.business.core.dao;

import com.zhouyi.business.core.model.SysUnit;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface SysUnitMapper {


    int deleteByPrimaryKey(String unitCode);

    int insert(SysUnit record);

    int insertSelective(SysUnit record);

    SysUnit selectByPrimaryKey(String unitCode);

    int updateByPrimaryKeySelective(SysUnit record);

    int updateByPrimaryKey(SysUnit record);

    /**
     * 根据条件查询部门列表（条件：暂无）
     * @return
     */
    List<SysUnit> listUnitsByConditions(Map<String,Object> conditions);

    /**
     * 根据条件查询出数据条数
     * @param conditions
     * @return
     */
    int getUnitCountByConditions(Map<String,Object> conditions);


    /**
     * 根据父级id查询部门集合
     * @param UNIT_CODE
     * @return
     */
    List<SysUnit> getSysUnitByParent(String UNIT_CODE);


    /**
     * 查询省下的所有部门
     * @param provinceId
     * @return
     */
    List<SysUnit> getAllUnits(String provinceId);


    /**
     * 根据部门编码查询部门信息
     * @param unitCode
     * @return
     */
    int getUnitCountByUnitCode(String unitCode);

    List<SysUnit> getUnitList();


}