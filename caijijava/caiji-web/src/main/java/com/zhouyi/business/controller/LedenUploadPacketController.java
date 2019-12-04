package com.zhouyi.business.controller;

import com.zhouyi.business.core.common.ReturnCode;
import com.zhouyi.business.core.exception.ExceptionCast;
import com.zhouyi.business.core.model.*;
import com.zhouyi.business.core.service.LedenUploadPacketService;
import com.zhouyi.business.core.utils.ResponseUtil;
import com.zhouyi.business.core.vo.LedenConllectPersonVo2;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.net.www.protocol.ftp.FtpURLConnection;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.util.Date;

/**
 * @author 杜承旭
 * @ClassNmae: LedenUploadPacketController
 * @Description: TODO
 * @date 2019/8/16 10:43
 * @Version 1.0
 **/
@RestController
@RequestMapping(value = "/api/uploadPacket")
public class LedenUploadPacketController {

    @Autowired
    private LedenUploadPacketService ledenUploadPacketService;

    @RequestMapping(value = "/findOne/{id}")
    public Response findOne(@PathVariable(value = "id")String id){
        return ledenUploadPacketService.findOne(id);
    }

    @RequestMapping(value = "/selectList")
    public Response<UploadPacketResult> selectDataList(@RequestBody LedenConllectPersonVo2 ledenConllectPersonVo2){
        return ledenUploadPacketService.selectDataList(ledenConllectPersonVo2);
    }

    @RequestMapping(value = "/fileSuffixList")
    public Response<String> selectFileSuffixList(){
        return ledenUploadPacketService.selectFileSuffixList();
    }

    @RequestMapping(value = "/dataTypeList")
    public Response<String> selectDataTypeList(){
        return ledenUploadPacketService.selectDataTypeList();
    }

    @RequestMapping(value = "/exportList")
    public String exportList(RequestList requestList,HttpServletResponse httpServletResponse){
        ServletOutputStream outputStream = null;
        try {
            HSSFWorkbook workbook = ledenUploadPacketService.selectDataById(requestList);
            OutputStream output = httpServletResponse.getOutputStream();
            httpServletResponse.reset();
            httpServletResponse.setHeader("Content-Type","application/octet-stream");
            httpServletResponse.setHeader("Content-Disposition","attachment;filename=details.xls");
            workbook.write(output);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (outputStream != null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    @RequestMapping(value = "/downloadPacketList")
    public Response downloadPacketList(@RequestBody UploadParam uploadParam,HttpServletResponse response){
        if (uploadParam.getList() == null || uploadParam.getList().size() < 1)
            return ResponseUtil.returnError(ReturnCode.ERROR_06);
        InputStream inputStream = ledenUploadPacketService.downloadPacketList(uploadParam.getList());
        if (inputStream == null){
            return ResponseUtil.returnError(ReturnCode.ERROR_1130);
        }
        ServletOutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            response.reset();
            response.setHeader("Content-Type","application/octet-stream");
            Date date = new Date();
            long time = date.getTime();
            response.setHeader("Content-Disposition","attachment;filename=personData-" + time + ".zip");

            byte[] bytes = new byte[2048];
            int count = 0;
            while (-1 != (count = inputStream.read(bytes))){
                outputStream.write(bytes,0,count);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseUtil.returnError(ReturnCode.ERROR_1130);
        }finally {
            if (outputStream != null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return ResponseUtil.getResponseInfo(true);
    }

    @RequestMapping(value = "/downloadPacket/{id}")
    public String downloadPacket(@PathVariable String id,HttpServletResponse response) throws IOException {
        LedenUploadPacket ledenUploadPacket = ledenUploadPacketService.downloadPacket(id);
        //判断该行数据中是否存在文件路径信息
        if (StringUtils.isEmpty(ledenUploadPacket.getFileLocation()))
            ExceptionCast.cast(ResponseUtil.returnError(ReturnCode.ERROR_1028));
        //获取文件对象
        //File file = new File(ledenUploadPacket.getFileLocation());
        URL url = new URL(ledenUploadPacket.getFileLocation());
        FtpURLConnection ftpURLConnection = new FtpURLConnection(url);
        ftpURLConnection.connect();
        InputStream inputStream = ftpURLConnection.getInputStream();
        BufferedInputStream bufferedInputStream = null;
        try {
            bufferedInputStream = new BufferedInputStream(inputStream);

            ServletOutputStream outputStream = response.getOutputStream();
            response.reset();
            response.setHeader("Content-Type","application/octet-stream");
            response.setHeader("Content-Disposition","attachment;filename="+ledenUploadPacket.getRyjcxxcjbh()+"-"+System.currentTimeMillis() +"."+ ledenUploadPacket.getFileSuffix());
            byte[] buffer=new byte[2048];
            int len=0;
            while((len=bufferedInputStream.read(buffer))!=-1){
                outputStream.write(buffer,0,len);
            }
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            ExceptionCast.cast(ResponseUtil.returnError(ReturnCode.ERROR_1028));
        }finally {
            if (bufferedInputStream != null) {
                try {
                    bufferedInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

}
