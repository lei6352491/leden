package com.zhouyi.business.core.model;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author 杜承旭
 * @ClassNmae: PersonResult
 * @Description: TODO
 * @date 2019/8/7 10:51
 * @Version 1.0
 **/

@Data
@ToString
public class PersonResult extends LedenCollectPerson implements Serializable {

    private String name;

    private String typeName;

    private String cjxxyymc;
    private String cyzjmc;
    private String gjmc;
    private String mzmc;
    private String jgssxmc;
    private String csdssxmc;
    private String hjdssxmc;
    private String zzmmmc;
    private String xlmc;
    private String rylbmc;
    private String tssfmc;
    private String cjlbmc;
    private String xbmc;
    private String zjxymc;
    private String ajlbmc;
    private String xzdqhmc;
    private String hyzkmc;
    private String grsfmc;
}
