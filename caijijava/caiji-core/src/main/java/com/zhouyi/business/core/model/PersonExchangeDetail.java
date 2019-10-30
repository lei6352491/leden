package com.zhouyi.business.core.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 账号流水
 * 
 *
 */
public class PersonExchangeDetail implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String accNo;
	private BigDecimal totalBalance;
	private BigDecimal oldAvaBalance;
	private BigDecimal oldFreBalance;
	private BigDecimal newAvaBalance;
	private BigDecimal newFreBalance;
	private Long serialNo;
	private Date createTime;
	private String remark;
	private String operateType;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getAccNo() {
		return accNo;
	}
	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}

	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public BigDecimal getTotalBalance() {
		return totalBalance;
	}
	public void setTotalBalance(BigDecimal totalBalance) {
		this.totalBalance = totalBalance;
	}
	public BigDecimal getOldAvaBalance() {
		return oldAvaBalance;
	}
	public void setOldAvaBalance(BigDecimal oldAvaBalance) {
		this.oldAvaBalance = oldAvaBalance;
	}
	public BigDecimal getOldFreBalance() {
		return oldFreBalance;
	}
	public void setOldFreBalance(BigDecimal oldFreBalance) {
		this.oldFreBalance = oldFreBalance;
	}
	public BigDecimal getNewAvaBalance() {
		return newAvaBalance;
	}
	public void setNewAvaBalance(BigDecimal newAvaBalance) {
		this.newAvaBalance = newAvaBalance;
	}
	public BigDecimal getNewFreBalance() {
		return newFreBalance;
	}
	public void setNewFreBalance(BigDecimal newFreBalance) {
		this.newFreBalance = newFreBalance;
	}
	public Long getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(Long serialNo) {
		this.serialNo = serialNo;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getOperateType() {
		return operateType;
	}
	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}
	
	
}
