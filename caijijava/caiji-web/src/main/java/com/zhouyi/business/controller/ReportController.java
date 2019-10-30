package com.zhouyi.business.controller;

import com.zhouyi.business.core.common.ReturnCode;
import com.zhouyi.business.core.model.ReportDto;
import com.zhouyi.business.core.model.Response;
import com.zhouyi.business.core.model.cmodes.ReportCondition;
import com.zhouyi.business.core.service.ReportService;
import com.zhouyi.business.core.utils.MapUtils;
import com.zhouyi.business.core.utils.ResponseUtil;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 李秸康
 * @ClassNmae: ReportController
 * @Description: TODO 报表控制器
 * @date 2019/9/3 15:14
 * @Version 1.0
 **/
@RestController
@RequestMapping(value = "/api/report")
public class ReportController {

    @Autowired
    private ReportService reportService;

    /**
     * 导出报表数据
     * @param reportCondition
     * @return
     */
    @ApiOperation(value = "查询报表")
    @RequestMapping(value = "",method = RequestMethod.POST)
    public Response<List<ReportDto>> report(@RequestBody ReportCondition reportCondition){
       Map<String,Object> conditions= MapUtils.objectTransferToMap(reportCondition);
        List<ReportDto> reportDtos = reportService.listReportInfos(conditions);
        return ResponseUtil.getResponseInfo(ReturnCode.SUCCESS,reportDtos);
    }


    /**
     * 导出报表数据
     * @param reportCondition
     */
    @RequestMapping(value = "/export",method = RequestMethod.POST)
    public String export(@RequestBody ReportCondition reportCondition, HttpServletResponse response){
        Map<String,Object> conditions=MapUtils.objectTransferToMap(reportCondition);
        ServletOutputStream outputStream=null;

        try {
            HSSFWorkbook workbook = reportService.generateWorkBookInfo(conditions);
            outputStream=response.getOutputStream();
            response.reset();
            response.setHeader("Content-Type","application/octet-stream");
            response.setHeader("Content-Disposition","attachment;filename=details.xls");
            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(outputStream!=null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }

        return null;
    }

}
