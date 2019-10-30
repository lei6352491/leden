package com.zhouyi.business.core.service;

import com.zhouyi.business.core.common.ReturnCode;
import com.zhouyi.business.core.dao.LedenCollectPersonMapper;
import com.zhouyi.business.core.exception.AuthenticationException;
import com.zhouyi.business.core.exception.ExceptionCast;
import com.zhouyi.business.core.exception.XmlParseException;
import com.zhouyi.business.core.model.LedenCollectPerson;
import com.zhouyi.business.core.model.PersonResult;
import com.zhouyi.business.core.model.Response;
import com.zhouyi.business.core.model.enums.AuthoirtyEnum;
import com.zhouyi.business.core.utils.ResponseUtil;
import com.zhouyi.business.core.utils.SecurityUtil;
import com.zhouyi.business.core.utils.XmlParseUtil;
import com.zhouyi.business.core.vo.LedenConllectPersonVo;
import com.zhouyi.business.core.vo.LedenConllectPersonVo2;
import com.zhouyi.business.core.vo.xml.LedenCollectPersonXml;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.misc.BASE64Encoder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 李秸康
 * @ClassNmae: LedenCollectPersonServiceImpl
 * @Description: TODO
 * @date 2019/7/1 15:31
 * @Version 1.0
 **/
@Service
public class LedenCollectPersonServiceImpl implements LedenCollectPersonService {

    @Autowired
    private LedenCollectPersonMapper ledenCollectPersonMapper;
    @Autowired
    private SecurityUtil securityUtil;


    /**
     * 解析入库
     * @param path
     * @return
     */
    @Override
    @Transactional
    public Boolean insertCollectPersonByXml(String path) throws XmlParseException,AuthenticationException {

        //获取解析到的vo对象
        LedenConllectPersonVo ledenConllectPersonVo=(LedenConllectPersonVo) XmlParseUtil.parseXml(path,LedenConllectPersonVo.class);

        //检验头部数据
        if(securityUtil.repairpermissions(ledenConllectPersonVo.head, AuthoirtyEnum.PERSON)==false){
            throw new AuthenticationException(ReturnCode.ERROR_1037);
        }


        //进行数据转换
        LedenCollectPersonXml personXml=ledenConllectPersonVo.data;
        XmlParseUtil.copyHeader(ledenConllectPersonVo,personXml);

        LedenCollectPerson ledenCollectPerson=new LedenCollectPerson();//创建pojo对象

        BeanUtils.copyProperties(personXml,ledenCollectPerson);

        //删除数据库该人员编号的个人信息
        ledenCollectPersonMapper.deleteByPrimaryKey(personXml.ryjcxxcjbh);

        //执行新增操作
        int executorResult=ledenCollectPersonMapper.insertSelective(ledenCollectPerson);
        return executorResult>0?true:false;
    }

    @Override
    public Response selectAllCJRXM() {
        List<LedenCollectPerson> list = ledenCollectPersonMapper.selectAllCJRXM();
        return ResponseUtil.getResponseInfo(ReturnCode.SUCCESS,list);
    }

    //多条件查询采集人员信息列表
    @Override
    public Response<Map<String,Object>> selectDataList(LedenConllectPersonVo2 ledenConllectPersonVo2) {
        try{
            initializationPage(ledenConllectPersonVo2);
            List<PersonResult> list = ledenCollectPersonMapper.selectCollectPersonList(ledenConllectPersonVo2);
            int total = ledenCollectPersonMapper.selectCollectPersonListCount(ledenConllectPersonVo2);
            Map<String, Object> map = new HashMap<>();
            map.put("list",list);
            map.put("total",total);
            return ResponseUtil.getResponseInfo(ReturnCode.SUCCESS,map);
        }catch (Exception e){
            e.printStackTrace();
            ExceptionCast.cast(ResponseUtil.returnError(ReturnCode.ERROR_01));
        }
        return null;
    }

    @Override
    public Response<PersonResult> selectPersonByPersonCode(String id) {
        PersonResult personResult = ledenCollectPersonMapper.selectPersonByPersonCode(id);
        if (personResult != null){
            personResult.setEncode(new String(personResult.getJdxp()));
            personResult.setJdxp(null);
        }
        //System.out.println(new String(personResult.getJdxp()));
        //对图片进行加密
        /*if (personResult.getJdxp() != null){
            String encode = new BASE64Encoder().encode(personResult.getJdxp());
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("data:image/png;base64,");
            stringBuilder.append(encode);
            personResult.setEncode(stringBuilder.toString());
            personResult.setJdxp(null);
        }*/
        return ResponseUtil.getResponseInfo(ReturnCode.SUCCESS,personResult);
    }

    //初始化分页
    private LedenConllectPersonVo2 initializationPage(LedenConllectPersonVo2 ledenConllectPersonVo2){
        if (ledenConllectPersonVo2 == null){
            ledenConllectPersonVo2 = new LedenConllectPersonVo2();
        }
        if (ledenConllectPersonVo2.getPage() == null || ledenConllectPersonVo2.getPage() < 1){
            ledenConllectPersonVo2.setPage(1);
        }
        if (ledenConllectPersonVo2.getSize() == null || ledenConllectPersonVo2.getSize() < 1){
            ledenConllectPersonVo2.setSize(20);
        }
        ledenConllectPersonVo2.setStartNo((ledenConllectPersonVo2.getPage() - 1) * ledenConllectPersonVo2.getSize() + 1);
        ledenConllectPersonVo2.setEndNo(ledenConllectPersonVo2.getStartNo() + ledenConllectPersonVo2.getSize());
        return ledenConllectPersonVo2;
    }

}
