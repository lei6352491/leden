package com.zhouyi.business.core.service;

import com.zhouyi.business.core.common.ReturnCode;
import com.zhouyi.business.core.dao.BuffBaseMapper;
import com.zhouyi.business.core.dao.LedenEquipmentMapper;
import com.zhouyi.business.core.dao.SysUnitMapper;
import com.zhouyi.business.core.dao.SysUserMapper;
import com.zhouyi.business.core.exception.ExceptionCast;
import com.zhouyi.business.core.model.*;
import com.zhouyi.business.core.utils.ResponseUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseServiceImpl<T, V> implements BaseService<T, V> {

    @Autowired
    private BuffBaseMapper<T, V> buffBaseMapper;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysUnitMapper sysUnitMapper;

    @Autowired
    private LedenEquipmentMapper ledenEquipmentMapper;

    private Class clazz;

    private Field[] fields;

    public BaseServiceImpl() {
        //获取父类的Class对象
        Class clazz = this.getClass();
        //通过子类的Class对象获取参数化类型
        Type type = clazz.getGenericSuperclass();
        ParameterizedType pType = (ParameterizedType) type;
        //获取实际化参数
        Type[] types = pType.getActualTypeArguments();
        //实际化参数的实现类是Class，可以把参数强转问Class对象，得到了子类中泛型的Class对象
        this.clazz = (Class) types[1];
        try {
            fields = this.clazz.getDeclaredFields();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Response findDataById(String id) {
        T t = buffBaseMapper.selectByPrimaryKey(id);
        //根据业务需求查询关联表信息

        if (t == null) {
            ExceptionCast.cast(ResponseUtil.getResponseInfo(false));
        }
        return ResponseUtil.getResponseInfo(ReturnCode.SUCCESS, t);
    }

    @Override
    public Response findDataList(V v) {

        Integer page = null;
        Integer size = null;

        //设置默认情况下的分页参数
        for (Field field : fields) {
            if (field != null) {
                field.setAccessible(true);
                if (field.getName().equals("page")) {
                    try {
                        page = (Integer) field.get(v);
                        if (page == null || page < 1) {
                            field.set(v, 1);
                            page = (Integer) field.get(v);
                        }
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
                if (field.getName().equals("size")) {
                    try {
                        size = (Integer) field.get(v);
                        if (size == null || size < 1) {
                            field.set(v, 20);
                            size = (Integer) field.get(v);
                        }
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            }
        }

        Integer startNo = (page - 1) * size + 1;
        Integer endNo = startNo + size;

        for (Field field : fields) {
            if (field != null) {
                field.setAccessible(true);
                if (field.getName().equals("startNo")) {
                    try {
                        field.set(v, startNo);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
                if (field.getName().equals("endNo")) {
                    try {
                        field.set(v, endNo);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        List<T> list = buffBaseMapper.selectByModel((T) v);
        int total = buffBaseMapper.findTotal(v);
        HashMap<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("list", list);
        return ResponseUtil.getResponseInfo(ReturnCode.SUCCESS, map);
    }

    @Override
    @Transactional
    public Response saveData(T t) {
        //buffBaseMapper.insertSelective(t);
        try {
            buffBaseMapper.insertSelective(t);
        } catch (Exception e) {
            e.printStackTrace();
            ExceptionCast.cast(ResponseUtil.returnError(ReturnCode.ERROR_01));

        }
        return ResponseUtil.getResponseInfo(true);
    }

    @Override
    @Transactional
    public Response updateData(T t) {
        try {
            buffBaseMapper.updateByPrimaryKeySelective(t);
        } catch (Exception e) {
            ExceptionCast.cast(ResponseUtil.returnError(ReturnCode.ERROR_1015));
        }
        return ResponseUtil.getResponseInfo(true);
    }

    @Override
    @Transactional
    public Response deleteData(String id) {
        try {
            buffBaseMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            ExceptionCast.cast(ResponseUtil.returnError(ReturnCode.ERROR_05));
        }
        return ResponseUtil.getResponseInfo(true);
    }

    //设备权限校验
    @Override
    public boolean checkHead(Head head) {
        if (head == null)
            ExceptionCast.cast(ResponseUtil.returnError(ReturnCode.ERROR_11));
        if (StringUtils.isEmpty(head.getUserCode()))
            ExceptionCast.cast(ResponseUtil.returnError(ReturnCode.ERROR_1001));
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(head.getUserCode());
        if (sysUser == null)
            ExceptionCast.cast(ResponseUtil.returnError(ReturnCode.ERROR_1014));
        if (StringUtils.isEmpty(head.getUserUnitCode()))
            ExceptionCast.cast(ResponseUtil.returnError(ReturnCode.ERROR_11));
        SysUnit sysUnit = sysUnitMapper.selectByPrimaryKey(head.getUserUnitCode());
        if (sysUnit == null)
            ExceptionCast.cast(ResponseUtil.returnError(ReturnCode.ERROR_12));
        if(!sysUnit.getUnitCode().equals(head.getUserUnitCode()))
            ExceptionCast.cast(ResponseUtil.returnError(ReturnCode.ERROR_1037));
        if (StringUtils.isEmpty(head.getEquipmentCode()))
            ExceptionCast.cast(ResponseUtil.returnError(ReturnCode.ERROR_11));
        LedenEquipment ledenEquipment = new LedenEquipment();
        ledenEquipment.setEquipmentCode(head.getEquipmentCode());
        Integer count = ledenEquipmentMapper.selectEquipmentByCodeTotal(ledenEquipment);
        if (count < 1)
            ExceptionCast.cast(ResponseUtil.returnError(ReturnCode.ERROR_13));
        return true;
    }


    @Override
    public int resoveSaveData(T t) {
        return buffBaseMapper.insertSelective(t);
    }

}
