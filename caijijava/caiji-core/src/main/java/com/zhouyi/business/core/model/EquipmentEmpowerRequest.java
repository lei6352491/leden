package com.zhouyi.business.core.model;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @author 杜承旭
 * @ClassNmae: EquipmentEmpowerRequest
 * @Description: TODO
 * @date 2019/8/12 10:03
 * @Version 1.0
 **/
@Data
@ToString
public class EquipmentEmpowerRequest implements Serializable {

    private String equipmentId;

    private String userId;

    private List<String> nodes;

}
