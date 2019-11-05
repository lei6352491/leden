package com.zhouyi.business.controller;

import com.zhouyi.business.core.common.ReturnCode;
import com.zhouyi.business.core.exception.AuthenticationException;
import com.zhouyi.business.core.model.LedenCollectFinger;
import com.zhouyi.business.core.model.Response;
import com.zhouyi.business.core.service.BaseService;
import com.zhouyi.business.core.service.LedenCollectFingerService;
import com.zhouyi.business.core.utils.ResponseUtil;
import com.zhouyi.business.core.vo.LedenCollectFingerVo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.management.modelmbean.XMLParseException;
import java.io.IOException;

@RestController
@RequestMapping(value = "/api/finger")
@Api(value = "指纹接口",hidden = true)
public class LedenCollectFingerController {

    @Autowired
    private BaseService<LedenCollectFinger, LedenCollectFingerVo> baseService;
    @Autowired
    private LedenCollectFingerService ledenCollectFingerService;
    @RequestMapping(value = "/get/{id}")
    public Response getDataById(@PathVariable(value = "id") String id){
        return baseService.findDataById(id);
    }

    @RequestMapping(value = "/getlist")
    public Response getList(@RequestBody LedenCollectFingerVo ledenCollectFingerVo){
        LedenCollectFingerVo ledenCollectFingerVo1 = ledenCollectFingerVo;
        if (ledenCollectFingerVo == null){
            ledenCollectFingerVo1 = new LedenCollectFingerVo();
        }
        return baseService.findDataList(ledenCollectFingerVo1);
    }

    @RequestMapping(value = "/save")
    public Response saveData(@RequestBody LedenCollectFinger ledenCollectFinger){
        return baseService.saveData(ledenCollectFinger);
    }

    @RequestMapping(value = "/update")
    public Response updateData(@RequestBody LedenCollectFinger ledenCollectFinger){
        return baseService.updateData(ledenCollectFinger);
    }

    @RequestMapping("/delete/{id}")
    public Response deleteData(@PathVariable(value = "id")String id){
        return baseService.deleteData(id);
    }




    /**
      * @author 李秸康
      * @Description 解析指纹信息
      * @date 2019/7/9
      * @param
      * @return
     **/
//    @RequestMapping(value="/parseXml")
//    public Response<Object> parseXml(String path){
//        boolean flag= false;
//        try {
//            flag = ledenCollectFingerService.inputFingersByXml(path);
//        } catch (XMLParseException e) {
//            e.printStackTrace();
//            return ResponseUtil.ntrError(e.getMessage());
//        }catch (AuthenticationException e){
//            return ResponseUtil.returnError(e.getReturnCode());
//        }
//        return ResponseUtil.getResponseInfo(flag);
//    }

    /**
     * 根据人员编号查询指纹信息
     * */
    @RequestMapping(value = "/getfinger/{id}")
    public Response selectFingerByPersonCode(@PathVariable String id){
        return ledenCollectFingerService.selectFingerByPersonCode(id);
    }




    @RequestMapping(value = "/xml/generate",method = RequestMethod.GET)
    public Response<Object> generatorXml(String cjrybh){
        try{
            boolean flag=ledenCollectFingerService.generateXml(cjrybh);
            return ResponseUtil.getResponseInfo(flag);
        }catch (IOException e){
            return ResponseUtil.ntrError("生成xml失败:"+e.getMessage());
        }
    }
}
