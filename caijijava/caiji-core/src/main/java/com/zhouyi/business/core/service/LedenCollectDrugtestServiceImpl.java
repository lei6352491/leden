package com.zhouyi.business.core.service;

import com.zhouyi.business.core.common.ReturnCode;
import com.zhouyi.business.core.dao.LedenCollectDrugtestMapper;
import com.zhouyi.business.core.model.LedenCollectDrugtest;
import com.zhouyi.business.core.model.Response;
import com.zhouyi.business.core.utils.ResponseUtil;
import com.zhouyi.business.core.vo.LedenCollectDrugtestVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Service
public class LedenCollectDrugtestServiceImpl
        extends BaseServiceImpl<LedenCollectDrugtest, LedenCollectDrugtestVo>
        implements LedenCollectDrugtestService{

   @Autowired
   private LedenCollectDrugtestMapper ledenCollectDrugtestMapper;

    @Override
    @Transactional
    public Response<Object> saveMapToRepository(List list,String userUnitCode){
        if (list == null){
            return ResponseUtil.returnError(ReturnCode.ERROR_14);
        }
        for (Object object : list){
            LedenCollectDrugtest ledenCollectDrugtest = (LedenCollectDrugtest)object;
            ledenCollectDrugtest.setPkId(UUID.randomUUID().toString().replace("-",""));
            //保存之前删除原有信息
            ledenCollectDrugtestMapper.deleteDrugtestByPersonId(ledenCollectDrugtest.getRyjcxxcjbh());
            this.resoveSaveData(ledenCollectDrugtest);
        }
       return ResponseUtil.getResponseInfo(true);
    }

    //获取主键
    /*private String getPrimaryKey(String userUnitCode,Long serialNumber){
        StringBuffer stringBuffer = new StringBuffer();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMdd");
        String dateString = dateFormat.format(new Date());
        stringBuffer.append("XD").append(userUnitCode).append(dateString).append(String.format("%06d", serialNumber));
        return stringBuffer.toString();
    }*/

}
