package com.zhouyi.business.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zhouyi.business.config.ProvinceFtpUtil;
import com.zhouyi.business.core.common.ReturnCode;
import com.zhouyi.business.core.model.LedenEquipment;
import com.zhouyi.business.core.model.PageData;
import com.zhouyi.business.core.model.Response;
import com.zhouyi.business.core.service.LedenEquipmentService;
import com.zhouyi.business.core.utils.HttpUtil;
import com.zhouyi.business.core.utils.MapUtils;
import com.zhouyi.business.core.utils.ResponseUtil;
import com.zhouyi.business.core.vo.LedenEquipmentVo;
import com.zhouyi.business.core.vo.LedenEquipmentVo2;
import com.zhouyi.business.core.vo.ResponseVo;
import com.zhouyi.business.dto.EquipmentListDto;
import com.zhouyi.business.dto.LedenEquipmentDto;
import com.zhouyi.business.core.model.provincecomprehensive.ResponseData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import okhttp3.HttpUrl;
import org.apache.commons.httpclient.HttpURL;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.methods.HttpUriRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author 李秸康
 * @ClassNmae: LedenEquipmentController
 * @Description: TODO
 * @date 2019/6/25 10:36
 * @Version 1.0
 **/
@RestController
@RequestMapping(value="/api/equipment")
@Api(description = "接入管理（设备管理）")
public class LedenEquipmentController {


    private static final Logger logger = LoggerFactory.getLogger(LedenEquipmentController.class);

    @Autowired
    private LedenEquipmentService ledenEquipmentService;

    @Value("${provinceComprehensive.ip}")
    private String provinceIp;
    @Value("${provinceComprehensive.port}")
    private String provincePort;
    @Value("${provinceComprehensive.interfaces.registry}")
    private String registryInterface;

    @Value("${temp.unitCode}")
    private String tempUnitCode;

    @Autowired
    private ProvinceFtpUtil provinceFtpUtil;
    /**
     * 分页获取设备信息
     * @param ledenEquipmentDto
     * @return
     */
    @RequestMapping(value="/listEquipment",method= RequestMethod.POST)
    @ApiOperation(value = "列表查询接入信息")
    public Response<PageData<LedenEquipment>> listEquipments(@RequestBody EquipmentListDto ledenEquipmentDto){
        Map<String,Object> conditions= MapUtils.objectTransferToMap(ledenEquipmentDto);
        PageData<LedenEquipment> pageData=ledenEquipmentService.getLedenEquipmentPage(conditions);
        return ResponseUtil.getResponseInfo(ReturnCode.SUCCESS,pageData);
    }


    /**
     * 更新设备信息
     * @param ledenEquipmentDto
     * @return
     */

    @RequestMapping(value="/updateEquipment",method=RequestMethod.PUT)
    @ApiOperation(value = "更新接入信息")
    public Response<Object> modifyEquipments(@RequestBody LedenEquipmentDto ledenEquipmentDto){
        LedenEquipment ledenEquipment=new LedenEquipment();
        BeanUtils.copyProperties(ledenEquipmentDto,ledenEquipment);
        Boolean result = ledenEquipmentService.updateLedenEquipment(ledenEquipment);
        return ResponseUtil.getResponseInfo(result);
    }


    /**
     * 添加设备信息
     * @param ledenEquipmentDto
     * @return
     */
    @RequestMapping(value="/insertEquipment",method=RequestMethod.POST)
    @ApiOperation(value = "添加接入信息")
    public Response<Object> addEquipments(@RequestBody LedenEquipmentDto ledenEquipmentDto){
        LedenEquipment ledenEquipment=transferObject(ledenEquipmentDto);
        String equipmentCode = ledenEquipmentService.addLedenEquipment(ledenEquipment);
        if (StringUtils.isEmpty(equipmentCode)){
            return ResponseUtil.getResponseInfo(false);
        }else {
            return ResponseUtil.getResponseInfo(true);
        }
    }


    /**
     * 删除设备信息
     * @param id
     * @return
     */
    @RequestMapping(value="/delEquipment/{id}",method=RequestMethod.DELETE)
    @ApiOperation(value = "删除接入信息")
    public Response<Object> delEquipment(@PathVariable Integer id){
        Boolean result=ledenEquipmentService.removeLedenEquipmentById(id);
        return ResponseUtil.getResponseInfo(result);
    }


