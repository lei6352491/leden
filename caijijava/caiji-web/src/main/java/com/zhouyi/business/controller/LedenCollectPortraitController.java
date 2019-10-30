package com.zhouyi.business.controller;

import com.zhouyi.business.core.common.ReturnCode;
import com.zhouyi.business.core.exception.AuthenticationException;
import com.zhouyi.business.core.exception.XmlParseException;
import com.zhouyi.business.core.model.LedenCollectPortrait;
import com.zhouyi.business.core.model.Response;
import com.zhouyi.business.core.service.BaseService;
import com.zhouyi.business.core.service.LedenCollectPortraitService;
import com.zhouyi.business.core.utils.ResponseUtil;
import com.zhouyi.business.core.vo.LedenCollectPortraitVo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/portrait")
@Api(hidden = true)
public class LedenCollectPortraitController {

    @Autowired
    private BaseService<LedenCollectPortrait, LedenCollectPortraitVo> baseService;
    @Autowired
    private LedenCollectPortraitService ledenCollectPortraitService;

    @RequestMapping(value = "/get/{id}")
    public Response getDataById(@PathVariable(value = "id") String id){
        return baseService.findDataById(id);
    }

    @RequestMapping(value = "/getlist")
    public Response getList(@RequestBody LedenCollectPortraitVo ledenCollectPortraitVo){
        LedenCollectPortraitVo ledenCollectPortraitVo1 = ledenCollectPortraitVo;
        if (ledenCollectPortraitVo == null){
            ledenCollectPortraitVo1 = new LedenCollectPortraitVo();
        }
        return baseService.findDataList(ledenCollectPortraitVo1);
    }

    @RequestMapping(value = "/save")
    public Response saveData(@RequestBody LedenCollectPortrait ledenCollectPortrait){
        return baseService.saveData(ledenCollectPortrait);
    }

    @RequestMapping(value = "/update")
    public Response updateData(@RequestBody LedenCollectPortrait ledenCollectPortrait){
        return baseService.updateData(ledenCollectPortrait);
    }

    @RequestMapping("/delete/{id}")
    public Response deleteData(@PathVariable(value = "id")String id){
        return baseService.deleteData(id);
    }


    /**
     *解析xml文件并将数据存入数据库
     * @param path
     * @return
     */
    @RequestMapping(value="/parseXml")
    public Response<Object> parseXml(String path){
        boolean flag= false;
        try {
            flag = ledenCollectPortraitService.insertPortraitByXml(path);
        } catch (XmlParseException e) {
            return ResponseUtil.ntrError(e.getMessage());
        }
        return ResponseUtil.getResponseInfo(flag);
    }

    /**
     * 根据人员编号查询人像信息
     * */
    @RequestMapping(value = "/selectPortraitByPersonCode/{id}")
    public Response selectPortraitByPersonCode(@PathVariable String id ){
        return ledenCollectPortraitService.selectPortraitByPersonCode(id);
    }
}
