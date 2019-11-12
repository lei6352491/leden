package com.zhouyi.business.controller;

import com.alibaba.fastjson.JSON;
import com.zhouyi.business.core.common.ReturnCode;
import com.zhouyi.business.core.exception.ExceptionCast;
import com.zhouyi.business.core.model.LedenEquipment;
import com.zhouyi.business.core.model.LedenEquipmentSZ;
import com.zhouyi.business.core.model.Response;
import com.zhouyi.business.core.model.SysUnit;
import com.zhouyi.business.core.service.LedenEquipmentService;
import com.zhouyi.business.core.service.LedenPersonIndexService;
import com.zhouyi.business.core.service.LedenPersonIndexServiceImpl;
import com.zhouyi.business.core.service.SysUnitService;
import com.zhouyi.business.core.utils.ResponseUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;

/**
 * 省综采集对接接口
 * @author 杜承旭
 * @ClassNmae: ProvinceComprehensiveCollectController
 * @Description: TODO
 * @date 2019/10/22 8:56
 * @Version 1.0
 **/
@RestController
@RequestMapping(value = "/htmisWebService")
public class ProvinceComprehensiveCollectController {

    @Autowired
    private LedenEquipmentService ledenEquipmentService;

    @Autowired
    private LedenPersonIndexService ledenPersonIndexService;

    @Autowired
    private SysUnitService sysUnitService;

    @RequestMapping(value = "/register/postRegisterClient")
    public Response<Object> addEquipment(@RequestBody LedenEquipmentSZ ledenEquipmentSZ){
        if (StringUtils.isEmpty(ledenEquipmentSZ.getUnitCode())||StringUtils.isEmpty(ledenEquipmentSZ.getIp())||StringUtils.isEmpty(ledenEquipmentSZ.getMac())){
            ExceptionCast.cast(ResponseUtil.returnError(ReturnCode.ERROR_02));
        }
        LedenEquipment ledenEquipment = new LedenEquipment();
        ledenEquipment.setEquipmentMac(ledenEquipmentSZ.getMac());
        ledenEquipment.setEquipmentIp(ledenEquipmentSZ.getIp());
        ledenEquipment.setEquipmentCode(ledenEquipmentSZ.getUnitCode());

        String equipmentCode = ledenEquipmentService.addLedenEquipment(ledenEquipment);

        if (StringUtils.isEmpty(equipmentCode)){
            return ResponseUtil.getResponseInfo(false);
        }else {
            return ResponseUtil.getResponseInfo(ReturnCode.SUCCESS,equipmentCode);
        }
    }

    @RequestMapping(value = "/register/getAuthorized")
    public Response<Object> getAuthorized(@RequestBody String cjdNo){
        if (StringUtils.isEmpty(cjdNo))
            ExceptionCast.cast(ResponseUtil.returnError(ReturnCode.ERROR_2));
        Map map = JSON.parseObject(cjdNo, Map.class);
        Object equipmentCodeObject = map.get("cjdNo");
        if (equipmentCodeObject == null)
            ExceptionCast.cast(ResponseUtil.returnError(ReturnCode.ERROR_02));
        return ledenEquipmentService.selectEquipmentByEquipmentCode(equipmentCodeObject.toString());
    }

    @RequestMapping(value = "/person/postCreateId")
    public Response<Object> createPersonCode(@RequestBody String unitCode){
        if (StringUtils.isEmpty(unitCode))
            ExceptionCast.cast(ResponseUtil.returnError(ReturnCode.ERROR_2));
        Map map = JSON.parseObject(unitCode, Map.class);
        String unitCodeObject = (String)map.get("unitCode");
        if (unitCodeObject == null)
            ExceptionCast.cast(ResponseUtil.returnError(ReturnCode.ERROR_2));
        //动态代理校验单位编号是否存在
        LedenPersonIndexService proxyLedenPersonIndexService = (LedenPersonIndexService)Proxy.newProxyInstance
                (LedenPersonIndexServiceImpl.class.getClassLoader(), LedenPersonIndexServiceImpl.class.getInterfaces(), new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        Object invoke = null;
                        if ("selectNextPrimaryKey".equals(method.getName())) {
                            String unitCode = (String) args[0];
                            //执行目标对象时校验部门编号是否存在
                            SysUnit sysUnit = sysUnitService.searchByPrimaryKey(unitCode);
                            if (sysUnit == null) {
                                ExceptionCast.cast(ResponseUtil.returnError(ReturnCode.ERROR_17));
                            }

                            invoke = method.invoke(ledenPersonIndexService, unitCode);
                        }
                        return invoke;
                    }
                });
        String personCode = proxyLedenPersonIndexService.selectNextPrimaryKey(unitCodeObject);
        return ResponseUtil.getResponseInfo(ReturnCode.SUCCESS,personCode);
    }

}
