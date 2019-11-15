package com.zhouyi.business.core.service;

import com.zhouyi.business.core.common.ReturnCode;
import com.zhouyi.business.core.dao.LedenAdvancedQueryMapper;
import com.zhouyi.business.core.exception.ExceptionCast;
import com.zhouyi.business.core.model.*;
import com.zhouyi.business.core.utils.DateUtil;
import com.zhouyi.business.core.utils.ResponseUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 李秸康
 * @ClassNmae: AdvanceServiceImpl
 * @Description: TODO 高级查询接口实例
 * @date 2019/7/25 15:44
 * @Version 1.0
 **/
@Service
public class AdvanceServiceImpl implements AdvanceService{

    @Autowired
    private LedenAdvancedQueryMapper ledenAdvancedQueryMapper;

    /**
     * 高级查询
     * @param map
     * @return
     */
    @Override
    public Map advanceQuery(Map<String, Object> map) {
        Map<String,Object> resultMap = new HashMap<>();
        List<LedenAdvancedQuery> list = ledenAdvancedQueryMapper.ledenAdvancedQueryUnionVersion(map);
        int count = ledenAdvancedQueryMapper.getDataCountByConditions(map);
        resultMap.put("total",count);
        resultMap.put("list",list);
        return resultMap;
    }

    @Override
    public Response selectAllCollect(AdvanceSearchVo advanceSearchVo) {
        try {
            //初始化分页参数
            initializationPage(advanceSearchVo);
            //判断参数中是否存在采集年龄段参数，若有把时间段参数的数据类型改为时间类型存储
            if (StringUtils.isNotEmpty(advanceSearchVo.getMinAge()) && StringUtils.isNotEmpty(advanceSearchVo.getMaxAge())){
                if (Integer.parseInt(advanceSearchVo.getMinAge()) <=  Integer.parseInt(advanceSearchVo.getMaxAge())){
                    int startAge = Integer.parseInt(advanceSearchVo.getMinAge());
                    advanceSearchVo.setStopDate(DateUtils.addYears(new Date(),0 - startAge));
                    int endAge = Integer.parseInt(advanceSearchVo.getMaxAge());
                    advanceSearchVo.setStartDate(DateUtils.addYears(new Date(), 0 - endAge));
                }
                if (Integer.parseInt(advanceSearchVo.getMinAge()) >  Integer.parseInt(advanceSearchVo.getMaxAge())){
                    int startAge = Integer.parseInt(advanceSearchVo.getMinAge());
                    advanceSearchVo.setStartDate(DateUtils.addYears(new Date(),0 - startAge));
                    int endAge = Integer.parseInt(advanceSearchVo.getMaxAge());
                    advanceSearchVo.setStopDate(DateUtils.addYears(new Date(), 0 - endAge));
                }
            }
            if (StringUtils.isNotEmpty(advanceSearchVo.getMinAge()) && StringUtils.isEmpty(advanceSearchVo.getMaxAge())){
                int startAge = Integer.parseInt(advanceSearchVo.getMinAge());
                advanceSearchVo.setStopDate(DateUtils.addYears(new Date(),0 - startAge));
                advanceSearchVo.setStartDate(DateUtils.addYears(new Date(0),-70));
            }
            if (StringUtils.isNotEmpty(advanceSearchVo.getMaxAge()) && StringUtils.isEmpty(advanceSearchVo.getMinAge())){
                int startAge = Integer.parseInt(advanceSearchVo.getMaxAge());
                advanceSearchVo.setStartDate(DateUtils.addYears(new Date(),0 - startAge));
                advanceSearchVo.setStopDate(new Date());
            }

            List<AdvancedQueryResult> list = ledenAdvancedQueryMapper.selectAllCollect(advanceSearchVo);
            int total = ledenAdvancedQueryMapper.selectAllCollectCount(advanceSearchVo);
            Map<String,Object> map = new HashMap<>();
            map.put("total",total);
            map.put("list",list);
            return ResponseUtil.getResponseInfo(ReturnCode.SUCCESS,map);
        }catch (Exception e){
            e.printStackTrace();
            ExceptionCast.cast(ResponseUtil.returnError(ReturnCode.ERROR_01));
        }
        return null;
    }

