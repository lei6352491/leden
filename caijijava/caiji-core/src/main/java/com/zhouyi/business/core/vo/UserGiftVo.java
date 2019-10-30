package com.zhouyi.business.core.vo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 商品信息
 * 
 *
 */
public class UserGiftVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Long fromUser;
	private Long toUser;
	private Integer amount;
	private Integer totalCount;
	private String productName;
	private String productCode;
	private String images;
	private String description;
	private Long productId;
	private List<Map<String,Object>> productUserList;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	
	public Long getFromUser() {
		return fromUser;
	}
	public void setFromUser(Long fromUser) {
		this.fromUser = fromUser;
	}
	public Long getToUser() {
		return toUser;
	}
	public void setToUser(Long toUser) {
		this.toUser = toUser;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getImages() {
		return images;
	}
	public void setImages(String images) {
		this.images = images;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public List<Map<String, Object>> getProductUserList() {
		return productUserList;
	}
	public void setProductUserList(List<Map<String, Object>> productUserList) {
		this.productUserList = productUserList;
	}
	
}
