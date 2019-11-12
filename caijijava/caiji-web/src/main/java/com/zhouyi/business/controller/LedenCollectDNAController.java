package com.zhouyi.business.controller;

import com.zhouyi.business.core.common.ReturnCode;
import com.zhouyi.business.core.exception.AuthenticationException;
import com.zhouyi.business.core.exception.XmlParseException;
import com.zhouyi.business.core.model.LedenCollectDna;
import com.zhouyi.business.core.model.Response;
import com.zhouyi.business.core.service.LedenCollectDNAService;
import com.zhouyi.business.core.utils.ResponseUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 李秸康
 * @ClassNmae: LedenCollectDNAController
 * @Description: DNA相关接口控制器
 * @date 2019/7/4 13:58
 * @Version 1.0
 **/
@RestController
@RequestMapping(value="/api/dna")
@Api(value = "DNS接口",hidden = true)
public class LedenCollectDNAController {

    @Autowired
    private LedenCollectDNAService ledenCollectDNAService;


    /**
     * 解析DNA文件接口
     * @param path
     * @return
     */
    @RequestMapping(value="/parseXml")
    public Response<Object> parseXml(String path){
        boolean flag=false;
        try {
            flag=ledenCollectDNAService.inputDNAByXml(path);
        } catch (XmlParseException e) {
            return ResponseUtil.ntrError(e.getMessage());
        } catch(AuthenticationException e){
            return ResponseUtil.returnError(e.getReturnCode());
        }
        return ResponseUtil.getResponseInfo(flag);

    }

    /**
     * 根据人员编号查询dna信息
     * */
    @RequestMapping(value = "/getDNAbyPersonCode/{id}")
    public Response<List<LedenCollectDna>> selectDataByPersonCode(@PathVariable String id){
        return ledenCollectDNAService.selectDataByPersonCode(id);
    }
}
