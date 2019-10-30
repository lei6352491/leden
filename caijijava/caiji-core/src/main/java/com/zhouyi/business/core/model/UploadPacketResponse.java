package com.zhouyi.business.core.model;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author 杜承旭
 * @ClassNmae: UploadPacketResponse
 * @Description: TODO
 * @date 2019/8/17 9:25
 * @Version 1.0
 **/

@Data
@ToString
public class UploadPacketResponse implements Serializable {

    //数据包信息
    private String pkId;

    private String uploadLogId;

    private String nodeSign;

    private String fileServer;

    private String fileLocation;

    private String fileSuffix;

    private BigDecimal fileSize;

    private String fileMd5;

    private String resolveStatus;

    private String resolveResultInfo;

    private Date resolveDatetime;


    //节点信息
    private String nodeCode;

    private String nodeName;

    private String nodeRequest;

    private String nodeImg;

    private String deleteFlag;


    //采集过程信息
    private Short collectStatus;

    private Date collectDate;

    private Short ord;

    private String collectNodeId;

    private Short isSkip;

    private String skipReason;

    private String skipUserId;

    private Date skipDatetimetime;

    //人员基本信息
    private String ryjcxxcjbh;

    private String jzrybh;

    private String cjxxyydm;

    private String xm;

    private String xmhypy;

    private String gmsfhm;

    private String cyzjdm;

    private String zjhm;

    private String xbdm;

    private Date csrq;

    private String cym;

    private String wwxm;

    private String bmch;

    private String gjdm;

    private String mzdm;

    private String jgssxdm;

    private String csdssxdm;

    private String csdxz;

    private String hjdssxdm;

    private String hjdxz;

    private String type;

    private String zzmmdm;

    private String hyzkdm;

    private String xldm;

    private String grsfdm;

    private String rylbdm;

    private String xzdqhdm;

    private String xzdxz;

    private String ajlbdm;

    private String cjrxm;

    private String cjdwdm;

    private String cjdwmc;

    private Date cjsj;

    private String tssfdm;

    private String zjxydm;

    private String collectCategoryId;

    private String status;

    private String schedule;

    private String taskId;

    private String equipmentCode;

    private String deletag;

    private String annex;

    private String createUserId;

    private Date createDatetime;

    private String updateUserId;

    private Date updateDatetime;

}
