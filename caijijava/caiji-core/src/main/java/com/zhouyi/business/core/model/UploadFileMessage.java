package com.zhouyi.business.core.model;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author 杜承旭
 * @ClassNmae: UploadFileMessage
 * @Description: TODO
 * @date 2019/9/9 13:56
 * @Version 1.0
 **/

@Data
@ToString
public class UploadFileMessage implements Serializable {

    private String userUnitCode;

    private String userCode;

    private String equipmentCode;

    private FileMessage dataBrief;

    private String personCode;

}
