package com.zhouyi.business.core.service;

import com.alibaba.fastjson.JSON;
import com.zhouyi.business.core.dao.LedenUploadLogMapper;
import com.zhouyi.business.core.dao.LedenUploadPacketMapper;
import com.zhouyi.business.core.model.*;
import com.zhouyi.business.core.vo.LedenUploadLogVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class LedenUploadLogServiceImpl
        extends BaseServiceImpl<LedenUploadLog, LedenUploadLogVo>
        implements LedenUploadLogService {

    @Autowired
    private LedenUploadLogMapper ledenUploadLogMapper;

    @Autowired
    private LedenUploadPacketMapper ledenUploadPacketMapper;

    @Override
    public Response insertDataUploadNode(List list, Head head, String ryjcxxcjbh) {
        //查询数据中最新插入的数据
        LedenUploadLog ledenUploadLog = ledenUploadLogMapper.selectLedenUploadLogByNewTime(head.getEquipmentCode());
        if (ledenUploadLog == null) {
            ledenUploadLog = new LedenUploadLog();
            ledenUploadLog.setUploadDatetime(new Date());
        }
        Date uploadDatetime = ledenUploadLog.getUploadDatetime();

        //解析最新插入的时间与当前时间的年月日是否相等来生成主键
        String primaryKey = null;
        if (!parseTime(new Date()).equals(parseTime(uploadDatetime))) {
            primaryKey = selectNextPrimaryKey("UP",head.getUserUnitCode(), null);
        } else {
            if (ledenUploadLog.getPkId() == null) {
                primaryKey = selectNextPrimaryKey("UP",head.getUserUnitCode(), null);
            } else {
                int count = Integer.parseInt(ledenUploadLog.getPkId().substring(ledenUploadLog.getPkId().length() - 4));
                primaryKey = selectNextPrimaryKey("UP",head.getUserUnitCode(), count);
            }
        }

        Integer count = 0;
        //拆分主键id
        String startString = primaryKey.substring(0, primaryKey.length() - 4);
        Integer endString = Integer.parseInt(primaryKey.substring(primaryKey.length() - 4));
        for (Object object : list) {
            LedenDataUpload ledenDataUpload = (LedenDataUpload) object;
            String string = JSON.toJSONString(ledenDataUpload);
            Map<String, String> map = JSON.parseObject(string, Map.class);
            //遍历map
            Set<String> strings = map.keySet();
            Date date=new Date();
            for (String key : strings) {
                if ("upload_Packet".equals(key))
                    continue;
                //补全存储的对象信息
                StringBuffer stringBuffer = new StringBuffer();
                String format = String.format("%04d", endString + count);
                ledenUploadLog.setPkId(stringBuffer.append(startString)
                        .append(format).toString());
                ledenUploadLog.setEquipmentId(head.getEquipmentCode());
                ledenUploadLog.setNodeSign(key);
                //1.设备已授权；2.设备未授权
                ledenUploadLog.setIsEmpower("1");
                ledenUploadLog.setRyjcxxcjbh(ryjcxxcjbh);
                ledenUploadLog.setUploadStatus(map.get(key));
                ledenUploadLog.setUploadDatetime(date);
                //添加采集上传文件的节点信息
                ledenUploadLogMapper.insertSelective(ledenUploadLog);

                //初始化数据解析信息到数据库中
                LedenUploadPacket ledenUploadPacket = new LedenUploadPacket();
                //同上传文件主键（测试用）
                ledenUploadPacket.setPkId(stringBuffer.toString().replace("UP","PA"));
                ledenUploadPacket.setNodeSign(key);
                ledenUploadPacket.setFileLocation(map.get("upload_Packet"));
                //0.未解析;1.已解析
                ledenUploadPacket.setResolveStatus("0");
                ledenUploadPacketMapper.insertSelective(ledenUploadPacket);

                count++;
            }
        }
        return null;
    }


    @Override
    public List<LedenUploadLog> listUplaodLogByCondition(Integer... status) {

        Integer[] uploadStatus=status;

        List<LedenUploadLog> array = ledenUploadLogMapper.listUploadLogByConditions(Arrays.asList(uploadStatus));;

        return array;
    }



    @Override
    public void uploadLogStatusByPersonCode(int status, String personCode,String info) {
        ledenUploadLogMapper.updateUploadLogByPersonCode(personCode,status,info);
    }


    @Override
    public void updateDataStatus() {

    }

    /**
     * 生成上传服务主键
     */
    private String selectNextPrimaryKey(String startStr,String unitCode, Integer count) {
        StringBuffer stringBuffer = new StringBuffer();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMdd");
        if (count == null) {
            stringBuffer.append(startStr).append(unitCode).append(dateFormat.format(new Date()))
                    .append(String.format("%04d", 1));
        } else {
            stringBuffer.append(startStr).append(unitCode).append(dateFormat.format(new Date()))
                    .append(String.format("%04d", count + 1));
        }
        return stringBuffer.toString();
    }

    /**
     * 解析时间格式
     */
    private String parseTime(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMdd");
        return dateFormat.format(date);
    }
}
