package com.zhouyi.business.core.model;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author 杜承旭
 * @ClassNmae: FileMessage
 * @Description: TODO
 * @date 2019/9/9 13:57
 * @Version 1.0
 **/

@Data
@ToString
public class FileMessage implements Serializable {
    
    private Boolean person;
    private Boolean portrait;
    private Boolean fingerplam;
    private Boolean signAlement;
    private Boolean dnaInfo;
    private Boolean footprint;
    private Boolean handwriting;
    private Boolean voiceprint;
    private Boolean irisInfo;
    private Boolean goods;
    private Boolean drugTest;
    private Boolean phoneInfo;
    private Boolean bankCard;
    private String uploadPacket;
}
