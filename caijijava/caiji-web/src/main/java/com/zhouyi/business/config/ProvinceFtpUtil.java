package com.zhouyi.business.config;

import com.zhouyi.business.core.common.ReturnCode;
import com.zhouyi.business.core.exception.BusinessException;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @Author: first
 * @Date: 上午9:56 2019/10/31
 * @Description: 省ftp工具
**/
@Component
public class ProvinceFtpUtil {

    @Autowired
    private ProvinceFtpConfig provinceFtpConfig;

    FTPClient ftpClient=null;

    private static final Logger logger= LoggerFactory.getLogger(ProvinceFtpUtil.class);

    public boolean connect() throws IOException {
            ftpClient=new FTPClient();
            int reply;
            ftpClient.connect(provinceFtpConfig.getIp());
            reply=ftpClient.getReplyCode();

            if(!FTPReply.isPositiveCompletion(reply)){
                ftpClient.disconnect();
                logger.info("省FTP服务器拒绝");
                return false;
            }else{
                ftpClient.login(provinceFtpConfig.getUsername(),provinceFtpConfig.getPassword());
                ftpClient.enterLocalActiveMode();
                ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
                ftpClient.changeWorkingDirectory(provinceFtpConfig.getTargetDir());
                logger.info("省ftp服务器连接成功");
                return true;
            }

    }


    /**
     * 创建文件夹
     * @param folderName
     */
    public void createDir(String folderName) throws IOException {
        ftpClient.makeDirectory(folderName);
    }


    /**
     * 断开连接
     * @throws IOException
     */
    public void disconnect() throws IOException {
        ftpClient.disconnect();
    }


}
