package com.zhouyi.business.core.service;

import com.zhouyi.business.core.common.ReturnCode;
import com.zhouyi.business.core.dao.LedenCollectDnaMapper;
import com.zhouyi.business.core.exception.AuthenticationException;
import com.zhouyi.business.core.exception.XmlParseException;
import com.zhouyi.business.core.model.LedenCollectDna;
import com.zhouyi.business.core.model.Response;
import com.zhouyi.business.core.model.enums.AuthoirtyEnum;
import com.zhouyi.business.core.utils.ResponseUtil;
import com.zhouyi.business.core.utils.SecurityUtil;
import com.zhouyi.business.core.utils.XmlParseUtil;
import com.zhouyi.business.core.vo.LedenCollectDNAVo;
import com.zhouyi.business.core.vo.xml.LedenCollectDNAXml;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author 李秸康
 * @ClassNmae: LedenCollectDNAServiceImpl
 * @Description: DNA解析接口
 * @date 2019/7/4 13:50
 * @Version 1.0
 **/
@Service
public class LedenCollectDNAServiceImpl implements LedenCollectDNAService {


    @Autowired
    private LedenCollectDnaMapper ledenCollectDnaMapper;
    @Autowired
    private SecurityUtil securityUtil;


    /**
     * 录入解析xml中的DNA数据
     * @param path
     * @return
     */
    @Override
    @Transactional
    public Boolean inputDNAByXml(String path) throws AuthenticationException, XmlParseException {
        LedenCollectDNAVo dNANo= (LedenCollectDNAVo) XmlParseUtil.parseXml(path,LedenCollectDNAVo.class);
        //进行头部数据校验
        boolean flag=securityUtil.repairpermissions(dNANo.head, AuthoirtyEnum.DNAINFO);
        if(!flag)
            throw new AuthenticationException(ReturnCode.ERROR_1037);

        //数据转换
        LedenCollectDna ledenCollectDna=new LedenCollectDna();
        //添加其他数据
        ledenCollectDna.setCreateUserId(dNANo.head.getUSER_CODE());
        ledenCollectDna.setCreateDatetime(new Date());
        BeanUtils.copyProperties(dNANo.getData(),ledenCollectDna);
        //清除原有的dna数据
        ledenCollectDnaMapper.deleteByPrimaryKey(ledenCollectDna.getRyjcxxcjbh());

        ledenCollectDna.setPkId(UUID.randomUUID().toString().replace("-",""));
        return ledenCollectDnaMapper.insertSelective(ledenCollectDna)==1?true:false;
    }

    @Override
    public Response<List<LedenCollectDna>> selectDataByPersonCode(String id) {
        if (StringUtils.isEmpty(id)){
            ResponseUtil.returnError(ReturnCode.ERROR_02);
        }
        List<LedenCollectDna> list = ledenCollectDnaMapper.selectDataByPersonCode(id);
        return ResponseUtil.getResponseInfo(ReturnCode.SUCCESS,list);
    }


    @Override
    public LedenCollectDna getDnaByPersonCode(String personCode) {
        List<LedenCollectDna> ledenCollectDnas = ledenCollectDnaMapper.selectDataByPersonCode(personCode);
        if (ledenCollectDnas != null && ledenCollectDnas.size() > 0) {
            return ledenCollectDnas.get(0);
        }
        return null;
    }



}
