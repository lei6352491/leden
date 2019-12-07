package com.zhouyi.business.controller;

import com.zhouyi.business.common.SshConnection;
import com.zhouyi.business.core.common.ReturnCode;
import com.zhouyi.business.core.dao.LedenEquipmentEmpowerMapper;
import com.zhouyi.business.core.exception.AuthenticationException;
import com.zhouyi.business.core.model.*;
import com.zhouyi.business.core.model.enums.AuthoirtyEnum;
import com.zhouyi.business.core.service.*;
import com.zhouyi.business.core.utils.ResponseUtil;
import com.zhouyi.business.core.utils.SecurityUtil;
import com.zhouyi.business.core.vo.headvo.HeaderVo;
import com.zhouyi.business.utils.FileStoreUtils;
import com.zhouyi.business.utils.JsoupParseXmlUtils;
import com.zhouyi.business.utils.XMLParamUtils;
import io.swagger.annotations.Api;
import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@ToString
@RestController
@RequestMapping(value = "/collectWebService")
@Api(hidden = true)
public class XMLParseController {

    private static final Logger logger = LoggerFactory.getLogger(XMLParseController.class);

    @Value("${xmlParse.ftp.path}")
    private String path;

    //用于存放当前采集人员的人员编号
    private String ryjcxxcjbh;

    @Autowired
    private FileStoreUtils fileStoreUtils;

    @Autowired
    private LedenCollectDrugtestService ledenCollectDrugtestService;

    @Autowired
    private LedenCollectIrisService ledenCollectIrisService;

    @Autowired
    private LedenCollectVoiceprintService ledenCollectVoiceprintService;

    @Autowired
    private LedenCollectBankcardService ledenCollectBankcardService;

    @Autowired
    private LedenPersonIndexService ledenPersonIndexService;

    @Autowired
    private LedenUploadLogService ledenUploadLogService;

    @Autowired
    private LedenUploadPacketService ledenUploadPacketService;

    @Autowired
    private LedenEquipmentEmpowerMapper ledenEquipmentEmpowerMapperMapper;

    @Autowired
    private SshConnection sshConnection;
    @Autowired
    SecurityUtil securityUtil;


    /**
     * 吸毒检查采集接口
     */
    @RequestMapping(value = "/receiveDrugTestInfo")
    public Response drugtestParseXmlToRepository(String xmlContent) {
        XMLParamUtils xmlParamUtils = new XMLParamUtils();
        Map map = xmlParamUtils.parseXmlToMap
                (xmlContent, LedenCollectDrugtest.class, null);
        Head head = (Head) map.get("head");
        List data = (List) map.get("data");

        //校验head是否有权限保存数据
        boolean boo = ledenCollectDrugtestService.checkHead(head);
        if (boo) {
            Response<Object> response = ledenCollectDrugtestService.saveMapToRepository(data, head.getUserUnitCode());
            return response;
        } else {
            return ResponseUtil.returnError(ReturnCode.ERROR_06);
        }
    }

    /**
     * 虹膜采集接口
     */
    @RequestMapping(value = "/receiveIrisInfo ")
    public Response irisParseXmlToRepository(String xmlContent) {
        Map map = JsoupParseXmlUtils.jsoupPerseXmlIrisInfo(xmlContent, LedenCollectIris.class);
        Head head = (Head) map.get("head");
        List data = (List) map.get("data");
        String ryjcxxcjbh = (String) map.get("ryjcxxcjbh");

        //校验head是否有权限保存数据
        boolean boo = ledenCollectIrisService.checkHead(head);
        if (boo) {
            Response<Object> response = ledenCollectIrisService.saveMapToRepository(data, head.getUserUnitCode(), ryjcxxcjbh, null);
            return response;
        } else {
            return ResponseUtil.returnError(ReturnCode.ERROR_06);
        }
    }

