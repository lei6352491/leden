package com.zhouyi.business.component;

import com.zhouyi.business.core.dao.LedenCollectProcessMapper;
import com.zhouyi.business.core.dao.LedenUploadLogMapper;
import com.zhouyi.business.core.dao.LedenUploadPacketMapper;
import com.zhouyi.business.core.exception.AuthenticationException;
import com.zhouyi.business.core.exception.CollectionException;
import com.zhouyi.business.core.model.*;
import com.zhouyi.business.core.model.enums.AuthoirtyEnum;
import com.zhouyi.business.core.model.provincecomprehensive.DataStatus;
import com.zhouyi.business.core.service.*;
import com.zhouyi.business.core.utils.SecurityUtil;
import com.zhouyi.business.core.vo.headvo.HeaderVo;
import com.zhouyi.business.utils.JsoupParseXmlUtils;
import com.zhouyi.business.utils.XMLParamUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.management.modelmbean.XMLParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author 杜承旭
 * @ClassNmae: CollectTimingTask
 * @Description: TODO
 * @date 2019/9/20 16:44
 * @Version 1.0
 **/

@Component
public class CollectTimingTask {


    @Autowired
    private LedenUploadLogMapper ledenUploadLogMapper;
    @Autowired
    private LedenEquipmentEmpowerService ledenEquipmentEmpowerService;
    @Autowired
    private LedenUploadPacketMapper ledenUploadPacketMapper;
    @Autowired
    private SecurityUtil securityUtil;

    @Autowired
    private LedenCollectPersonService ledenCollectPersonService;

    @Autowired
    private LedenCollectPortraitService ledenCollectPortraitService;

    @Autowired
    private LedenCollectFingerService ledenCollectFingerService;

    @Autowired
    private LedenCollectSLSService ledenCollectSLSService;

    @Autowired
    private LedenCollectDNAService ledenCollectDNAService;

    @Autowired
    private LedenCollectFootprintService ledenCollectFootprintService;

    @Autowired
    private LedenCollectHandWritingService ledenCollectHandWritingService;

    @Autowired
    private LedenCollectVoiceprintService ledenCollectVoiceprintService;

    @Autowired
    private LedenCollectIrisService ledenCollectIrisService;

    @Autowired
    private LedenCollectGoodsService ledenCollectGoodsService;

    @Autowired
    private LedenCollectDrugtestService ledenCollectDrugtestService;

    @Autowired
    private LedenCollectBankcardService ledenCollectBankcardService;

    @Autowired
    private PhoneService phoneService;

    @Autowired
    private LedenCollectProcessMapper ledenCollectProcessMapper;
    @Autowired
    private UploadProvinceComponent uploadProvinceComponent;

    private XMLParamUtils xmlParamUtils = new XMLParamUtils();


    private static final Logger logger = LoggerFactory.getLogger(CollectTimingTask.class);

    @Scheduled(cron = "0/30 * * * * ?")
    public void configureTasks() {
        analysisXmlFile();
    }


    private void setResolveResult(LedenUploadPacket resolveResult, String status, String message) {
        resolveResult.setResolveStatus(status);
        resolveResult.setResolveDatetime(new Date());
        String newMessage="";
        try {
             newMessage = message.substring(0, 2000);
        } catch (Exception e) {
            newMessage=message;
        }
        resolveResult.setResolveResultInfo(newMessage);

    }

