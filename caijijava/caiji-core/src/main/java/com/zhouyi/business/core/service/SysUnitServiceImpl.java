package com.zhouyi.business.core.service;

import com.zhouyi.business.core.common.ReturnCode;
import com.zhouyi.business.core.dao.SysUnitMapper;
import com.zhouyi.business.core.model.PageData;
import com.zhouyi.business.core.model.Response;
import com.zhouyi.business.core.model.SysUnit;
import com.zhouyi.business.core.model.SysUser;
import com.zhouyi.business.core.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author 李秸康
 * @ClassNmae: SystemUnitServiceImpl
 * @Description: 系统部门服务接口是实现
 * @date 2019/6/20 16:36
 * @Version 1.0
 **/
@Service
public class SysUnitServiceImpl implements SysUnitService {


    @Autowired
    private SysUnitMapper sysUnitMapper;

    /**
     * 查询部门信息集合
     *
     * @return 部门集合信息
     */
    @Override
    public List<SysUnit> listSysUnits() {
        return null;
    }

    /**
     * 根据私钥删除系统部门信息
     *
     * @param unitCode 私钥字符串
     * @return 删除结果
     */
    @Override
    @Transactional
    public Boolean removeByPrimaryKey(String unitCode) {
        SysUnit sysUnit = new SysUnit();
        sysUnit.setUnitCode(unitCode);
        sysUnit.setDeleteFlag("1");

        sysUnitMapper.updateByPrimaryKeySelective(sysUnit); //禁用当前部门
        //查询出该部门的上级部门并修改是否是根节点
        String uppperUnitCode = sysUnitMapper.selectByPrimaryKey(unitCode).getUpperUnitCode();

        boolean isLeaf=false;
        List<SysUnit> childrenUnits = sysUnitMapper.getSysUnitByParent(uppperUnitCode);
        if (childrenUnits != null && childrenUnits.size() > 0) {
            for (SysUnit childrenUnit : childrenUnits) {
                if (childrenUnit.getDeleteFlag().equals("0")){
                    isLeaf=true;
                }
            }
        }

        if(!isLeaf){

            //更新上级部门为叶子
            SysUnit upperUnit=new SysUnit();
            upperUnit.setUnitCode(uppperUnitCode);
            upperUnit.setIsLeaf(0);
            sysUnitMapper.updateByPrimaryKeySelective(upperUnit);
        }

        return true;
    }

    /**
     * 新增系统部门信息
     *
     * @param sysUnit 部门信息对象
     * @return
     */
    @Override
    public Boolean addSysUnit(SysUnit sysUnit) {
        return sysUnitMapper.insert(sysUnit) == 1 ? true : false;
    }

    /**
     * 根据部门编码查询详细部门信息
     *
     * @param unitCode 部门编码
     * @return
     */
    @Override
    public SysUnit searchByPrimaryKey(String unitCode) {
        return sysUnitMapper.selectByPrimaryKey(unitCode);
    }

    /**
     * 选择性修改部门信息
     *
     * @param record 修改对象
     * @return
     */
    @Override
    public Boolean modifyByPrimaryKeySelective(SysUnit record) {
        record.setUpdateDatetime(new Date());
        return sysUnitMapper.updateByPrimaryKeySelective(record) == 1 ? true : false;
    }

    /**
     * 完全修改部门信息
     *
     * @param record 修改对象
     * @return
     */
    @Override
    public Boolean modifyByPrimaryKey(SysUnit record) {
        record.setUpdateDatetime(new Date());
        return sysUnitMapper.updateByPrimaryKey(record) == 1 ? true : false;
    }

    /**
     * 选择性新增部门信息
     *
     * @param record
     * @return
     */
    @Override
    @Transactional
    public Boolean addSelective(SysUnit record) {

        //将上级部门节点修改为0（代表有下级）
        SysUnit upperSysUnit = new SysUnit();
        upperSysUnit.setUnitCode(record.getUpperUnitCode());
        upperSysUnit.setIsLeaf(1);
        sysUnitMapper.updateByPrimaryKeySelective(upperSysUnit);
        record.setCreateDatetime(new Date());
        return sysUnitMapper.insertSelective(record) == 1 ? true : false;
    }


