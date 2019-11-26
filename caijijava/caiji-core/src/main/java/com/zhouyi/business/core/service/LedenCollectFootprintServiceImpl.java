package com.zhouyi.business.core.service;

import com.zhouyi.business.core.common.ReturnCode;
import com.zhouyi.business.core.dao.LedenCollectFootprintMapper;
import com.zhouyi.business.core.exception.AuthenticationException;
import com.zhouyi.business.core.exception.XmlParseException;
import com.zhouyi.business.core.model.LedenCollectFootprint;
import com.zhouyi.business.core.model.Response;
import com.zhouyi.business.core.model.enums.AuthoirtyEnum;
import com.zhouyi.business.core.utils.MapUtils;
import com.zhouyi.business.core.utils.ResponseUtil;
import com.zhouyi.business.core.utils.SecurityUtil;
import com.zhouyi.business.core.utils.XmlParseUtil;
import com.zhouyi.business.core.vo.LedenCollectFootprintSearchVo;
import com.zhouyi.business.core.vo.LedenCollectFootprintVo;
import com.zhouyi.business.core.vo.xml.LedenCollectFootprintXml;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.misc.BASE64Encoder;

import java.util.*;

/**
 * @author 李秸康
 * @ClassNmae: LedenCollectFootprintServiceImpl
 * @Description: 足迹业务接口
 * @date 2019/7/4 14:53
 * @Version 1.0
 **/
@Service
public class LedenCollectFootprintServiceImpl implements LedenCollectFootprintService {

    @Autowired
    private LedenCollectFootprintMapper ledenCollectFootprintMapper;
    @Autowired
    private SecurityUtil securityUtil;

    /**
     * 录入xml数据信息
     *
     * @param path
     * @return
     */
    @Override
    @Transactional
    public Boolean inputFootprintByXml(String path) throws XmlParseException, AuthenticationException {

        LedenCollectFootprintVo ledenCollectFootprintVo = (LedenCollectFootprintVo) XmlParseUtil.parseXml(path, LedenCollectFootprintVo.class);
        //进行头部信息校验
        boolean flag = securityUtil.repairpermissions(ledenCollectFootprintVo.head, AuthoirtyEnum.FOOTPRINT);
        if (!flag) {
            throw new AuthenticationException(ReturnCode.ERROR_1037);
        }

        List<LedenCollectFootprint> ledenCollectFootprints = new ArrayList<>();

        //复制数据
        for (LedenCollectFootprintXml data : ledenCollectFootprintVo.getData()) {
            LedenCollectFootprint ledenCollectFootprint = new LedenCollectFootprint();
            BeanUtils.copyProperties(data, ledenCollectFootprint);
            ledenCollectFootprint.setCreateUserId(ledenCollectFootprintVo.head.getUSER_CODE());
            ledenCollectFootprint.setCreateDatetime(new Date());
            ledenCollectFootprint.setPkId(UUID.randomUUID().toString().substring(0, 32));
            ledenCollectFootprints.add(ledenCollectFootprint);
            ledenCollectFootprint.setCreateDatetime(new Date());
        }
        //清除原有数据
        ledenCollectFootprintMapper.deleteFootPrintByPersonId(ledenCollectFootprints.get(0).getRyjcxxcjbh());
        if (ledenCollectFootprints != null && ledenCollectFootprints.size() > 0) {
            ledenCollectFootprintMapper.insertFootPrints(ledenCollectFootprints);
        }
        return true;
    }

    @Override
    public List<LedenCollectFootprintSearchVo> listFoots(String RYJCXXCJBH, String pNo, String pSize) {
        Map<String, Object> conditions = new HashMap<>();
        conditions.put("RYJCXXCJBH", RYJCXXCJBH);
        if (pNo != null && pSize != null)
            MapUtils.setPageConditions(Integer.parseInt(pNo), Integer.parseInt(pSize), conditions);
        List<LedenCollectFootprintSearchVo> foots = ledenCollectFootprintMapper.listDataByConditions(conditions);
        return foots;
    }

    @Override
    public Response<LedenCollectFootprint> getFootprint(String id) {
        LedenCollectFootprint ledenCollectFootprint = ledenCollectFootprintMapper.selectDataById(id);
        if (ledenCollectFootprint != null) {
            ledenCollectFootprint.setZjzp(new String(ledenCollectFootprint.getZjsj()));
            ledenCollectFootprint.setZjsj(null);
        }
        return ResponseUtil.getResponseInfo(ReturnCode.SUCCESS, ledenCollectFootprint);
    }

    @Override
    public List<LedenCollectFootprint> listFootPrintByPersonCode(String personCode) {
        return ledenCollectFootprintMapper.listFootPrintByConditions(new HashMap<String, Object>(1) {{
            put("personCode", personCode);
        }});
    }
}