    /**
     * 高级查询数据的导出
     * */
    @Override
    public HSSFWorkbook selectDataByIdList(RequestList requestList) {
        List<PersonResult> list = ledenAdvancedQueryMapper.selectDataByIdList(requestList.getList());
        try{
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("数据包列表");
            HSSFCellStyle headerStyle = getStyleHeader(workbook);
            HSSFCellStyle bodyStyle = getStyleHeader(workbook);
            HSSFRow row = sheet.createRow(0);

            HSSFCell cell00 = row.createCell(0);
            cell00.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell00.setCellValue("序号");
            cell00.setCellStyle(headerStyle);

            HSSFCell cell01 = row.createCell(1);
            cell01.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell01.setCellValue("采集信息原因");
            cell01.setCellStyle(headerStyle);

            HSSFCell cell02 = row.createCell(2);
            cell02.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell02.setCellValue("所属采集类别");
            cell02.setCellStyle(headerStyle);

            HSSFCell cell03 = row.createCell(3);
            cell03.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell03.setCellValue("人员编号");
            cell03.setCellStyle(headerStyle);

            HSSFCell cell04 = row.createCell(4);
            cell04.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell04.setCellValue("姓名");
            cell04.setCellStyle(headerStyle);

            HSSFCell cell05 = row.createCell(5);
            cell05.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell05.setCellValue("姓名汉语拼音");
            cell05.setCellStyle(headerStyle);

            HSSFCell cell06 = row.createCell(6);
            cell06.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell06.setCellValue("曾用名");
            cell06.setCellStyle(headerStyle);

            HSSFCell cell07 = row.createCell(7);
            cell07.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell07.setCellValue("外文姓名");
            cell07.setCellStyle(headerStyle);

            HSSFCell cell08 = row.createCell(8);
            cell08.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell08.setCellValue("别号绰号");
            cell08.setCellStyle(headerStyle);

            HSSFCell cell09 = row.createCell(9);
            cell09.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell09.setCellValue("公民身份号码");
            cell09.setCellStyle(headerStyle);

            HSSFCell cell10 = row.createCell(10);
            cell10.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell10.setCellValue("常用证件");
            cell10.setCellStyle(headerStyle);

            HSSFCell cell11 = row.createCell(11);
            cell11.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell11.setCellValue("证件号码");
            cell11.setCellStyle(headerStyle);

            HSSFCell cell12 = row.createCell(12);
            cell12.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell12.setCellValue("出生日期");
            cell12.setCellStyle(headerStyle);

            HSSFCell cell13 = row.createCell(13);
            cell13.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell13.setCellValue("国籍");
            cell13.setCellStyle(headerStyle);

            HSSFCell cell14 = row.createCell(14);
            cell14.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell14.setCellValue("民族");
            cell14.setCellStyle(headerStyle);

            HSSFCell cell15 = row.createCell(15);
            cell15.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell15.setCellValue("籍贯省市县");
            cell15.setCellStyle(headerStyle);

            HSSFCell cell16 = row.createCell(16);
            cell16.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell16.setCellValue("出生地省市县");
            cell16.setCellStyle(headerStyle);

            HSSFCell cell17 = row.createCell(17);
            cell17.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell17.setCellValue("出生地详址");
            cell17.setCellStyle(headerStyle);

            HSSFCell cell18 = row.createCell(18);
            cell18.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell18.setCellValue("户籍地省市县");
            cell18.setCellStyle(headerStyle);

            HSSFCell cell19 = row.createCell(19);
            cell19.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell19.setCellValue("政治面貌");
            cell19.setCellStyle(headerStyle);

            HSSFCell cell20 = row.createCell(20);
            cell20.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell20.setCellValue("学历");
            cell20.setCellStyle(headerStyle);

            HSSFCell cell21 = row.createCell(21);
            cell21.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell21.setCellValue("现住地详址");
            cell21.setCellStyle(headerStyle);

            HSSFCell cell22 = row.createCell(22);
            cell22.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell22.setCellValue("特殊身份");
            cell22.setCellStyle(headerStyle);

            HSSFCell cell23 = row.createCell(23);
            cell23.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell23.setCellValue("警综人员编号");
            cell23.setCellStyle(headerStyle);

            HSSFCell cell24 = row.createCell(24);
            cell24.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell24.setCellValue("采集人姓名");
            cell24.setCellStyle(headerStyle);

            HSSFCell cell25 = row.createCell(25);
            cell25.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell25.setCellValue("采集单位名称");
            cell25.setCellStyle(headerStyle);

            HSSFCell cell26 = row.createCell(26);
            cell26.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell26.setCellValue("当前状态");
            cell26.setCellStyle(headerStyle);

            HSSFCell cell27 = row.createCell(27);
            cell27.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell27.setCellValue("采集时间");
            cell27.setCellStyle(headerStyle);

            HSSFCell cell28 = row.createCell(28);
            cell28.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell28.setCellValue("采集进度");
            cell28.setCellStyle(headerStyle);

            HSSFCell cell29 = row.createCell(29);
            cell29.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell29.setCellValue("设备编号");
            cell29.setCellStyle(headerStyle);

            HSSFCell cell30 = row.createCell(30);
            cell30.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell30.setCellValue("人员类别");
            cell30.setCellStyle(headerStyle);

            for (int rowNo = 1; rowNo <= list.size(); rowNo ++){
                HSSFRow rowCode = sheet.createRow(rowNo);
                PersonResult personResult = list.get(rowNo - 1);
                for (int cellNo = 0; cellNo <= 30; cellNo ++){
                    HSSFCell cell = rowCode.createCell(cellNo);
                    switch(cellNo){
                        case 0:
                            cell.setCellValue(rowNo);
                            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                            cell.setCellStyle(bodyStyle);
                            break;
                        case 1:
                            cell.setCellValue(personResult.getCjxxyymc());
                            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                            cell.setCellStyle(bodyStyle);
                            break;
                        case 2:
                            cell.setCellValue(personResult.getCjlbmc());
                            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                            cell.setCellStyle(bodyStyle);
                            break;
                        case 3:
                            cell.setCellValue(personResult.getRyjcxxcjbh());
                            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                            cell.setCellStyle(bodyStyle);
                            break;
                        case 4:
                            cell.setCellValue(personResult.getXm());
                            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                            cell.setCellStyle(bodyStyle);
                            break;
                        case 5:
                            cell.setCellValue(personResult.getXmhypy());
                            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                            cell.setCellStyle(bodyStyle);
                            break;
                        case 6:
                            cell.setCellValue(personResult.getCym());
                            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                            cell.setCellStyle(bodyStyle);
                            break;
                        case 7:
                            cell.setCellValue(personResult.getWwxm());
                            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                            cell.setCellStyle(bodyStyle);
                            break;
                        case 8:
                            cell.setCellValue(personResult.getBmch());
                            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                            cell.setCellStyle(bodyStyle);
                            break;
                        case 9:
                            cell.setCellValue(personResult.getGmsfhm());
                            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                            cell.setCellStyle(bodyStyle);
                            break;
                        case 10:
                            cell.setCellValue(personResult.getCyzjmc());
                            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                            cell.setCellStyle(bodyStyle);
                            break;
                        case 11:
                            cell.setCellValue(personResult.getZjhm());
                            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                            cell.setCellStyle(bodyStyle);
                            break;
                        case 12:
                            if (personResult.getCsrq() != null){
                                cell.setCellValue(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(personResult.getCsrq()));
                                cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                                cell.setCellStyle(bodyStyle);
                            }else {
                                cell.setCellValue("");
                                cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                                cell.setCellStyle(bodyStyle);
                            }
                            break;
                        case 13:
                            cell.setCellValue(personResult.getGjmc());
                            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                            cell.setCellStyle(bodyStyle);
                            break;
                        case 14:
                            cell.setCellValue(personResult.getMzmc());
                            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                            cell.setCellStyle(bodyStyle);
                            break;
                        case 15:
                            cell.setCellValue(personResult.getJgssxmc());
                            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                            cell.setCellStyle(bodyStyle);
                            break;
                        case 16:
                            cell.setCellValue(personResult.getCsdssxmc());
                            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                            cell.setCellStyle(bodyStyle);
                            break;
                        case 17:
                            cell.setCellValue(personResult.getCsdxz());
                            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                            cell.setCellStyle(bodyStyle);
                            break;
                        case 18:
                            cell.setCellValue(personResult.getHjdssxmc());
                            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                            cell.setCellStyle(bodyStyle);
                            break;
                        case 19:
                            cell.setCellValue(personResult.getZzmmmc());
                            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                            cell.setCellStyle(bodyStyle);
                            break;
                        case 20:
                            cell.setCellValue(personResult.getXlmc());
                            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                            cell.setCellStyle(bodyStyle);
                            break;
                        case 21:
                            cell.setCellValue(personResult.getXzdxz());
                            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                            cell.setCellStyle(bodyStyle);
                            break;
                        case 22:
                            cell.setCellValue(personResult.getTssfmc());
                            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                            cell.setCellStyle(bodyStyle);
                            break;
                        case 23:
                            cell.setCellValue(personResult.getJzrybh());
                            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                            cell.setCellStyle(bodyStyle);
                            break;
                        case 24:
                            cell.setCellValue(personResult.getCjrxm());
                            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                            cell.setCellStyle(bodyStyle);
                            break;
                        case 25:
                            cell.setCellValue(personResult.getCjdwmc());
                            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                            cell.setCellStyle(bodyStyle);
                            break;
                        case 26:
                            if ("00".equals(personResult.getStatus())){
                                cell.setCellValue("待采集");
                                cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                                cell.setCellStyle(bodyStyle);
                                break;
                            }else if ("01".equals(personResult.getStatus())){
                                cell.setCellValue("采集中");
                                cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                                cell.setCellStyle(bodyStyle);
                                break;
                            }else if ("02".equals(personResult.getStatus())){
                                cell.setCellValue("采集完成");
                                cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                                cell.setCellStyle(bodyStyle);
                                break;
                            }else {
                                cell.setCellValue(personResult.getStatus());
                                cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                                cell.setCellStyle(bodyStyle);
                                break;
                            }
                        case 27:
                            if (personResult.getCjsj() != null){
                                cell.setCellValue(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(personResult.getCjsj()));
                                cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                                cell.setCellStyle(bodyStyle);
                            }else {
                                cell.setCellValue("");
                                cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                                cell.setCellStyle(bodyStyle);
                            }
                            break;
                        case 28:
                            cell.setCellValue(personResult.getSchedule());
                            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                            cell.setCellStyle(bodyStyle);
                            break;
                        case 29:
                            cell.setCellValue(personResult.getEquipmentCode());
                            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                            cell.setCellStyle(bodyStyle);
                            break;
                        case 30:
                            cell.setCellValue(personResult.getRylbmc());
                            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                            cell.setCellStyle(bodyStyle);
                            break;
                    }
                }
            }
            return workbook;
        }catch (Exception e){
            e.printStackTrace();
            ExceptionCast.cast(ResponseUtil.returnError(ReturnCode.ERROR_500));
        }
        return null;
    }

