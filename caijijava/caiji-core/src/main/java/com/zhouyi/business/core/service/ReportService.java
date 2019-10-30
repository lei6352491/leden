package com.zhouyi.business.core.service;

import com.zhouyi.business.core.model.ReportDto;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.util.List;
import java.util.Map;

/**
 * @author 李秸康
 * @ClassNmae: ReportService
 * @Description: TODO 接口 报表业务接口
 * @date 2019/9/3 12:19
 * @Version 1.0
 **/
public interface ReportService {

    /**
     * 查询报表信息
     * @param conditions
     * @return
     */
    List<ReportDto> listReportInfos(Map<String,Object> conditions);


    /**
     * 生成Excel文档对象
     * @param conditions
     * @return
     */
    HSSFWorkbook generateWorkBookInfo(Map<String,Object> conditions);
}
