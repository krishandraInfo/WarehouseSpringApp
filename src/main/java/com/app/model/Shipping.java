package com.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="shipping")
public class Shipping {

	@Id
	@GeneratedValue(generator="ship_gen")
	@GenericGenerator(name="ship_gen",strategy="increment")
	@Column(name="ship_id")
	private Integer shipId;
	@Column(name="ship_code")
	private String shipCode;
	@Column(name="ship_ref_num")
	private String shipRefNum;
	@Column(name="cour_ref_num")
	private String courRefNum;
	@Column(name="cor_dtls")
	private String couContdtls;
	@Column(name="ship_desc")
	private String shipDesc;
	@Column(name="bill_addr")
	private String billAddr;
	@Column(name="ship_addr")
	private String shipAddr;
	
	@ManyToOne
	@JoinColumn(name="saleOrderId")
	private SaleOrder saleOrder;

	public Shipping() {
		super();
	}

	public Shipping(Integer shipId) {
		super();
		this.shipId = shipId;
	}

	public Integer getShipId() {
		return shipId;
	}

	public void setShipId(Integer shipId) {
		this.shipId = shipId;
	}

	public String getShipCode() {
		return shipCode;
	}

	public void setShipCode(String shipCode) {
		this.shipCode = shipCode;
	}

	public String getShipRefNum() {
		return shipRefNum;
	}

	public void setShipRefNum(String shipRefNum) {
		this.shipRefNum = shipRefNum;
	}

	public String getCourRefNum() {
		return courRefNum;
	}

	public void setCourRefNum(String courRefNum) {
		this.courRefNum = courRefNum;
	}

	public String getCouContdtls() {
		return couContdtls;
	}

	public void setCouContdtls(String couContdtls) {
		this.couContdtls = couContdtls;
	}

	public String getShipDesc() {
		return shipDesc;
	}

	public void setShipDesc(String shipDesc) {
		this.shipDesc = shipDesc;
	}

	public String getBillAddr() {
		return billAddr;
	}

	public void setBillAddr(String billAddr) {
		this.billAddr = billAddr;
	}

	public String getShipAddr() {
		return shipAddr;
	}

	public void setShipAddr(String shipAddr) {
		this.shipAddr = shipAddr;
	}

	public SaleOrder getSaleOrder() {
		return saleOrder;
	}

	public void setSaleOrder(SaleOrder saleOrder) {
		this.saleOrder = saleOrder;
	}

	
	
}
