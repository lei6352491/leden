package com.zhouyi.business.core.vo;

import com.zhouyi.business.core.model.LedenEquipment;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 杜承旭
 * @ClassNmae: LedenEquipmentVo2
 * @Description: TODO
 * @date 2019/8/5 10:47
 * @Version 1.0
 **/

@Data
@ToString
public class LedenEquipmentVo2 extends LedenEquipment implements Serializable {

    private String sign;

    private Integer page;

    private Integer startNo;

    private Integer size;

    private Integer endNo;

    private String overName;

    private Integer total;

    private Date dateStart;

    private Date dateEnd;
}
