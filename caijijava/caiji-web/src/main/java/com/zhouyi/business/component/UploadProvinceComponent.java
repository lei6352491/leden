package com.zhouyi.business.component;

import com.alibaba.fastjson.JSON;
import com.zhouyi.business.config.ProvinceFtpConfig;
import com.zhouyi.business.core.dao.LedenCollectDnaMapper;
import com.zhouyi.business.core.dao.LedenCollectPersonMapper;
import com.zhouyi.business.core.dao.LedenUploadLogMapper;
import com.zhouyi.business.core.model.LedenCollectDna;
import com.zhouyi.business.core.utils.HttpUtil;
import com.zhouyi.business.core.vo.ResponseVo;
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
import java.util.Map;

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

    @Autowired
    private LedenCollectDnaMapper ledenCollectDnaMapper;

    @Value("${provinceComprehensive.ip}")
    private String provinceIp;
    @Value("${provinceComprehensive.ftp.port}")
    private String provincePort;
    @Value("${provinceComprehensive.ftp.username}")
    private String ftpUserName;
    @Value("${provinceComprehensive.ftp.password}")
    private String ftpPassword;
    @Value("${provinceComprehensive.interfaces.dataNumber}")
    private String dataNumberAddress;
    @Value("${provinceComprehensive.interfaces.dataUpload}")
    private String dataUpload;
    @Value("${provinceComprehensive.interfaces.getUploadStatus}")
    private String dataUploadStatus;
    @Value("${provinceComprehensive.upload.user}")
    private String user;
    @Value("${provinceComprehensive.upload.password}")
    private String password;
    @Value("${provinceComprehensive.port}")
    private String port;


    @Autowired
    private LedenUploadLogMapper ledenUploadLogMapper;



    /**
     * 生成采集数据编号
     *
     * @param unitCode 单位代码
     * @return 采集数据编号(相当远人员编号)
     */
    public String generateDataNumber(String personCode,String unitCode,String uploadLogPkId) throws Exception {
        log.info("正在获取数据编号");
        StringBuffer urlBuffer = new StringBuffer("http://");
        urlBuffer.append(provinceIp);
        urlBuffer.append(":");
        urlBuffer.append(port);
        urlBuffer.append(dataNumberAddress);
        ResponseVo dataNumberResponse = HttpUtil.sendPostByform(urlBuffer.toString(), new HashMap<String, String>(1) {{
            put("unitCode", unitCode);
        }});
        log.info(dataNumberResponse.toString());
        if (dataNumberResponse.isOk()) {
            log.info("获取人员编号成功");
            Map result=(Map)JSON.parse(dataNumberResponse.getData());
            log.info(result.toString());
            if (result.get("status").equals("1")) {
                String generatedPersonCode=result.get("value").toString();
                Map result2=(Map)JSON.parse(generatedPersonCode);
                //将人员编号存入数据库
                ledenCollectPersonMapper.updatePersonByPersonCode(new HashMap<String,Object>(2){{put("personCode",personCode);put("jzrybh",result2.get("rybh"));}});
                LedenCollectDna ledenCollectDna=new LedenCollectDna();
                ledenCollectDna.setRyjcxxcjbh(personCode);
                ledenCollectDna.setRydnabh(result2.get("dna_n").toString());
                ledenCollectDnaMapper.updateByPrimaryKeySelective(ledenCollectDna);
                //更新是否获取到省综人员编号的状态
                ledenUploadLogMapper.updateIsGetCodeByPersonCode(uploadLogPkId,1);


                return result2.get("rybh").toString();
            }else{
                log.error("生成人员编号失败");
                throw new Exception("生成人员编号失败");
            }
        }else{
            throw new Exception("调用生成数据编号失败");
        }
    }


    /**
     * 将文件推送到ftp服务器
     */
    public void pushZipToFtp(String dir, String zipFile) throws Exception{
        log.info(provinceIp+":"+provincePort);
        log.info("上传的目录为:"+dir);
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
            log.info("ZIP文件为:"+zipFile);
            File file=new File(zipFile);
            String fileName=file.getName();
            String newFileName = new String(fileName.getBytes("UTF-8"), "iso-8859-1");

            FileInputStream fileInputStream = new FileInputStream(zipFile);
            //上传文件
            if (!ftpClient.storeFile(newFileName, fileInputStream)) {
                log.info("上传文件失败");
            }
            fileInputStream.close();
            ftpClient.logout();


    }


    /**
     * 通知省综接口
     * @param rybh
     * @param cjdbh
     * @param dir
     * @return
     */
    public String uploadData(String rybh,String cjdbh,String dir){
        Map<String,String> params=new HashMap<>(5);
        params.put("userName",user);
        params.put("passWord",password);
        params.put("rybh",rybh);
        params.put("cjdNo",cjdbh);
        params.put("dir",dir);
        try {
            StringBuffer targetUrlBuffer=new StringBuffer("http://");
            targetUrlBuffer.append(provinceIp);
            targetUrlBuffer.append(":");
            targetUrlBuffer.append(port);
            targetUrlBuffer.append(dataUpload);
            ResponseVo responseVo = HttpUtil.sendPostByform(targetUrlBuffer.toString(), params);
            if(responseVo.isOk()){
                return responseVo.getData();
            }else{
                log.info("通知省厅失败");
            }
        } catch (IOException e) {
            e.printStackTrace();
            log.info("通知省厅失败");
        }
        return null;
    }


    /**
     * 查询上传状态借口
     * @param rybh 被查询人的人员编号
     * @return 响应的结果实体
     */
    public ResponseVo getDataUploadStatus(String rybh){
        String targetInterface=joinCompleteInterface(dataUploadStatus);
        return null;
    }


    /**
     * 拼接访问接口的字符串
     * @param targetInterface
     * @return
     */
    private String joinCompleteInterface(String targetInterface){
        StringBuffer stringBuffer=new StringBuffer("http://");
        stringBuffer.append(provinceIp);
        stringBuffer.append(":");
        stringBuffer.append(port);
        stringBuffer.append(targetInterface);
        return stringBuffer.toString();
    }
}
