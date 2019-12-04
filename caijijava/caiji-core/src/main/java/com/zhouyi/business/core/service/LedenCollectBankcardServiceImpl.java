package com.zhouyi.business.core.service;

import com.zhouyi.business.core.common.ReturnCode;
import com.zhouyi.business.core.dao.LedenCollectBRecordMapper;
import com.zhouyi.business.core.dao.LedenCollectBankcardMapper;
import com.zhouyi.business.core.model.LedenCollectBRecord;
import com.zhouyi.business.core.model.LedenCollectBankcard;
import com.zhouyi.business.core.model.LedenCollectDrugtest;
import com.zhouyi.business.core.model.Response;
import com.zhouyi.business.core.utils.ResponseUtil;
import com.zhouyi.business.core.vo.LedenCollectBankcardVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class LedenCollectBankcardServiceImpl
        extends BaseServiceImpl<LedenCollectBankcard, LedenCollectBankcardVo>
        implements LedenCollectBankcardService{

    @Autowired
    private LedenCollectBRecordMapper ledenCollectBRecordMapper;

    @Autowired
    private LedenCollectBankcardMapper ledenCollectBankcardMapper;

    @Override
    @Transactional
    public Response<Object> saveMapToRepository(List list, String userUnitCodet){
        if (list == null){
            return ResponseUtil.returnError(ReturnCode.ERROR_14);
        }
        for (Object object : list){
            LedenCollectBankcard ledenCollectBankcard = (LedenCollectBankcard)object;
            //设置银行卡主键主键
            ledenCollectBankcard.setPkid(UUID.randomUUID().toString().replace("-",""));
            //保存银行卡信息
            this.resoveSaveData(ledenCollectBankcard);
            //保存该张银行卡的交易记录信息
            for (Object objectChild : ledenCollectBankcard.getList()){
                LedenCollectBRecord ledenCollectBRecord = (LedenCollectBRecord)objectChild;
                //设置交易记录主键
                ledenCollectBRecord.setYhkxxid(UUID.randomUUID().toString().replace("-",""));
                //保存交易记录信息
                ledenCollectBRecordMapper.insertSelective(ledenCollectBRecord);
            }
        }
        return ResponseUtil.returnError(ReturnCode.SUCCESS);
    }

    //根据人员编号查询银行卡信息
    @Override
    public Response<List<LedenCollectBankcard>> selectBankcardByPersonCode(String id) {
        //查询银行卡信息
        List<LedenCollectBankcard> list = ledenCollectBankcardMapper.selectBankcardByPersonCode(id);
        for (LedenCollectBankcard ledenCollectBankcard : list){
            //查询该银行卡的交易记录信息
            List<LedenCollectBRecord> ledenCollectBRecords = ledenCollectBRecordMapper.selectBRecordByBankcard(ledenCollectBankcard.getYhkWpbzh());
            ledenCollectBankcard.setList(ledenCollectBRecords);
        }
        return ResponseUtil.getResponseInfo(ReturnCode.SUCCESS,list);
    }

    //获取主键
    private String getPrimaryKey(String userUnitCode,Long serialNumber){
        StringBuffer stringBuffer = new StringBuffer();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMdd");
        String dateString = dateFormat.format(new Date());
        stringBuffer.append("YH").append(userUnitCode).append(dateString).append(String.format("%06d", serialNumber));
        return stringBuffer.toString();
    }
}
