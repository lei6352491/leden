package com.zhouyi.business.core.model.provincecomprehensive.utils;

import com.zhouyi.business.core.model.provincecomprehensive.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author: first
 * @Date: 下午12:37 2019/10/31
 * @Description: 总数据对象
**/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MIS {
   private PersonInfo personInfo;
   private List<GDSInfo> goodsInfo;
   private List<BodySignInfo> newDataSet;
   private List<IrisInfo> irisInfos;
   private VoiceInfo voiceInfo;
}
