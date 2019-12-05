package com.zhouyi.business.core.service;

import com.zhouyi.business.core.common.ReturnCode;
import com.zhouyi.business.core.dao.LedenCollectHandwritingMapper;
import com.zhouyi.business.core.exception.AuthenticationException;
import com.zhouyi.business.core.exception.XmlParseException;
import com.zhouyi.business.core.model.LedenCollectHandwriting;
import com.zhouyi.business.core.model.Response;
import com.zhouyi.business.core.model.enums.AuthoirtyEnum;
import com.zhouyi.business.core.utils.ResponseUtil;
import com.zhouyi.business.core.utils.SecurityUtil;
import com.zhouyi.business.core.utils.XmlParseUtil;
import com.zhouyi.business.core.vo.LedenCollectHandWritingVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.misc.BASE64Encoder;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * @author 李秸康
 * @ClassNmae: LedenCollectHandWritingServiceImpl
 * @Description: TODO
 * @date 2019/7/4 15:56
 * @Version 1.0
 **/
@Service
public class LedenCollectHandWritingServiceImpl implements LedenCollectHandWritingService {

    @Autowired
    private LedenCollectHandwritingMapper ledenCollectHandwritingMapper;

    @Autowired
    private SecurityUtil securityUtil;
    /**
     * 录入笔记业务
     * @param path
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean inputHandWirtingXml(String path) throws XmlParseException,AuthenticationException {
        LedenCollectHandWritingVo ledenCollectHandWritingVo=(LedenCollectHandWritingVo) XmlParseUtil.parseXml(path,LedenCollectHandWritingVo.class);

        //进行头部数据验证
       securityUtil.repairpermissions(ledenCollectHandWritingVo.head, AuthoirtyEnum.HANDWRITING);

        //复制属性
        LedenCollectHandwriting ledenCollectHandwriting=new LedenCollectHandwriting();

        BeanUtils.copyProperties(ledenCollectHandWritingVo.getData(),ledenCollectHandwriting);
        ledenCollectHandwriting.setCreateUserId(ledenCollectHandWritingVo.head.getUSER_CODE());
        ledenCollectHandwriting.setCreateDatetime(new Date());
        //删除原有数据
        ledenCollectHandwritingMapper.deleteHandWritingByPerson(ledenCollectHandwriting.getRyjcxxcjbh());
        ledenCollectHandwriting.setPkId(UUID.randomUUID().toString().replace("-",""));
        return ledenCollectHandwritingMapper.insertSelective(ledenCollectHandwriting)>0?true:false;
    }

    /**
     *根据人员编号获取笔记信息(只获取一条)
     * */
    @Override
    public Response<LedenCollectHandwriting> selectHandWritingById(String id) {
        List<LedenCollectHandwriting> list = ledenCollectHandwritingMapper.selectDataByPersonCode(id);
        LedenCollectHandwriting ledenCollectHandwriting = null;
        if (list != null && list.size() > 0){
            ledenCollectHandwriting = list.get(0);
        }
        if (ledenCollectHandwriting != null){
            ledenCollectHandwriting.setBjzp(new String(ledenCollectHandwriting.getBjsj()));
            ledenCollectHandwriting.setBjsj(null);
        }
        return ResponseUtil.getResponseInfo(ReturnCode.SUCCESS,ledenCollectHandwriting);
    }

    @Override
    public List<LedenCollectHandwriting> listHandWritingByPersonCode(String personCode) {
        return ledenCollectHandwritingMapper.selectDataByPersonCode(personCode);
    }
}