    /**
     * 声纹采集接口
     */
    @RequestMapping(value = "/receiveVoicePrint")
    public Response voiceprintParseXmlToRepository(String xmlContent) {
        XMLParamUtils xmlParamUtils = new XMLParamUtils();
        Map map = xmlParamUtils.parseXmlToMap(xmlContent, LedenCollectVoiceprint.class, null);
        Head head = (Head) map.get("head");
        List data = (List) map.get("data");

        //校验head是否有权限保存数据
        boolean boo = ledenCollectVoiceprintService.checkHead(head);
        if (boo) {

            Response<Object> response = ledenCollectVoiceprintService.saveMapToRepository(data, head.getUserUnitCode(), null);
            //解析完成添加采集过程


            return response;
        } else {
            return ResponseUtil.returnError(ReturnCode.ERROR_06);
        }
    }

    /**
     * 银行卡信息采集接口
     */
    @RequestMapping(value = "/bankCardInfo")
    public Response bankCardParseXmlToRepository(String xmlContent) {
        Map map = JsoupParseXmlUtils.jsoupParseXml(xmlContent, LedenCollectBankcard.class, LedenCollectBRecord.class);
        Head head = (Head) map.get("head");
        List data = (List) map.get("data");

        //校验head是否有权限保存数据
        boolean boo = ledenCollectBankcardService.checkHead(head);
        if (boo) {
            Response<Object> response = ledenCollectBankcardService.saveMapToRepository(data, head.getUserUnitCode());
            return response;
        } else {
            return ResponseUtil.returnError(ReturnCode.ERROR_06);
        }
    }

    /**
     * 获取人员编号接口
     */
    @RequestMapping(value = "/getPersonID")
    public Response getPersonID(@RequestBody Head head) throws InterruptedException {
        //校验head是否有权限保存数据
        boolean boo = ledenCollectBankcardService.checkHead(head);
        if (boo) {
            String personCode = ledenPersonIndexService.selectNextPrimaryKey(head.getUserUnitCode());
            return ResponseUtil.getResponseInfo(ReturnCode.SUCCESS, personCode);
        } else {
            return ResponseUtil.returnError(ReturnCode.ERROR_06);
        }
    }

