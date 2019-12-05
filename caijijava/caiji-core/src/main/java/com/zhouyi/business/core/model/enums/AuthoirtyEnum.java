package com.zhouyi.business.core.model.enums;

/**
 * @author 李秸康
 * @ClassNmae: AuthoirtyEnum
 * @Description: 权限枚举
 * @date 2019/7/8 11:19
 * @Version 1.0
 **/
public enum  AuthoirtyEnum {
    PERSON("000000000001","person"),  //人员信息
    PORTRAIT("000000000002","portrait"), //人像信息
    FINGERPLAM("000000000003","fingerPlam"), //指纹信息
    PHONEINFO("000000000012","phoneInfo"), //手机信息
    BANKCARD("000000000013","bankCard"), //银行卡信息
    HANDWRITING("000000000008","handWriting"), //笔记信息
    VOICEPRINT("000000000007","voicePrint"), //声纹信息
    SIGNALEMENT("000000000004","signalement"), //特征信息
    DRUGTEST("000000000011","drugtest"), //药物信息
    FOOTPRINT("000000000006","footPrint"), //足迹信息
    IRISINFO("000000000009","irisiInfo"), //虹膜信息
    GOODS("000000000010","goods"), //随身物品信息
    DNAINFO("000000000005","dnaInfo"); //DNA信息

    private String nodeSign;
    private String nodeName;

    public String getNodeSign() {
        return nodeSign;
    }

    public void setNodeSign(String nodeSign) {
        this.nodeSign = nodeSign;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    AuthoirtyEnum(String nodeSign, String nodeName) {
        this.nodeSign = nodeSign;
        this.nodeName = nodeName;
    }

    AuthoirtyEnum() {
    }
}
