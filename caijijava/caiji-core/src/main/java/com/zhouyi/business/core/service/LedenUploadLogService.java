package com.zhouyi.business.core.service;

import com.zhouyi.business.core.model.Head;
import com.zhouyi.business.core.model.LedenUploadLog;
import com.zhouyi.business.core.model.Response;
import com.zhouyi.business.core.vo.LedenUploadLogVo;

import java.util.List;

public interface LedenUploadLogService extends BaseService<LedenUploadLog, LedenUploadLogVo>{

    Response insertDataUploadNode(List list, Head head,String ryjcxxcjbh);
}
