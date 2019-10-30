package com.zhouyi.business.core.vo;

import com.zhouyi.business.core.model.LedenCollectFootprint;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 李秸康
 * @ClassNmae: LedenCollectFootprintSearchVo
 * @Description: TODO
 * @date 2019/8/27 10:41
 * @Version 1.0
 **/
@Data
public class LedenCollectFootprintSearchVo extends LedenCollectFootprint {
    private String xdhw="未知";
    private String xy="未知";
    private String zjbw="未知";
    private String zjlx="未知";
    private String createUserName;
    private String updateUserName;


}
