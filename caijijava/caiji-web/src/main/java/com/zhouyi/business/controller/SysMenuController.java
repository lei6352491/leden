package com.zhouyi.business.controller;

import com.zhouyi.business.core.model.Response;
import com.zhouyi.business.core.model.ResponseRoleMenu;
import com.zhouyi.business.core.model.SysMenu;
import com.zhouyi.business.core.service.SysMenuService;
import com.zhouyi.business.core.vo.SysMenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value = "/api/menu")
public class SysMenuController {

    @Autowired
    private SysMenuService sysMenuService;

    @RequestMapping(value = "/get/menu/{id}")
    public Response<ResponseRoleMenu> getSysMenuById(@PathVariable(value = "id")String id){
        ResponseRoleMenu responseRoleMenu = sysMenuService.findSysMenuById(id);
        if (responseRoleMenu != null){
            Response<ResponseRoleMenu> responseResult = new Response<ResponseRoleMenu>();
            responseResult.setData(responseRoleMenu);
            return responseResult;
        }
        Response<ResponseRoleMenu> responseResult = new Response<ResponseRoleMenu>(201,"没有该菜单！");
        return responseResult;
    }

    @RequestMapping(value = "/get/menuList")
    public Response<Object> getSysMenuList(@RequestBody SysMenuVo sysMenuVo){
        List<SysMenu> menuList = sysMenuService.findSysMenuListBySysMenu(sysMenuVo);
        if (menuList == null){
            return null;
        }
        int total = sysMenuService.findTotal(sysMenuVo);
        HashMap<String, Object> map = new HashMap<String,Object>();
        map.put("total",total);
        map.put("list",menuList);
        Response<Object> response = new Response<Object>();
        response.setData(map);
        return response;
    }

    @RequestMapping(value = "/save/menu")
    public Response<Object> saveSysMenu(@RequestBody SysMenu sysMenu){
        sysMenuService.saveSysMenu(sysMenu);
        Response<Object> response = new Response<Object>(200,"操作成功！");
        return response;
    }

    @RequestMapping(value = "/update/menu")
    public Response<Object> updateSysMen(@RequestBody SysMenu sysMenu){
        sysMenuService.updateSysMenu(sysMenu);
        Response<Object> response = new Response<Object>(200,"操作成功！");
        return response;
    }

    @RequestMapping(value = "/delete/menu/{id}")
    public Response<Object> deleteSysMenu(@PathVariable(value = "id") String id){
        sysMenuService.deleteSysMenu(id);
        Response<Object> response = new Response<Object>(200,"操作成功！");
        return response;
    }

}