    private AdvanceSearchVo initializationPage(AdvanceSearchVo advanceSearchVo){
        if (advanceSearchVo == null){
            advanceSearchVo = new AdvanceSearchVo();
        }
        if(advanceSearchVo.getPNo() < 1 ){
            advanceSearchVo.setPNo(1);
        }
        if (advanceSearchVo.getPSize() < 1){
            advanceSearchVo.setPSize(10);
        }
        return advanceSearchVo;
    }

    private static HSSFCellStyle getStyleHeader(HSSFWorkbook workbook) {
        HSSFFont font = workbook.createFont();
        font.setFontHeightInPoints((short) 12);//设置字体大小
        //font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//字体加粗
        font.setFontName("宋体");//设置字体名字
        HSSFCellStyle style = workbook.createCellStyle();//设置样式
        style.setAlignment(HorizontalAlignment.CENTER);//设置颜色
        style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);//前景颜色
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);//填充方式，前色填充
        //边框填充
        style.setBorderTop(BorderStyle.THIN);//上边框
        style.setTopBorderColor(HSSFColor.BLACK.index);//上边框颜色
        style.setBorderRight(BorderStyle.THIN);//右边框
        style.setRightBorderColor(HSSFColor.BLACK.index);//右边框颜色
        style.setBorderBottom(BorderStyle.THIN); //下边框
        style.setBottomBorderColor(HSSFColor.BLACK.index); //下边框颜色
        style.setBorderLeft(BorderStyle.THIN);//左边框
        style.setLeftBorderColor(HSSFColor.BLACK.index); //左边框颜色
        style.setFont(font);//设置的字体
        style.setWrapText(true);//设置自动换行
        style.setAlignment(HorizontalAlignment.LEFT);//设置水平对齐的样式为居中对齐
        style.setVerticalAlignment(VerticalAlignment.CENTER);//设置垂直对齐的样式为居中对齐
        return style;
    }

}