    private void analysisXmlFile() {
        /**
         * 查询
         */
        LedenUploadPacket zipUploadPacket = ledenUploadPacketMapper.selectDataByResolveStatus();


        List<LedenUploadPacket> list = null;


        if (zipUploadPacket != null) {

            list = ledenUploadPacketMapper.selectTaskResolveByRyjcxxcjbh(zipUploadPacket.getRyjcxxcjbh());

            logger.info("正在解析：" + zipUploadPacket.getRyjcxxcjbh() + "的数据包");


            //查询该设备的所有授权节点
            List<String> strings = ledenEquipmentEmpowerService.searchEmpwerdNodeSign(zipUploadPacket.getEquipmentId());

            /**
             * 默认解析成功
             */
            boolean flag = true;
            boolean flagFinger = true;


            for (LedenUploadPacket ledenUploadPacket : list) {
                if ("PERSON".equals(ledenUploadPacket.getDataType())) {

                    try {
                        if (!strings.contains("000000000001")) {
                            setResolveResult(ledenUploadPacket, "2", "未授权");
                            break;
                        } else {
                            ledenCollectPersonService.insertCollectPersonByXml(ledenUploadPacket.getFileLocation());
                            setResolveResult(ledenUploadPacket, "1", "解析成功");
                            //删除数据库中该编号该节点的采集过程
                            ledenCollectProcessMapper.deleteProcessByPersonCodeAndNodeCode(ledenUploadPacket.getRyjcxxcjbh(), "000000000001");
                            //添加采集过程
                            LedenCollectProcess ledenCollectProcess = getLedenCollectProcess(ledenUploadPacket, 1, "000000000001");
                            ledenCollectProcessMapper.insertSelective(ledenCollectProcess);
                        }

                        ledenUploadPacketMapper.updateByPrimaryKey(ledenUploadPacket);

                    } catch (AuthenticationException e) {
//                        e.printStackTrace();
                        setResolveResult(ledenUploadPacket, "2", e.getReturnCode().getMsg());

                        ledenUploadPacketMapper.updateByPrimaryKey(ledenUploadPacket);
                        flag = false;
                        break;
                    } catch (Exception E) {
                        E.printStackTrace();
                        setResolveResult(ledenUploadPacket, "2", E.getMessage());
                        flag = false;
                        ledenUploadPacketMapper.updateByPrimaryKey(ledenUploadPacket);
                        break;
                    }
                    continue;
                }
                if ("PORTRAIT".equals(ledenUploadPacket.getDataType())) {
                    try {
                        if (!strings.contains("000000000002")) {
                            setResolveResult(ledenUploadPacket, "2", "未授权");
                        } else {
                            ledenCollectPortraitService.insertPortraitByXml(ledenUploadPacket.getFileLocation());
                            setResolveResult(ledenUploadPacket, "1", "解析成功");
                            //删除数据库中该编号该节点的采集过程
                            ledenCollectProcessMapper.deleteProcessByPersonCodeAndNodeCode(ledenUploadPacket.getRyjcxxcjbh(), "000000000002");
                            //添加采集过程
                            LedenCollectProcess ledenCollectProcess = getLedenCollectProcess(ledenUploadPacket, 1, "000000000002");
                            ledenCollectProcessMapper.insertSelective(ledenCollectProcess);
                        }

                        ledenUploadPacketMapper.updateByPrimaryKey(ledenUploadPacket);


                    } catch (AuthenticationException e) {
                        e.printStackTrace();
                        setResolveResult(ledenUploadPacket, "2", e.getReturnCode().getMsg());

                        flag = false;

                        ledenUploadPacketMapper.updateByPrimaryKey(ledenUploadPacket);
                    } catch (Exception E) {
                        E.printStackTrace();
                        setResolveResult(ledenUploadPacket, "2", E.getMessage());
                        flag = false;

                        ledenUploadPacketMapper.updateByPrimaryKey(ledenUploadPacket);
                    }

                    continue;
                }
                if ("FINGERPLAMBMP".equals(ledenUploadPacket.getDataType())) {

                    try {

                        if (!strings.contains("000000000003")) {
                            setResolveResult(ledenUploadPacket, "2", "未授权");

                            flagFinger = false;
                        } else {
                            ledenCollectFingerService.inputFingersByXml(ledenUploadPacket.getFileLocation(), "0000");
                            setResolveResult(ledenUploadPacket, "1", "解析成功");
                            //删除数据库中该编号该节点的采集过程
                            ledenCollectProcessMapper.deleteProcessByPersonCodeAndNodeCode(ledenUploadPacket.getRyjcxxcjbh(), "000000000003");
                            //添加采集过程
                            LedenCollectProcess ledenCollectProcess = getLedenCollectProcess(ledenUploadPacket, 1, "000000000003");
                            ledenCollectProcessMapper.insertSelective(ledenCollectProcess);
                        }

                        ledenUploadPacketMapper.updateByPrimaryKey(ledenUploadPacket);


                    } catch (Exception E) {
                        setResolveResult(ledenUploadPacket, "2", E.getMessage());
                        E.printStackTrace();
                        flag = false;
                        flagFinger = false;

                        ledenUploadPacketMapper.updateByPrimaryKey(ledenUploadPacket);
                    }
                    continue;
                }
                if ("FINGERPLAMWSQ".equals(ledenUploadPacket.getDataType()) && flagFinger) {
                    try {
                        if (!strings.contains("000000000003")) {
                            setResolveResult(ledenUploadPacket, "2", "未授权");
                        } else {
                            ledenCollectFingerService.inputFingersByXml(ledenUploadPacket.getFileLocation(), "xxxx");
                            setResolveResult(ledenUploadPacket, "1", "解析成功");
                            //删除数据库中该编号该节点的采集过程
                            ledenCollectProcessMapper.deleteProcessByPersonCodeAndNodeCode(ledenUploadPacket.getRyjcxxcjbh(), "000000000003");
                            //添加采集过程
                            LedenCollectProcess ledenCollectProcess = getLedenCollectProcess(ledenUploadPacket, 1, "000000000003");
                            ledenCollectProcessMapper.insertSelective(ledenCollectProcess);
                        }

                        ledenUploadPacketMapper.updateByPrimaryKey(ledenUploadPacket);
                    } catch (Exception E) {
                        setResolveResult(ledenUploadPacket, "2", E.getMessage());
                        E.printStackTrace();
                        flag = false;

                        ledenUploadPacketMapper.updateByPrimaryKey(ledenUploadPacket);
                    }
                    continue;
                }
                if ("SIGNALEMENT".equals(ledenUploadPacket.getDataType())) {
                    try {

                        if (!strings.contains("000000000004")) {
                            setResolveResult(ledenUploadPacket, "2", "未授权");

                        } else {
                            ledenCollectSLSService.insertSignalement(ledenUploadPacket.getFileLocation());
                            setResolveResult(ledenUploadPacket, "1", "解析成功");
                            //删除数据库中该编号该节点的采集过程
                            ledenCollectProcessMapper.deleteProcessByPersonCodeAndNodeCode(ledenUploadPacket.getRyjcxxcjbh(), "000000000004");
                            //添加采集过程
                            LedenCollectProcess ledenCollectProcess = getLedenCollectProcess(ledenUploadPacket, 1, "000000000004");
                            ledenCollectProcessMapper.insertSelective(ledenCollectProcess);
                        }

                        ledenUploadPacketMapper.updateByPrimaryKey(ledenUploadPacket);

                    } catch (AuthenticationException e) {
                        e.printStackTrace();
                        setResolveResult(ledenUploadPacket, "2", e.getReturnCode().getMsg());
                        flag = false;

                        ledenUploadPacketMapper.updateByPrimaryKey(ledenUploadPacket);
                    } catch (Exception E) {
                        E.printStackTrace();
                        setResolveResult(ledenUploadPacket, "2", E.getMessage());
                        flag = false;

                        ledenUploadPacketMapper.updateByPrimaryKey(ledenUploadPacket);
                    }
                    continue;
                }
                if ("DNAINFO".equals(ledenUploadPacket.getDataType())) {
                    try {
                        if (!strings.contains("000000000005")) {
                            setResolveResult(ledenUploadPacket, "2", "未授权");
                        } else {
                            ledenCollectDNAService.inputDNAByXml(ledenUploadPacket.getFileLocation());
                            setResolveResult(ledenUploadPacket, "1", "解析成功");
                            //删除数据库中该编号该节点的采集过程
                            ledenCollectProcessMapper.deleteProcessByPersonCodeAndNodeCode(ledenUploadPacket.getRyjcxxcjbh(), "000000000005");
                            //添加采集过程
                            LedenCollectProcess ledenCollectProcess = getLedenCollectProcess(ledenUploadPacket, 1, "000000000005");
                            ledenCollectProcessMapper.insertSelective(ledenCollectProcess);
                        }


                        ledenUploadPacketMapper.updateByPrimaryKey(ledenUploadPacket);


                    } catch (AuthenticationException e) {
                        e.printStackTrace();
                        setResolveResult(ledenUploadPacket, "2", e.getReturnCode().getMsg());
                        flag = false;

                        ledenUploadPacketMapper.updateByPrimaryKey(ledenUploadPacket);
                    } catch (Exception E) {
                        E.printStackTrace();
                        setResolveResult(ledenUploadPacket, "2", E.getMessage());
                        flag = false;

                        ledenUploadPacketMapper.updateByPrimaryKey(ledenUploadPacket);
                    }
                    continue;
                }
                if ("FOOTPRINT".equals(ledenUploadPacket.getDataType())) {
                    try {
                        if (!strings.contains("000000000006")) {
                            setResolveResult(ledenUploadPacket, "2", "未授权");

                        } else {
                            ledenCollectFootprintService.inputFootprintByXml(ledenUploadPacket.getFileLocation());
                            setResolveResult(ledenUploadPacket, "1", "解析成功");

                            //删除数据库中该编号该节点的采集过程
                            ledenCollectProcessMapper.deleteProcessByPersonCodeAndNodeCode(ledenUploadPacket.getRyjcxxcjbh(), "000000000006");
                            //添加采集过程
                            LedenCollectProcess ledenCollectProcess = getLedenCollectProcess(ledenUploadPacket, 1, "000000000006");
                            ledenCollectProcessMapper.insertSelective(ledenCollectProcess);
                        }

                        ledenUploadPacketMapper.updateByPrimaryKey(ledenUploadPacket);

                    } catch (AuthenticationException e) {
                        e.printStackTrace();
                        setResolveResult(ledenUploadPacket, "2", e.getReturnCode().getMsg());

                        flag = false;

                        ledenUploadPacketMapper.updateByPrimaryKey(ledenUploadPacket);
                    } catch (Exception E) {
                        setResolveResult(ledenUploadPacket, "2", E.getMessage());
                        E.printStackTrace();

                        flag = false;

                        ledenUploadPacketMapper.updateByPrimaryKey(ledenUploadPacket);
                    }
                    continue;
                }
                if ("VOICEPRINT".equals(ledenUploadPacket.getDataType())) {
                    try {
                        if (!strings.contains("000000000007")) {
                            setResolveResult(ledenUploadPacket, "2", "未授权");

                        } else {
                            Map map = xmlParamUtils.parseXmlToMap(ledenUploadPacket.getFileLocation(), LedenCollectVoiceprint.class, null);
                            Head head = (Head) map.get("head");

                            HeaderVo headerVo=new HeaderVo();
                            headerVo.setUSER_CODE(head.getUserCode());
                            headerVo.setUSER_UNIT_CODE(head.getUserUnitCode());
                            headerVo.setEQUIPMENT_CODE(head.getEquipmentCode());

                            List data = (List) map.get("data");
                            //校验head是否有权限保存数据
                            boolean boo2 = securityUtil.repairpermissions(headerVo, AuthoirtyEnum.VOICEPRINT);
                            if (boo2) {
                                ledenCollectVoiceprintService.saveMapToRepository(data, head.getUserUnitCode(), head.getUserCode());
                                setResolveResult(ledenUploadPacket, "1", "解析成功");

                                //删除数据库中该编号该节点的采集过程
                                ledenCollectProcessMapper.deleteProcessByPersonCodeAndNodeCode(ledenUploadPacket.getRyjcxxcjbh(), "000000000007");
                                //添加采集过程
                                LedenCollectProcess ledenCollectProcess = getLedenCollectProcess(ledenUploadPacket, 1, "000000000007");
                                ledenCollectProcessMapper.insertSelective(ledenCollectProcess);
                            } else {
                                setResolveResult(ledenUploadPacket, "2", "用户部门设备不匹配");
                                flag = false;
                            }

                        }


                        ledenUploadPacketMapper.updateByPrimaryKey(ledenUploadPacket);


                    } catch (Exception E) {
                        E.printStackTrace();
                        setResolveResult(ledenUploadPacket, "2", E.getMessage());
                        flag = false;

                        ledenUploadPacketMapper.updateByPrimaryKey(ledenUploadPacket);
                    }
                    continue;
                }
                if ("HANDWRITING".equals(ledenUploadPacket.getDataType())) {
                    try {
                        if (!strings.contains("000000000008")) {
                            setResolveResult(ledenUploadPacket, "2", "未授权");

                        } else {
                            ledenCollectHandWritingService.inputHandWirtingXml(ledenUploadPacket.getFileLocation());
                            setResolveResult(ledenUploadPacket, "1", "解析成功");
                            //删除数据库中该编号该节点的采集过程
                            ledenCollectProcessMapper.deleteProcessByPersonCodeAndNodeCode(ledenUploadPacket.getRyjcxxcjbh(), "000000000008");
                            //添加采集过程
                            LedenCollectProcess ledenCollectProcess = getLedenCollectProcess(ledenUploadPacket, 1, "000000000008");
                            ledenCollectProcessMapper.insertSelective(ledenCollectProcess);
                        }

                        ledenUploadPacketMapper.updateByPrimaryKey(ledenUploadPacket);

                    } catch (AuthenticationException e) {
                        setResolveResult(ledenUploadPacket, "2", e.getReturnCode().getMsg());
                        e.printStackTrace();

                        flag = false;

                        ledenUploadPacketMapper.updateByPrimaryKey(ledenUploadPacket);
                    } catch (Exception E) {
                        setResolveResult(ledenUploadPacket, "2", E.getMessage());
                        E.printStackTrace();

                        flag = false;

                        ledenUploadPacketMapper.updateByPrimaryKey(ledenUploadPacket);
                    }
                    continue;
                }
                if ("IRISINFO".equals(ledenUploadPacket.getDataType())) {
                    try {
                        if (!strings.contains("000000000009")) {
                            setResolveResult(ledenUploadPacket, "2", "未授权");

                        } else {
                            Map map = JsoupParseXmlUtils.jsoupPerseXmlIrisInfo(ledenUploadPacket.getFileLocation(), LedenCollectIris.class);
                            Head head = (Head) map.get("head");
                            List data = (List) map.get("data");
                            String ryjcxxcjbh = (String) map.get("ryjcxxcjbh");

                            HeaderVo headerVo=new HeaderVo();
                            headerVo.setUSER_CODE(head.getUserCode());
                            headerVo.setUSER_UNIT_CODE(head.getUserUnitCode());
                            headerVo.setEQUIPMENT_CODE(head.getEquipmentCode());
                            //校验head是否有权限保存数据
                            boolean boo2 = securityUtil.repairpermissions(headerVo,AuthoirtyEnum.IRISINFO);
                            if (boo2) {
                                ledenCollectIrisService.saveMapToRepository(data, head.getUserUnitCode(), ryjcxxcjbh, head.getUserCode());
                                setResolveResult(ledenUploadPacket, "1", "解析成功");
                                //删除数据库中该编号该节点的采集过程
                                ledenCollectProcessMapper.deleteProcessByPersonCodeAndNodeCode(ledenUploadPacket.getRyjcxxcjbh(), "000000000009");
                                //添加采集过程
                                LedenCollectProcess ledenCollectProcess = getLedenCollectProcess(ledenUploadPacket, 1, "000000000009");
                                ledenCollectProcessMapper.insertSelective(ledenCollectProcess);
                            } else {
                                setResolveResult(ledenUploadPacket, "2", "用户部门设备不匹配");
                                flag = false;
                            }
                        }
                        ledenUploadPacketMapper.updateByPrimaryKey(ledenUploadPacket);


                    } catch (Exception E) {
                        E.printStackTrace();
                        setResolveResult(ledenUploadPacket, "2", E.toString());
                        System.out.println("-------------------------------------");
                        System.out.println(E.toString().length());
                        System.out.println("-------------------------------------");
                        flag = false;
                        ledenUploadPacketMapper.updateByPrimaryKey(ledenUploadPacket);

                    }
                    continue;
                }
                if ("GOODS".equals(ledenUploadPacket.getDataType())) {
                    try {
                        if (!strings.contains("000000000010")) {
                            setResolveResult(ledenUploadPacket, "2", "未授权");

                        } else {
                            ledenCollectGoodsService.inputGoodsByXml(ledenUploadPacket.getFileLocation());
                            ledenUploadPacket.setResolveResultInfo("解析成功");

                            setResolveResult(ledenUploadPacket, "1", "解析成功");
                            //删除数据库中该编号该节点的采集过程
                            ledenCollectProcessMapper.deleteProcessByPersonCodeAndNodeCode(ledenUploadPacket.getRyjcxxcjbh(), "000000000010");
                            //添加采集过程
                            LedenCollectProcess ledenCollectProcess = getLedenCollectProcess(ledenUploadPacket, 1, "000000000010");
                            ledenCollectProcessMapper.insertSelective(ledenCollectProcess);
                        }
                        ledenUploadPacketMapper.updateByPrimaryKey(ledenUploadPacket);

                    } catch (AuthenticationException e) {
                        setResolveResult(ledenUploadPacket, "2", e.getReturnCode().getMsg());
                        e.printStackTrace();
                        flag = false;

                        ledenUploadPacketMapper.updateByPrimaryKey(ledenUploadPacket);
                    } catch (Exception E) {
                        setResolveResult(ledenUploadPacket, "2", E.getMessage());
                        E.printStackTrace();
                        flag = false;

                        ledenUploadPacketMapper.updateByPrimaryKey(ledenUploadPacket);
                    }
                    continue;
                }
                if ("DRUGTEST".equals(ledenUploadPacket.getDataType())) {
                    try {
                        if (!strings.contains("000000000011")) {
                            setResolveResult(ledenUploadPacket, "2", "未授权");

                        } else {
                            Map map = xmlParamUtils.parseXmlToMap(ledenUploadPacket.getFileLocation(), LedenCollectDrugtest.class, null);
                            Head head = (Head) map.get("head");
                            List data = (List) map.get("data");

                            HeaderVo headerVo=new HeaderVo();
                            headerVo.setUSER_CODE(head.getUserCode());
                            headerVo.setUSER_UNIT_CODE(head.getUserUnitCode());
                            headerVo.setEQUIPMENT_CODE(head.getEquipmentCode());

                            boolean boo2 = securityUtil.repairpermissions(headerVo,AuthoirtyEnum.DRUGTEST);
                            if (boo2) {
                                ledenCollectDrugtestService.saveMapToRepository(data, head.getUserUnitCode());
                                setResolveResult(ledenUploadPacket, "1", "解析成功");

                                //删除数据库中该编号该节点的采集过程
                                ledenCollectProcessMapper.deleteProcessByPersonCodeAndNodeCode(ledenUploadPacket.getRyjcxxcjbh(), "000000000011");
                                //添加采集过程
                                LedenCollectProcess ledenCollectProcess = getLedenCollectProcess(ledenUploadPacket, 1, "000000000011");
                                ledenCollectProcessMapper.insertSelective(ledenCollectProcess);
                            } else {
                                setResolveResult(ledenUploadPacket, "2", "用户部门设备不匹配");
                                flag = false;
                            }
                        }

                        ledenUploadPacketMapper.updateByPrimaryKey(ledenUploadPacket);

                    } catch (Exception E) {
                        setResolveResult(ledenUploadPacket, "2", E.getMessage());
                        E.printStackTrace();

                        flag = false;

                        ledenUploadPacketMapper.updateByPrimaryKey(ledenUploadPacket);
                    }
                    continue;
                }
                if ("PHONEINFO".equals(ledenUploadPacket.getDataType())) {

                }
                if ("BANKCARD".equals(ledenUploadPacket.getDataType())) {
                    try {
                        if (!strings.contains("000000000013")) {
                            setResolveResult(ledenUploadPacket, "2", "未授权");

                        } else {
                            Map map = JsoupParseXmlUtils.jsoupParseXml(ledenUploadPacket.getFileLocation(), LedenCollectBankcard.class, LedenCollectBRecord.class);
                            Head head = (Head) map.get("head");
                            List data = (List) map.get("data");
                            HeaderVo headerVo=new HeaderVo();
                            headerVo.setUSER_CODE(head.getUserCode());
                            headerVo.setUSER_UNIT_CODE(head.getUserUnitCode());
                            headerVo.setEQUIPMENT_CODE(head.getEquipmentCode());

                            boolean boo2 = securityUtil.repairpermissions(headerVo,AuthoirtyEnum.BANKCARD);
                            //校验head是否有权限保存数据
                            if (boo2) {
                                ledenCollectBankcardService.saveMapToRepository(data, head.getUserUnitCode());
                                setResolveResult(ledenUploadPacket, "1", "解析成功");

                                //删除数据库中该编号该节点的采集过程
                                ledenCollectProcessMapper.deleteProcessByPersonCodeAndNodeCode(ledenUploadPacket.getRyjcxxcjbh(), "000000000013");
                                //添加采集过程
                                LedenCollectProcess ledenCollectProcess = getLedenCollectProcess(ledenUploadPacket, 1, "000000000013");
                                ledenCollectProcessMapper.insertSelective(ledenCollectProcess);
                            } else {
                                setResolveResult(ledenUploadPacket, "2", "用户部门设备不匹配");
                                flag = false;
                            }
                        }
                        ledenUploadPacketMapper.updateByPrimaryKey(ledenUploadPacket);

                    } catch (Exception E) {
                        E.printStackTrace();
                        setResolveResult(ledenUploadPacket, "2", E.getMessage());

                        flag = false;

                        ledenUploadPacketMapper.updateByPrimaryKey(ledenUploadPacket);
                    }

                }
            }


            //如果解析完成并且上报至省综平台
            if (flag) {
                setResolveResult(zipUploadPacket, "1", "解析成功");
                LedenUploadLog ledenUploadLog = new LedenUploadLog();

                ledenUploadLog.setEquipmentId(zipUploadPacket.getEquipmentId());
                ledenUploadLog.setRyjcxxcjbh(zipUploadPacket.getRyjcxxcjbh());
                ledenUploadLog.setPkId(UUID.randomUUID().toString().replace("-", ""));
                ledenUploadLog.setUploadStatus("0");
                ledenUploadLog.setCreateDatetime(new Date());

                ledenUploadLogMapper.insertUploadLog(ledenUploadLog);

            } else {
                setResolveResult(zipUploadPacket, "2", "解析失败");
            }


            ledenUploadPacketMapper.updateByPrimaryKey(zipUploadPacket);
        } else {

            logger.error("未获取到解析队列，等待下次执行");
        }
    }

//    private void updateUploadPacket(LedenUploadPacket ledenUploadPacket) {
//        LedenUploadPacket ledenUploadPacket1 = ledenUploadPacketMapper.selectByPrimaryKey(ledenUploadPacket.getPkId());
//        ledenUploadPacket1.setResolveStatus("1");
//        ledenUploadPacket1.setResolveDatetime(new Date());
//        ledenUploadPacket1.setResolveResultInfo(ledenUploadPacket.getResolveResultInfo());
//        ledenUploadPacketMapper.updateByPrimaryKey(ledenUploadPacket1);
//    }

