package com.zhouyi.business.core.vo;

import com.zhouyi.business.core.model.LedenEquipmentPlugIn;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author 杜承旭
 * @ClassNmae: LedenEquipmentPlugInVo
 * @Description: TODO
 * @date 2019/9/19 14:04
 * @Version 1.0
 **/

@Data
@ToString
public class LedenEquipmentPlugInVo extends LedenEquipmentPlugIn implements Serializable {

    private Integer page;

    private Integer startNo;

    private Integer size;

    private Integer endNo;

    private String overName;
}
