package com.zhouyi.business.controller;

import com.zhouyi.business.core.common.ReturnCode;
import com.zhouyi.business.core.exception.AuthenticationException;
import com.zhouyi.business.core.exception.XmlParseException;
import com.zhouyi.business.core.model.LedenCollectPerson;
import com.zhouyi.business.core.model.PersonResult;
import com.zhouyi.business.core.model.Response;
import com.zhouyi.business.core.service.LedenCollectPersonService;
import com.zhouyi.business.core.utils.ResponseUtil;
import com.zhouyi.business.core.vo.LedenConllectPersonVo;
import com.zhouyi.business.core.vo.LedenConllectPersonVo2;
import io.netty.handler.codec.base64.Base64Encoder;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.BASE64Encoder;

import java.util.List;
import java.util.Map;

/**
 * @author 李秸康
 * @ClassNmae: LedenCollectPersonController
 * @Description: 基本信息控制器
 * @date 2019/7/1 16:13
 * @Version 1.0
 **/
@RestController
@RequestMapping(value="/api/collectPerson")
@Api(hidden = true)
public class LedenCollectPersonController {

    @Autowired
    private LedenCollectPersonService ledenCollectPersonService;

    /**
     * 解析个人信息接口
     * @param path
     * @return
     */
    @RequestMapping(value="/parseXml")
    public Response<Object> parseXml(String path){
        boolean flag;
        try {
            flag = ledenCollectPersonService.insertCollectPersonByXml(path);
        } catch (XmlParseException e) {
            return ResponseUtil.ntrError(e.getMessage());
        }
        return ResponseUtil.getResponseInfo(flag);
    }

    @RequestMapping(value = "/selectallcjrxm")
    public Response selectAllCJRXM(){
        return ledenCollectPersonService.selectAllCJRXM();
    }

    @RequestMapping(value = "/selectlist")
    public Response selectCollectPersonAll(@RequestBody LedenConllectPersonVo2 ledenConllectPersonVo2){
        Response<Map<String, Object>> response = ledenCollectPersonService.selectDataList(ledenConllectPersonVo2);
        return response;
    }

    @RequestMapping(value = "/selectpersonbycode/{id}")
    public Response<PersonResult> selectPersonByPersonCode(@PathVariable String id){
        Response<PersonResult> personResultResponse = ledenCollectPersonService.selectPersonByPersonCode(id);
        return personResultResponse;
    }
}
