package com.zhouyi.business.core.service;

import com.alibaba.fastjson.JSON;
import com.zhouyi.business.core.common.ReturnCode;
import com.zhouyi.business.core.dao.LedenEquipmentMapper;
import com.zhouyi.business.core.dao.SysUnitMapper;
import com.zhouyi.business.core.dao.SysUserMapper;
import com.zhouyi.business.core.exception.BusinessException;
import com.zhouyi.business.core.model.*;
import com.zhouyi.business.core.utils.JWTUtil;
import com.zhouyi.business.core.utils.MD5Util;
import com.zhouyi.business.core.utils.TokenUtil;
import com.zhouyi.business.core.vo.SysUserVo;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.*;


/**
 * @author 李秸康
 * @ClassNmae: SysUserServiceImpl
 * @Description: TODO
 * @date 2019/6/20 17:10
 * @Version 1.0
 **/
@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysUserRoleService sysUserRoleService;
    @Autowired
    private SysUnitMapper sysUnitMapper;
    @Autowired
    private LedenEquipmentMapper ledenEquipmentMapper;

    @Autowired
    private JWTUtil jwtUtil;

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 删除系统用户信息
     *
     * @param userCode 用户编码
     * @return
     */
    @Override
    public Boolean deleteByPrimaryKey(String userCode) {

        return sysUserMapper.deleteByPrimaryKey(userCode) == 1 ? true : false;
    }


    /**
     * 完整新增用户信息
     *
     * @param sysUser
     * @return
     */
    @Override
    public Boolean insert(SysUser sysUser) {
        return sysUserMapper.insert(sysUser) == 1 ? true : false;
    }

    /**
     * 根据用户编码查询用户信息
     *
     * @param userCode
     * @return
     */
    @Override
    public SysUser searchSysUser(String userCode) {
        return sysUserMapper.getSysUserByUserAccount(userCode);
    }


    /**
     * 完整修改用户信息
     *
     * @param sysUser
     * @return
     */
    @Override
    public Boolean modifySysUser(SysUser sysUser) {
        return sysUserMapper.updateByPrimaryKey(sysUser) == 1 ? true : false;
    }


    /**
     * 用户登陆业务
     *
     * @param userAccount
     * @param userPassword
     * @return
     */
    @Override
    public SysUser login(String userAccount, String userPassword, HttpServletResponse response,String equipmentMac) throws BusinessException {
        SysUser sysUser = null;
        try {
            sysUser = sysUserMapper.getSysUserByUserAccount(userAccount);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(ReturnCode.ERROR_04);
        }
        //用户不存在情况
        if (sysUser == null)
            throw new BusinessException(ReturnCode.ERROR_1001);


        //用户被封禁情况
        if (sysUser.getDeleteFlag().equals("1"))
            throw new BusinessException(ReturnCode.ERROR_1030);
        /**
         * 将密码md5加密加盐后比对
         */
        String newPassword = MD5Util.MD5(userPassword, sysUser.getSalt());
        if (sysUser.getUserPassword().equals(newPassword) == false)
            throw new BusinessException(ReturnCode.Error_1025);

        String unitCode = sysUser.getUserUnitCode();
        //查看该用户所在的部门是否启用
        SysUnit sysUnit = sysUnitMapper.selectByPrimaryKey(unitCode);
        if (sysUnit.getDeleteFlag().equals("1"))
            throw new BusinessException(ReturnCode.ERROR_1029);

        //如果用户登陆成功，则签发token信息
        /**
         * 修改2019-6-25签发
         */
        Object[] resultObject=jwtUtil.createJwtToken(sysUser);
        String token = resultObject[1].toString();
        sysUser.setExpDate((Date)resultObject[0]);
        logger.info("签发token:" + token);
        //获取cookie，进行写入
        TokenUtil.setCookie("token", token, response);

        if (StringUtils.isNotEmpty(equipmentMac)){
            LedenEquipment ledenEquipment = ledenEquipmentMapper.selectEquipmentByMac(equipmentMac);
            sysUser.setLedenEquipment(ledenEquipment);
        }

        return sysUser;
    }

    /**
     * 系统分页查询信息
     *
     * @param conditions
     * @return
     */
    @Override
    public PageData<SysUser> searchSysUserPage(Map<String, Object> conditions) {
        List<SysUser> list = sysUserMapper.listSysUserByConditions(conditions);
        Integer totalCount = sysUserMapper.getSysUserCountByConditions(conditions);
        PageData<SysUser> pageData = new PageData<>(list, totalCount, (int) conditions.get("pSize"));
        return pageData;
    }


    /**
     * 用户注册接口
     * 开启事务处理
     *
     * @param sysUserVo
     * @return
     */
    @Override
    @Transactional
    public Boolean sysUserRegister(SysUserVo sysUserVo) throws Exception {
        //验证账户是否重复
        if (sysUserMapper.getSysUserAccountCount(sysUserVo.getUserAccount()) == 1) {
            throw new BusinessException(ReturnCode.ERROR_1031);
        }
        sysUserVo.setCreateDatetime(new Date());
        String userCode = UUID.randomUUID().toString().substring(0, 32);
        sysUserVo.setUserCode(userCode);
        modifyUserRoles(sysUserVo);

        //设置创建时间
        //获取一个盐值
        String salt = MD5Util.generateSalt(sysUserVo.getUserAccount());
        String newPassword = MD5Util.MD5(sysUserVo.getUserPassword(), salt);
        sysUserVo.setSalt(salt);
        sysUserVo.setUserPassword(newPassword);
        return sysUserMapper.insertSelective(sysUserVo) == 1 ? true : false;
    }

    /**
     * 用户账户查重
     *
     * @param userAccount
     * @return
     */
    @Override
    public boolean checkUserAccount(String userAccount) {
        return sysUserMapper.getSysUserAccountCount(userAccount) == 1 ? true : false;
    }

    /**
     * 密码校验
     *
     * @param userAccount
     * @param userPassword
     * @return
     */
    @Override
    public boolean checkUserPassword(String userAccount, String userPassword) {
        SysUser sysUser = sysUserMapper.getSysUserByUserAccount(userAccount);
        if (sysUser == null) {
            throw new BusinessException(ReturnCode.ERROR_1001);
        }

        //加密输入的密码和原有的比对
        String tempPassword = MD5Util.MD5(userPassword, sysUser.getSalt());
        if (!sysUser.getUserPassword().equals(tempPassword))
            return false;
        return true;
    }


    /**
     * 选择性修改
     *
     * @param sysUserVo
     * @return
     */
    @Override
    public Boolean modifySysUserSelective(SysUserVo sysUserVo) throws Exception {
        //修改角色信息
        if (sysUserVo.getRoleIds() != null)
            modifyUserRoles(sysUserVo);
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(sysUserVo, sysUser);
        sysUser.setUpdateDatetime(new Date());
        sysUser.setUpdateUserId(sysUser.getUserCode());
        return sysUserMapper.updateByPrimaryKeySelective(sysUser) == 1 ? true : false;
    }

    public void modifyUserRoles(SysUserVo sysUserVo) throws Exception {
        //删除原有角色
        if (
                sysUserVo.getUserCode() != null && sysUserRoleService.deleteRolesById(sysUserVo.getUserCode()) == false) {
            throw new Exception("删除原有角色失败");
        }
        //添加新的角色信息
        //循环创建角色对象
        for (String roleId : sysUserVo.getRoleIds()) {
            //构建一个角色对象
            SysUserRole sysUserRole = new SysUserRole(
                    UUID.randomUUID().toString().substring(0, 32),
                    sysUserVo.getUserCode(),
                    roleId,
                    "0",
                    sysUserVo.getCreateUserId(),
                    new Date()
            );

            //进行新增操作
            sysUserRoleService.saveData(sysUserRole);
        }
    }


    public static void main(String[] args) {

        Student student = new Student();
        student.setName("duchengxu");
        String[] ar = new String[]{"fsd", "fds"};
        student.setBooks(ar);
        System.out.println(JSON.toJSONString(student));
    }

    @Data
    static class Student {
        private String name;
        private String[] books;


    }
}
