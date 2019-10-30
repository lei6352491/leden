package com.zhouyi.business.core.service;

import com.zhouyi.business.core.dao.ReportMapper;
import com.zhouyi.business.core.model.CollectInfo;
import com.zhouyi.business.core.model.ReportDto;
import com.zhouyi.business.core.model.cmodes.ReportCondition;
import com.zhouyi.business.core.utils.CalendarUtils;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author 李秸康
 * @ClassNmae: ReportServiceImpl
 * @Description: TODO 报表业务实现
 * @date 2019/9/3 12:20
 * @Version 1.0
 **/
@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private ReportMapper reportMapper;

    @Override
    public List<ReportDto> listReportInfos(Map<String, Object> conditions) {
        //查询出所有采集单位和总采集数量
        LinkedList<ReportDto> reportDtos = packagingData(conditions);
        this.computeRate(reportDtos);

        return reportDtos;
    }


    /**
     * 生成HSSFWorkBook对象
     * @param conditions
     * @return
     */
    @Override
    public HSSFWorkbook generateWorkBookInfo(Map<String, Object> conditions) {

        LinkedList<ReportDto> reportDtos=packagingData(conditions);
        //计算比率和总计
        computeRate(reportDtos);
        //生成HSSFWorkBook

        HSSFWorkbook workbook=new HSSFWorkbook();
        HSSFSheet sheet=workbook.createSheet("报表数据");
        HSSFRow rowHeader=sheet.createRow(0); //创建标题行o
        String[] titleColumns=new String[]{
                "地区","采集总数","人员基本信息采集数","人员基本信息采集率","人像采集数","人像采集率","指纹采集数","指纹采集率",
                "体貌特征采集数","体貌特征采集率","DNA采集数","DNA采集率","足迹采集数","足迹采集率",
                "笔记采集数","笔记采集率","声纹采集数","声纹采集率","虹膜采集数","虹膜采集率","随身物品采集数","随身物品采集率",
                "吸毒检测采集数","吸毒检测采集率","手机信息采集数","手机信息采集率","银行卡信息采集数","银行卡信息采集率"};
        //封装标题
        packaingRowHeader(rowHeader,titleColumns);
        //封装数据包
        packagingData(sheet,reportDtos);

        //插入数据
        return workbook;
    }


    /**
     * 打包数据对象
     * @param conditions
     * @return
     */
    public LinkedList<ReportDto> packagingData(Map<String,Object> conditions){
        LinkedList<ReportDto> reportDtos= reportMapper.listReportInfoByConditions(conditions);
        //查询出上级部门的采集信息并添加到首条数据
        conditions.put("upper",conditions.get("unitCode")) ;
        reportDtos.addFirst(reportMapper.listReportInfoByConditions(conditions).get(0));
        return reportDtos;
    }


    //封装标题
    private void packaingRowHeader(HSSFRow rowHeader,String[] titleColumns){
       for(int i=0;i<titleColumns.length;i++){
           HSSFCell cell = rowHeader.createCell(i);
           cell.setCellValue(titleColumns[i]);
       }

    }
    //封装数据
    private void packagingData(HSSFSheet sheet,List<ReportDto> data){
        for(int i=1;i<data.size();i++){
            //创建一行
            HSSFRow eachRow=sheet.createRow(i);
            ReportDto reportDto=data.get(i-1); //每次-1，数据从0行开始
            for(int j=0;j<3;j++){
                HSSFCell cellFirst = eachRow.createCell(0);
                cellFirst.setCellValue(reportDto.getUnitName());
                HSSFCell cellSecond = eachRow.createCell(1);
                cellSecond.setCellValue(reportDto.getCollectSum()==null?0:reportDto.getCollectSum());
                for (int l=1;l<reportDto.getCollectInfos().size()+1;l++){
                    //循环遍历集合数据封装
                    CollectInfo collectInfo=reportDto.getCollectInfos().get(l-1);
                    HSSFCell cellOne=eachRow.createCell(l*2);
                    cellOne.setCellValue(collectInfo.getCollectNum()==null?0:collectInfo.getCollectNum());
                    HSSFCell cellTwo=eachRow.createCell(l*2+1);
                    cellTwo.setCellValue(collectInfo.getCollectRate());
                }
            }

        }
    }


    private void computeRate(List<ReportDto> reportDtos){
        reportDtos.stream().forEach(x -> {
            if (x.getCollectSum() == null)
                x.setCollectSum(0);
            x.getCollectInfos().forEach(y -> {
                if (y.getCollectNum() == null)
                    y.setCollectNum(0);
                if (x.getCollectSum() != 0)
                    y.setCollectRate(Double.toString(new BigDecimal((float) y.getCollectNum() / x.getCollectSum()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue()));
                else
                    y.setCollectRate("0.0");
            });

        });
        Optional<ReportDto> reduce = reportDtos.stream().reduce((x, y) -> {
            ReportDto reportDto = new ReportDto();//构建报表数据
            List<CollectInfo> collectInfos = new ArrayList<>(); //构建报表节点数据
            int count = x.getCollectSum() + y.getCollectSum();
            for (int i = 0; i < x.getCollectInfos().size(); i++) {
                int collectCount = x.getCollectInfos().get(i).getCollectNum() + y.getCollectInfos().get(i).getCollectNum();
                CollectInfo collectInfo = new CollectInfo(collectCount);
                collectInfos.add(collectInfo);
                collectInfo.setNodeName(x.getCollectInfos().get(i).getNodeName());
                if(collectCount!=0)
                    collectInfo.setCollectRate(Double.toString(new BigDecimal((float)collectCount/count).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue()));
                else
                    collectInfo.setCollectRate("0.0");
            }
            reportDto.setCollectInfos(collectInfos);
            reportDto.setCollectSum(count);
            reportDto.setUnitName("总计");
            return reportDto;
        });
        //将对象添加到数组末尾
        reportDtos.add(reduce.get());
    }
}
