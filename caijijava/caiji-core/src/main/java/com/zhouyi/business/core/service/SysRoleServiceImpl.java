package com.zhouyi.business.core.service;

import com.zhouyi.business.core.common.ReturnCode;
import com.zhouyi.business.core.dao.*;
import com.zhouyi.business.core.exception.BusinessException;
import com.zhouyi.business.core.exception.ExceptionCast;
import com.zhouyi.business.core.model.*;
import com.zhouyi.business.core.utils.InitializationPageUtils;
import com.zhouyi.business.core.utils.ResponseUtil;
import com.zhouyi.business.core.vo.SysRoleMenuVo;
import com.zhouyi.business.core.vo.SysRoleVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    private static final Integer ROW_NUMBER_ZERO = 0;

    @Override
    public Response findSysRoleById(String id) {
        /*SysRole sysRole = sysRoleMapper.selectByPrimaryKey(id);
        //查询该角色下的菜单信息
        List<SysRoleMenu> roleMenus = sysRoleMenuService.getSysRoleMenuByRoleId(id);
        Set<SysMenu> menus = new HashSet<>();
        if (roleMenus != null){
            for (SysRoleMenu sysRoleMenu : roleMenus){
                SysMenu sysMenu = sysMenuMapper.selectByPrimaryKey(sysRoleMenu.getMenuCode());
                menus.add(sysMenu);
            }
        }
        ResponseRoleMenu responseRoleMenu = new ResponseRoleMenu();
        responseRoleMenu.getRoles().add(sysRole);
        responseRoleMenu.setMenus(menus);
        return responseRoleMenu;*/

        SysRole sysRole = sysRoleMapper.selectByPrimaryKey(id);
        if (StringUtils.isNotEmpty(sysRole.getCreateUserId())){
            sysRole.setCreateUserName(sysUserMapper.selectByPrimaryKey(sysRole.getCreateUserId()).getUserName());
        }
        if (StringUtils.isNotEmpty(sysRole.getUpdateUserId())){
            sysRole.setUpdateUserName(sysUserMapper.selectByPrimaryKey(sysRole.getUpdateUserId()).getUserName());
        }

        return ResponseUtil.getResponseInfo(ReturnCode.SUCCESS,sysRole);
    }

    /**
     * 分页条件查询
     * */
    @Override
    public List<SysRole> findSysRoleListBySysRole(SysRoleVo sysRoleVo) {
        if (sysRoleVo.getPage() == null || sysRoleVo.getPage() < 1){
            sysRoleVo.setPage(1);
        }
        if (sysRoleVo.getSize() == null || sysRoleVo.getSize() < 1){
            sysRoleVo.setSize(20);
        }
        int stratNo = (sysRoleVo.getPage() - 1) * sysRoleVo.getSize() + 1;
        sysRoleVo.setStartNo(stratNo);
        int endNo = stratNo + sysRoleVo.getSize();
        sysRoleVo.setEndNo(endNo);

        List<SysRole> sysRoles = sysRoleMapper.selectByModel(sysRoleVo);
        return sysRoles;
    }

    @Override
    @Transactional
    public void saveSysRole(SysRole sysRole) {
        sysRole.setCreateDatetime(new Date());
        try{
            sysRoleMapper.insertSelective(sysRole);
        }catch (RuntimeException e){
            e.printStackTrace();
            ExceptionCast.cast(ResponseUtil.returnError(ReturnCode.ERROR_01));
        }
    }

    @Override
    @Transactional
    public void updateSysRole(SysRole sysRole) {
        if (sysRole == null || sysRole.getPkId() == null){
            ExceptionCast.cast(ResponseUtil.returnError(ReturnCode.ERROR_02));
        }
        //记录更新人从当前登陆用户中获取（后期按需求完成）
        sysRole.setUpdateDatetime(new Date());
        try{
            sysRoleMapper.updateByPrimaryKeySelective(sysRole);
        }catch (Exception e){
            e.printStackTrace();
            ExceptionCast.cast(ResponseUtil.returnError(ReturnCode.ERROR_01));
        }
        //更新中间表信息...
    }

    @Override
    @Transactional
    public void deleteSysRole(String id) {
        SysRole sysRole = sysRoleMapper.selectByPrimaryKey(id);
        if (sysRole != null){
            //删除该角色所对应的中间表信息
            //根据角色id删除中间表信息
            sysRoleMenuMapper.deleteRoleMenuByRoleId(id);
            int rowNumber = sysRoleMapper.deleteByPrimaryKey(id);
            if (rowNumber == ROW_NUMBER_ZERO){
                throw new BusinessException(503,"删除失败!");
            }
        }else {
            ExceptionCast.cast(ResponseUtil.returnError(ReturnCode.ERROR_05));
        }
    }

    @Override
    public int findTotal(SysRoleVo sysRoleVo){
        return sysRoleMapper.findTotal(sysRoleVo);
    }

    @Override
    public MenuTreeNode selectMenuTreeByUserCode(String userCode) {
        //根据用户id查询角色id
        List<String> list = sysUserRoleMapper.getRoleIdByUserCode(userCode,"0");
        List<MenuTreeNode> treeNodeList = sysRoleMapper.selectMenuTreeByRoleId(list);
        MenuTree resultMenuTree = new MenuTree();
        for (MenuTreeNode menuTreeNodeOne : treeNodeList){
            for (MenuTreeNode menuTreeNodeTow : treeNodeList){
                if (menuTreeNodeOne.getMenuCode().equals(menuTreeNodeTow.getUpperMenuCode())){
                    menuTreeNodeOne.getMenuTreeNodes().add(menuTreeNodeTow);
                }
            }
            resultMenuTree.getMenuTreeNodes().add(menuTreeNodeOne);
        }
        if (resultMenuTree.getMenuTreeNodes().size() != 0){
            return resultMenuTree.getMenuTreeNodes().get(0);
        }else {
            return null;
        }
    }

    @Override
    public MenuTreeNode selectMenuTreeByRoleId(String roleId) {
        List<MenuTreeNode> treeNodeList = sysRoleMapper.selectMenuTreeNodeByRoleId(roleId);
        MenuTree resultMenuTree = new MenuTree();
        for (MenuTreeNode menuTreeNodeOne : treeNodeList){
            for (MenuTreeNode menuTreeNodeTow : treeNodeList){
                if (menuTreeNodeOne.getMenuCode().equals(menuTreeNodeTow.getUpperMenuCode())){
                    menuTreeNodeOne.getMenuTreeNodes().add(menuTreeNodeTow);
                }
            }
            resultMenuTree.getMenuTreeNodes().add(menuTreeNodeOne);
        }
        if (resultMenuTree.getMenuTreeNodes().size() != 0){
            return resultMenuTree.getMenuTreeNodes().get(0);
        }else {
            return null;
        }
    }

    @Override
    public List<MenuTreeNode> selectMenuTreeTableByRoleId(String roleId) {
         return sysRoleMapper.selectMenuTreeNodeByRoleId(roleId);
    }

    @Override
    public Response updateRoleMenu(RoleMenuRequest roleMenuRequest) {
        //修改角色信息
        SysRole sysRole = new SysRole();
        sysRole.setPkId(roleMenuRequest.getRoleId());
        sysRole.setRoleDescription(roleMenuRequest.getRoleDescription());
        sysRole.setUpdateUserId(roleMenuRequest.getUserId());
        sysRole.setUpdateDatetime(new Date());

        sysRoleMapper.updateByPrimaryKeySelective(sysRole);

        //初始化查询条件
        SysRoleMenuVo sysRoleMenuVo = new SysRoleMenuVo();
        sysRoleMenuVo.setRoleId(roleMenuRequest.getRoleId());
        sysRoleMenuVo.setStartNo(1);
        sysRoleMenuVo.setEndNo(1000);
        List<SysRoleMenu> list = sysRoleMenuMapper.selectByModel(sysRoleMenuVo);
        //List<MenuTreeNode> list = sysRoleMapper.selectMenuTreeNodeByRoleId(roleMenuRequest.getRoleId());
        //筛选出该角色中新增的菜单，添加到数据库
        if (roleMenuRequest.getMenus() != null){
            for (String menuId : roleMenuRequest.getMenus()){
                boolean flag = false;
                for (SysRoleMenu menuTreeNode : list){
                    if (menuId.equals(menuTreeNode.getMenuCode()))
                        flag = true;
                }
                //当flag为false，该菜单信息为新增的信息
                if (!flag){
                    SysRoleMenu sysRoleMenu = new SysRoleMenu();
                    sysRoleMenu.setPkId(UUID.randomUUID().toString().replace("-",""));
                    sysRoleMenu.setRoleId(roleMenuRequest.getRoleId());
                    sysRoleMenu.setMenuCode(menuId);
                    sysRoleMenu.setUpdateUserId(roleMenuRequest.getUserId());
                    sysRoleMenu.setUpdateDatetime(new Date());
                    sysRoleMenu.setCreateUserId(roleMenuRequest.getUserId());
                    sysRoleMenu.setCreateDatetime(new Date());
                    sysRoleMenu.setDeleteFlag("0");
                    sysRoleMenuMapper.insertSelective(sysRoleMenu);
                }
            }
            //筛选出该角色中删除的菜单
            for (SysRoleMenu menuTreeNode : list){
                boolean flag = false;
                for (String menuId : roleMenuRequest.getMenus()){
                    if (menuId.equals(menuTreeNode.getMenuCode())){
                        flag = true;
                    }
                }
                //当flag为false，该菜单信息为删除的信息
                if (!flag){
                    sysRoleMenuMapper.deleteByPrimaryKey(menuTreeNode.getPkId());
                }
            }
        }else {
            if (list != null){
                for (SysRoleMenu menuTreeNode : list){
                    sysRoleMenuMapper.deleteByPrimaryKey(menuTreeNode.getPkId());
                }
            }
        }
        return ResponseUtil.getResponseInfo(true);
    }

    @Override
    public Response saveRoleMenu(RoleMenuRequest roleMenuRequest) {
        //新增的时候判断角色明是否重复
        SysRoleVo sysRoleQuery = new SysRoleVo();
        sysRoleQuery.setRoleName(roleMenuRequest.getRoleName());
        new InitializationPageUtils<SysRole>().initializationPage(sysRoleQuery);
        List<SysRole> roles = sysRoleMapper.selectByModel(sysRoleQuery);
        if (roles == null || roles.size() > 0){
            ExceptionCast.cast(ResponseUtil.returnError(ReturnCode.ERROR_09));
        }
        //新增角色到数据库
        SysRole sysRole = new SysRole();
        sysRole.setPkId(UUID.randomUUID().toString().replace("-",""));
        sysRole.setRoleName(roleMenuRequest.getRoleName());
        sysRole.setDeleteFlag("0");
        sysRole.setCreateUserId(roleMenuRequest.getUserId());
        sysRole.setCreateDatetime(new Date());
        sysRole.setUpdateUserId(roleMenuRequest.getUserId());
        sysRole.setUpdateDatetime(sysRole.getCreateDatetime());
        sysRole.setRoleDescription(roleMenuRequest.getRoleDescription());
        sysRoleMapper.insertSelective(sysRole);
        //在该角色下添加菜单数据

        if (roleMenuRequest.getMenus() != null){
            for (String menuId : roleMenuRequest.getMenus()){
                SysRoleMenu sysRoleMenu = new SysRoleMenu();
                sysRoleMenu.setPkId(UUID.randomUUID().toString().replace("-",""));
                sysRoleMenu.setRoleId(sysRole.getPkId());
                sysRoleMenu.setMenuCode(menuId);
                sysRoleMenu.setDeleteFlag("0");
                sysRoleMenu.setCreateUserId(roleMenuRequest.getUserId());
                sysRoleMenu.setCreateDatetime(new Date());
                sysRoleMenu.setUpdateUserId(roleMenuRequest.getUserId());
                sysRole.setUpdateDatetime(sysRoleMenu.getCreateDatetime());
                sysRoleMenuMapper.insertSelective(sysRoleMenu);
            }
        }
        return ResponseUtil.getResponseInfo(true);
    }


}
