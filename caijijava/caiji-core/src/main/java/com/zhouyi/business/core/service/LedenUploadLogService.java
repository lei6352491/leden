package com.zhouyi.business.core.service;

import com.zhouyi.business.core.model.Head;
import com.zhouyi.business.core.model.LedenUploadLog;
import com.zhouyi.business.core.model.Response;
import com.zhouyi.business.core.vo.LedenUploadLogVo;

import java.util.List;

public interface LedenUploadLogService extends BaseService<LedenUploadLog, LedenUploadLogVo>{

    Response insertDataUploadNode(List list, Head head,String ryjcxxcjbh);



    /**
     * 根据上传状态查询数据
     * @param status 状态列表
     * @return
     */
    List<LedenUploadLog> listUplaodLogByCondition(Integer... status);


    /**
     * 根据人员修改状态
     * @param status
     * @param personCode
     */
    void uploadLogStatusByPersonCode(int status,String personCode,String info);
}
