package com.zhouyi.business.core.vo;

import com.zhouyi.business.core.model.LedenEquipmentEmpower;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author 杜承旭
 * @ClassNmae: LedenEquipmentEmpowerVo
 * @Description: TODO
 * @date 2019/8/12 8:47
 * @Version 1.0
 **/

@Data
@ToString
public class LedenEquipmentEmpowerVo extends LedenEquipmentEmpower implements Serializable {

    private Integer page;

    private Integer startNo;

    private Integer size;

    private Integer endNo;

    private String overName;

}
