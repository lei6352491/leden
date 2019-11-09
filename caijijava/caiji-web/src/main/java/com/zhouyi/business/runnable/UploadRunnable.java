package com.zhouyi.business.runnable;

import com.zhouyi.business.component.DataReportComponent;
import com.zhouyi.business.component.UploadProvinceComponent;
import com.zhouyi.business.core.service.*;
import com.zhouyi.business.core.model.provincecomprehensive.utils.ProvinceZipUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * @Author: first
 * @Date: 上午6:19 2019/11/6
 * @Description: 数据上报线程
**/
@Data
@Slf4j
@NoArgsConstructor
@Configuration
public class UploadRunnable implements Runnable{

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

    private String personCode;
    private String equipmentCode;

    public UploadRunnable(String personCode,String equipmentCode) {
        this.personCode = personCode;
        this.equipmentCode=equipmentCode;
    }

    @Override
    public void run() {
        try {
            log.info("正在打包压缩"+personCode+"的数据");
            //1.更新上传状态
            ledenUploadLogService.uploadLogStatusByPersonCode(DataReportComponent.UPLOAD_STATUS.PACKING.getValue(),personCode, DataReportComponent.UPLOAD_STATUS.PACKING.getName());
//            log.info("正在从数据库读取数据");
//            //2.从数据库里读取数据
//            ledenCollectPersonService.getLedenCollectPersonByConditions(new HashMap<String,Object>(1){{put("ryjcxxcjbh",personCode);}});
            log.info("封装数据ing");
            //3.封装数据
            DataReportComponent.DataInfoMis dataInfoMis=dataReportComponent.getMappedData(personCode,equipmentCode);
            log.info("生成ZIP");
            //4.生成ZIP
            String fileLocation = ProvinceZipUtils.generatorZip(classPath, dataInfoMis.getMis(), dataInfoMis.getDataInfos());
            log.info("上传中-========");
            //5.上传
            uploadProvinceComponent.pushZipToFtp(equipmentCode,fileLocation);
            //6.修改状态
            ledenUploadLogService.uploadLogStatusByPersonCode(DataReportComponent.UPLOAD_STATUS.UPLOADED.getValue(),personCode,"上传成功");
        } catch (Exception e) {

            e.printStackTrace();
            //如果报错则记录报错信息到数据库
            ledenUploadLogService.uploadLogStatusByPersonCode(2,personCode,e.getMessage());
        }




//        //根据人员编号查询出人
//        System.out.println("data uplaod task:"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    }


    /**
     * 获取classpath
     * @return
     */
    public String getClasspath(){
        return System.getProperty("user.dir");

    }


}
