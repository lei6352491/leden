package com.zhouyi.business.core.service;

import com.zhouyi.business.core.common.ReturnCode;
import com.zhouyi.business.core.dao.LedenCollectPortraitMapper;
import com.zhouyi.business.core.exception.AuthenticationException;
import com.zhouyi.business.core.model.LedenCollectPortrait;
import com.zhouyi.business.core.model.PortraitResult;
import com.zhouyi.business.core.model.Response;
import com.zhouyi.business.core.model.enums.AuthoirtyEnum;
import com.zhouyi.business.core.utils.ResponseUtil;
import com.zhouyi.business.core.utils.SecurityUtil;
import com.zhouyi.business.core.utils.XmlParseUtil;
import com.zhouyi.business.core.vo.LedenCollectPortraitVo;
import com.zhouyi.business.core.vo.xml.LedenCollectPortraitXml;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.misc.BASE64Encoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class LedenCollectPortraitServiceImpl
        extends BaseServiceImpl<LedenCollectPortrait, LedenCollectPortraitVo>
        implements LedenCollectPortraitService {

    @Autowired
    private LedenCollectPortraitMapper ledenCollectPortraitMapper;
    @Autowired
    private SecurityUtil securityUtil;

    private Logger logger= LoggerFactory.getLogger(LedenCollectPortraitServiceImpl.class);

    /**
     * @param
     * @return
     * @author 李秸康
     * @Description
     * @date 2019/7/2
     **/
    @Override
    @Transactional
    public Boolean insertPortraitByXml(String path) throws AuthenticationException {

        //解析xml获取数据对象
        LedenCollectPortraitVo ledenCollectPortraitVo = (LedenCollectPortraitVo) XmlParseUtil.parseXml(path, LedenCollectPortraitVo.class);

        //进行授权验证
        /*if (!securityUtil.repairpermissions(ledenCollectPortraitVo.head, AuthoirtyEnum.PORTRAIT))
            throw new AuthenticationException(ReturnCode.ERROR_1037);*/

        //获取xml数据集合
        List<LedenCollectPortraitXml> xmlDatas = ledenCollectPortraitVo.data;
        XmlParseUtil.userCodeThreadLocal.set(ledenCollectPortraitVo.head.getUSER_CODE());

        //进行数据转换
        //将头部信息填入xml对象
        XmlParseUtil.copyHeader(ledenCollectPortraitVo, xmlDatas);
        List<LedenCollectPortrait> data = new ArrayList<>();
        //将xml数据写入data数据
        for (LedenCollectPortraitXml ledenCollectPortraitXml :
                xmlDatas) {
            LedenCollectPortrait ledenCollectPortrait = new LedenCollectPortrait();
            BeanUtils.copyProperties(ledenCollectPortraitXml, ledenCollectPortrait);
            ledenCollectPortrait.setPkId(UUID.randomUUID().toString().substring(0, 32));
            ledenCollectPortrait.setCreateUserId(XmlParseUtil.userCodeThreadLocal.get().toString());
            data.add(ledenCollectPortrait);
        }
        XmlParseUtil.userCodeThreadLocal.remove();

        logger.info("采集的人像数据数量为："+data.size());
        //删除原来的人像数据
        if(data!=null && data.size()>0){
            ledenCollectPortraitMapper.deletePortraitByPerson(data.get(0).getRyjcxxcjbh());
            ledenCollectPortraitMapper.insertLedenCollectPortraits(data);
        }

        return true;
    }

    /**
     * 根据人员编号查询人像信息
     * */
    @Override
    public Response selectPortraitByPersonCode(String id) {
        List<LedenCollectPortrait> ledenCollectPortraits = ledenCollectPortraitMapper.selectPortraitByPersonCode(id);
        if (ledenCollectPortraits != null && ledenCollectPortraits.size() > 1){
            for (LedenCollectPortrait ledenCollectPortrait : ledenCollectPortraits){
                if (ledenCollectPortrait.getRxtxsj() != null){
                    byte[] rxtxsj = ledenCollectPortrait.getRxtxsj();
                    ledenCollectPortrait.setRxtp(new String(rxtxsj));
                    ledenCollectPortrait.setRxtxsj(null);
                }
            }
            //PortraitResult portraitResult = new PortraitResult();
            //BASE64Encoder base64Encoder = new BASE64Encoder();
            /*int count = 0;
            for (LedenCollectPortrait ledenCollectPortrait : ledenCollectPortraits){
                if (count == 0){
                    BeanUtils.copyProperties(ledenCollectPortrait,portraitResult);
                }
                if (ledenCollectPortrait.getRxtxsj() != null){
                    //String encode = base64Encoder.encode(ledenCollectPortrait.getRxtxsj());
                    //StringBuilder stringBuilder = new StringBuilder();
                    //stringBuilder.append("data:image/png;base64,");
                    //stringBuilder.append(encode);
                    //Map<String, byte[]> txzps = portraitResult.getTxzps();
                    //String rxzplxdm = ledenCollectPortrait.getRxzplxdm();
                    *//*if ("1".equals(rxzplxdm))
                        txzps.put("zhen",ledenCollectPortrait.getRxtxsj());
                    else if ("2".equals(rxzplxdm))
                        txzps.put("zuo",stringBuilder.toString());
                    else
                        txzps.put("you",stringBuilder.toString());*//*
                }
                count ++;
            }*/
            return ResponseUtil.getResponseInfo(ReturnCode.SUCCESS,ledenCollectPortraits);
        }
        return ResponseUtil.returnError(ReturnCode.ERROR_05);
    }

    @Override
    public List<LedenCollectPortrait> listPortraitsByPersonCode(String personCode) {
        return ledenCollectPortraitMapper.selectPortraitByPersonCode(personCode);
    }
}
