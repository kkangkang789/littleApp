package com.app.yy.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 产品种类
 * @author yk
 *
 */
@Entity
@Table(name = "tb_product_categories")
public class ProductCategoriesPo implements Serializable {

	/**
	 * 序号
	 */
	private static final long serialVersionUID = 1058842039737076230L;
	
	/**
	 * id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/**
	 * 种类名称
	 */
	private String pcName;
	
	/**
	 * 种类描述
	 */
	private String pcDescribe;

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
	 * @return the pcName
	 */
	public String getPcName() {
		return pcName;
	}

	/**
	 * @param pcName the pcName to set
	 */
	public void setPcName(String pcName) {
		this.pcName = pcName;
	}

	/**
	 * @return the pcDescribe
	 */
	public String getPcDescribe() {
		return pcDescribe;
	}

	/**
	 * @param pcDescribe the pcDescribe to set
	 */
	public void setPcDescribe(String pcDescribe) {
		this.pcDescribe = pcDescribe;
	}
}
