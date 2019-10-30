package com.zhouyi.business.core.service;

import com.zhouyi.business.core.common.ReturnCode;
import com.zhouyi.business.core.dao.LedenPersonIndexMapper;
import com.zhouyi.business.core.exception.ExceptionCast;
import com.zhouyi.business.core.model.Head;
import com.zhouyi.business.core.model.LedenPersonIndex;
import com.zhouyi.business.core.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Service
public class LedenPersonIndexServiceImpl implements LedenPersonIndexService {

    @Autowired
    private LedenPersonIndexMapper ledenPersonIndexMapper;

    /**
     * 生成自增主键
     * */
    @Override
    public synchronized String selectNextPrimaryKey(String unitCode){
        //获取数据库中该设备下的最新的那条记录
        LedenPersonIndex ledenPersonIndex = ledenPersonIndexMapper.selectNextPrimaryKey(unitCode);
        //比较该记录的月份是否是当前时间的月份
        StringBuffer stringBuffer = new StringBuffer();
        //当数据库中存在该单位的数据时
        if (ledenPersonIndex != null){
            if (parseDate(new Date()).equals(parseDate(ledenPersonIndex.getCreateDatetime()))){
                stringBuffer.append("R").append(ledenPersonIndex.getUnitCode())
                        .append(parseDate(ledenPersonIndex.getCreateDatetime()))
                        .append(String.format("%04d", ledenPersonIndex.getCount() + 1));
                //重新设置数据库信息
                ledenPersonIndex.setCount((short)(ledenPersonIndex.getCount() + 1));
                ledenPersonIndex.setCreateDatetime(new Date());
            }else {
                stringBuffer.append("R").append(ledenPersonIndex.getUnitCode())
                        .append(parseDate(new Date()))
                        .append(String.format("%04d", 1));
                //重新设置数据库信息
                ledenPersonIndex.setCount((short) 1);
                ledenPersonIndex.setCreateDatetime(new Date());
            }
            ledenPersonIndex.setPkId(UUID.randomUUID().toString().substring(0,32));
            //把重新设置的信息存入数据库中
            ledenPersonIndexMapper.insertSelective(ledenPersonIndex);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                ExceptionCast.cast(ResponseUtil.returnError(ReturnCode.ERROR_16));
            }
            return stringBuffer.toString();
        }
        //当数据库中不存在该单位的数据时
        else {
            stringBuffer.append("R").append(unitCode)
                    .append(parseDate(new Date()))
                    .append(String.format("%04d", 1));
            ledenPersonIndex = new LedenPersonIndex();
            ledenPersonIndex.setPkId(UUID.randomUUID().toString().substring(0,32));
            ledenPersonIndex.setCreateDatetime(new Date());
            ledenPersonIndex.setUnitCode(unitCode);
            ledenPersonIndex.setCount((short) 1);
            ledenPersonIndexMapper.insertSelective(ledenPersonIndex);
            return stringBuffer.toString();
        }
    }

    /**
     * 转换时间格式：yyyyMM
     * */
    private String parseDate(Date date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMM");
        return dateFormat.format(date);
    }
}
