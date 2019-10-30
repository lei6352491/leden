package com.zhouyi.business.controller;

import com.zhouyi.business.core.model.LedenCollectPAddressbook;
import com.zhouyi.business.core.model.Response;
import com.zhouyi.business.core.service.BaseService;
import com.zhouyi.business.core.service.LedenCollectPAddressbookService;
import com.zhouyi.business.core.vo.LedenCollectPAddressbookVo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/dressbook")
@Api(value = "地址接口",hidden = true)
public class LedenCollectPAddressbookController {

    @Autowired
    private BaseService<LedenCollectPAddressbook, LedenCollectPAddressbookVo> baseService;

    @Autowired
    private LedenCollectPAddressbookService ledenCollectPAddressbookService;

    @RequestMapping(value = "/get/{id}")
    public Response getDataById(@PathVariable(value = "id") String id){
        return baseService.findDataById(id);
    }

    @RequestMapping(value = "/getlist")
    public Response getList(@RequestBody LedenCollectPAddressbookVo ledenCollectPAddressbookVo){
        LedenCollectPAddressbookVo ledenCollectPAddressbookVo1 = ledenCollectPAddressbookVo;
        if (ledenCollectPAddressbookVo == null){
            ledenCollectPAddressbookVo1 = new LedenCollectPAddressbookVo();
        }
        return baseService.findDataList(ledenCollectPAddressbookVo1);
    }

    @RequestMapping(value = "/save")
    public Response saveData(@RequestBody LedenCollectPAddressbook ledenCollectPAddressbook){
        return baseService.saveData(ledenCollectPAddressbook);
    }

    @RequestMapping(value = "/update")
    public Response updateData(@RequestBody LedenCollectPAddressbook ledenCollectPAddressbook){
        return baseService.updateData(ledenCollectPAddressbook);
    }

    @RequestMapping("/delete/{id}")
    public Response deleteData(@PathVariable(value = "id")String id){
        return baseService.deleteData(id);
    }

    /**
     * 根据主键查询通讯录列表
     * */
    @RequestMapping(value = "/getbook")
    public Response findDataListById(@RequestBody LedenCollectPAddressbookVo ledenCollectPAddressbookVo){
        return ledenCollectPAddressbookService.findDataListById(ledenCollectPAddressbookVo);
    }

}
