package com.zhouyi.business.core.service;

import com.zhouyi.business.core.model.Response;
import com.zhouyi.business.core.model.SysUserRole;
import com.zhouyi.business.core.vo.SysUserRoleVo;

/**
 * @author 杜承旭
 * @ClassNmae: SysUserRoleService
 * @Description: TODO
 * @date 2019/7/15 16:41
 * @Version 1.0
 **/
public interface SysUserRoleService extends BaseService<SysUserRole, SysUserRoleVo> {

    Response getRoleListByUserCode(SysUserRoleVo sysUserRoleVo);

    /**
      * @author 李秸康
      * @Description 删除用户所有id
      * @date 2019/8/8
      * @params
      * @return
     **/
    boolean deleteRolesById(String userId);
}
