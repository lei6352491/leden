package com.zhouyi.business.model.provincecomprehensive.utils;

import com.zhouyi.business.model.provincecomprehensive.*;
import lombok.Data;

import java.util.List;

/**
 * @Author: first
 * @Date: 下午12:37 2019/10/31
 * @Description: 总数据对象
**/
@Data
public class MIS {
   private PersonInfo personInfo;
   private List<GDSInfo> goodsInfo;
   private List<BodySignInfo> NewDataSet;
   private List<IrisInfo> irisInfos;
   private VoiceInfo voiceInfo;
}