    /**
     * 根据条件筛选部门相关信息
     *
     * @param conditions 条件集合
     * @return
     */
    @Override
    public List<SysUnit> searchUnitsByConditions(Map<String, Object> conditions) {
        return sysUnitMapper.listUnitsByConditions(conditions);
    }


    /**
     * 分页获取部门信息
     *
     * @param conditions 分页条件
     * @return
     */
    @Override
    public PageData<SysUnit> searchUnitsPage(Map<String, Object> conditions) {

        List<SysUnit> list = sysUnitMapper.getSysUnitByParent("440000000000");
        List<SysUnit> sysUnitList = sysUnitMapper.listUnitsByConditions(conditions); //根据条件得到数据集合
        Integer totalCount = sysUnitMapper.getUnitCountByConditions(conditions); //获取该条件下的记录数

        PageData<SysUnit> pageData = new PageData<SysUnit>();
        pageData.setDataList(sysUnitList);
        pageData.setTotalCount(totalCount);
        pageData.setpSize((int) conditions.get("pSize"));
        return pageData;
    }

    /**
     * 查询所有部门信息
     *
     * @return
     */
    @Override
    public List<SysUnit> listAllSysUnit(String provinceId) {
        return sysUnitMapper.getAllUnits(provinceId);
    }


    /**
     * 根据父级部门编码查询
     *
     * @param conditions
     * @return
     */
    @Override
    public List<SysUnit> listSysUnitByParent(Map<String, Object> conditions) {
        return null;
    }


    /**
     * 根据伏击父级id查询部门列表
     *
     * @param useCode
     * @return
     */
    @Override
    public List<SysUnit> getSysUnitByParent(String useCode) {
        return sysUnitMapper.getSysUnitByParent(useCode);
    }

    /**
     * 根据部门编码查询部门数量（查重）
     *
     * @param unitCode
     * @return
     */
    @Override
    public boolean checkUnitCode(String unitCode) {
        return sysUnitMapper.getUnitCountByUnitCode(unitCode) == 1 ? true : false;
    }


    /**
     * 列表展示部门数据
     *
     * @param conditions
     * @return
     */
    @Override
    public PageData<SysUnit> listSysUnitByConditions(Map<String, Object> conditions) {
        conditions.put("list", null);
        if (conditions.get("cycle") != null && conditions.get("cycle").equals("1")) {
            List<SysUnit> belowUnits = sysUnitMapper.getSysUnitByParent(conditions.get("upperUnitCode").toString());
            //提取所有下级部门编码
            List<String> belowUnitCode = new ArrayList<>();
            fetchUnitData(belowUnits, belowUnitCode);
            conditions.put("list", belowUnitCode);
            conditions.put("parentId", null);
        } else {
            conditions.put("parentId", conditions.get("upperUnitCode"));
        }


        List<SysUnit> sysUnitList = sysUnitMapper.listUnitsByConditions(conditions); //根据条件得到数据集合
        Integer totalCount = sysUnitMapper.getUnitCountByConditions(conditions); //获取该条件下的记录数
        return new PageData<>(sysUnitList, totalCount, (int) conditions.get("pSize"));
    }

    @Override
    public List<SysUnit> listOnselfAndChildrens(String unitCode) {
        List<SysUnit> sysUnitByParent = sysUnitMapper.getSysUnitByParent(unitCode);
        SysUnit sysUnit = sysUnitMapper.selectByPrimaryKey(unitCode);
        LinkedList<SysUnit> returnList=new LinkedList<>(sysUnitByParent);
        returnList.addFirst(sysUnit);
        return returnList;
    }

    @Override
    public Response selectUnitAll() {
        List<SysUnit> list = sysUnitMapper.getUnitList();
        return ResponseUtil.getResponseInfo(ReturnCode.SUCCESS,list);
    }


    /**
     * 提取多级部门数据
     *
     * @param originData
     * @param target
     */
    private void fetchUnitData(List<SysUnit> originData, List<String> target) {
        originData.stream().forEach(x -> {
            target.add(x.getUnitCode());
            fetchUnitData(x.getChildUnit(), target);
        });
    }


}
