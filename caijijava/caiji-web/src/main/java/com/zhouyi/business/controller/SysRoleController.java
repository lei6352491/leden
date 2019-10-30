package com.zhouyi.business.controller;

import com.zhouyi.business.core.common.ReturnCode;
import com.zhouyi.business.core.exception.ExceptionCast;
import com.zhouyi.business.core.model.*;
import com.zhouyi.business.core.service.SysRoleService;
import com.zhouyi.business.core.utils.ResponseUtil;
import com.zhouyi.business.core.vo.SysRoleVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/role")
@Api(description = "角色管理API接口")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;

    @RequestMapping(value = "/get/role/{id}")
    @ApiOperation(value = "角色详情",notes = "根据角色id查询角色详情")
    public Response getSysRoleById(@PathVariable(value = "id")String id){
        return sysRoleService.findSysRoleById(id);
    }

    @RequestMapping(value = "/get/roleList")
    @ApiOperation(value = "获取角色列表",notes = "根据多条件查询角色列表")
    public Response<Object> getSysRoleList(@RequestBody SysRoleVo sysRoleVo){
        List<SysRole> roleList = sysRoleService.findSysRoleListBySysRole(sysRoleVo);
        if (roleList == null){
            return null;
        }
        int total = sysRoleService.findTotal(sysRoleVo);
        HashMap<String, Object> map = new HashMap<>();
        map.put("total",total);
        map.put("list",roleList);
        Response<Object> response = new Response<>();
        response.setData(map);
        return response;
    }

    @RequestMapping(value = "/save/role")
    @ApiOperation(value = "新增角色",notes = "添加角色信息")
    public Response<Object> saveSysRole(@RequestBody SysRole sysRole){
        sysRoleService.saveSysRole(sysRole);
        return ResponseUtil.getResponseInfo(true);
    }

    @RequestMapping(value = "/update/role")
    @ApiOperation(value = "修改角色",notes = "更新角色信息")
    public Response<Object> updateSysRole(@RequestBody SysRole sysRole){
        sysRoleService.updateSysRole(sysRole);
        return ResponseUtil.getResponseInfo(true);
    }

    @RequestMapping(value = "/delete/role")
    @ApiOperation(value = "删除角色",notes = "根据id删除角色信息")
    public Response<Object> delete(@PathVariable(value = "id") String id){
        sysRoleService.deleteSysRole(id);
        return ResponseUtil.getResponseInfo(true);
    }


    @RequestMapping(value = "/select/menutree")
    @ApiOperation(value = "获取菜单列表",notes = "获取该用户下的菜单列表")
    public Response<Object> selectMenuTreeByUserId(@RequestBody SysUser sysUser){
        if (sysUser == null || sysUser.getUserCode() == null || sysUser.getUserCode().equals("")){
            ExceptionCast.cast(ResponseUtil.returnError(ReturnCode.ERROR_02));
        }
        MenuTreeNode menuTreeNode = sysRoleService.selectMenuTreeByUserCode(sysUser.getUserCode());
        return ResponseUtil.getResponseInfo(ReturnCode.SUCCESS,menuTreeNode);
    }

    @RequestMapping(value = "/select/menutreebyrole")
    @ApiOperation(value = "获取角色下的菜单列表",notes = "获取该角色下的菜单列表（树型结构）")
    public Response selectMenuTreeByRoleId(@RequestBody SysRole sysRole){
        if (sysRole == null || sysRole.getPkId() == null || StringUtils.isEmpty(sysRole.getPkId())){
            ExceptionCast.cast(ResponseUtil.returnError(ReturnCode.ERROR_02));
        }
        MenuTreeNode menuTreeNode = sysRoleService.selectMenuTreeByRoleId(sysRole.getPkId());
        return ResponseUtil.getResponseInfo(ReturnCode.SUCCESS,menuTreeNode);
    }

    @RequestMapping(value = "/select/menutreetablebyrole")
    @ApiOperation(value = "获取角色下的菜单列表",notes = "获取该角色下的菜单列表（表型结构）")
    public Response selectMenuTreeTableByRoleId(@RequestBody SysRole sysRole){
        if (sysRole == null || sysRole.getPkId() == null || StringUtils.isEmpty(sysRole.getPkId())){
            ExceptionCast.cast(ResponseUtil.returnError(ReturnCode.ERROR_02));
        }
        List<MenuTreeNode> list = sysRoleService.selectMenuTreeTableByRoleId(sysRole.getPkId());
        if (list != null){
            Map<String,Object> map = new HashMap<>();
            map.put("list",list);
            map.put("total",list.size());
            return ResponseUtil.getResponseInfo(ReturnCode.SUCCESS,map);
        }
        return ResponseUtil.getResponseInfo(ReturnCode.SUCCESS,list);
    }

    @RequestMapping(value = "/update/rolemenu")
    @ApiOperation(value = "编辑角色下的菜单列表信息",notes = "更新该角色下的菜单列表信息")
    public Response updateRoleMenu(@RequestBody RoleMenuRequest roleMenuRequest){
        if (roleMenuRequest == null || StringUtils.isEmpty(roleMenuRequest.getRoleId())){
            ExceptionCast.cast(ResponseUtil.returnError(ReturnCode.ERROR_02));
        }
        return sysRoleService.updateRoleMenu(roleMenuRequest);
    }

    @RequestMapping(value = "/save/rolemenu")
    @ApiOperation(value = "新增角色下的菜单列表信息",notes = "新增该角色下的菜单列表信息")
    public Response saveRoleMenu(@RequestBody RoleMenuRequest roleMenuRequest){
        if (roleMenuRequest == null || StringUtils.isEmpty(roleMenuRequest.getRoleName())){
            ExceptionCast.cast(ResponseUtil.returnError(ReturnCode.ERROR_02));
        }
        return sysRoleService.saveRoleMenu(roleMenuRequest);
    }
}
