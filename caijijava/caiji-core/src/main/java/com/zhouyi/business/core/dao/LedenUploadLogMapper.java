package com.zhouyi.business.core.dao;

import com.zhouyi.business.core.model.LedenUploadLog;
import com.zhouyi.business.core.model.LedenUploadPacket;
import com.zhouyi.business.core.model.provincecomprehensive.DataStatus;
import com.zhouyi.business.core.vo.LedenUploadLogVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface LedenUploadLogMapper extends BuffBaseMapper<LedenUploadLog, LedenUploadLogVo>{

    LedenUploadLog selectLedenUploadLogByNewTime(String equipmentId);

    int insertUploadLog(LedenUploadLog ledenUploadLog);

    /**
     * 根据条件筛选日志消息
     * @return
     */
    List<LedenUploadLog> listUploadLogByConditions(@Param("status") List status);


    /**
     * 根据人员编号更新上传日志
      * @param personCode
     * @param status
     * @param resolveInfo 解析结果信息
     * @return
     */
    int updateUploadLogByPersonCode(@Param("pkId")String pkId,
                                    @Param("status")int status,
                                    @Param("resolveInfo")String resolveInfo);


    /**
     * 更改是否获取到省综人员编号
     * @param pkId
     * @param isGetCode
     * @return
     */
    int updateIsGetCodeByPersonCode(@Param("pkId")String pkId,
                                    @Param("isGetCode")Integer isGetCode);


    /**
     * 查询出上传成功状态的数据
     * @return 上传成功数据的人员编号
     */
    DataStatus getUploadSuccessData();
}