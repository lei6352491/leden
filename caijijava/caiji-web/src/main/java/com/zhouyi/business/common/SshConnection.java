package com.zhouyi.business.common;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import com.zhouyi.business.core.exception.ExceptionCast;
import com.zhouyi.business.core.utils.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


import java.io.IOException;


/**
 * @author 杜承旭
 * @ClassNmae: SshTest
 * @Description: TODO
 * @date 2019/10/31 9:14
 * @Version 1.0
 **/

@Component
@Slf4j
public class SshConnection {

    @Value("${Script.src.host}")
    private String host;
    @Value("${Script.src.port}")
    private Integer port;
    @Value("${Script.src.user}")
    private String user;
    @Value("${Script.src.password}")
    private String password;
    @Value("${Script.src.cmd}")
    private String cmd;

    public Integer executionScript(String fileName) {

        Integer exitStatus = null;
        Session session = null;
        Connection connection = null;

        try {
            connection = new Connection(host,port);
            connection.connect();
            boolean boo = connection.authenticateWithPassword(user, password);
            if (boo){
                session = connection.openSession();
                String newCmd=cmd+" "+fileName;
                log.info(newCmd);
                session.execCommand(newCmd);


                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                exitStatus = session.getExitStatus();

                for (int i = 0;i < 5; i++){
                    if (exitStatus == null){
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        exitStatus = session.getExitStatus();
                    }else {
                        break;
                    }
                }
            }
            return exitStatus;
        } catch (IOException e) {
            e.printStackTrace();
            ExceptionCast.cast(ResponseUtil.ntrError("ssh连接错误"));
        }finally {
            if (session != null){
                session.close();
            }
            if (connection != null){
                connection.close();
            }
        }
        return null;
    }
}
