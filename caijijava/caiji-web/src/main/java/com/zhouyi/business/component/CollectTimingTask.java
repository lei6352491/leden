package com.zhouyi.business.component;

import com.zhouyi.business.core.dao.LedenCollectProcessMapper;
import com.zhouyi.business.core.dao.LedenUploadPacketMapper;
import com.zhouyi.business.core.exception.AuthenticationException;
import com.zhouyi.business.core.exception.CollectionException;
import com.zhouyi.business.core.model.*;
import com.zhouyi.business.core.service.*;
import com.zhouyi.business.utils.JsoupParseXmlUtils;
import com.zhouyi.business.utils.XMLParamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

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
public class CollectTimingTask{

    @Autowired
    private LedenUploadPacketMapper ledenUploadPacketMapper;

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

    private XMLParamUtils xmlParamUtils = new XMLParamUtils();


    @Scheduled(cron = "0/30 * * * * ?")
    private void configureTasks() {
        analysisXmlFile();
    }

    private void analysisXmlFile(){
        List<LedenUploadPacket> list = ledenUploadPacketMapper.selectDataByIsEmpowerOrResolveStatus();
        for (LedenUploadPacket ledenUploadPacket : list){
            if ("000000000001".equals(ledenUploadPacket.getNodeSign())){
                try{
                    ledenCollectPersonService.insertCollectPersonByXml(ledenUploadPacket.getFileLocation());
                    ledenUploadPacket.setResolveResultInfo("解析成功");
                    updateUploadPacket(ledenUploadPacket);

                    //删除数据库中该编号该节点的采集过程
                    ledenCollectProcessMapper.deleteProcessByPersonCodeAndNodeCode(ledenUploadPacket.getPersonCode(),"000000000001");
                    //添加采集过程
                    LedenCollectProcess ledenCollectProcess = getLedenCollectProcess(ledenUploadPacket, 1, "000000000001");
                    ledenCollectProcessMapper.insertSelective(ledenCollectProcess);

                }catch (AuthenticationException e){
                    e.printStackTrace();
                    ledenUploadPacket.setResolveResultInfo(e.getReturnCode().getMsg());
                    updateUploadPacket(ledenUploadPacket);
                }catch (Exception E){
                    E.printStackTrace();
                    ledenUploadPacket.setResolveResultInfo(E.toString());
                    updateUploadPacket(ledenUploadPacket);
                }
                continue;
            }
            if ("000000000002".equals(ledenUploadPacket.getNodeSign())){
                try{
                    ledenCollectPortraitService.insertPortraitByXml(ledenUploadPacket.getFileLocation());
                    ledenUploadPacket.setResolveResultInfo("解析成功");
                    updateUploadPacket(ledenUploadPacket);

                    //删除数据库中该编号该节点的采集过程
                    ledenCollectProcessMapper.deleteProcessByPersonCodeAndNodeCode(ledenUploadPacket.getPersonCode(),"000000000002");
                    //添加采集过程
                    LedenCollectProcess ledenCollectProcess = getLedenCollectProcess(ledenUploadPacket, 1, "000000000002");
                    ledenCollectProcessMapper.insertSelective(ledenCollectProcess);

                }catch (AuthenticationException e){
                    e.printStackTrace();
                    ledenUploadPacket.setResolveResultInfo(e.getReturnCode().getMsg());
                    updateUploadPacket(ledenUploadPacket);
                }catch (Exception E){
                    E.printStackTrace();
                    ledenUploadPacket.setResolveResultInfo(E.toString());
                    updateUploadPacket(ledenUploadPacket);
                }

                continue;
            }
            if ("000000000003".equals(ledenUploadPacket.getNodeSign())){
                try {
                    ledenCollectFingerService.inputFingersByXml(ledenUploadPacket.getFileLocation());
                    ledenUploadPacket.setResolveResultInfo("解析成功");
                    updateUploadPacket(ledenUploadPacket);

                    //删除数据库中该编号该节点的采集过程
                    ledenCollectProcessMapper.deleteProcessByPersonCodeAndNodeCode(ledenUploadPacket.getPersonCode(),"000000000003");
                    //添加采集过程
                    LedenCollectProcess ledenCollectProcess = getLedenCollectProcess(ledenUploadPacket, 1, "000000000003");
                    ledenCollectProcessMapper.insertSelective(ledenCollectProcess);

                } catch (XMLParseException e) {
                    e.printStackTrace();
                    ledenUploadPacket.setResolveResultInfo(e.getMessage());
                    updateUploadPacket(ledenUploadPacket);
                }catch (Exception E){
                    E.printStackTrace();
                    ledenUploadPacket.setResolveResultInfo(E.toString());
                    updateUploadPacket(ledenUploadPacket);
                }
                continue;
            }
            if ("000000000004".equals(ledenUploadPacket.getNodeSign())){
                try{
                    ledenCollectSLSService.insertPersonInfo(ledenUploadPacket.getFileLocation());
                    ledenUploadPacket.setResolveResultInfo("解析成功");
                    updateUploadPacket(ledenUploadPacket);

                    //删除数据库中该编号该节点的采集过程
                    ledenCollectProcessMapper.deleteProcessByPersonCodeAndNodeCode(ledenUploadPacket.getPersonCode(),"000000000004");
                    //添加采集过程
                    LedenCollectProcess ledenCollectProcess = getLedenCollectProcess(ledenUploadPacket, 1, "000000000004");
                    ledenCollectProcessMapper.insertSelective(ledenCollectProcess);
                }catch (AuthenticationException e){
                    e.printStackTrace();
                    ledenUploadPacket.setResolveResultInfo(e.getReturnCode().getMsg());
                    updateUploadPacket(ledenUploadPacket);
                }catch (Exception E){
                    E.printStackTrace();
                    ledenUploadPacket.setResolveResultInfo(E.toString());
                    updateUploadPacket(ledenUploadPacket);
                }
                continue;
            }
            if ("000000000005".equals(ledenUploadPacket.getNodeSign())){
                try {
                    ledenCollectDNAService.inputDNSByXml(ledenUploadPacket.getFileLocation());
                    ledenUploadPacket.setResolveResultInfo("解析成功");
                    updateUploadPacket(ledenUploadPacket);

                    //删除数据库中该编号该节点的采集过程
                    ledenCollectProcessMapper.deleteProcessByPersonCodeAndNodeCode(ledenUploadPacket.getPersonCode(),"000000000005");
                    //添加采集过程
                    LedenCollectProcess ledenCollectProcess = getLedenCollectProcess(ledenUploadPacket, 1, "000000000005");
                    ledenCollectProcessMapper.insertSelective(ledenCollectProcess);
                }catch (AuthenticationException e){
                    e.printStackTrace();
                    ledenUploadPacket.setResolveResultInfo(e.getReturnCode().getMsg());
                    updateUploadPacket(ledenUploadPacket);
                }catch (Exception E){
                    E.printStackTrace();
                    ledenUploadPacket.setResolveResultInfo(E.toString());
                    updateUploadPacket(ledenUploadPacket);
                }
                continue;
            }
            if ("000000000006".equals(ledenUploadPacket.getNodeSign())){
                try{
                    ledenCollectFootprintService.inputFootprintByXml(ledenUploadPacket.getFileLocation());
                    ledenUploadPacket.setResolveResultInfo("解析成功");
                    updateUploadPacket(ledenUploadPacket);

                    //删除数据库中该编号该节点的采集过程
                    ledenCollectProcessMapper.deleteProcessByPersonCodeAndNodeCode(ledenUploadPacket.getPersonCode(),"000000000006");
                    //添加采集过程
                    LedenCollectProcess ledenCollectProcess = getLedenCollectProcess(ledenUploadPacket, 1, "000000000006");
                    ledenCollectProcessMapper.insertSelective(ledenCollectProcess);
                }catch (AuthenticationException e){
                    e.printStackTrace();
                    ledenUploadPacket.setResolveResultInfo(e.getReturnCode().getMsg());
                    updateUploadPacket(ledenUploadPacket);
                }catch (Exception E){
                    E.printStackTrace();
                    ledenUploadPacket.setResolveResultInfo(E.toString());
                    updateUploadPacket(ledenUploadPacket);
                }
                continue;
            }
            if ("000000000007".equals(ledenUploadPacket.getNodeSign())){
                try {
                    Map map = xmlParamUtils.parseXmlToMap(ledenUploadPacket.getFileLocation(), LedenCollectVoiceprint.class, null);
                    Head head = (Head) map.get("head");
                    List data = (List) map.get("data");
                    //校验head是否有权限保存数据
                    boolean boo2 = ledenCollectVoiceprintService.checkHead(head);
                    if (boo2) {
                        ledenCollectVoiceprintService.saveMapToRepository(data, head.getUserUnitCode());
                        ledenUploadPacket.setResolveResultInfo("解析成功");
                        updateUploadPacket(ledenUploadPacket);

                        //删除数据库中该编号该节点的采集过程
                        ledenCollectProcessMapper.deleteProcessByPersonCodeAndNodeCode(ledenUploadPacket.getPersonCode(),"000000000007");
                        //添加采集过程
                        LedenCollectProcess ledenCollectProcess = getLedenCollectProcess(ledenUploadPacket, 1, "000000000007");
                        ledenCollectProcessMapper.insertSelective(ledenCollectProcess);
                    }
                }catch (Exception E){
                    E.printStackTrace();
                    ledenUploadPacket.setResolveResultInfo(E.toString());
                    updateUploadPacket(ledenUploadPacket);
                }
                continue;
            }
            if ("000000000008".equals(ledenUploadPacket.getNodeSign())){
                try {
                    ledenCollectHandWritingService.inputHandWirtingXml(ledenUploadPacket.getFileLocation());
                    ledenUploadPacket.setResolveResultInfo("解析成功");
                    updateUploadPacket(ledenUploadPacket);

                    //删除数据库中该编号该节点的采集过程
                    ledenCollectProcessMapper.deleteProcessByPersonCodeAndNodeCode(ledenUploadPacket.getPersonCode(),"000000000007");
                    //添加采集过程
                    LedenCollectProcess ledenCollectProcess = getLedenCollectProcess(ledenUploadPacket, 1, "000000000007");
                    ledenCollectProcessMapper.insertSelective(ledenCollectProcess);
                }catch (AuthenticationException e){
                    e.printStackTrace();
                    ledenUploadPacket.setResolveResultInfo(e.getReturnCode().getMsg());
                    updateUploadPacket(ledenUploadPacket);
                }catch (Exception E){
                    E.printStackTrace();
                    ledenUploadPacket.setResolveResultInfo(E.toString());
                    updateUploadPacket(ledenUploadPacket);
                }
                continue;
            }
            if ("000000000009".equals(ledenUploadPacket.getNodeSign())){
                try {
                    Map map = JsoupParseXmlUtils.jsoupPerseXmlIrisInfo(ledenUploadPacket.getFileLocation(),LedenCollectIris.class);
                    Head head = (Head) map.get("head");
                    List data = (List) map.get("data");
                    String ryjcxxcjbh =  (String)map.get("ryjcxxcjbh");
                    //校验head是否有权限保存数据
                    boolean boo2 = ledenCollectIrisService.checkHead(head);
                    if (boo2) {
                        ledenCollectIrisService.saveMapToRepository(data, head.getUserUnitCode(),ryjcxxcjbh);
                        ledenUploadPacket.setResolveResultInfo("解析成功");
                        updateUploadPacket(ledenUploadPacket);

                        //删除数据库中该编号该节点的采集过程
                        ledenCollectProcessMapper.deleteProcessByPersonCodeAndNodeCode(ledenUploadPacket.getPersonCode(),"000000000009");
                        //添加采集过程
                        LedenCollectProcess ledenCollectProcess = getLedenCollectProcess(ledenUploadPacket, 1, "000000000009");
                        ledenCollectProcessMapper.insertSelective(ledenCollectProcess);
                    }
                }catch (CollectionException e){
                    e.printStackTrace();
                    ledenUploadPacket.setResolveResultInfo(e.getResponse().getMsg());
                    updateUploadPacket(ledenUploadPacket);
                }catch (Exception E){
                    E.printStackTrace();
                    ledenUploadPacket.setResolveResultInfo(E.toString());
                    updateUploadPacket(ledenUploadPacket);
                }
                continue;
            }
            if ("000000000010".equals(ledenUploadPacket.getNodeSign())){
                try {
                    ledenCollectGoodsService.inputGoodsByXml(ledenUploadPacket.getFileLocation());
                    ledenUploadPacket.setResolveResultInfo("解析成功");
                    updateUploadPacket(ledenUploadPacket);

                    //删除数据库中该编号该节点的采集过程
                    ledenCollectProcessMapper.deleteProcessByPersonCodeAndNodeCode(ledenUploadPacket.getPersonCode(),"000000000010");
                    //添加采集过程
                    LedenCollectProcess ledenCollectProcess = getLedenCollectProcess(ledenUploadPacket, 1, "000000000010");
                    ledenCollectProcessMapper.insertSelective(ledenCollectProcess);
                }catch (AuthenticationException e){
                    e.printStackTrace();
                    ledenUploadPacket.setResolveResultInfo(e.getReturnCode().getMsg());
                    updateUploadPacket(ledenUploadPacket);
                }catch (Exception E){
                    E.printStackTrace();
                    ledenUploadPacket.setResolveResultInfo(E.toString());
                    updateUploadPacket(ledenUploadPacket);
                }
                continue;
            }
            if ("000000000011".equals(ledenUploadPacket.getNodeSign())){
                try {
                    Map map = xmlParamUtils.parseXmlToMap(ledenUploadPacket.getFileLocation(), LedenCollectDrugtest.class, null);
                    Head head = (Head) map.get("head");
                    List data = (List) map.get("data");
                    boolean boo2 = ledenCollectDrugtestService.checkHead(head);
                    if (boo2){
                        ledenCollectDrugtestService.saveMapToRepository(data, head.getUserUnitCode());
                        ledenUploadPacket.setResolveResultInfo("解析成功");
                        updateUploadPacket(ledenUploadPacket);

                        //删除数据库中该编号该节点的采集过程
                        ledenCollectProcessMapper.deleteProcessByPersonCodeAndNodeCode(ledenUploadPacket.getPersonCode(),"000000000011");
                        //添加采集过程
                        LedenCollectProcess ledenCollectProcess = getLedenCollectProcess(ledenUploadPacket, 1, "000000000011");
                        ledenCollectProcessMapper.insertSelective(ledenCollectProcess);
                    }
                }catch (Exception E){
                    E.printStackTrace();
                    ledenUploadPacket.setResolveResultInfo(E.toString());
                    updateUploadPacket(ledenUploadPacket);
                }
                continue;
            }
            if ("000000000012".equals(ledenUploadPacket.getNodeSign())){

            }
            if ("000000000013".equals(ledenUploadPacket.getNodeSign())){
                try {
                    Map map = JsoupParseXmlUtils.jsoupParseXml(ledenUploadPacket.getFileLocation(), LedenCollectBankcard.class, LedenCollectBRecord.class);
                    Head head = (Head) map.get("head");
                    List data = (List) map.get("data");
                    //校验head是否有权限保存数据
                    boolean boo2 = ledenCollectBankcardService.checkHead(head);
                    if (boo2) {
                        ledenCollectBankcardService.saveMapToRepository(data, head.getUserUnitCode());
                        ledenUploadPacket.setResolveResultInfo("解析成功");
                        updateUploadPacket(ledenUploadPacket);

                        //删除数据库中该编号该节点的采集过程
                        ledenCollectProcessMapper.deleteProcessByPersonCodeAndNodeCode(ledenUploadPacket.getPersonCode(),"000000000013");
                        //添加采集过程
                        LedenCollectProcess ledenCollectProcess = getLedenCollectProcess(ledenUploadPacket, 1, "000000000013");
                        ledenCollectProcessMapper.insertSelective(ledenCollectProcess);
                    }
                }catch (Exception E){
                    E.printStackTrace();
                    ledenUploadPacket.setResolveResultInfo(E.getMessage());
                    updateUploadPacket(ledenUploadPacket);
                }
            }
        }



        //如果解析完成并且上报至省综平台

    }

    private void updateUploadPacket(LedenUploadPacket ledenUploadPacket){
        LedenUploadPacket ledenUploadPacket1 = ledenUploadPacketMapper.selectByPrimaryKey(ledenUploadPacket.getPkId());
        ledenUploadPacket1.setResolveStatus("1");
        ledenUploadPacket1.setResolveDatetime(new Date());
        ledenUploadPacket1.setResolveResultInfo(ledenUploadPacket.getResolveResultInfo());
        ledenUploadPacketMapper.updateByPrimaryKey(ledenUploadPacket1);
    }

    private LedenCollectProcess getLedenCollectProcess(LedenUploadPacket ledenUploadPacket,int stauts,String nodeCode){
        LedenCollectProcess ledenCollectProcess = new LedenCollectProcess();
        ledenCollectProcess.setPkId(UUID.randomUUID().toString().replace("-",""));
        ledenCollectProcess.setRyjcxxcjbh(ledenUploadPacket.getPersonCode());
        ledenCollectProcess.setCollectDate(ledenUploadPacket.getUploadDate());
        ledenCollectProcess.setCollectStatus((short) stauts);
        ledenCollectProcess.setCollectNodeId(nodeCode);
        return ledenCollectProcess;
    }






}
