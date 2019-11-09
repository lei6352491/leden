package com.zhouyi.business.component;

import com.alibaba.fastjson.JSON;
import com.zhouyi.business.config.ProvinceFtpConfig;
import com.zhouyi.business.core.dao.LedenCollectPersonMapper;
import com.zhouyi.business.core.model.LedenCollectPerson;
import com.zhouyi.business.core.model.provincecomprehensive.pojo.StandardPerson;
import com.zhouyi.business.core.utils.HttpUtil;
import com.zhouyi.business.core.vo.ResponseVo;
import com.zhouyi.business.core.model.provincecomprehensive.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * @Author: first
 * @Date: 上午6:40 2019/11/6
 * @Description: 上报省综组件
 **/
@Component
@Slf4j
public class UploadProvinceComponent {

    @Autowired
    private ProvinceFtpConfig provinceFtpConfig;

    @Value("${provinceComprehensive.interfaces.registry}")
    private String registry;

    @Autowired
    private LedenCollectPersonMapper ledenCollectPersonMapper;


    @Value("${provinceComprehensive.ip}")
    private String provinceIp;
    @Value("${provinceComprehensive.port}")
    private String provincePort;
    @Value("${provinceComprehensive.ftp.username}")
    private String ftpUserName;
    @Value("${provinceComprehensive.ftp.password}")
    private String ftpPassword;
    @Value("${provinceComprehensive.interfaces.dataNumber}")
    private String dataNumberAddress;

    @Value("${upload.ip}")
    private String uploadIP;
    @Value("${upload.mac}")
    private String uploadMac;
    /**
     * 向省综注册
     *
     * @param personCode 用户编码
     * @return 采集点编号
     */
    public String registry(String personCode) throws Exception {
        StandardPerson standardPerson = ledenCollectPersonMapper.getStandardPerson(personCode);

        //如果没有则获取调用省综接口
        ResponseVo response = HttpUtil.sendPostByJson(provinceFtpConfig.getServerAddress() + registry, new HashMap<String, String>(3) {{
            put("unitCode", standardPerson.getCjdwdm());
            put("ip",uploadIP);
            put("mac",uploadMac);
        }});

        if (response.isOk()) {
            //如果为调用成功则获取人员编号
            String data = response.getData();
            ResponseData responseData = JSON.parseObject(data, ResponseData.class);
            if (response.getStatus() == 1) {
                //生成的采集点编号
                String equipmentCode = response.getData();
                log.info("获取的采集点编号为:" + equipmentCode);
                //生成目录
                FTPClient ftpClient = new FTPClient();
                ftpClient.connect(provinceIp, Integer.valueOf(provincePort));
                ftpClient.login(ftpUserName, ftpPassword);
                ftpClient.setControlEncoding("UTF-8");
                ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
                ftpClient.enterLocalPassiveMode();
                ftpClient.makeDirectory(equipmentCode);
                return equipmentCode;
            } else {
                throw new Exception("生成编号出错");
            }

        }
        return null;
    }


    /**
     * 生成采集数据编号
     *
     * @param unitCode 单位代码
     * @return 采集数据编号(相当远人员编号)
     */
    public String generateDataNumber(String personCode,String unitCode) throws Exception {
        StringBuffer urlBuffer = new StringBuffer("http://");
        urlBuffer.append(provinceIp);
        urlBuffer.append(":");
        urlBuffer.append(provincePort);
        urlBuffer.append(dataNumberAddress);
        ResponseVo dataNumberResponse = HttpUtil.sendPost(urlBuffer.toString(), new HashMap<String, String>(1) {{
            put("unitCode", unitCode);
        }});
        if (dataNumberResponse.isOk()) {
            if (dataNumberResponse.getStatus() == 1) {
                String generatedPersonCode=dataNumberResponse.getData();
                //将人员编号存入数据库
                ledenCollectPersonMapper.updatePersonByPersonCode(new HashMap<String,Object>(2){{put("personCode",personCode);put("jzrybh",generatedPersonCode);}});
            }else{
                log.error("生成人员编号失败");
                throw new Exception("生成人员编号失败");
            }
        }
        return null;
    }


    /**
     * 将文件推送到ftp服务器
     */
    public void pushZipToFtp(String dir, String zipFile) throws Exception{
        FTPClient ftpClient = new FTPClient();
            ftpClient.connect(provinceIp, Integer.valueOf(provincePort));
            ftpClient.login(ftpUserName, ftpPassword);
            ftpClient.setControlEncoding("UTF-8");
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            ftpClient.enterLocalPassiveMode();
            ftpClient.changeWorkingDirectory(dir);

            int reply = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftpClient.disconnect();
            }
            String newFileName = new String(zipFile.substring(zipFile.lastIndexOf("/")).getBytes("UTF-8"), "iso-8859-1");

            FileInputStream fileInputStream = new FileInputStream(zipFile);
            //上传文件
            if (!ftpClient.storeFile(newFileName, fileInputStream)) {
                log.info("上传文件失败");
            }
            fileInputStream.close();
            ftpClient.logout();


    }
}
