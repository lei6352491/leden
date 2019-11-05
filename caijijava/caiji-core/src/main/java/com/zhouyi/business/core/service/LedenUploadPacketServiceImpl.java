package com.zhouyi.business.core.service;

import com.zhouyi.business.core.common.ReturnCode;
import com.zhouyi.business.core.dao.LedenUploadPacketMapper;
import com.zhouyi.business.core.exception.ExceptionCast;
import com.zhouyi.business.core.model.*;
import com.zhouyi.business.core.utils.InitializationPageUtils;
import com.zhouyi.business.core.utils.ResponseUtil;
import com.zhouyi.business.core.vo.LedenConllectPersonVo2;
import com.zhouyi.business.core.vo.LedenUploadPacketVo;
import org.apache.commons.collections4.map.LinkedMap;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

@Service
public class LedenUploadPacketServiceImpl
        extends BaseServiceImpl<LedenUploadPacket, LedenUploadPacketVo>
        implements LedenUploadPacketService{

    @Autowired
    private LedenUploadPacketMapper ledenUploadPacketMapper;

    @Override
    public List<LedenUploadPacket> selectDataAnalysisStatus(String ryjcxxcjbh) {
        return ledenUploadPacketMapper.selectDataAnalysisStatus(ryjcxxcjbh);
    }

    /**
     * 获取文件类型列表
     * */
    @Override
    public Response<String> selectFileSuffixList(){
        List<String> strings = ledenUploadPacketMapper.selectFileSuffixList();
        return ResponseUtil.getResponseInfo(ReturnCode.SUCCESS,strings);
    }

    /**
     * 多条件查询数据包列表
     * */
    @Override
    public Response<UploadPacketResult> selectDataList(LedenConllectPersonVo2 ledenConllectPersonVo2) {
        //初始化分页
        new InitializationPageUtils<>().initializationPage(ledenConllectPersonVo2);
        List<UploadPacketResult> list = ledenUploadPacketMapper.selectDataList(ledenConllectPersonVo2);
        //添加zip文件的信息
        Integer total = ledenUploadPacketMapper.selectDataListCount(ledenConllectPersonVo2);
        Map<String,Object> map = new LinkedMap<>();
        map.put("list",list);
        map.put("total",total);
        return ResponseUtil.getResponseInfo(ReturnCode.SUCCESS,map);
    }

    /**
     * 导出数据到Excel
     * */
    @Override
    public HSSFWorkbook selectDataById(RequestList requestList) {
        List<UploadPacketResponse> responses = ledenUploadPacketMapper.selectDataById(requestList.getList());
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
            cell01.setCellValue("主键编号");
            cell01.setCellStyle(headerStyle);

            HSSFCell cell02 = row.createCell(2);
            cell02.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell02.setCellValue("人员编号");
            cell02.setCellStyle(headerStyle);

            HSSFCell cell03 = row.createCell(3);
            cell03.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell03.setCellValue("姓名");
            cell03.setCellStyle(headerStyle);

            HSSFCell cell04 = row.createCell(4);
            cell04.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell04.setCellValue("证件号码");
            cell04.setCellStyle(headerStyle);

            HSSFCell cell05 = row.createCell(5);
            cell05.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell05.setCellValue("身份证号码");
            cell05.setCellStyle(headerStyle);

            HSSFCell cell06 = row.createCell(6);
            cell06.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell06.setCellValue("出生日期");
            cell06.setCellStyle(headerStyle);

            HSSFCell cell07 = row.createCell(7);
            cell07.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell07.setCellValue("采集单位代码");
            cell07.setCellStyle(headerStyle);

            HSSFCell cell08 = row.createCell(8);
            cell08.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell08.setCellValue("采集单位名称");
            cell08.setCellStyle(headerStyle);

            HSSFCell cell09 = row.createCell(9);
            cell09.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell09.setCellValue("案件类别代码");
            cell09.setCellStyle(headerStyle);

            HSSFCell cell10 = row.createCell(10);
            cell10.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell10.setCellValue("设备编号");
            cell10.setCellStyle(headerStyle);

            HSSFCell cell11 = row.createCell(11);
            cell11.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell11.setCellValue("采集节点名称");
            cell11.setCellStyle(headerStyle);

            HSSFCell cell12 = row.createCell(12);
            cell12.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell12.setCellValue("采集人编号");
            cell12.setCellStyle(headerStyle);

            HSSFCell cell13 = row.createCell(13);
            cell13.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell13.setCellValue("采集人姓名");
            cell13.setCellStyle(headerStyle);

            HSSFCell cell14 = row.createCell(14);
            cell14.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell14.setCellValue("采集时间");
            cell14.setCellStyle(headerStyle);

            HSSFCell cell15 = row.createCell(15);
            cell15.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell15.setCellValue("数据文件服务器");
            cell15.setCellStyle(headerStyle);

            HSSFCell cell16 = row.createCell(16);
            cell16.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell16.setCellValue("文件路径");
            cell16.setCellStyle(headerStyle);

            HSSFCell cell17 = row.createCell(17);
            cell17.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell17.setCellValue("文件类型");
            cell17.setCellStyle(headerStyle);

            HSSFCell cell18 = row.createCell(18);
            cell18.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell18.setCellValue("文件大小");
            cell18.setCellStyle(headerStyle);

            HSSFCell cell19 = row.createCell(19);
            cell19.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell19.setCellValue("解析状态");
            cell19.setCellStyle(headerStyle);

            HSSFCell cell20 = row.createCell(20);
            cell20.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell20.setCellValue("解析结果");
            cell20.setCellStyle(headerStyle);

            HSSFCell cell21 = row.createCell(21);
            cell21.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell21.setCellValue("解析时间");
            cell21.setCellStyle(headerStyle);

            for (int rowNo = 1;rowNo <= requestList.getList().size();rowNo++){
                HSSFRow rowHSSF = sheet.createRow(rowNo);
                UploadPacketResponse uploadPacketResponse = responses.get(rowNo - 1);
                for (int cellNo = 0; cellNo <= 21; cellNo++){
                    HSSFCell cell = rowHSSF.createCell(cellNo);
                    switch (cellNo){
                        case 0:
                            cell.setCellValue(rowNo);
                            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                            cell.setCellStyle(bodyStyle);
                            break;
                        case 1:
                            cell.setCellValue(uploadPacketResponse.getPkId());
                            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                            cell.setCellStyle(bodyStyle);
                            break;
                        case 2:
                            cell.setCellValue(uploadPacketResponse.getRyjcxxcjbh());
                            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                            cell.setCellStyle(bodyStyle);
                            break;
                        case 3:
                            cell.setCellValue(uploadPacketResponse.getXm());
                            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                            cell.setCellStyle(bodyStyle);
                            break;
                        case 4:
                            cell.setCellValue(uploadPacketResponse.getZjhm());
                            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                            cell.setCellStyle(bodyStyle);
                            break;
                        case 5:
                            cell.setCellValue(uploadPacketResponse.getGmsfhm());
                            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                            cell.setCellStyle(bodyStyle);
                            break;
                        case 6:
                            if (uploadPacketResponse.getCsrq() != null){
                                cell.setCellValue(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(uploadPacketResponse.getCsrq()));
                                cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                                cell.setCellStyle(bodyStyle);
                            }else {
                                cell.setCellValue("");
                                cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                                cell.setCellStyle(bodyStyle);
                            }
                            break;
                        case 7:
                            cell.setCellValue(uploadPacketResponse.getCjdwdm());
                            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                            cell.setCellStyle(bodyStyle);
                            break;
                        case 8:
                            cell.setCellValue(uploadPacketResponse.getCjdwmc());
                            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                            cell.setCellStyle(bodyStyle);
                            break;
                        case 9:
                            cell.setCellValue(uploadPacketResponse.getAjlbdm());
                            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                            cell.setCellStyle(bodyStyle);
                            break;
                        case 10:
                            cell.setCellValue(uploadPacketResponse.getEquipmentCode());
                            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                            cell.setCellStyle(bodyStyle);
                            break;
                        case 11:
                            cell.setCellValue(uploadPacketResponse.getNodeName());
                            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                            cell.setCellStyle(bodyStyle);
                            break;
                        case 12:
                            cell.setCellValue(uploadPacketResponse.getJzrybh());
                            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                            cell.setCellStyle(bodyStyle);
                            break;
                        case 13:
                            cell.setCellValue(uploadPacketResponse.getCjrxm());
                            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                            cell.setCellStyle(bodyStyle);
                            break;
                        case 14:
                            if (uploadPacketResponse.getCjsj() != null){
                                cell.setCellValue(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(uploadPacketResponse.getCjsj()));
                                cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                                cell.setCellStyle(bodyStyle);
                            }else {
                                cell.setCellValue("");
                                cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                                cell.setCellStyle(bodyStyle);
                            }
                            break;
                        case 15:
                            cell.setCellValue(uploadPacketResponse.getFileServer());
                            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                            cell.setCellStyle(bodyStyle);
                            break;
                        case 16:
                            cell.setCellValue(uploadPacketResponse.getFileLocation());
                            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                            cell.setCellStyle(bodyStyle);
                            break;
                        case 17:
                            cell.setCellValue(uploadPacketResponse.getFileSuffix());
                            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                            cell.setCellStyle(bodyStyle);
                            break;
                        case 18:
                            cell.setCellValue(uploadPacketResponse.getFileSize().toString());
                            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                            cell.setCellStyle(bodyStyle);
                            break;
                        case 19:
                            cell.setCellValue(uploadPacketResponse.getResolveStatus());
                            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                            cell.setCellStyle(bodyStyle);
                            break;
                        case 20:
                            cell.setCellValue(uploadPacketResponse.getResolveResultInfo());
                            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                            cell.setCellStyle(bodyStyle);
                            break;
                        case 21:
                            if (uploadPacketResponse.getResolveDatetime() != null){
                                cell.setCellValue(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(uploadPacketResponse.getResolveDatetime()));
                                cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                                cell.setCellStyle(bodyStyle);
                            }else {
                                cell.setCellValue("");
                                cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                                cell.setCellStyle(bodyStyle);
                            }
                            break;
                    }
                }
            }
            /*FileOutputStream out = new FileOutputStream(requestList.getPath());
            workbook.write(out);
            out.close();*/
            return workbook;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public LedenUploadPacket downloadPacket(String id) {
        return ledenUploadPacketMapper.selectByPrimaryKey(id);
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
