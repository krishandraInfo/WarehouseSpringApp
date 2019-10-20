package com.app.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="ordermethodtab")
public class OrderMethod {
	
	@Id
	@GeneratedValue(generator="orderMethod")
	@GenericGenerator(name="orderMethod",strategy="increment")
	@Column(name="oid")
	private Integer orderId;
	@Column(name="omode")
	private String orderMode;
	@Column(name="ocode")
	private String orderCode;
	@Column(name="oexetype")
	private String orderExeType;
	@ElementCollection(fetch=FetchType.EAGER)
	@JoinTable(name="ordacptab",joinColumns=@JoinColumn(name="oid"))//join column
	@OrderColumn(name="pos")//index
	@Column(name="oacpt")//column name
	private List<String> orderAccept;
	@Column(name="odesc")
	private String orderDecs;
	public OrderMethod() {
		super();
	}
	public OrderMethod(Integer orderId) {
		super();
		this.orderId = orderId;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public String getOrderMode() {
		return orderMode;
	}
	public void setOrderMode(String orderMode) {
		this.orderMode = orderMode;
	}
	public String getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	public String getOrderExeType() {
		return orderExeType;
	}
	public void setOrderExeType(String orderExeType) {
		this.orderExeType = orderExeType;
	}
	public List<String> getOrderAccept() {
		return orderAccept;
	}
	public void setOrderAccept(List<String> orderAccept) {
		this.orderAccept = orderAccept;
	}
	public String getOrderDecs() {
		return orderDecs;
	}
	public void setOrderDecs(String orderDecs) {
		this.orderDecs = orderDecs;
	}
	@Override
	public String toString() {
		return "OrderMethod [orderId=" + orderId + ", orderMode=" + orderMode + ", orderCode=" + orderCode
				+ ", orderExeType=" + orderExeType + ", orderAccept=" + orderAccept + ", orderDecs=" + orderDecs + "]";
	}
	
	
}
