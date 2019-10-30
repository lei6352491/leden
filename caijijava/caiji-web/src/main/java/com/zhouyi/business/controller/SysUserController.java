package com.zhouyi.business.controller;

import com.zhouyi.business.core.common.ReturnCode;
import com.zhouyi.business.core.model.PageData;
import com.zhouyi.business.core.model.Response;
import com.zhouyi.business.core.model.SysUnit;
import com.zhouyi.business.core.model.SysUser;
import com.zhouyi.business.core.service.SysUnitService;
import com.zhouyi.business.core.service.SysUserService;
import com.zhouyi.business.core.utils.*;
import com.zhouyi.business.core.vo.SysUserPasswordVo;
import com.zhouyi.business.core.vo.SysUserVo;
import com.zhouyi.business.dto.ListSysUserDto;
import com.zhouyi.business.dto.SysUserDto;
import com.zhouyi.business.dto.UserInfoDto;
import com.zhouyi.business.dto.UserInfoReciverDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author 李秸康
 * @ClassNmae: SysUserController
 * @Description: 用户控制器
 * @date 2019/6/21 9:38
 * @Version 1.0
 **/
@RestController
@RequestMapping(value = "/api/sysUser")
@Api(description = "用户接口")
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private JWTUtil jwtUtil;
    @Autowired
    private SysUnitService sysUnitService;

    /**
     * 获取用户信息
     *
     * @param reciverDto
     * @return
     */
    @RequestMapping(value = "/getSysUser", method = RequestMethod.POST)
    @ApiOperation(value = "用户信息查询", notes = "获取用户信息", response = Response.class)
    public Response<Object> getSysUserByUserCode(@RequestBody UserInfoReciverDto reciverDto) {
        SysUser sysUser = sysUserService.searchSysUser(reciverDto.getUserAccount());
        if (sysUser == null)
            return ResponseUtil.returnError(ReturnCode.ERROR_1001);
        //将密码加盐后比对
        if (!sysUser.getUserPassword().equals(MD5Util.MD5(reciverDto.getUserPassword(), sysUser.getSalt())))
            return ResponseUtil.returnError(ReturnCode.Error_1025);


        //将数据封装为DTO
        UserInfoDto userInfoDto = new UserInfoDto();
        BeanUtils.copyProperties(sysUser, userInfoDto);
        return ResponseUtil.getResponseInfo(ReturnCode.SUCCESS, userInfoDto);
    }


    /**
     * 根据用户编码删除用户
     *
     * @param userCode
     * @return
     */
    @RequestMapping(value = "/deleteUser/{userCode}", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除用户(物理删除)", notes = "根据用户编码删除")
    @ApiImplicitParam(value = "被删除的用户编码", paramType = "path")
    public Response<Object> removeSysUser(@PathVariable String userCode) {
        boolean result = sysUserService.deleteByPrimaryKey(userCode);
        return ResponseUtil.getResponseInfo(result);
    }


    /**
     * 分页获取数据
     *
     * @param listSysUserDto
     * @return
     */
    @RequestMapping(value = "/listSysUser", method = RequestMethod.POST)
    @ApiOperation(value = "分页获取用户列表数据", response = Response.class)
    public Response<SysUser> listSysUser(@RequestBody ListSysUserDto listSysUserDto) {
        Map<String, Object> conditions = MapUtils.objectTransferToMap(listSysUserDto);


        //查询下级部门
        if (listSysUserDto.getCycle() == 1) {

            List<SysUnit> childUnits = sysUnitService.getSysUnitByParent(listSysUserDto.getUnitCode()
            );//获取下级部门list集合
            //提取其中的部门编码
            List<String> unitCodes = new ArrayList<>();
            fetchUnitCode(childUnits, unitCodes);
            unitCodes.add(listSysUserDto.getUnitCode());
            //将下级部门条件带入数据库
            conditions.put("units", unitCodes);
            conditions.put("unitCode", null);
        }
        //查询数据
        PageData<SysUser> pageData = sysUserService.searchSysUserPage(conditions);
        return ResponseUtil.getResponseInfo(ReturnCode.SUCCESS, pageData);
    }


    private void fetchUnitCode(List<SysUnit> unit, List<String> list) {
        unit.stream().collect(Collectors.toList()).forEach(x -> {
            if (x.getChildUnit() != null && x.getChildUnit().size() > 0) {
                fetchUnitCode(x.getChildUnit(), list);
            }
            list.add(x.getUnitCode());
        });

    }

    /**
     * 修改密码接口
     *
     * @param sysUserDto
     * @return
     */
    @RequestMapping(value = "/updatePassword", method = RequestMethod.PUT)
    @ApiOperation(value = "修改密码", response = Response.class)
    public Response<Object> modifyPassword(@RequestBody SysUserPasswordVo sysUserDto) {
        SysUserVo sysUserVo = new SysUserVo();
        sysUserVo.setUserCode(sysUserDto.getUserCode());
        //生成随机盐值
        String salt = MD5Util.generateSalt(sysUserVo.getUserCode());
        //将密码加盐处理
        String newPassword = MD5Util.MD5(sysUserDto.getUserPassword(), salt);
        sysUserVo.setSalt(salt);
        sysUserVo.setUserPassword(newPassword);

        boolean flag = false;
        try {
            flag = sysUserService.modifySysUserSelective(sysUserVo);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.ntrError("系统异常");
        }

        return ResponseUtil.getResponseInfo(flag);
    }


    /**
     * 修改用户信息接口
     *
     * @param sysUserDto
     * @return
     */
    @ApiOperation(value = "修改用户信息", notes = "根据用户编码修改用户信息")
    @RequestMapping(value = "/updateSysUser", method = RequestMethod.PUT)
    public Response<Object> modifySysUser(@RequestBody SysUserDto sysUserDto) {
        sysUserDto.setUpdateDatetime();//设置更新时间
        SysUserVo sysUserVo = new SysUserVo();
        BeanUtils.copyProperties(sysUserDto, sysUserVo);
        boolean result = false;
        try {
            result = sysUserService.modifySysUserSelective(sysUserVo);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.ntrError("系统日常");
        }

        return ResponseUtil.getResponseInfo(result);
    }


    /**
     * 用户注册接口
     *
     * @param sysUserDto
     * @return
     */
    @ApiOperation(value = "用户注册接口")
    @RequestMapping(value = "/userRegister", method = RequestMethod.POST)
    public Response<Object> userRegister(@RequestBody SysUserDto sysUserDto) {
        SysUserVo sysUserVo = new SysUserVo();
        BeanUtils.copyProperties(sysUserDto, sysUserVo);
        Boolean result = null;
        try {
            result = sysUserService.sysUserRegister(sysUserVo);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.ntrError("系统异常");
        }

        return ResponseUtil.getResponseInfo(result);
    }


    @ApiOperation(hidden = true, value = "测试")
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public Response<Object> test(HttpServletRequest request) {

        Cookie[] cookies = request.getCookies();
        for (Cookie cookie :
                cookies) {
            System.out.println(cookie.getName() + ":" + cookie.getValue());
        }
        return ResponseUtil.getResponseInfo(true);
    }


    @ApiOperation(value = "封禁用户")
    @RequestMapping(value = "/forbid/{userCode}", method = RequestMethod.PUT)
    @ApiImplicitParam(value = "用户编码", paramType = "path", name = "userCode")
    public Response<Object> forbidSysUser(@PathVariable String userCode) {
        SysUserVo sysUser = new SysUserVo();
        sysUser.setUserCode(userCode);
        sysUser.setDeleteFlag("1");
        boolean result = false;
        try {
            result = sysUserService.modifySysUserSelective(sysUser);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.ntrError("系统异常");
        }


        return ResponseUtil.getResponseInfo(result);

    }


    /**
     * 注销接口
     *
     * @param response
     * @return
     */
    @RequestMapping(value = "/logout")
    public Response<Object> logout(HttpServletResponse response, HttpServletRequest request) {
        TokenUtil.delCookie(request, response, "token");
        return ResponseUtil.getResponseInfo(true);
    }


    @RequestMapping(value = "/verification/password", method = RequestMethod.POST)
    @ApiOperation(value = "确认旧密码接口")
    public Response<Object> verificationPassword(@RequestBody VerificationPasswordDto verificationPasswordDto) {
        //获取用户信息
        boolean flag = sysUserService.checkUserPassword(verificationPasswordDto.getUserAccount(), verificationPasswordDto.getUserPassword());
        return ResponseUtil.getResponseInfo(ReturnCode.SUCCESS, flag);

    }


    @RequestMapping(value = "/check/user_account/{userAccount}", method = RequestMethod.GET)
    @ApiOperation(value = "/账户查重")
    @ApiImplicitParam(value = "用户账户", name = "userAccount", paramType = "path")
    public Response<Object> checkUserAccount(@PathVariable String userAccount) {
        boolean flag = sysUserService.checkUserAccount(userAccount);
        if (flag == true) {
            return ResponseUtil.getResponseInfo(ReturnCode.SUCCESS, "exists");
        } else {
            return ResponseUtil.getResponseInfo(ReturnCode.SUCCESS, "noexists");
        }
    }

    @Data
     static class VerificationPasswordDto {
        private String userAccount;
        private String userPassword;
    }
}
