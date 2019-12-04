package com.zhouyi.business.core.model;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @author 杜承旭
 * @ClassNmae: UploadPacketResult
 * @Description: TODO
 * @date 2019/8/16 9:57
 * @Version 1.0
 **/

@Data
@ToString
public class UploadPacketResult {

    private String pkId;
    private String ryjcxxcjbh;
    private String resolveStatus;
    private String nodeName;
    private String equipmentCode;
    private String fileSuffix;
    private String fileSize;
    private String cjrxm;
    private String cjdwmc;
    private Date cjsj;
    private String dataType;
    private String resolveStatusInfo;

}
