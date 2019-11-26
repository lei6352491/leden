package com.zhouyi.business.core.service;

import com.zhouyi.business.core.common.ReturnCode;
import com.zhouyi.business.core.dao.LedenCollectLooksMapper;
import com.zhouyi.business.core.dao.LedenCollectSgtzzcMapper;
import com.zhouyi.business.core.dao.LedenCollectSignMapper;
import com.zhouyi.business.core.exception.AuthenticationException;
import com.zhouyi.business.core.exception.XmlParseException;
import com.zhouyi.business.core.model.LedenCollectLooks;
import com.zhouyi.business.core.model.LedenCollectSgtzzc;
import com.zhouyi.business.core.model.LedenCollectSign;
import com.zhouyi.business.core.model.enums.AuthoirtyEnum;
import com.zhouyi.business.core.utils.SecurityUtil;
import com.zhouyi.business.core.utils.XmlParseUtil;
import com.zhouyi.business.core.vo.LedenCollectSLSVo;
import com.zhouyi.business.core.vo.xml.LedenCollectSignXml;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.security.Security;
import java.util.*;

/**
 * @author 李秸康
 * @ClassNmae: LedenCollectSLSServiceImpl
 * @Description: TODO
 * @date 2019/7/3 16:11
 * @Version 1.0
 **/
@Service
public class LedenCollectSLSServiceImpl implements LedenCollectSLSService {
    @Autowired
    private LedenCollectSgtzzcMapper ledenCollectSgtzzcMapper;
    @Autowired
    private LedenCollectSignMapper ledenCollectSignMapper;
    @Autowired
    private LedenCollectLooksMapper ledenCollectLooksMapper;
    @Autowired
    private SecurityUtil securityUtil;


    /**
     * 录入人员的体貌/特征/足长等信息
     *
     * @param path
     * @return
     * @throws RuntimeException
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean insertSignalement(String path) throws AuthenticationException, XmlParseException {


        //获取解析的数据
        LedenCollectSLSVo ledenCollectSLSVo = (LedenCollectSLSVo) XmlParseUtil.parseXml(path, LedenCollectSLSVo.class);
        //进行头部校验
        boolean flag = securityUtil.repairpermissions(ledenCollectSLSVo.head, AuthoirtyEnum.SIGNALEMENT);
        if (!flag) {
            throw new AuthenticationException(ReturnCode.ERROR_1037);
        }


        //创建身高/足长/体重对象
        LedenCollectSgtzzc ledenCollectSgtzzc = new LedenCollectSgtzzc();
        ledenCollectSgtzzc.setPkId(UUID.randomUUID().toString().replace("-", ""));
        //创建体貌特征对象
        LedenCollectLooks ledenCollectLooks = new LedenCollectLooks();
        ledenCollectLooks.setPkId(UUID.randomUUID().toString().replace("-", ""));
        //创建特殊体征对象
        List<LedenCollectSign> ledenCollectSigns = new ArrayList<>();


        List targets = Arrays.asList(ledenCollectSgtzzc, ledenCollectLooks);
        //复制头部信息
        XmlParseUtil.copyHeader(ledenCollectSLSVo, targets);
        //将vo对象属性信息分解为三个对象
        XmlParseUtil.copyProperties(ledenCollectSLSVo.data, targets);
        //特殊体征信息集合
        List<LedenCollectSignXml> dataSon = ledenCollectSLSVo.getData().getDataSon();
        //复制头部信息
        XmlParseUtil.copyHeader(ledenCollectSLSVo, dataSon);
        //复制基础信息
        for (int i = 0; i < dataSon.size(); i++) {
            LedenCollectSign ledenCollectSign = new LedenCollectSign();
            BeanUtils.copyProperties(dataSon.get(i), ledenCollectSign);
            ledenCollectSign.setPkId(UUID.randomUUID().toString().replace("-", ""));
            ledenCollectSigns.add(ledenCollectSign);
        }

        //进行持久化操作
        //清除原有数据
        ledenCollectLooksMapper.deleteLooksById(ledenCollectLooks.getRyjcxxcjbh());

        ledenCollectLooksMapper.insertSelective(ledenCollectLooks);

        ledenCollectSgtzzcMapper.deleteSgtzzcByPersonId(ledenCollectSgtzzc.getRyjcxxcjbh());
        ledenCollectSgtzzcMapper.insertSelective(ledenCollectSgtzzc);

        if (ledenCollectSigns != null && ledenCollectSigns.size() != 0) {
            for (LedenCollectSign ledenCollectSign : ledenCollectSigns) {
                if (ledenCollectSign.getRyjcxxcjbh() == null) {
                    ledenCollectSign.setRyjcxxcjbh(ledenCollectSLSVo.data.ryjcxxcjbh);
                    ledenCollectSign.setPkId(UUID.randomUUID().toString().replace("-", ""));
                }
            }
            //删除原有数据
            ledenCollectSignMapper.deleteSignByPersonId(ledenCollectSigns.get(0).getRyjcxxcjbh());
            ledenCollectSignMapper.insertSigns(ledenCollectSigns);
        }
        return true;
    }
}
