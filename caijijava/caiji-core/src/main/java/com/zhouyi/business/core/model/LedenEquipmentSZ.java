package com.zhouyi.business.core.model;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author 杜承旭
 * @ClassNmae: LedenEquipmentSZ
 * @Description: TODO
 * @date 2019/10/22 9:28
 * @Version 1.0
 **/
@Data
@ToString
public class LedenEquipmentSZ implements Serializable {
    private String unitCode;
    private String ip;
    private String mac;
}
