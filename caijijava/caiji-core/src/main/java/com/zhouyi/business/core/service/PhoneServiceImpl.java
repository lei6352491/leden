package com.zhouyi.business.core.service;

import com.zhouyi.business.core.dao.LedenCollectPAddressbookMapper;
import com.zhouyi.business.core.dao.LedenCollectPCallrecordsMapper;
import com.zhouyi.business.core.dao.LedenCollectPTerminalMapper;
import com.zhouyi.business.core.dao.LedenCollectPTotalMapper;
import com.zhouyi.business.core.utils.PhoneParseXmlUtil;
import com.zhouyi.business.core.vo.xml.PhonePackXml;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author 李秸康
 * @ClassNmae: PhoneServiceImpl
 * @Description: TODO
 * @date 2019/7/12 9:28
 * @Version 1.0
 **/
@Service
public class PhoneServiceImpl implements PhoneService {

    @Autowired
    private LedenCollectPTerminalMapper ledenCollectPTerminalMapper;
    @Autowired
    private LedenCollectPAddressbookMapper ledenCollectPAddressbookMapper;
    @Autowired
    private LedenCollectPTotalMapper ledenCollectPTotalMapper;
    @Autowired
    private LedenCollectPCallrecordsMapper ledenCollectPCallrecordsMapper;

    private Logger logger= LoggerFactory.getLogger(PhoneServiceImpl.class);
    /**
     * 把解析出来的数据进行解析入库
     * @param path
     * @return
     * @throws RuntimeException 事务控制，抛出异常
     */
    @Override
    @Transactional
    public boolean parseXml(String path) throws RuntimeException{

        PhonePackXml phonePackXml= PhoneParseXmlUtil.parsePhoneDataPack(path); //获取解析数据入库


        logger.info("采集的总数信息为:"+phonePackXml.getLedenCollectPTotals().size());
        //1.首先新增total信息
        if(phonePackXml.getLedenCollectPTotals()!=null&&phonePackXml.getLedenCollectPTotals().size()>0){
            ledenCollectPTotalMapper.deletePtotalByPersonId(phonePackXml.getLedenCollectPTotals().get(0).getPersonid());
            ledenCollectPTotalMapper.insertTotals(phonePackXml.getLedenCollectPTotals());
        }


        logger.info("采集终端信息个数为："+phonePackXml.getLedenCollectPTerminal().size());
        if(phonePackXml.getLedenCollectPTotals()!=null&&phonePackXml.getLedenCollectPTotals().size()>0){
            //2.新增终端信息
            ledenCollectPTotalMapper.deletePtotalByPersonId(phonePackXml.getLedenCollectPTerminal().get(0).getCjmbbh());
            ledenCollectPTerminalMapper.insert(phonePackXml.getLedenCollectPTerminal().get(0));
        }

        logger.info("采集的通讯录的个数为"+phonePackXml.getLedenCollectPAddressbook().size());
        if(phonePackXml.getLedenCollectPAddressbook()!=null&&phonePackXml.getLedenCollectPAddressbook().size()>0){
            //3.新增通讯录信息
            ledenCollectPAddressbookMapper.deleteAddressByTargetId(phonePackXml.getLedenCollectPAddressbook().get(0).getCjmbbh());
            ledenCollectPAddressbookMapper.insertAddressBooks(phonePackXml.getLedenCollectPAddressbook());
        }
        if(phonePackXml.getLedenCollectPCallrecords()!=null&&phonePackXml.getLedenCollectPCallrecords().size()>0){
            //4.新增通话记录信息
            ledenCollectPCallrecordsMapper.deleteCallRecordsByTargetId(phonePackXml.getLedenCollectPCallrecords().get(0).getCjmbbh());
            ledenCollectPCallrecordsMapper.insertCallRecords(phonePackXml.getLedenCollectPCallrecords());
        }

        return true;
    }
}
