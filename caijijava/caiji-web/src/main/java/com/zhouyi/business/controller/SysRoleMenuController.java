package com.zhouyi.business.controller;

import com.zhouyi.business.core.model.Response;
import com.zhouyi.business.core.model.SysRoleMenu;
import com.zhouyi.business.core.service.SysRoleMenuService;
import com.zhouyi.business.core.vo.SysRoleMenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value = "/api/rolemenu")
public class SysRoleMenuController {

    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    @RequestMapping(value = "/get/rolemenu/{id}")
    public Response<SysRoleMenu> getSysRoleMenuById(@PathVariable(value = "id") String id){
        SysRoleMenu roleMenu = sysRoleMenuService.findSysRoleMenuById(id);
        Response<SysRoleMenu> responseResult = new Response<SysRoleMenu>();
        responseResult.setData(roleMenu);
        return responseResult;
    }

    @RequestMapping(value = "/get/list")
    public Response<Object> getSysRoleMenuListByRoleMenu(
                                        @RequestBody SysRoleMenuVo sysRoleMenuVo){
        List<SysRoleMenu> roleMenuList =
                sysRoleMenuService.findSysRoleMenuListBySysRoleMenu(sysRoleMenuVo);
        if (roleMenuList == null){
            return null;
        }
        int total = sysRoleMenuService.findTotal(sysRoleMenuVo);
        HashMap<String, Object> map = new HashMap<String,Object>();
        map.put("total",total);
        map.put("list",roleMenuList);
        Response<Object> response = new Response<Object>();
        response.setData(map);
        return response;
    }

    @RequestMapping(value = "/save/rolemenu")
    public Response<Object> saveSysRoleMenu(@RequestBody SysRoleMenu sysRoleMenu){
        sysRoleMenuService.saveSysRoleMenu(sysRoleMenu);
        Response<Object> response = new Response<Object>(200,"操作成功！");
        return response;
    }

    @RequestMapping(value = "/update/rolemenu")
    public Response<Object> updateSysRoleMenu(@RequestBody SysRoleMenu sysRoleMenu){
        sysRoleMenuService.updateSysRoleMenu(sysRoleMenu);
        Response<Object> response = new Response<Object>(200,"操作成功！");
        return response;
    }
}