    private LedenCollectProcess getLedenCollectProcess(LedenUploadPacket ledenUploadPacket, int stauts, String nodeCode) {
        LedenCollectProcess ledenCollectProcess = new LedenCollectProcess();
        ledenCollectProcess.setPkId(UUID.randomUUID().toString().replace("-", ""));
        ledenCollectProcess.setRyjcxxcjbh(ledenUploadPacket.getRyjcxxcjbh());
        ledenCollectProcess.setCollectDate(ledenUploadPacket.getCreateDatetime());
        ledenCollectProcess.setCollectStatus((short) stauts);
        ledenCollectProcess.setCollectNodeId(nodeCode);
        return ledenCollectProcess;
    }


    @Scheduled(cron = "0/30 * * * * ?")
    public void searchDataStatus() {
        DataStatus uploadSuccessData = ledenUploadLogMapper.getUploadSuccessData();
        if (uploadSuccessData != null) {
            logger.info("查询" + uploadSuccessData.getRybh() + "的解析状态信息");
        } else {
            logger.info("没有待查询的人员信息");
            return;
        }
        uploadProvinceComponent.getDataUploadStatus(uploadSuccessData);

        //修改解析时间
        ledenUploadLogMapper.updateResolveByPkId(uploadSuccessData.getPkId(), new Date());


    }


}
