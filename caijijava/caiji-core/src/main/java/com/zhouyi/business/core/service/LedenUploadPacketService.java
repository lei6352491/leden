package com.zhouyi.business.core.service;

import com.zhouyi.business.core.model.*;
import com.zhouyi.business.core.vo.LedenConllectPersonVo2;
import com.zhouyi.business.core.vo.LedenUploadPacketVo;
import org.apache.ibatis.annotations.Param;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public interface LedenUploadPacketService extends BaseService<LedenUploadPacket, LedenUploadPacketVo> {

    List<LedenUploadPacket> selectDataAnalysisStatus(String ryjcxxcjbh);

    Response<UploadPacketResult> selectDataList(LedenConllectPersonVo2 ledenConllectPersonVo2);

    HSSFWorkbook selectDataById(RequestList requestList);

    LedenUploadPacket downloadPacket(String id);

    Response<String> selectFileSuffixList();

    Response<String> selectDataTypeList();

    InputStream downloadPacketList(List<String> list);
}
