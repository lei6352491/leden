package com.zhouyi.business.controller.iris;

import com.zhouyi.business.core.model.xinzhen.IrisCommons;
import com.zhouyi.business.core.model.xinzhen.IrisComparsion;
import com.zhouyi.business.core.model.xinzhen.IrisReceive;
import com.zhouyi.business.core.service.iris.IrisButtService;
import okhttp3.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: first
 * @Date: 下午3:51 2019/12/9
 * @Description: 中科虹霸对接
**/
@RestController
public class IrisButtController {

    @Autowired
    private IrisButtService irisButtService;

    @RequestMapping(value = "/iris/collect")
    public IrisReceive irisCollect(String rybh){
        return irisButtService.irisCollect(rybh);
    }



    @RequestMapping(value = "/iris/examine")
    public IrisCommons irisExamine(String rybh){
       return irisButtService.irisExamine(rybh);
    }



    @RequestMapping(value = "/iris/check")
    public IrisComparsion irisComparsion(String rybh){
       return irisButtService.irisCheck(rybh);
    }



    @RequestMapping(value = "/iris/getCodeMsg")
    public Object getCodeMessage(String codeType,String pId){
        return irisButtService.getCodeMessage(codeType,pId);
    }



}
