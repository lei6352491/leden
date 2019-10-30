package com.zhouyi.business.controller;

import com.zhouyi.business.core.model.LedenEquipmentPlugIn;
import com.zhouyi.business.core.model.Response;
import com.zhouyi.business.core.service.LedenEquipmentPlugInService;
import com.zhouyi.business.core.utils.InitializationPageUtils;
import com.zhouyi.business.core.vo.LedenEquipmentPlugInVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 杜承旭
 * @ClassNmae: LedenEquipmentPlugInController
 * @Description: TODO
 * @date 2019/9/19 14:13
 * @Version 1.0
 **/

@RestController
@RequestMapping("/api/plugin")
public class LedenEquipmentPlugInController {

    @Autowired
    private LedenEquipmentPlugInService ledenEquipmentPlugInService;

    @RequestMapping(value = "/getPluginList/{id}")
    public Response selectPlugInByNodeCode(@PathVariable(value = "id")String nodeCode){
        return ledenEquipmentPlugInService.selectDataByNodeCode(nodeCode);
    }

}
