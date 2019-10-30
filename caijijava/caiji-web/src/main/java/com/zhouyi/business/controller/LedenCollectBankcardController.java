package com.zhouyi.business.controller;

import com.zhouyi.business.core.model.LedenCollectBankcard;
import com.zhouyi.business.core.model.Response;
import com.zhouyi.business.core.service.LedenCollectBankcardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 杜承旭
 * @ClassNmae: LedenCollectBankcardController
 * @Description: TODO
 * @date 2019/8/27 16:42
 * @Version 1.0
 **/

@RestController
@RequestMapping(value = "/api/bankcard")
public class LedenCollectBankcardController {

    @Autowired
    private LedenCollectBankcardService ledenCollectBankcardService;

    @RequestMapping(value = "/selectBankcard/{id}")
    public Response<List<LedenCollectBankcard>> selectBankcardByPersonCode(@PathVariable String id){
        return ledenCollectBankcardService.selectBankcardByPersonCode(id);
    }
}
