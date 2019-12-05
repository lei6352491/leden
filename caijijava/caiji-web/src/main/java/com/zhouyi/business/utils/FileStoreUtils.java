package com.zhouyi.business.utils;

import com.zhouyi.business.core.model.LedenUploadLog;
import com.zhouyi.business.core.model.LedenUploadPacket;
import com.zhouyi.business.core.service.LedenUploadLogService;
import com.zhouyi.business.core.service.LedenUploadPacketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sun.net.www.protocol.ftp.FtpURLConnection;

import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.UUID;

/**
 * @author 杜承旭
 * @ClassNmae: FileStoreUtils
 * @Description: TODO
 * @date 2019/9/20 15:46
 * @Version 1.0
 **/

@Component
public class FileStoreUtils {

    @Autowired
    private LedenUploadLogService ledenUploadLogService;

    @Autowired
    private LedenUploadPacketService ledenUploadPacketService;

    public void automaticSavaData
            (String equipmentCode, String personCode, String nodeCode, String fileType, String path, String status,String dataType, String unitCode){

        LedenUploadPacket ledenUploadPacket = new LedenUploadPacket();
        ledenUploadPacket.setPkId(UUID.randomUUID().toString().replace("-", ""));
        ledenUploadPacket.setNodeSign(nodeCode);
        ledenUploadPacket.setFileLocation(path);
        ledenUploadPacket.setFileSuffix(fileType);
        ledenUploadPacket.setResolveStatus(status);
        ledenUploadPacket.setEquipmentId(equipmentCode);
        ledenUploadPacket.setRyjcxxcjbh(personCode);
        ledenUploadPacket.setCjdwdm(unitCode);
        ledenUploadPacket.setCreateDatetime(new Date());
        ledenUploadPacket.setDataType(dataType);
        try {
            URL url = null;
            url = new URL(ledenUploadPacket.getFileLocation());
            FtpURLConnection ftpURLConnection = new FtpURLConnection(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        //把数据信息保存到数据库
        ledenUploadPacketService.saveData(ledenUploadPacket);
    }

    /**
     * 录入ZIP数据包
     * @param equipmentCode
     * @param personCode
     * @param packetNamePath
     * @param nodeCode
     * @param path
     */
    public void automaticSavaPacket(String equipmentCode,String personCode,String packetNamePath,String nodeCode,String path){
        //补全数据包信息
        LedenUploadPacket ledenUploadPacket = new LedenUploadPacket();
        ledenUploadPacket.setPkId(UUID.randomUUID().toString().replace("-", ""));
        ledenUploadPacket.setNodeSign(nodeCode);
        ledenUploadPacket.setDataType("ZIP");
        ledenUploadPacket.setRyjcxxcjbh(personCode);
        ledenUploadPacket.setEquipmentId(equipmentCode);
        ledenUploadPacket.setFileLocation
                (path+"/bak/" + packetNamePath + ".zip");
        ledenUploadPacket.setFileSuffix("zip");
        ledenUploadPacket.setCreateDatetime(new Date());
        //等所有队列插入成功后改成1
        ledenUploadPacket.setResolveStatus("0");
        URL url = null;
        try {
            url = new URL(ledenUploadPacket.getFileLocation());
            FtpURLConnection ftpURLConnection = new FtpURLConnection(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        //把数据包信息保存到数据库
        ledenUploadPacketService.saveData(ledenUploadPacket);
    }

}
