package com.zhouyi.business.core.model.enums;


/**
 * @author 李秸康
 * @ClassNmae: CodesEnum
 * @Description: 编码标识 枚举类
 * @date 2019/7/11 9:51
 * @Version 1.0
 **/
public enum CodesEnum {
    I050008("I050008","CJMBBH"),
    C010010("C010010","ZZS_DWMC"),
    G020013("G020013","00000"),
    B010001("B010001","DXXM"),
    B030004("B030004","00000"),
    B030005("B030005","00000"),
    H010014("H010014","00000"),
    E020012("E020012","00000"),
    B010028("B010028","00000"),
    G020032("G020032","00000"),
    I050001("I050001","00000"),
    I050003("I050003","00000"),
    I050002("I050002","00000"),

    C010001("C010001","WPMC"),
    C050001("C050001","IMEI_WPBZH"),
    C040002("C040002","MACDZ"),
    C040006("C040006","LY_MACDZ"),
    C010003("C010003","WPXH"),
    C010009("C010009","WPTZMS"),

    B020005("B020005","YDDH"),
    B020007("B020007","00000"),
    B020020("B020020","00000"),
    H010029("H010029","XXSC_PDBZ"),
    B040033("B040033","XXSC_CLSJ"),

    J020011("J020011","00000"),
    B070002("B070002","GXR_XM"),
    H100034("H100034","SMXSZ"),


    I010051("I010051","TXLZDLXDM"),
    B020017("B020017","LXDHLX"),
    B070003("B070003",new String[]{"GXRLXFS","GXR_YDDH"}),
    B020019("B020019","THZT"),
    H040002("H040002","BDSJBS"),
    H010015("H010015","KSSJ"),
    H010016("H010016","JSSJ"),
    H010030("H010030","THSC"),

    H030008("H030008","DXSFSJ"),
    H040001("H040001","DX_JYQK"),
    H030009("H030009","CKZT"),
    H030010("H030010","DXCCWZ");

    private String code;
    private Object propertiy;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getPropertiy() {
        return propertiy;
    }

    public void setPropertiy(Object propertiy) {
        this.propertiy = propertiy;
    }

    CodesEnum(String code, Object propertiy) {
        this.code = code;
        this.propertiy = propertiy;
    }
}
