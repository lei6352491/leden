package com.zhouyi.business.core.model;

/**
 * 订单支付类型
 * 
 *
 */
public enum PayTypeEnum {

	TYPE_100(100,"充值"),
	TYPE_101(101,"提现"),
	TYPE_102(102,"开通会员"),
	TYPE_103(103,"升级高级版"),
	TYPE_104(104,"升级专业版"),
	TYPE_105(105,"其它支付"),
	TYPE_106(106,"购买高分卡"),
	TYPE_107(107,"人工起名服务");
	
    private int type;
	private String msg;
	PayTypeEnum(int type,String msg){
		this.type=type;
		this.msg=msg;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
    
}
