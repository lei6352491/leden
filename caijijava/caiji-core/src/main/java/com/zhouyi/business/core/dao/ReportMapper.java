package com.zhouyi.business.core.dao;

import com.zhouyi.business.core.model.ReportDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author 李秸康
 * @ClassNmae: ReportMapper
 * @Description: TODO 报表
 * @date 2019/9/3 10:46
 * @Version 1.0
 **/
@Mapper
public interface ReportMapper {

    /**
     * 根据条件查询报表信息
     * @param conditions
     * @return
     */
    LinkedList<ReportDto> listReportInfoByConditions(Map<String,Object> conditions);


}
