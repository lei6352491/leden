package com.zhouyi.business.controller;

import com.zhouyi.business.core.model.LedenCollectPTotal;
import com.zhouyi.business.core.model.Response;
import com.zhouyi.business.core.service.LedenCollectPToalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 杜承旭
 * @ClassNmae: LedenCollectPTotal
 * @Description: TODO
 * @date 2019/9/4 9:41
 * @Version 1.0
 **/

@RestController
@RequestMapping(value = "/api/ptotal")
public class LedenCollectPTotalController {

    @Autowired
    private LedenCollectPToalService ledenCollectPToalService;

    @RequestMapping(value = "/get/{id}")
    public Response selectDataByPersonCode(@PathVariable String id){
        return ledenCollectPToalService.selectDataByPersonCode(id);
    }

}
