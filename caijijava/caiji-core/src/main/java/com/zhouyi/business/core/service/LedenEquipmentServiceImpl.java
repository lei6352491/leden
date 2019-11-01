package com.zhouyi.business.core.service;

import com.zhouyi.business.core.common.ReturnCode;
import com.zhouyi.business.core.dao.LedenCollectNodeMapper;
import com.zhouyi.business.core.dao.LedenEquipmentEmpowerMapper;
import com.zhouyi.business.core.dao.LedenEquipmentMapper;
import com.zhouyi.business.core.dao.SysDictListMapper;
import com.zhouyi.business.core.exception.BusinessException;
import com.zhouyi.business.core.exception.ExceptionCast;
import com.zhouyi.business.core.model.*;
import com.zhouyi.business.core.utils.InitializationPageUtils;
import com.zhouyi.business.core.utils.MathUtil;
import com.zhouyi.business.core.utils.ResponseUtil;
import com.zhouyi.business.core.vo.LedenEquipmentVo;
import com.zhouyi.business.core.vo.LedenEquipmentVo2;
import com.zhouyi.business.core.vo.SysDictListVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author 李秸康
 * @ClassNmae: LedenEquipmentServiceImpl
 * @Description: TODO
 * @date 2019/6/25 10:12
 * @Version 1.0
 **/
@Service
public class LedenEquipmentServiceImpl implements LedenEquipmentService {

    @Autowired
    private LedenEquipmentMapper ledenEquipmentMapper;

    @Autowired
    private LedenEquipmentEmpowerMapper ledenEquipmentEmpowerMapper;

    @Autowired
    private LedenCollectNodeMapper ledenCollectNodeMapper;

    @Autowired
    private SysDictListMapper sysDictListMapper;

    @Autowired
    private InitializationPageUtils<LedenEquipmentVo2> initializationPageUtils;

    /**
     * 获取设备分页对象
     * @param conditions
     * @return
     */
    @Override
    public PageData<LedenEquipment> getLedenEquipmentPage(Map<String, Object> conditions) {
        List<LedenEquipment> list=ledenEquipmentMapper.listLedenEquipmentsByConditios(conditions);
        int totalCount=ledenEquipmentMapper.getLedenEquipmentCountByConditions(conditions);
        PageData<LedenEquipment> pageData=new PageData<>(list,totalCount,(int)conditions.get("pSize"));
        return pageData;
    }


    /**
     * 通过id获取设备信息
     * @param id
     * @return
     */
    @Override
    public LedenEquipment getLedenEquipmentById(Integer id) {
        return ledenEquipmentMapper.selectByPrimaryKey(id.toString());
    }

    /**
     * 添加设备信息
     * @param ledenEquipment
     * @return
     */
    @Override
    public String addLedenEquipment(LedenEquipment ledenEquipment) {
        if (StringUtils.isEmpty(ledenEquipment.getEquipmentMac())){
            ExceptionCast.cast(ResponseUtil.returnError(ReturnCode.ERROR_15));
        }
        //查询Mac地址是否已经注册
        LedenEquipment ledenEquipmentByMac = ledenEquipmentMapper.selectEquipmentByMac(ledenEquipment.getEquipmentMac());
        if (ledenEquipmentByMac != null){
            ExceptionCast.cast(ResponseUtil.returnError(ReturnCode.ERROR_1049));
        }
        try{
            ledenEquipment.setPkId(UUID.randomUUID().toString().replace("-",""));
            StringBuffer equipCodeBuffer=new StringBuffer();
            //生成一个设备编码返回给用户
            String unitCode = ledenEquipment.getUnitCode();//12位的部门编码
            equipCodeBuffer.append(unitCode);
            equipCodeBuffer.append(MathUtil.generateRandomCode(6));
            ledenEquipment.setEquipmentCode(equipCodeBuffer.toString());

            //设置设备的数据上传授权.0：未授权，1：已授权
            ledenEquipment.setStatus("0");

            ledenEquipment.setCreateDatetime(new Date());
            ledenEquipment.setDeleteFlag("0");
            //1.添加设备信息
            ledenEquipmentMapper.insertSelective(ledenEquipment);
            //2初始化设备授权信息
            initializationEquipmentEmpower(ledenEquipment);
            return equipCodeBuffer.toString();
        }catch (Exception e){
            e.printStackTrace();
            ExceptionCast.cast(ResponseUtil.returnError(ReturnCode.ERROR_01));
        }
        return null;
    }

    /**
     * 更新设备信息
     * @param ledenEquipment
     * @return
     */
    @Override
    public Boolean updateLedenEquipment(LedenEquipment ledenEquipment) {
        if (StringUtils.isEmpty(ledenEquipment.getPkId())){
            ExceptionCast.cast(ResponseUtil.returnError(ReturnCode.ERROR_02));
        }
        ledenEquipment.setUpdateDatetime(new Date());
        return ledenEquipmentMapper.updateByPrimaryKeySelective(ledenEquipment)==1?true:false;
    }

