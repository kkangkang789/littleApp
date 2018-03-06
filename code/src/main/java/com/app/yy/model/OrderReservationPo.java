package com.app.yy.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 商品预定订单Po
 * @author yk
 *
 */
@Entity
@Table(name = "tb_order_reservation")
public class OrderReservationPo implements Serializable {

	/**
	 * 序号
	 */
	private static final long serialVersionUID = -6514708479732438952L;

	
	/**
	 * id
	 * 订单号
	 */
	@Id
	private String orderNo;
	
	/**
	 * 商品id
	 */
	private Long productId;
	
	/**
	 * 商品数量
	 */
	private int productNum;
	
	/**
	 * 订单总价格
	 */
	private int orderTotalPrice;
	
	/**
	 * 创建订单时间
	 */
	private Date createOrderTime;
	
	/**
	 * 更新订单时间
	 */
	private Date updateOrderTime;
	
	/**
	 * 订单状态
	 */
	private boolean orderStatus;

	/**
	 * @return the orderNo
	 */
	public String getOrderNo() {
		return orderNo;
	}

	/**
	 * @param orderNo the orderNo to set
	 */
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	/**
	 * @return the productId
	 */
	public Long getProductId() {
		return productId;
	}

	/**
	 * @param productId the productId to set
	 */
	public void setProductId(Long productId) {
		this.productId = productId;
	}

	/**
	 * @return the productNum
	 */
	public int getProductNum() {
		return productNum;
	}

	/**
	 * @param productNum the productNum to set
	 */
	public void setProductNum(int productNum) {
		this.productNum = productNum;
	}

	/**
	 * @return the createOrderTime
	 */
	public Date getCreateOrderTime() {
		return createOrderTime;
	}

	/**
	 * @param createOrderTime the createOrderTime to set
	 */
	public void setCreateOrderTime(Date createOrderTime) {
		this.createOrderTime = createOrderTime;
	}

	/**
	 * @return the updateOrderTime
	 */
	public Date getUpdateOrderTime() {
		return updateOrderTime;
	}

	/**
	 * @param updateOrderTime the updateOrderTime to set
	 */
	public void setUpdateOrderTime(Date updateOrderTime) {
		this.updateOrderTime = updateOrderTime;
	}

	/**
	 * @return the orderStatus
	 */
	public boolean isOrderStatus() {
		return orderStatus;
	}

	/**
	 * @param orderStatus the orderStatus to set
	 */
	public void setOrderStatus(boolean orderStatus) {
		this.orderStatus = orderStatus;
	}
	
}
