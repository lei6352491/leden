package com.zhouyi.business.controller;

import com.zhouyi.business.core.common.ReturnCode;
import com.zhouyi.business.core.exception.AuthenticationException;
import com.zhouyi.business.core.exception.XmlParseException;
import com.zhouyi.business.core.model.LedenCollectGoods;
import com.zhouyi.business.core.model.PageData;
import com.zhouyi.business.core.model.Response;
import com.zhouyi.business.core.service.LedenCollectGoodsService;
import com.zhouyi.business.core.utils.ResponseUtil;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 李秸康
 * @ClassNmae: LedenCollectGoodsController
 * @Description: 随声物品控制器
 * @date 2019/7/5 9:29
 * @Version 1.0
 **/
@RestController
@RequestMapping(value="/api")
@Api(description = "随身物品接口",hidden = true)
public class LedenCollectGoodsController {

    @Autowired
    private LedenCollectGoodsService ledenCollectGoodsService;

    @RequestMapping(value="/goods/parseXml")
    public Response inputGoodsByXml(String path){
        boolean flag= false;
        try {
            flag = ledenCollectGoodsService.inputGoodsByXml(path);
        } catch (XmlParseException e) {
            return ResponseUtil.ntrError(e.getMessage());
        } catch (AuthenticationException e){
            return ResponseUtil.returnError(e.getReturnCode());
        }

        return ResponseUtil.getResponseInfo(flag);
    }


    @RequestMapping(method = RequestMethod.GET,value = "/good")
    @ApiOperation(value = "查询个人随身物品信息")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(value = "人员编号",paramType = "query",name = "personId"),
                    @ApiImplicitParam(value = "页码",paramType = "query",name = "pNo"),
                    @ApiImplicitParam(value = "页面大小",paramType = "query",name = "pSize")
            }
    )
    public Response<PageData<LedenCollectGoods>> goods(String personId,
                                                   String pNo,
                                                   String pSize){

            PageData<LedenCollectGoods> list=ledenCollectGoodsService.goods(personId,pNo,pSize);
           return  ResponseUtil.getResponseInfo(ReturnCode.SUCCESS,list);
    }


    @ApiOperation(value = "查询物品详情")
    @RequestMapping(value = "/good/{wpbh}",method = RequestMethod.GET)
    public Response<LedenCollectGoods> good(@PathVariable String wpbh){
        return  ResponseUtil.getResponseInfo(ReturnCode.SUCCESS,ledenCollectGoodsService.good(wpbh));
    }


}