    /**
     * 移除设备信息
     * @param id
     * @return
     */
    @Override
    public Boolean removeLedenEquipmentById(Integer id) {
        return ledenEquipmentMapper.deleteByPrimaryKey(id.toString())==1?true:false;
    }


    /**
     * 采集节点注册业务
     * @param ledenEquipmentvo 采集信息对象
     * @return 设备编码
     */
    @Override
    public String collectNodeRegister(LedenEquipmentVo ledenEquipmentvo){

        //判断该设备是否注册过(判断条件：部门/mac地址)
        if(ledenEquipmentvo.getUnitCode()==null||ledenEquipmentvo.getUnitCode().equals("")){
            throw new BusinessException(ReturnCode.ERROR_1047);
        }
        List<String> macs=ledenEquipmentMapper.listEqipmentCodebyUnitCode(ledenEquipmentvo.getUnitCode());
        if(macs.contains(ledenEquipmentvo.getEquipmentMac()))
            throw new BusinessException(ReturnCode.ERROR_1048);
        if (ledenEquipmentvo.getChamberType() == null || ledenEquipmentvo.getChamberType().equals(""))
            throw new BusinessException(ReturnCode.ERROR_1050);
        //查询采集场所是否存在
        SysDictList sysDictList = new SysDictList();
        sysDictList.setCode(ledenEquipmentvo.getChamberType());
        sysDictList.setSign("DICT_CJCHSLX");
        List<SysDictList> list = sysDictListMapper.findDictByBean(sysDictList);
        if (list == null || list.size() < 1){
            throw new BusinessException(ReturnCode.ERROR_1051);
        }
        LedenEquipment ledenEquipment=new LedenEquipment();
        BeanUtils.copyProperties(ledenEquipmentvo,ledenEquipment);
        ledenEquipment.setCreateDatetime(new Date());

        StringBuffer equipCodeBuffer=new StringBuffer();
        //生成一个设备编码返回给用户
        String unitCode = ledenEquipment.getUnitCode();//12位的部门编码
        equipCodeBuffer.append(unitCode);
        equipCodeBuffer.append(MathUtil.generateRandomCode(6));

        //设置设备的数据上传授权.0：未授权，1：已授权
        ledenEquipment.setStatus("0");

        ledenEquipment.setEquipmentCode(equipCodeBuffer.toString());
        ledenEquipment.setCreateDatetime(new Date());
        ledenEquipment.setDeleteFlag("0");
        ledenEquipmentMapper.insertSelective(ledenEquipment);
        //2初始化设备授权信息
        initializationEquipmentEmpower(ledenEquipment);
        return equipCodeBuffer.toString();
    }

    /**
     * 查询设备接入注册信息列表
     * */
    @Override
    public Response selectEquipmentListByData(LedenEquipmentVo2 ledenEquipmentVo2) {
        ledenEquipmentVo2 = initializationPageUtils.initializationPage(ledenEquipmentVo2);
        List<LedenEquipmentResult> list = ledenEquipmentMapper.selectEquipmentListByData(ledenEquipmentVo2);
        Integer total = ledenEquipmentMapper.selectEquipmentTotal(ledenEquipmentVo2);
        Map<String,Object> map = new HashMap<>();
        map.put("list",list);
        map.put("total",total);
        return ResponseUtil.getResponseInfo(ReturnCode.SUCCESS,map);
    }

    /**
     * 根据设备编号查询设备信息
     * */
    @Override
    public Response selectEquipmentByEquipmentCode(String equipmentCode) {
        LedenEquipment ledenEquipment = ledenEquipmentMapper.selectEquipmentByEquipmentCode(equipmentCode);
        if (ledenEquipment == null)
            return ResponseUtil.returnError(ReturnCode.ERROR_1038);
        String equipmentStatus = ledenEquipment.getStatus();
        if ("1".equals(equipmentStatus))
            return ResponseUtil.returnError(ReturnCode.ERROR_1052);
        else
            return ResponseUtil.returnError(ReturnCode.ERROR_1037);
    }

    /**
     * 初始化设备授权信息
     * */
    private void initializationEquipmentEmpower(LedenEquipment ledenEquipment){
        List<LedenCollectNode> nodeList = ledenCollectNodeMapper.selectAll();
        LedenEquipmentEmpower ledenEquipmentEmpower = new LedenEquipmentEmpower();
        for (LedenCollectNode ledenCollectNode : nodeList){
            //补全信息
            ledenEquipmentEmpower.setPkId(UUID.randomUUID().toString().replace("-",""));
            //0.正常 1.禁用
            ledenEquipmentEmpower.setDeletag("1");
            ledenEquipmentEmpower.setNodeSign(ledenCollectNode.getNodeCode());
            ledenEquipmentEmpower.setEquipmentId(ledenEquipment.getEquipmentCode());
            ledenEquipmentEmpower.setCreateUserId(ledenEquipment.getCreateUserId());
            ledenEquipmentEmpower.setCreateDatetime(ledenEquipment.getCreateDatetime());
            ledenEquipmentEmpowerMapper.insertSelective(ledenEquipmentEmpower);
        }
    }


}
