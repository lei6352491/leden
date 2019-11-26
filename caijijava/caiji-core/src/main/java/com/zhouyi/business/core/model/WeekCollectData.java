package com.zhouyi.business.core.model;

import lombok.Data;
import lombok.ToString;

/**
 * @author 杜承旭
 * @ClassNmae: WeekCollectData
 * @Description: TODO
 * @date 2019/11/21 15:08
 * @Version 1.0
 **/

@Data
@ToString
public class WeekCollectData {
    private Integer monday;
    private Integer tuesday;
    private Integer wednesday;
    private Integer thursday;
    private Integer friday;
    private Integer saturday;
    private Integer sunday;
}
