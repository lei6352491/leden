package com.zhouyi.business.core.service;

import com.zhouyi.business.core.model.PageData;
import com.zhouyi.business.core.model.Response;
import com.zhouyi.business.core.model.SysUnit;

import java.util.List;
import java.util.Map;

/**
 * @author 李秸康
 * @ClassNmae: SysUnitService
 * @Description: 查询部门接口
 * @date 2019/6/20 16:15
 * @Version 1.0
 **/
public interface SysUnitService {


    /**
     * 查询部门信息集合
     * @return 部门集合信息
     */
    List<SysUnit> listSysUnits();


    /**
     * 根据私钥删除
     * @param unitCode 私钥字符串
     * @return
     */
    Boolean removeByPrimaryKey(String unitCode);


    /**
     * 新增系统部门
     * @param sysUnit 部门信息对象
     * @return
     */
    Boolean addSysUnit(SysUnit sysUnit);

    /**
     * 根据部门编码查询部门信息
     * @param unitCode 部门编码
     * @return 部门信息对象
     */
    SysUnit searchByPrimaryKey(String unitCode);


    /**
     * 修改系统部门信息（选择性）
     * @param record 修改对象
     * @return 修改结果
     */
    Boolean modifyByPrimaryKeySelective(SysUnit record);


    /**
     * 更新所有字段信息
     * @param record 修改对象
     * @return 修改结果
     */
    Boolean modifyByPrimaryKey(SysUnit record);

    /**
     * 新增完整部门信息
     * @param record
     * @return
     */
    Boolean addSelective(SysUnit record);

    /**
     * 根据条件分页查询部门信息
     * @param conditions 条件集合
     * @return
     */
    List<SysUnit> searchUnitsByConditions(Map<String,Object> conditions);


    /**
     * 分页获取部门信息
     * @param conditions 分页条件
     * @return
     */
    PageData<SysUnit> searchUnitsPage(Map<String,Object> conditions);


    /**
     * 查询所有部门信息
     * @return
     */
    List<SysUnit> listAllSysUnit(String provinceId);


    /**
     * 查询父级id下的部门集合
     * @param conditions
     * @return
     */
    List<SysUnit> listSysUnitByParent(Map<String,Object> conditions);

    /**
     * 根据父级code查询
     * @param userCode
     * @return
     */
    List<SysUnit> getSysUnitByParent(String userCode);


    /**
     * 根据部门编码查询数量
     */
    boolean checkUnitCode(String unitCode);


    /**
     * 部门列表查询（多级）
     */
   PageData<SysUnit> listSysUnitByConditions(Map<String,Object> conditions);


    /**
     * 返回自己以及子部门
     * @param unitCode
     * @return
     */
   List<SysUnit> listOnselfAndChildrens(String unitCode);

    Response selectUnitAll();

}
