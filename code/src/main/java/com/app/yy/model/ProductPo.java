package com.app.yy.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 商品po
 * @author yk
 *
 */
@Entity
@Table(name = "tb_products")
public class ProductPo implements Serializable {

	/**
	 * 序号
	 */
	private static final long serialVersionUID = -5634156045035346494L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/**
	 * 商品名称
	 */
	private Long pName;
	
	/**
	 * 商品描述
	 */
	private String pDescribe;
	
	/**
	 * 商品数量
	 */
	private int pNum;
	
	/**
	 * 商品原始价格
	 */
	private int pOrgPrice;
	
	/**
	 * 商品现有价格
	 */
	private int pNowPrice;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the pName
	 */
	public Long getpName() {
		return pName;
	}

	/**
	 * @param pName the pName to set
	 */
	public void setpName(Long pName) {
		this.pName = pName;
	}

	/**
	 * @return the pDescribe
	 */
	public String getpDescribe() {
		return pDescribe;
	}

	/**
	 * @param pDescribe the pDescribe to set
	 */
	public void setpDescribe(String pDescribe) {
		this.pDescribe = pDescribe;
	}

	/**
	 * @return the pNum
	 */
	public int getpNum() {
		return pNum;
	}

	/**
	 * @param pNum the pNum to set
	 */
	public void setpNum(int pNum) {
		this.pNum = pNum;
	}

	/**
	 * @return the pOrgPrice
	 */
	public int getpOrgPrice() {
		return pOrgPrice;
	}

	/**
	 * @param pOrgPrice the pOrgPrice to set
	 */
	public void setpOrgPrice(int pOrgPrice) {
		this.pOrgPrice = pOrgPrice;
	}

	/**
	 * @return the pNowPrice
	 */
	public int getpNowPrice() {
		return pNowPrice;
	}

	/**
	 * @param pNowPrice the pNowPrice to set
	 */
	public void setpNowPrice(int pNowPrice) {
		this.pNowPrice = pNowPrice;
	}
	
}