    /**
     * 数据上传消息接口
     */
    @RequestMapping(value = "/getDataUploadMessage")
    public Response getDataUploadMessage(@RequestBody UploadFileMessage uploadFileMessage) {

        logger.info("数据上传消息接口参数为：" + uploadFileMessage.toString());

        //1.保存数据包数据到数据库
        //校验权限
        HeaderVo headerVo = new HeaderVo();
        headerVo.setUSER_UNIT_CODE(uploadFileMessage.getUserUnitCode());
        headerVo.setUSER_CODE(uploadFileMessage.getUserCode());
        headerVo.setEQUIPMENT_CODE(uploadFileMessage.getEquipmentCode());
        Boolean repairpermissions = null;
        try {
            repairpermissions = securityUtil.repairpermissions(headerVo, AuthoirtyEnum.HEAD);


            if (repairpermissions) {

                String fileName = uploadFileMessage.getDataBrief().getUploadPacket();
                //执行ssh脚本文件
                Integer integer = sshConnection.executionScript(fileName);

                if (integer == null) {
                    return ResponseUtil.returnError(ReturnCode.ERROR_1127);
                } else if (integer == 0) {
                    //获取数据包名中的人员编号
                    Integer indexOf = uploadFileMessage.getDataBrief().getUploadPacket().indexOf("-");
                    String ryjcxxcjbh = uploadFileMessage.getDataBrief().getUploadPacket().substring(0, indexOf);


                    logger.info("zip入库成功");
                    //2.解析数据
                    //获取文件名，用于拼接文件路劲
                    String uploadPacketFileName = uploadFileMessage.getDataBrief().getUploadPacket();
                    StringBuilder filePathPrefix = new StringBuilder();
                    filePathPrefix.append(path);
                    filePathPrefix.append("/dist/");
                    filePathPrefix.append(uploadPacketFileName);

                    if (uploadFileMessage.getDataBrief().getPerson()) {
                        String path = filePathPrefix.toString() + "/PERSON-" + ryjcxxcjbh + ".xml";

                        fileStoreUtils.automaticSavaData(uploadFileMessage.getEquipmentCode(), ryjcxxcjbh, "000000000001", "xml", path, "0", "PERSON", uploadFileMessage.getUserUnitCode());

                    }
                    if (uploadFileMessage.getDataBrief().getPortrait()) {
                        String path = filePathPrefix.toString() + "/PORTRAIT-" + ryjcxxcjbh + ".xml";

                        fileStoreUtils.automaticSavaData(uploadFileMessage.getEquipmentCode(), ryjcxxcjbh, "000000000002", "xml", path, "0", "PORTRAIT", uploadFileMessage.getUserUnitCode());

                    }
                    if (uploadFileMessage.getDataBrief().getFingerplam()) {
                        String pathbmp = filePathPrefix.toString() + "/FINGERPLAMBMP-" + ryjcxxcjbh + ".fptx";
                        String pathwsq = filePathPrefix.toString() + "/FINGERPLAMWSQ-" + ryjcxxcjbh + ".fptx";

                        fileStoreUtils.automaticSavaData(uploadFileMessage.getEquipmentCode(), ryjcxxcjbh, "000000000003", "fptx", pathbmp, "0", "FINGERPLAMBMP", uploadFileMessage.getUserUnitCode());
                        fileStoreUtils.automaticSavaData(uploadFileMessage.getEquipmentCode(), ryjcxxcjbh, "000000000003", "fptx", pathwsq, "0", "FINGERPLAMWSQ", uploadFileMessage.getUserUnitCode());

                    }
                    //文件中数据的字段超过数据库的最大长度
                    if (uploadFileMessage.getDataBrief().getSignAlement()) {
                        String path = filePathPrefix.toString() + "/SIGNALEMENT-" + ryjcxxcjbh + ".xml";

                        fileStoreUtils.automaticSavaData(uploadFileMessage.getEquipmentCode(), ryjcxxcjbh, "000000000004", "xml", path, "0", "SIGNALEMENT", uploadFileMessage.getUserUnitCode());

                    }
                    if (uploadFileMessage.getDataBrief().getDnaInfo()) {
                        String path = filePathPrefix.toString() + "/DNAINFO-" + ryjcxxcjbh + ".xml";

                        fileStoreUtils.automaticSavaData(uploadFileMessage.getEquipmentCode(), ryjcxxcjbh, "000000000005", "xml", path, "0", "DNAINFO", uploadFileMessage.getUserUnitCode());

                    }
                    if (uploadFileMessage.getDataBrief().getFootprint()) {
                        String path = filePathPrefix.toString() + "/FOOTPRINT-" + ryjcxxcjbh + ".xml";

                        fileStoreUtils.automaticSavaData(uploadFileMessage.getEquipmentCode(), ryjcxxcjbh, "000000000006", "xml", path, "0", "FOOTPRINT", uploadFileMessage.getUserUnitCode());

                    }
                    //文件中的值超过表中字段的最大长度
                    if (uploadFileMessage.getDataBrief().getVoiceprint()) {
                        String path = filePathPrefix.toString() + "/VOICEPRINT-" + ryjcxxcjbh + ".xml";


                        fileStoreUtils.automaticSavaData(uploadFileMessage.getEquipmentCode(), ryjcxxcjbh, "000000000007", "xml", path, "0", "VOICEPRINT", uploadFileMessage.getUserUnitCode());

                    }
                    //文件中没有数据
                    if (uploadFileMessage.getDataBrief().getHandwriting()) {
                        String path = filePathPrefix.toString() + "/HANDWRITING-" + ryjcxxcjbh + ".xml";

                        fileStoreUtils.automaticSavaData(uploadFileMessage.getEquipmentCode(), ryjcxxcjbh, "000000000008", "xml", path, "0", "HANDWRITING", uploadFileMessage.getUserUnitCode());

                    }
                    if (uploadFileMessage.getDataBrief().getIrisInfo()) {
                        String path = filePathPrefix.toString() + "/IRISINFO-" + ryjcxxcjbh + ".xml";

                        fileStoreUtils.automaticSavaData(uploadFileMessage.getEquipmentCode(), ryjcxxcjbh, "000000000009", "xml", path, "0", "IRISINFO", uploadFileMessage.getUserUnitCode());

                    }
                    //文件中的值超过表中字段的最大长度
                    if (uploadFileMessage.getDataBrief().getGoods()) {
                        String path = filePathPrefix.toString() + "/GOODS-" + ryjcxxcjbh + ".xml";

                        fileStoreUtils.automaticSavaData(uploadFileMessage.getEquipmentCode(), ryjcxxcjbh, "000000000010", "xml", path, "0", "GOODS", uploadFileMessage.getUserUnitCode());

                    }
                    if (uploadFileMessage.getDataBrief().getDrugTest()) {
                        String path = filePathPrefix.toString() + "/DRUGTEST-" + ryjcxxcjbh + ".xml";

                        fileStoreUtils.automaticSavaData(uploadFileMessage.getEquipmentCode(), ryjcxxcjbh, "000000000011", "xml", path, "0", "DRUGTEST", uploadFileMessage.getUserUnitCode());

                    }
                    if (uploadFileMessage.getDataBrief().getPhoneInfo()) {
                        String path = filePathPrefix.toString() + "/PhoneInfo-" + ryjcxxcjbh + ".xml";

                        fileStoreUtils.automaticSavaData(uploadFileMessage.getEquipmentCode(), ryjcxxcjbh, "000000000012", "xml", path, "0", "PhoneInfo", uploadFileMessage.getUserUnitCode());

                    }
                    if (uploadFileMessage.getDataBrief().getBankCard()) {
                        String path = filePathPrefix.toString() + "/BANKCARD-" + ryjcxxcjbh + ".xml";

                        fileStoreUtils.automaticSavaData(uploadFileMessage.getEquipmentCode(), ryjcxxcjbh, "000000000013", "xml", path, "0", "BANKCARD", uploadFileMessage.getUserUnitCode());

                    }


                    //存储zip数据包信息
                    fileStoreUtils.automaticSavaPacket(uploadFileMessage.getEquipmentCode(), ryjcxxcjbh, uploadFileMessage.getDataBrief().getUploadPacket(), "000000000000", path, headerVo.getUSER_UNIT_CODE());


                    return ResponseUtil.returnError(ReturnCode.SUCCESS);
                } else {
                    return ResponseUtil.returnError(ReturnCode.ERROR_1128);
                }
            } else {
                return ResponseUtil.returnError(ReturnCode.ERROR_1027);
            }
        } catch (AuthenticationException e) {
            return ResponseUtil.ntrError(e.getReturnCode().getMsg());
        }
    }

