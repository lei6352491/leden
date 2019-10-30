package com.zhouyi.business.controller;

import com.zhouyi.business.core.model.Response;
import com.zhouyi.business.core.service.SysUserRoleService;
import com.zhouyi.business.core.vo.SysUserRoleVo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 杜承旭
 * @ClassNmae: SysUserRoleController
 * @Description: TODO
 * @date 2019/7/15 16:53
 * @Version 1.0
 **/

@RestController
@RequestMapping(value = "/api/sysuserrole")
@Api(hidden = true)
public class SysUserRoleController {

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @RequestMapping(value = "/getlist")
    public Response getRoleListByUserCode(@RequestBody SysUserRoleVo sysUserRoleVo){
        return sysUserRoleService.getRoleListByUserCode(sysUserRoleVo);
    }
}
