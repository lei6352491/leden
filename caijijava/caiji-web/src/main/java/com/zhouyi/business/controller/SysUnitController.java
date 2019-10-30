package com.zhouyi.business.controller;


import com.zhouyi.business.core.common.ReturnCode;
import com.zhouyi.business.core.model.PageData;
import com.zhouyi.business.core.model.Response;
import com.zhouyi.business.core.model.SysUnit;
import com.zhouyi.business.core.service.SysUnitService;
import com.zhouyi.business.core.utils.MapUtils;
import com.zhouyi.business.core.utils.ResponseUtil;
import com.zhouyi.business.dto.SysUnitDto;
import com.zhouyi.business.dto.SysUnitListDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 李秸康
 * @ClassNmae: SysUnitController
 * @Description: 系统部门控制器
 * @date 2019/6/20 17:30
 * @Version 1.0
 **/
@RestController
@RequestMapping(value="/api/unit")
@Api(description = "部门api接口列表")
public class SysUnitController {

    @Autowired
    private SysUnitService sysUnitService;


    /**
     * 根据编码查询部门信息
     * @param sysUnitCode
     * @return
     */
    @ApiOperation(value = "部门详情",notes = "根据部门编码查询部门详情")
    @RequestMapping(value="/queryByUnit/{sysUnitCode}",method = RequestMethod.GET)
    public Response<SysUnit> getSysUnit(@PathVariable String sysUnitCode){
        Response<SysUnit> sysUnitResponse=new Response<SysUnit>();

        SysUnit sysUnit=sysUnitService.searchByPrimaryKey(sysUnitCode);
        sysUnitResponse.setData(sysUnit);
        return sysUnitResponse;
    }


    /**
     * 修改部门信息
     * @param sysUnit
     * @return
     */
    @ApiOperation(value = "修改部门",notes = "修改部门信息")
    @RequestMapping(value="/updateUnit",method = RequestMethod.PUT)
    public Response<Object> updateUnit(@RequestBody SysUnit sysUnit){
        boolean result=sysUnitService.modifyByPrimaryKeySelective(sysUnit);
        return ResponseUtil.getResponseInfo(result);
    }


    /**
     * 添加部门信息
     * @param sysUnit 部门信息对象
     * @return
     */
    @ApiOperation(value = "新增部门",notes = "添加部门信息")
    @RequestMapping(value="/addSysUnit",method = RequestMethod.POST)
    public Response<Object> addSysUnit(@RequestBody SysUnit sysUnit){
        boolean result=sysUnitService.addSelective(sysUnit);
        return ResponseUtil.getResponseInfo(result);
    }


    /**
     * 删除部门信息对象
     * @param unitCode
     * @return
     */
    @ApiOperation(value = "删除部门",notes = "删除部门信息")
    @RequestMapping(value="/sys_unit/{unitCode}",method = RequestMethod.DELETE)
    public Response<Object> removeSysUnit(@PathVariable String unitCode){
        boolean result=sysUnitService.removeByPrimaryKey(unitCode);
        return ResponseUtil.getResponseInfo(result);
    }


    /**
     * 条件筛选部门信息
     * （条件暂无）
     * @return
     */
    @ApiOperation(value = "筛选部门信息",notes = "根据条件筛选所有部门信息")
    @RequestMapping(value="/listUnits",method = RequestMethod.POST)
    public Response<PageData<SysUnit>> searchSysUnitsByConditions(@RequestBody SysUnitListDto sysUnitListDto){

        Map<String,Object> conditions= MapUtils.objectTransferToMap(sysUnitListDto);
        PageData<SysUnit> pageData=sysUnitService.listSysUnitByConditions(conditions);
        return ResponseUtil.getResponseInfo(ReturnCode.SUCCESS,pageData);
    }


    /**
     * 查询所有部门接口
     * @return
     */
    @ApiOperation(value = "查询所有部门信息",notes = "查询一个省下的所有部门列表信息")
    @RequestMapping(value="/listAllUnit/{provinceId}",method=RequestMethod.GET)
    public Response<List<SysUnit>> getAllUnit(@PathVariable String provinceId){
        List<SysUnit> units=sysUnitService.listAllSysUnit(provinceId);
        return ResponseUtil.getResponseInfo(ReturnCode.SUCCESS,units);
    }


    /**
     * 根据父级id查询
     * @param sysUnitDto
     * @return
     */
    @ApiOperation(value = "根据父级Id查询",notes = "根据父级Id查询部门信息")
    @RequestMapping(value="/listSysUnitByParent",method=RequestMethod.POST)
    public Response<PageData<SysUnit>> listSysUnitByParent(@RequestBody SysUnitDto sysUnitDto){

        Map<String,Object> conditions=getConditionsMap(sysUnitDto);
        PageData<SysUnit> pageData = sysUnitService.searchUnitsPage(conditions);
        return ResponseUtil.getResponseInfo(ReturnCode.SUCCESS,pageData);
    }


    /**
     * 将条件封装成集合
     * @param sysUnitDto
     * @return
     */
    private Map<String,Object> getConditionsMap(SysUnitDto sysUnitDto){
        int start=(sysUnitDto.getpNo()-1)*sysUnitDto.getpSize();
        Map map=new HashMap();
        map.put("start",start);
        map.put("end",start+sysUnitDto.getpSize());
        map.put("pSize",sysUnitDto.getpSize());
        map.put("parentId",sysUnitDto.getParentId());
        return map;
    }



    @ApiOperation(value = "部门编码查重")
    @ApiImplicitParam(value = "部门编码",name = "unitCode",paramType = "path")
    @RequestMapping(value = "/unit_code/check/{unitCode}",method=RequestMethod.GET)
    public Response<String> checkUnitCode(@PathVariable(value = "unitCode")String unitCode){
        boolean flag=sysUnitService.checkUnitCode(unitCode);
        if(flag)
            return ResponseUtil.getResponseInfo(ReturnCode.SUCCESS,"exists");
        else
            return ResponseUtil.getResponseInfo(ReturnCode.SUCCESS,"no_exists");

    }


    @RequestMapping(value = "/{unitCode}",method = RequestMethod.GET)
    @ApiOperation(value = "查询本部门以及子部门")
    public Response<List<SysUnit>> listOnselfAndChildren(@PathVariable String unitCode){
        List<SysUnit> sysUnitList = sysUnitService.listOnselfAndChildrens(unitCode);
        return ResponseUtil.getResponseInfo(ReturnCode.SUCCESS,sysUnitList);
    }

    @RequestMapping(value = "/selectUnitAll")
    public Response selectUnitAll(){
        return sysUnitService.selectUnitAll();
    }


}