    /**
     * 根据id获取设备信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/getEquipment/{id}",method=RequestMethod.GET)
    @ApiOperation(value = "根据id获取接入信息")
    public Response<LedenEquipment> getEquipmentById(@PathVariable Integer id){
        LedenEquipment ledenEquipment=ledenEquipmentService.getLedenEquipmentById(id);
        return ResponseUtil.getResponseInfo(ReturnCode.SUCCESS,ledenEquipment);
    }


    /**
     * 对象转换方法
     * @param ledenEquipmentDto
     * @return
     */
    private LedenEquipment transferObject(LedenEquipmentDto ledenEquipmentDto){
        LedenEquipment ledenEquipment=new LedenEquipment();
        BeanUtils.copyProperties(ledenEquipmentDto,ledenEquipment);
        return ledenEquipment;
    }


    /**
     * 注册设备接口
     * @param ledenEquipmentVo
     * @return
     */
    @ApiOperation(value = "接入注册")
    @RequestMapping(value = "/applyRegisterClient", method = RequestMethod.POST)
    public Response<String> collectNodeRegister(@RequestBody LedenEquipmentVo ledenEquipmentVo) throws Exception {
        ledenEquipmentVo.setPkId(UUID.randomUUID().toString().substring(0, 32));
        //向省厅发起注册
        String provinceNumber = postRegisterClient(ledenEquipmentVo.getUnitCode(), ledenEquipmentVo.getEquipmentIp(), ledenEquipmentVo.getEquipmentMac());
        if(provinceNumber!=null&&!"".equals(provinceNumber)){
            ledenEquipmentVo.setProvincialEquipmentCode(provinceNumber);
            String equipmentCode = ledenEquipmentService.collectNodeRegister(ledenEquipmentVo);
            return ResponseUtil.getResponseInfo(ReturnCode.SUCCESS, equipmentCode);
        }else{
            return ResponseUtil.ntrError("向省厅获取的编号为空");
        }

    }

    /**
     * 查询设备接入注册信息列表
     * */
    @ApiOperation(value = "查询接入注册列表")
    @RequestMapping(value="/selectEquipmentList",method=RequestMethod.POST)
    public Response selectEquipmentListByData(@RequestBody LedenEquipmentVo2 ledenEquipmentVo2) {
        return ledenEquipmentService.selectEquipmentListByData(ledenEquipmentVo2);
    }



    /**
     * 向省厅注册方法
     * @param unitCode
     * @param ip
     * @param mac
     */
    private String postRegisterClient(String unitCode, String ip, String mac) throws Exception {
        StringBuffer url = new StringBuffer("http://");
        url.append(provinceIp);
        url.append(":");
        url.append(provincePort);
        url.append(registryInterface);

        Map<String, String> params = new HashMap<>(3);
        params.put("unitCode", tempUnitCode);
        params.put("ip", ip);
        params.put("mac", mac);
        ResponseVo responseVo = HttpUtil.sendPostByform(url.toString(), params);
        if (responseVo.isOk()) {
            //如果服务调用成功则检测数据
            logger.info(responseVo.getData());
            Map result=(Map) JSON.parse(responseVo.getData());
            if ("0".equals(result.get("status"))) {
                //失败
                logger.info("接口调用失败:" + result.get("value"));
                throw new Exception("省厅注册借口调用失败" + result.get("value"));
            } else if ("1".equals(result.get("status"))) {
                //成功
                logger.info("省厅注册成功：设备编号为" +result.get("value"));
                generaterFtpFolder(result.get("value").toString());
                return result.get("value").toString();
            }else{
                throw new Exception("注册失败:"+result.get("value"));
            }
        } else {
            throw new Exception("省厅注册借口调用失败");
        }
    }



    /**
     * 在省综ftp下生成文件夹
     */
    private void generaterFtpFolder(String folderName) throws Exception {
        provinceFtpUtil.connect();
        provinceFtpUtil.createDir(folderName);
        provinceFtpUtil.disconnect();
    }

}
