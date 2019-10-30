package com.zhouyi.business.core.service;

import com.zhouyi.business.core.dao.LedenEquipmentParetsMapper;
import com.zhouyi.business.core.model.LedenEquipmentParets;
import com.zhouyi.business.core.model.PageData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author 李秸康
 * @ClassNmae: LedenEquipmentParetsServiceImpl
 * @Description: TODO
 * @date 2019/6/25 10:18
 * @Version 1.0
 **/
@Service
public class LedenEquipmentParetsServiceImpl implements LedenEquipmentParetsService {

    @Autowired
    private LedenEquipmentParetsMapper ledenEquipmentParetsMapper;


    /**
     * 获取分页对象
     * @param conditions
     * @return
     */
    @Override
    public PageData<LedenEquipmentParets> getLedenEquipmentParetsPage(Map<String, Object> conditions) {
        List<LedenEquipmentParets> list=ledenEquipmentParetsMapper.listLedenEquipmentParetesByConditions(conditions);
        Integer totalCount=ledenEquipmentParetsMapper.getLedenEquipmentParetsCountByConditions(conditions);
        PageData<LedenEquipmentParets> pageData=new PageData<>(list,totalCount,(int)conditions.get("pSize"));
        return pageData;
    }

    /**
     * 根据id获取配件信息
     * @param id
     * @return
     */
    @Override
    public LedenEquipmentParets getLedenEquipmentParetsById(Integer id) {
        return ledenEquipmentParetsMapper.selectByPrimaryKey(id.toString());
    }


    /**
     * 添加配件信息
     *
     * @param ledenEquipmentParets
     * @return
     */
    @Override
    public Boolean addLedenEquipmentParets(LedenEquipmentParets ledenEquipmentParets) {
        return ledenEquipmentParetsMapper.insertSelective(ledenEquipmentParets)==1?true:false;
    }


    /**
     * 移除配件信息
     * @param id
     * @return
     */
    @Override
    public Boolean removeLedenEquipmentParetsById(Integer id) {
        return ledenEquipmentParetsMapper.deleteByPrimaryKey(id.toString())==1?true:false;
    }


    /**
     * 更新配件信息
     * @param ledenEquipmentParets
     * @return
     */
    @Override
    public Boolean updateLedenEquipmentParets(LedenEquipmentParets ledenEquipmentParets) {
        return ledenEquipmentParetsMapper.updateByPrimaryKeySelective(ledenEquipmentParets)==1?true:false;
    }




}