    /**
     * 数据解析状态
     */
    @RequestMapping(value = "/getDataAnalysisStatus")
    public Response getDataAnalysisStatus(@RequestBody UploadFileMessage uploadFileMessage) {

        //校验head是否有权限保存数据
        Head head = new Head();
        head.setEquipmentCode(uploadFileMessage.getEquipmentCode());
        head.setUserCode(uploadFileMessage.getUserCode());
        head.setUserUnitCode(uploadFileMessage.getUserUnitCode());
        boolean boo = ledenUploadPacketService.checkHead(head);
        if (boo) {
            List<LedenUploadPacket> list = ledenUploadPacketService.selectDataAnalysisStatus(uploadFileMessage.getPersonCode());
            return ResponseUtil.getResponseInfo(ReturnCode.SUCCESS, list);
        } else {
            return ResponseUtil.returnError(ReturnCode.ERROR_06);
        }
    }

    /**
     * 根据节点查询集合中的设备授权信息
     */
    private LedenEquipmentEmpower getLedenEquipmentEmpower(List<LedenEquipmentEmpower> list, String nodeCode) {
        for (LedenEquipmentEmpower ledenEquipmentEmpower : list) {
            if (nodeCode.equals(ledenEquipmentEmpower.getNodeSign()))
                return ledenEquipmentEmpower;
        }
        return null;
    }


}
