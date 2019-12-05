package com.zhouyi.business.core.utils;

import cn.jiguang.common.utils.StringUtils;
import com.zhouyi.business.core.common.ReturnCode;
import com.zhouyi.business.core.dao.LedenEquipmentEmpowerMapper;
import com.zhouyi.business.core.dao.LedenEquipmentMapper;
import com.zhouyi.business.core.dao.SysUserMapper;
import com.zhouyi.business.core.exception.AuthenticationException;
import com.zhouyi.business.core.model.LedenEquipment;
import com.zhouyi.business.core.model.LedenEquipmentEmpower;
import com.zhouyi.business.core.model.SysUser;
import com.zhouyi.business.core.model.enums.AuthoirtyEnum;
import com.zhouyi.business.core.vo.headvo.HeaderVo;
import com.zhouyi.business.core.vo.headvo.PackageHeadVo;
import org.apache.poi.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 李秸康
 * @ClassNmae: SecurityUtil
 * @Description: 安全工具
 * @date 2019/7/8 11:09
 * @Version 1.0
 **/
@Service("securityUtil")
public class SecurityUtil {

    @Autowired
    private LedenEquipmentEmpowerMapper ledenEquipmentEmpowerMapper;
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private LedenEquipmentMapper ledenEquipmentMapper;
    /**
     * 头部信息
     * @param headerVo
     * @param authoirtyNode
     * @return
     */
    public  Boolean repairpermissions(HeaderVo headerVo, AuthoirtyEnum authoirtyNode){
        boolean flag=false;

        if(StringUtils.isEmpty(headerVo.getUSER_CODE())||StringUtils.isEmpty(headerVo.EQUIPMENT_CODE)||StringUtils.isEmpty(headerVo.getUSER_UNIT_CODE())){
            throw new AuthenticationException(ReturnCode.ERROR_1036);
        }
        //验证用户信息
        SysUser sysUser=sysUserMapper.getSysUserByUserCode(headerVo.getUSER_CODE());
        if(sysUser==null){
            throw new AuthenticationException(ReturnCode.ERROR_1001);
        }else if(!sysUser.getUserUnitCode().equals(headerVo.getUSER_UNIT_CODE())){
            throw new AuthenticationException(ReturnCode.ERROR_1032);
        }
        //用户部门信息通过
        //根据编码拿到主键
        LedenEquipment tempEquipment = ledenEquipmentMapper.getLedenEquipmentByEquipmentCode(headerVo.getEQUIPMENT_CODE());
        if(tempEquipment==null){
            throw new AuthenticationException(ReturnCode.ERROR_1038);
        }
//        else if(!tempEquipment.getUnitCode().equals(sysUser.getUserUnitCode())){
//            throw new AuthenticationException(ReturnCode.ERROR_1046);
//        }
        //授权信息通过
        boolean empowerFlag=false;
        List<LedenEquipmentEmpower> equipmentEmpowerByEquipmnetCode = ledenEquipmentEmpowerMapper.getEquipmentEmpowerByEquipmnetCode(tempEquipment.getEquipmentCode());
        for (LedenEquipmentEmpower ledenEquipmentEmpower :
                equipmentEmpowerByEquipmnetCode) {
            if (!ledenEquipmentEmpower.getNodeSign().equals(authoirtyNode.getNodeSign())&&ledenEquipmentEmpower.getDeletag().equals("0")){
                //如果授权信息为0则表示有权限
                empowerFlag=true;
            }
        }


        if(!empowerFlag){
            throw new AuthenticationException(ReturnCode.ERROR_1053);
        }

        return true;
    }


    /**
     * 工具验证权限
     * @param data
     * @param authoirtyEnum
     * @return
     */
    public boolean verification(List<LedenEquipmentEmpower> data, AuthoirtyEnum authoirtyEnum){

        for (LedenEquipmentEmpower ledenEquipmentEmpower :
                data) {
            if (ledenEquipmentEmpower.getNodeSign().equals(authoirtyEnum.getNodeSign())&&ledenEquipmentEmpower.getDeletag().equals("0")){
                //如果授权信息为0则表示有权限
                return true;
            }
        }
        return false;
    }





}
