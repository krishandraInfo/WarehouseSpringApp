package com.app.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="purchase_tab")
public class Purchase {
	
	@Id
	@Column(name="order_id")
	@GeneratedValue(generator="purchase")
	@GenericGenerator(name="purchase",strategy="increment")
	private Integer orderId;
	@Column(name="order_code")
	private String orderCode;

	@Column(name="ref_num")
	private String refNumber;
	@Column(name="qua_check")
	private String quaCheck;
	@Column(name="order_status")
	private String orderStatus;
	@Column(name="order_desc")
	private String orderDesc;
	
	//Integration with whuser
	@ManyToOne
	@JoinColumn(name="whuser_id")
	private WhUserType whUserType;
	
	//Integration with ShipmentType
	@ManyToOne
	@JoinColumn(name="shipmentid")
	private  ShipmentType shipmentType;

	//child items data
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="order_id_fk")
	private List<PurchaseDtl> details=new ArrayList<>(0);
	
	
	public Purchase() {
		super();
	}
	public Purchase(Integer orderId) {
		super();
		this.orderId = orderId;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public String getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	public String getRefNumber() {
		return refNumber;
	}
	public void setRefNumber(String refNumber) {
		this.refNumber = refNumber;
	}
	public String getQuaCheck() {
		return quaCheck;
	}
	public void setQuaCheck(String quaCheck) {
		this.quaCheck = quaCheck;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getOrderDesc() {
		return orderDesc;
	}
	public void setOrderDesc(String orderDesc) {
		this.orderDesc = orderDesc;
	}
	public WhUserType getWhUserType() {
		return whUserType;
	}
	public void setWhUserType(WhUserType whUserType) {
		this.whUserType = whUserType;
	}
	public ShipmentType getShipmentType() {
		return shipmentType;
	}
	public void setShipmentType(ShipmentType shipmentType) {
		this.shipmentType = shipmentType;
	}
	public List<PurchaseDtl> getDetails() {
		return details;
	}
	public void setDetails(List<PurchaseDtl> details) {
		this.details = details;
	}
	
	
	
}
