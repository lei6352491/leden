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
            (String equipmentCode, String personCode, String nodeCode, String fileType, String path, String status,String isEmpower){

        LedenUploadLog ledenUploadLog = new LedenUploadLog();
        ledenUploadLog.setPkId(UUID.randomUUID().toString().replace("-", ""));
        ledenUploadLog.setEquipmentId(equipmentCode);
        ledenUploadLog.setNodeSign(nodeCode);
        ledenUploadLog.setIsEmpower(isEmpower);
        ledenUploadLog.setRyjcxxcjbh(personCode);
        ledenUploadLog.setUploadStatus("0");
        ledenUploadLog.setUploadDatetime(new Date());
        //把日志信息添加到数据库
        ledenUploadLogService.saveData(ledenUploadLog);

        LedenUploadPacket ledenUploadPacket = new LedenUploadPacket();
        ledenUploadPacket.setPkId(UUID.randomUUID().toString().replace("-", ""));
        ledenUploadPacket.setUploadLogId(ledenUploadLog.getPkId());
        ledenUploadPacket.setNodeSign(nodeCode);
        ledenUploadPacket.setFileLocation(path);
        ledenUploadPacket.setFileSuffix(fileType);
        ledenUploadPacket.setResolveStatus(status);
        try {
            URL url = null;
            url = new URL(ledenUploadPacket.getFileLocation());
            FtpURLConnection ftpURLConnection = new FtpURLConnection(url);
            ledenUploadPacket.setFileSize(BigDecimal.valueOf(ftpURLConnection.getContentLengthLong()));
        } catch (MalformedURLException e) {
            e.printStackTrace();
            ledenUploadPacket.setFileSize(BigDecimal.valueOf(0));
        }
        //把数据信息保存到数据库
        ledenUploadPacketService.saveData(ledenUploadPacket);
    }

    public void automaticSavaPacket(String equipmentCode,String personCode,String packetNamePath,String nodeCode,String status,String path){
        //补全数据包信息
        LedenUploadLog ledenUploadLog = new LedenUploadLog();
        ledenUploadLog.setPkId(UUID.randomUUID().toString().replace("-", ""));
        ledenUploadLog.setEquipmentId(equipmentCode);
        ledenUploadLog.setNodeSign(nodeCode);
        ledenUploadLog.setIsEmpower("1");
        ledenUploadLog.setRyjcxxcjbh(personCode);
        ledenUploadLog.setUploadStatus("0");
        ledenUploadLog.setUploadDatetime(new Date());
        //把日志信息添加到数据库
        ledenUploadLogService.saveData(ledenUploadLog);

        LedenUploadPacket ledenUploadPacket = new LedenUploadPacket();
        ledenUploadPacket.setPkId(UUID.randomUUID().toString().replace("-", ""));
        ledenUploadPacket.setUploadLogId(ledenUploadLog.getPkId());
        ledenUploadPacket.setNodeSign(nodeCode);
        ledenUploadPacket.setFileLocation
                (path+"/bak/" + packetNamePath + ".zip");
        ledenUploadPacket.setFileSuffix("zip");
        URL url = null;
        try {
            url = new URL(ledenUploadPacket.getFileLocation());
            FtpURLConnection ftpURLConnection = new FtpURLConnection(url);
            ledenUploadPacket.setFileSize(BigDecimal.valueOf(ftpURLConnection.getContentLengthLong()));
        } catch (MalformedURLException e) {
            e.printStackTrace();
            ledenUploadPacket.setFileSize(BigDecimal.valueOf(0));
        }
        ledenUploadPacket.setResolveStatus(status);
        //把数据包信息保存到数据库
        ledenUploadPacketService.saveData(ledenUploadPacket);
    }

}
