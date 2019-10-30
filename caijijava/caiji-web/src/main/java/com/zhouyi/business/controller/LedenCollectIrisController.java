package com.zhouyi.business.controller;

import com.zhouyi.business.core.model.LedenCollectIris;
import com.zhouyi.business.core.model.Response;
import com.zhouyi.business.core.service.BaseService;
import com.zhouyi.business.core.service.LedenCollectIrisService;
import com.zhouyi.business.core.vo.LedenCollectIrisVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 杜承旭
 * @ClassNmae: LedenCollectIrisController
 * @Description: TODO
 * @date 2019/9/3 9:39
 * @Version 1.0
 **/

@RestController
@RequestMapping(value = "/api/iris")
public class LedenCollectIrisController {

    @Autowired
    private BaseService<LedenCollectIris, LedenCollectIrisVo> baseService;

    @Autowired
    private LedenCollectIrisService ledenCollectIrisService;

    /**
     *根据人员编号查询虹膜信息
     */
    @RequestMapping(value = "/get/{id}")
    public Response selectIrisByPersonCode(@PathVariable String id){
        return ledenCollectIrisService.selectIrisByPersonCode(id);
    }
}
