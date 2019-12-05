package com.zhouyi.business.core.common;

/**
 * modifyBy:李秸康 新增错误
 */
public enum ReturnCode {
    ERROR_17(-17,"部门不存在"),
    ERROR_16(-16,"线程停顿错误"),
    ERROR_15(-15,"MAC地址为空"),
    ERROR_14(-14,"数据内容为空"),
    ERROR_13(-13,"设备信息不存在"),
    ERROR_12(-12,"不存在该部门编号"),
    ERROR_11(-11,"验证数据缺失"),
    ERROR_10(-10,"该数据不可再移动"),
    ERROR_09(-9,"角色名重复"),
    ERROR_08(-8,"注册编号重复"),
    ERROR_07(-7,"重复主键"),
    ERROR_06(-6,"参数异常"),
    ERROR_05(-5,"没有该数据"),
	ERROR_04(-4, "数据库数据错误"),
    ERROR_03(-3, "缺少参数"),
    ERROR_02(-2, "参数不正确"),
    ERROR_01(-1, "操作失败"),
    SUCCESS(0, "成功"),
    ERROR_1(1, "交易已受理"),
    ERROR_2(2, "参数为空"),
    ERROR_3(3, "请求重复提交"),
    ERROR_404(404, "请求不存在"),
    ERROR_500(500, "服务器繁忙，请稍后再试"),

    ERROR_1000(1000, "请先登录"),
    ERROR_1001(1001, "用户不存在"),
    Error_1025(1025,"密码错误"),
	ERROR_1002(1002, "会话过期请重新登录"),
	ERROR_1004(1004, "授权信息不存在"),
	ERROR_1005(1005, "提现失败"),
	ERROR_1003(1003, "没有登录令牌"),
	ERROR_1006(1006, "1分钟内不要重复发验证码"),
	ERROR_1007(1007, "验证码失效"),
	ERROR_1008(1008, "验证码不正确"),
	ERROR_1009(1009, "您好,未开通会员,暂且无法使用该功能"),
	ERROR_1010(1010, "提现金额不能为0"),
	ERROR_1011(1011, "消息已发送,不能删除"),
	ERROR_1012(1012, "不能分享"),
	ERROR_1013(1013, "您好,已经登记信息,可以跟专属客服联系"),
	ERROR_1014(1014, "账号不存在"),
	ERROR_1015(1015, "非法操作"),
	ERROR_1016(1016, "抱歉,没有管理员权限"),
	ERROR_1017(1017, "用户已存在"),
	ERROR_1018(1018, "入账失败"),
	ERROR_1019(1019, "流水号重复了"),
	ERROR_1020(1020, "微信最低提现金额为1元"),
	ERROR_1021(1021, "订单不存在"),
	ERROR_1022(1022, "您还未绑定手机号码,麻烦到公众号先绑定"),
	ERROR_1023(1023, "未找到您的订单信息,请联系专属客服"),
	ERROR_1024(1024, "请选择对应的套餐"),
	ERROR_1027(1027, "解析失败，设备认证失败"),
    ERROR_1028(1028,"文件不存在"),
    ERROR_1029(1029,"该用户所在的单位已禁用，登录失败"),
    ERROR_1030(1030,"账号已冻结"),
    ERROR_1031(1031,"账户名已存在"),
    ERROR_1127(1127, "解压失败"),
    ERROR_1128(1128, "执行脚本错误"),
    ERROR_1129(1129, "创建压缩文件失败"),
    ERROR_1130(1130, "压缩过程失败"),

    //设备认证异常信息
    ERROR_1032(1032,"人员与部门信息不匹配"),
    ERROR_1033(1033,"身份证密码不匹配"),
    ERROR_1034(1034,"部门编号不匹配"),
    ERROR_1035(1035,"指纹评分长度不正确，长度为87位"),
    ERROR_1036(1036,"授权信息未填写"),
    ERROR_1037(1037,"设备用户部门信息不匹配"),
    ERROR_1038(1038,"设备未注册"),
    ERROR_1052(1052,"设备已授权"),
    ERROR_1053(1053,"设备未授权"),
    ERROR_1046(1046,"设备注册部门与用户所在部门不匹配"),


    //业务异常
    ERROR_1039(1039,"无法辨识该物品的类别信息"),
    ERROR_1040(1040,"无法调用改方法"),
    ERROR_1041(1041,"调用目标方法失败"),
    ERROR_1047(1047,"单位编号不正确"),
    ERROR_1048(1048,"该设备已被注册"),
    ERROR_1049(1049,"该设备MAC地址已被注册"),
    ERROR_1050(1050,"采集场所编号不正确"),
    ERROR_1051(1051,"采集场所编号不存在"),

    //数据日常
    ERROR_1042(1042,"数据异常：没有缺少创建时间"),
    ERROR_1043(1043,"图片解码失败"),
    ERROR_1044(1044,"附件不存在"),
    ERROR_1045(1045,"构建临时文件出错");
    private int code;
    private String msg;

    ReturnCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public String getKey() {
        return this.name();
    }
}