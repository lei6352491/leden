package com.zhouyi.business.core.service;

import com.zhouyi.business.core.common.ReturnCode;
import com.zhouyi.business.core.dao.SysRoleMapper;
import com.zhouyi.business.core.dao.SysUserRoleMapper;
import com.zhouyi.business.core.exception.ExceptionCast;
import com.zhouyi.business.core.model.Response;
import com.zhouyi.business.core.model.SysRole;
import com.zhouyi.business.core.model.SysUserRole;
import com.zhouyi.business.core.utils.ResponseUtil;
import com.zhouyi.business.core.vo.SysUserRoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 杜承旭
 * @ClassNmae: SysUserRoleServiceImpl
 * @Description: TODO
 * @date 2019/7/15 16:42
 * @Version 1.0
 **/
@Service
public class SysUserRoleServiceImpl extends BaseServiceImpl<SysUserRole, SysUserRoleVo> implements SysUserRoleService{

    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;
    @Override
    public Response getRoleListByUserCode(SysUserRoleVo sysUserRoleVo) {
        Response dataList = this.findDataList(sysUserRoleVo);
        Map map = (Map)dataList.getData();
        if (map == null){
            ExceptionCast.cast(ResponseUtil.returnError(ReturnCode.ERROR_05));
        }
        Integer total = (Integer)map.get("total");
        List<SysUserRole> list = (List<SysUserRole>)map.get("list");
        List<SysRole> sysRoles = new ArrayList<>();
        for (SysUserRole sysUserRole : list){
            String roleId = sysUserRole.getRoleId();
            SysRole sysRole = sysRoleMapper.selectByPrimaryKey(roleId);
            sysRoles.add(sysRole);
        }
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("total",total);
        hashMap.put("list",sysRoles);
        return ResponseUtil.getResponseInfo(ReturnCode.SUCCESS,hashMap);
    }

    @Override
    public boolean deleteRolesById(String userId) {
        try {
            sysUserRoleMapper.deleteRoleByUserId(userId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
