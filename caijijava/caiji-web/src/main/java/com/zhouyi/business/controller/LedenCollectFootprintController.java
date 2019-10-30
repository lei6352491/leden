package com.zhouyi.business.controller;

import com.alibaba.fastjson.util.RyuDouble;
import com.zhouyi.business.core.common.ReturnCode;
import com.zhouyi.business.core.exception.AuthenticationException;
import com.zhouyi.business.core.exception.XmlParseException;
import com.zhouyi.business.core.model.LedenCollectFootprint;
import com.zhouyi.business.core.model.Response;
import com.zhouyi.business.core.service.LedenCollectFootprintService;
import com.zhouyi.business.core.utils.ResponseUtil;
import com.zhouyi.business.core.vo.LedenCollectFootprintSearchVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * @author 李秸康
 * @ClassNmae: LedenCollectFootprintController
 * @Description: 足迹控制器
 * @date 2019/7/4 14:59
 * @Version 1.0
 **/
@RestController
@RequestMapping(value="/api/footprint")
@Api(value = "足迹接口",hidden = true)
public class LedenCollectFootprintController {

    @Autowired
    private LedenCollectFootprintService ledenCollectFootprintService;


    /**
     * 解析xml文件录入数据接口
     * @param path
     * @return
     */
    @RequestMapping(value="/parseXml")
    public Response<Object> inputFootprint(String path){

        boolean flag=false;
        try {
            flag=ledenCollectFootprintService.inputFootprintByXml(path);
        } catch (XmlParseException e) {
            return ResponseUtil.ntrError(e.getMessage());
        } catch (AuthenticationException e){
            return ResponseUtil.returnError(e.getReturnCode());
        }
        return ResponseUtil.getResponseInfo(flag);
    }


    @RequestMapping(value = "",method = RequestMethod.GET)
    @ApiOperation(value = "查询足迹信息")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(value = "人员编号",paramType = "query",name = "RYJCXXCJBH"),
                    @ApiImplicitParam(value = "页码",paramType = "query",name = "pNo"),
                    @ApiImplicitParam(value = "页面大小",paramType = "query",name = "pSize")
            }
    )
    public Response foots(String RYJCXXCJBH,
                                                         @RequestParam(defaultValue = "1") String pNo,
                                                         @RequestParam(defaultValue = "20") String pSize) {
        if (null == RYJCXXCJBH || "".equals(RYJCXXCJBH)){
            return ResponseUtil.returnError(ReturnCode.ERROR_02);
        }
        List<LedenCollectFootprintSearchVo> ledenCollectFootprintSearchVos = ledenCollectFootprintService.listFoots(RYJCXXCJBH,pNo,pSize);
        return ResponseUtil.getResponseInfo(ReturnCode.SUCCESS,ledenCollectFootprintSearchVos);
    }

    @RequestMapping(value = "/get/{id}")
    public Response<LedenCollectFootprint> getFootprint(@PathVariable String id){
        return ledenCollectFootprintService.getFootprint(id);
    }
}
