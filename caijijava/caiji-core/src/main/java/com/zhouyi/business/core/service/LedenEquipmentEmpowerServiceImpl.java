package com.zhouyi.business.core.service;

import com.zhouyi.business.core.common.ReturnCode;
import com.zhouyi.business.core.dao.LedenCollectNodeMapper;
import com.zhouyi.business.core.dao.LedenEquipmentEmpowerMapper;
import com.zhouyi.business.core.dao.LedenEquipmentMapper;
import com.zhouyi.business.core.model.*;
import com.zhouyi.business.core.utils.ResponseUtil;
import com.zhouyi.business.core.vo.EmpowerNodeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author 李秸康
 * @ClassNmae: LedenEquipmentEmpowerServiceImpl
 * @Description: TODO
 * @date 2019/7/5 15:38
 * @Version 1.0
 **/
@Service
public class LedenEquipmentEmpowerServiceImpl implements LedenEquipmentEmpowerService {

    @Autowired
    private LedenEquipmentMapper ledenEquipmentMapper;

    @Autowired
    private LedenEquipmentEmpowerMapper ledenEquipmentEmpowerMapper;

    @Autowired
    private LedenCollectNodeMapper ledenCollectNodeMapper;

    /**
     * 根据设备编码查询设备的授权信息
     * @param equipmentCode
     * @return
     */
    @Override
    public Map<String,String> searchEmpowerByEquipmentCode(String equipmentCode) {
        //设备授权信息集合
        List<LedenEquipmentEmpower> empowerList=ledenEquipmentEmpowerMapper.getEquipmentEmpowerByEquipmnetCode(equipmentCode);
        //获取所有节点信息
        List<LedenCollectNode> nodes=ledenCollectNodeMapper.selectAll();

        List<EmpowerNodeVo> empowerNodeVos=new ArrayList<>();

        //定义结果数据集map对象
        Map<String,String> maps=new HashMap<>();

        //进行遍历封装
        for (LedenCollectNode node : nodes) {
            maps.put(node.getNodeName(),"1");//设置默认值为1
            for (LedenEquipmentEmpower empower :
                    empowerList) {
                if(empower.getNodeSign().equals(node.getNodeCode())){
                    maps.put(node.getNodeName(),empower.getDeletag());
                    break;
                }
            }
        }
        return maps;
    }

    /**
     * 显示设备的授权信息集合
     * */
    @Override
    public Response<LedenEquipmentEmpower> selectEquipmentEmpowerListByModel(LedenEquipmentEmpower ledenEquipmentEmpower){
        //初始化查询条件(设备授权为启动)
        ledenEquipmentEmpower.setDeletag("0");
        LedenEquipment ledenEquipment = ledenEquipmentMapper.selectByPrimaryKey(ledenEquipmentEmpower.getEquipmentId());
        ledenEquipmentEmpower.setEquipmentId(ledenEquipment.getEquipmentCode());
        List<LedenEquipmentEmpower> list = ledenEquipmentEmpowerMapper.selectByModel(ledenEquipmentEmpower);
        return ResponseUtil.getResponseInfo(ReturnCode.SUCCESS,list);
    }

    /**
     * 更新设备采集节点授权
     * */
    @Override
    public Response updateEquipmentEmpower(EquipmentEmpowerRequest equipmentEmpowerRequest){

        LedenEquipment ledenEquipment = ledenEquipmentMapper.selectByPrimaryKey(equipmentEmpowerRequest.getEquipmentId());

        //设置查询条件
        LedenEquipmentEmpower ledenEquipmentEmpower = new LedenEquipmentEmpower();
        ledenEquipmentEmpower.setEquipmentId(ledenEquipment.getEquipmentCode());
        //查询该设备所有的授权节点信息
        List<LedenEquipmentEmpower> list = ledenEquipmentEmpowerMapper.selectByModel(ledenEquipmentEmpower);
        //当设备授权信息为空的时候
        if (equipmentEmpowerRequest.getNodes() == null){
            for (LedenEquipmentEmpower ledenEquipmentEmpowerChild : list){
                ledenEquipmentEmpowerChild.setDeletag("1");
                ledenEquipmentEmpowerChild.setUpdateUserId(equipmentEmpowerRequest.getUserId());
                ledenEquipmentEmpowerChild.setUpdateDatetime(new Date());
                ledenEquipmentEmpowerMapper.updateByPrimaryKey(ledenEquipmentEmpowerChild);
            }
            return ResponseUtil.getResponseInfo(true);
        }
        //初始化数据库中的该设备的信息
        for (LedenEquipmentEmpower ledenEquipmentEmpowerChild : list){
            ledenEquipmentEmpowerChild.setDeletag("1");
            ledenEquipmentEmpowerMapper.updateByPrimaryKey(ledenEquipmentEmpowerChild);
        }
        //更新该设备的授权信息
        for (String nodeString : equipmentEmpowerRequest.getNodes()){
            for (LedenEquipmentEmpower ledenEquipmentEmpowerChild : list){
                if (nodeString.equals(ledenEquipmentEmpowerChild.getNodeSign())){
                    ledenEquipmentEmpowerChild.setDeletag("0");
                    ledenEquipmentEmpowerChild.setUpdateUserId(equipmentEmpowerRequest.getUserId());
                    ledenEquipmentEmpowerChild.setUpdateDatetime(new Date());
                    ledenEquipmentEmpowerMapper.updateByPrimaryKey(ledenEquipmentEmpowerChild);
                }
            }
        }
        return ResponseUtil.getResponseInfo(true);
    }
}
