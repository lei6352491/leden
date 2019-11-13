package com.zhouyi.business.runnable;

import com.alibaba.fastjson.JSON;
import com.zhouyi.business.component.DataReportComponent;
import com.zhouyi.business.component.UploadProvinceComponent;
import com.zhouyi.business.core.model.LedenEquipment;
import com.zhouyi.business.core.model.LedenUploadLog;
import com.zhouyi.business.core.model.provincecomprehensive.utils.MIS;
import com.zhouyi.business.core.service.*;
import com.zhouyi.business.core.model.provincecomprehensive.utils.ProvinceZipUtils;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.List;
import java.util.Map;

/**
 * @Author: first
 * @Date: 上午6:19 2019/11/6
 * @Description: 数据上报线程
 **/
@Data
@Slf4j
@NoArgsConstructor
@Configuration
@Scope("prototype")
public class UploadRunnable implements Runnable {

    @Autowired
    private LedenUploadLogService ledenUploadLogService;
    @Autowired
    private LedenCollectPersonService ledenCollectPersonService;
    @Autowired
    private DataReportComponent dataReportComponent;
    @Autowired
    private UploadProvinceComponent uploadProvinceComponent;
    @Value("${provinceComprehensive.generate.dir}")
    private String classPath;
    @Autowired
    private LedenEquipmentService ledenEquipmentService;


    @Override
    public void run() {
        String personCode = null;
        String equipmentCode = null;
        Integer isGetLog=0;
        String uploadLogPkId=null;
        //获取要解析的数据包集合
        List<LedenUploadLog> waitingUploadLogs = ledenUploadLogService.listUplaodLogByCondition(DataReportComponent.UPLOAD_STATUS.NO_UPLOAD.getValue(),
                DataReportComponent.UPLOAD_STATUS.UPLOAD_LOSE.getValue());
        log.info("获取到" + waitingUploadLogs.size() + "条待上传的数据");
        //开解解析
        if (waitingUploadLogs != null && waitingUploadLogs.size() > 0) {
            LedenUploadLog ledenUploadLog = waitingUploadLogs.get(0);
            waitingUploadLogs.clear();

            LedenEquipment ledenEquipment = ledenEquipmentService.getEquipmentByEquipmentCode(ledenUploadLog.getEquipmentId());
            //查询出设备id
            personCode = ledenUploadLog.getRyjcxxcjbh();
            isGetLog=ledenUploadLog.getIsGetCode();
            equipmentCode = ledenEquipment.getProvincialEquipmentCode();
            uploadLogPkId=ledenUploadLog.getPkId();
            ledenEquipment = null;
        }
        if (personCode == null || equipmentCode == null) {
            log.error("没有带解析的数据");
            return;
        }

        try {
            log.info("正在打包压缩" + personCode + "的数据");
            //1.更新上传状态
            ledenUploadLogService.uploadLogStatusByPersonCode(DataReportComponent.UPLOAD_STATUS.PACKING.getValue(), personCode, DataReportComponent.UPLOAD_STATUS.PACKING.getName());
            log.info("封装数据ing");
            //3.封装数据
            MIS mis = dataReportComponent.getMappedData(personCode, equipmentCode,isGetLog,uploadLogPkId);
            //生成xml
            ProvinceZipUtils.generatorXml(classPath, mis);
            log.info("生成ZIP");
            //4.生成ZIP
            String fileLocation = ProvinceZipUtils.generateZip2(classPath, mis);
            log.info("上传中-========");
            //5.上传
//            uploadProvinceComponent.pushZipToFtp(equipmentCode, fileLocation);
            //6.修改状态
            ledenUploadLogService.uploadLogStatusByPersonCode(DataReportComponent.UPLOAD_STATUS.UPLOADING.getValue(), personCode, "正在上传");
            //7.通知省厅
//            String s = uploadProvinceComponent.uploadData(mis.getPersonInfo().getPersonId(),
//                    equipmentCode, new StringBuffer("/").append(equipmentCode).append("/").append(mis.getPersonInfo().getPersonId()).append(".zip").toString());
//            if (s != null) {
//                Map result = (Map) JSON.parse(s);
//                String key = result.get("status").toString();
//                switch (key) {
//                    case "1":
//                        ledenUploadLogService.uploadLogStatusByPersonCode(DataReportComponent.UPLOAD_STATUS.UPLOADED.getValue(), personCode, "已上传");
//                        break;
//                    case "0":
//                        ledenUploadLogService.uploadLogStatusByPersonCode(DataReportComponent.UPLOAD_STATUS.WAITING_RESOLVE.getValue(), personCode, "正在等待解析");
//                        break;
//                    default:
//                        ledenUploadLogService.uploadLogStatusByPersonCode(DataReportComponent.UPLOAD_STATUS.UPLOAD_LOSE.getValue(), personCode, result.get("value").toString());
//                        break;
//
//                }
//            }else{
//                ledenUploadLogService.uploadLogStatusByPersonCode(DataReportComponent.UPLOAD_STATUS.UPLOAD_LOSE.getValue(),personCode,"通知省厅失败");
//            }

        } catch (Exception e) {

            e.printStackTrace();
            //如果报错则记录报错信息到数据库
            ledenUploadLogService.uploadLogStatusByPersonCode(2, personCode, "上传失败");
        }


//        //根据人员编号查询出人
//        System.out.println("data uplaod task:"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    }


    /**
     * 获取classpath
     *
     * @return
     */
    public String getClasspath() {
        return System.getProperty("user.dir");

    }


}
