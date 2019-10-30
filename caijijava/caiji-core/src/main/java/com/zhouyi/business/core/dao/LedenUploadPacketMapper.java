package com.zhouyi.business.core.dao;

import com.zhouyi.business.core.model.LedenUploadPacket;
import com.zhouyi.business.core.model.UploadPacketResponse;
import com.zhouyi.business.core.model.UploadPacketResult;
import com.zhouyi.business.core.vo.LedenConllectPersonVo2;
import com.zhouyi.business.core.vo.LedenUploadPacketVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LedenUploadPacketMapper extends BuffBaseMapper<LedenUploadPacket, LedenUploadPacketVo>{

    List<LedenUploadPacket> selectDataAnalysisStatus(String ryjcxxcjbh);

    List<UploadPacketResult> selectDataList(LedenConllectPersonVo2 ledenConllectPersonVo2);

    Integer selectDataListCount(LedenConllectPersonVo2 ledenConllectPersonVo2);

    List<UploadPacketResponse> selectDataById(List<String> list);

    List<LedenUploadPacket> selectDataByIsEmpowerOrResolveStatus();
}