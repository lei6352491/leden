package com.zhouyi.business.controller;

import com.zhouyi.business.core.common.ReturnCode;
import com.zhouyi.business.core.exception.ExceptionCast;
import com.zhouyi.business.core.model.*;
import com.zhouyi.business.core.service.AdvanceService;
import com.zhouyi.business.core.utils.MapUtils;
import com.zhouyi.business.core.utils.ResponseUtil;
import com.zhouyi.business.dto.AdvanceSearchDto;
import io.swagger.annotations.Api;
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
import java.util.List;
import java.util.Map;

/**
 * @author 李秸康
 * @ClassNmae: AdvanceSearchController
 * @Description: TODO
 * @date 2019/7/24 17:19
 * @Version 1.0
 **/
@RestController
@RequestMapping(value = "/api/advance")
@Api(description = "高级查询接口")
public class AdvanceSearchController {

    @Autowired
    private AdvanceService advanceService;

    /*@RequestMapping(value = "/search",method = RequestMethod.POST)
    @ApiOperation(value = "高级查询")*/
    public Response<LedenAdvancedQuery> query(@RequestBody AdvanceSearchDto advanceSearchDto){
        //封装数据
        Map<String,Object> conditionMap= MapUtils.objectTransferToMap(advanceSearchDto);
        Map map = advanceService.advanceQuery(conditionMap);
        return ResponseUtil.getResponseInfo(ReturnCode.SUCCESS,map);
    }

    @RequestMapping(value = "/search",method = RequestMethod.POST)
    @ApiOperation(value = "高级查询")
    public Response<LedenAdvancedQuery> query2(@RequestBody AdvanceSearchVo advanceSearchVo){
        //封装数据
        return advanceService.selectAllCollect(advanceSearchVo);
    }

    /**
     * 导出数据到Excel
     * */
    @RequestMapping(value = "/exportList")
    public String exportList(RequestList requestList, HttpServletResponse httpServletResponse){
        if (requestList.getList() == null || requestList.getList().size() < 1){
            ExceptionCast.cast(ResponseUtil.getResponseInfo(false));
        }
        ServletOutputStream outputStream = null;
        try {
            HSSFWorkbook workbook = advanceService.selectDataByIdList(requestList);
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

}
