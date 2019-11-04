package com.zhouyi.business.core.service;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.management.modelmbean.XMLParseException;

import com.zhouyi.business.core.common.ReturnCode;
import com.zhouyi.business.core.config.FtpConfig;
import com.zhouyi.business.core.dao.*;
import com.zhouyi.business.core.model.*;
import com.zhouyi.business.core.utils.MathUtil;
import com.zhouyi.business.core.utils.ResponseUtil;
import javafx.beans.property.adapter.ReadOnlyJavaBeanBooleanProperty;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.ibatis.javassist.bytecode.ByteArray;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.zhouyi.business.core.utils.FingerXmlParse;
import com.zhouyi.business.core.vo.LedenCollectFingerVo;
import com.zhouyi.business.core.vo.xml.FingerXml;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import sun.misc.BASE64Encoder;


@Service
public class LedenCollectFingerServiceImpl
        extends BaseServiceImpl<LedenCollectFinger, LedenCollectFingerVo>
        implements LedenCollectFingerService {

    @Value("${finger.dir}")
    private String fingerDir;
    @Autowired
    private LedenCollectFingerMapper ledenCollectFingerMapper;
    @Autowired
    private FtpConfig ftpConfig;
    @Autowired
    private LedenCollectFourfingerMapper ledenCollectFourfingerMapper;
    @Autowired
    private LedenCollectPalmMapper ledenCollectPalmMapper;
    @Autowired
    private LedenCollectFullpalmMapper ledenCollectFullpalmMapper;
    @Autowired
    private LedenCollectPhalangeMapper ledenCollectPhalangeMapper;
    /**
     * @param
     * @return
     * @author 李秸康
     * @Description 录入xml中的指纹数据
     * @date 2019/7/6
     **/
    @Override
    @Transactional
    public Boolean inputFingersByXml(String path) throws XMLParseException {
        FingerAndPalm fingerAndPalm = FingerXmlParse.parseFptx(path);
        //指纹数据
        List<? extends LedenCollectFinger> fingers = fingerAndPalm.getFingers();
        if(fingers!=null&&fingers.size()>0){
            //删除原有的指纹数据
            String ryjcxxcjbh = fingers.get(0).getRyjcxxcjbh();
            ledenCollectFingerMapper.deleteByPersonCode(ryjcxxcjbh);
            fingers.forEach(x->x.setPkId(UUID.randomUUID().toString().replace("-","")));
            ledenCollectFingerMapper.insertFingers(fingers);
        }

        //四指数据
        List<LedenCollectFourfinger> fourfingers = fingerAndPalm.getFourfingers();
        if(fourfingers!=null&& fourfingers.size()>0){
            //删除数据
            if (fourfingers.get(0).getRyjcxxcjbh() == null){
                ledenCollectFourfingerMapper.deleteFourFingerByPersonId(fingers.get(0).getRyjcxxcjbh());
            }else {
                ledenCollectFourfingerMapper.deleteFourFingerByPersonId(fourfingers.get(0).getRyjcxxcjbh());
            }
            fourfingers.forEach(x-> {
                x.setPkId(MathUtil.generateUUID());
                if (x.getRyjcxxcjbh() == null){
                    x.setRyjcxxcjbh(fingers.get(0).getRyjcxxcjbh());
                }
            });
            ledenCollectFourfingerMapper.insertBatch(fourfingers);
        }

        //掌纹数据
        List<LedenCollectPalm> palms = fingerAndPalm.getPalms();
        if(palms!=null&&palms.size()>0){
            ledenCollectPalmMapper.deletePalmByPersonId(palms.get(0).getRyjcxxcjbh());
            palms.forEach(x->x.setPkId(MathUtil.generateUUID()));
            ledenCollectPalmMapper.insertBatch(palms);
        }


        //全掌纹
        List<LedenCollectFullpalm> fullpalms = fingerAndPalm.getFullpalms();
        if(fullpalms!=null&&fullpalms.size()>0){
            ledenCollectFullpalmMapper.deleteFullPalmByPersonId(fullpalms.get(0).getRyjcxxcjbh());
            fullpalms.forEach(x->x.setPkId(MathUtil.generateUUID()));
            ledenCollectFullpalmMapper.insertBatch(fullpalms);
        }


        //指节纹
        List<LedenCollectPhalange> phalanges = fingerAndPalm.getPhalanges();
        if(phalanges!=null&&phalanges.size()>0){
            //删除原有的指节纹数据
            ledenCollectPhalangeMapper.deletePhalangeByPersonId(phalanges.get(0).getRyjcxxcjbh());
             phalanges.forEach(x->x.setPkId(MathUtil.generateUUID()));
             ledenCollectPhalangeMapper.insertBatch(phalanges);
        }

        return true;
    }

    @Override
    public Response selectFingerByPersonCode(String id) {
        List<LedenCollectFinger> list = ledenCollectFingerMapper.selectFingerByPersonCode(id);
        if (list == null || list.size() < 1) {
            return ResponseUtil.returnError(ReturnCode.ERROR_05);
        }
        //BASE64Encoder base64Encoder = new BASE64Encoder();
        //加密图片信息
        list.stream().forEach(s -> {
            //0是指纹采集为正常
            /*if ("0".equals(s.getZzhwqsqkdm())) {
                *//*String encode = base64Encoder.encode(s.getZwTxsj());
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("data:image/png;base64,");
                stringBuilder.append(encode);
                s.setZwzp(stringBuilder.toString());*//*
                s.setZwzp(new String(s.getZwTxsj()));
                s.setZwTxsj(null);
            }*/
            if (s.getZwTxsj() != null){
                s.setZwzp(new String(s.getZwTxsj()));
                s.setZwTxsj(null);
            }
            //添加状态码(供前端归类)
            if (s.getZwzwdm() != null) {
                s.setTypeCode(Integer.parseInt(s.getZwzwdm()));
            }
        });
        return ResponseUtil.getResponseInfo(ReturnCode.SUCCESS, list);
    }

    /**
     * 生成xml
     *
     * @return
     */
    @Override
    public Boolean generateXml(String cjrybh) throws IOException {
        StringBuffer stringBuffer = new StringBuffer(cjrybh);
        stringBuffer.append(".xml");
        //生成文件夹
        File fingerDirFile = new File(fingerDir);
        if (!fingerDirFile.exists()) {
            fingerDirFile.mkdirs();
        }
        //生成文件
        File file = new File(fingerDir + File.separator + stringBuffer.toString());
        //设置生成xml
        OutputFormat format = OutputFormat.createCompactFormat();
        //设置编码
        format.setEncoding("UTF-8");
        XMLWriter xmlWriter = new XMLWriter(new FileOutputStream(file), format);
        xmlWriter.setEscapeText(false); //关闭字符串中xml特殊字符转义


        //查询出该人员的所有信息
        List<LedenCollectFinger> ledenCollectFingers = ledenCollectFingerMapper.selectFingerByPersonCode(cjrybh);
        xmlWriter.write(createDocument(ledenCollectFingers));


        //将文件推送到ftp服务器
        FTPClient ftpClient = new FTPClient();
        ftpClient.connect(ftpConfig.getIp(), ftpConfig.getPort());
        ftpClient.login(ftpConfig.getUser(), ftpConfig.getPassword());
        ftpClient.setControlEncoding("UTF-8");
        ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
        ftpClient.enterLocalPassiveMode();
        ftpClient.changeWorkingDirectory(ftpConfig.getDir());

        int reply = ftpClient.getReplyCode();
        if (!FTPReply.isPositiveCompletion(reply)) {
            ftpClient.disconnect();
            return false;
        }
        String newFileName = new String(stringBuffer.toString().getBytes("UTF-8"), "iso-8859-1");

        FileInputStream fileInputStream = new FileInputStream(file);
        //上传文件
        if (!ftpClient.storeFile(newFileName, fileInputStream)) {
            return false;
        }

        fileInputStream.close();
        ftpClient.logout();
        return true;
    }

    /**
     * 生成document对象
     *
     * @return
     */
    private Document createDocument(List<LedenCollectFinger> fingers) {
        Document document = DocumentHelper.createDocument();

        Element root = document.addElement("root");

        //生成头部
//        Element head = root.addElement("head");
//        head.addElement("EQUIPMENT_CODE");
//        head.addElement("USER_CODE");
//        head.addElement("USER_UNIT_CODE");
//        head.addElement("RYJCXXCJBH");

        //生成指纹列表
        Element fingerlist = root.addElement("FINGERLIST");
        for (int i = 0; i < 20; i++) {
            //添加20个指纹节点
            Element finger = fingerlist.addElement("FINGER");
            if (fingers.get(i) != null) {
                LedenCollectFinger fingerTemp=fingers.get(i);
                finger.addElement("ZWZWDM").setText(setTxt(fingerTemp.getZwzwdm()));
                finger.addElement("ZZHWQSQKDM").setText(setTxt(fingerTemp.getZzhwqsqkdm()));
                finger.addElement("ZW_TXYSFFMS").setText(setTxt(fingerTemp.getZwTxysffms()));
                finger.addElement("ZW_TXZL").setText(setTxt(fingerTemp.getZwTxzl()));
                finger.addElement("ZW_TXSJ").setText(setTxt(fingerTemp.getZwTxsj()));
            }
        }
        //掌纹列表
        Element plamlist = root.addElement("PLAMLIST");
        for (int i = 0; i < 4; i++) {
            Element plam = plamlist.addElement("PLAM");
            plam.addElement("ZHWZHWDM");
            plam.addElement("ZHW_ZZHWQSQKDM");
            plam.addElement("ZHW_TXYSFSMS");
            plam.addElement("ZHW_TXZL");
            plam.addElement("ZHW_TXSJ");

        }
        //四指纹列表
        Element fourfingerlist = root.addElement("FOURFINGERLIST");
        for (int i = 0; i < 2; i++) {
            Element fourFinger = fourfingerlist.addElement("FOURFINGER");
            fourFinger.addElement("SLZ_ZWZWDM");
            fourFinger.addElement("SLZ_ZZHWQSQKDM");
            fourFinger.addElement("SLZ_TXYSFSMS");
            fourFinger.addElement("SLZ_TXZL");
            fourFinger.addElement("SLZ_TXSJ");
        }
        //指节纹列表
        Element phalangelist = root.addElement("PHALANGELIST");

        //全掌纹列表
        Element fullplamlist = root.addElement("FULLPLAMLIST");
        for (int i = 0; i < 2; i++) {
            Element fullPlam = fullplamlist.addElement("FULLPLAM");
            fullPlam.addElement("QZ_ZHWZHWDM");
            fullPlam.addElement("QZ_ZZHWQSQKDM");
            fullPlam.addElement("QZ_TXYSFSMS");
            fullPlam.addElement("QZ_TXZL");
            fullPlam.addElement("QZ_TXSJ");
        }
        return document;


    }


    private String setTxt(Object object){
        if(object==null)
            return "";
        else if(object instanceof String)
            return object.toString();
        else
            return new String((byte[])object);


    }
}
