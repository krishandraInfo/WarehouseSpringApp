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
@Table(name="sales_table")
public class SaleOrder {

	@Id
	@Column(name="s_order_id")
	@GeneratedValue(generator="salesgen")
	@GenericGenerator(name="salesgen",strategy="increment")
	private Integer saleOrderId;
	@Column(name="s_order_code")
	private String saleOrderCode;
	@Column(name="s_ref_num")
	private String refNum;
	@Column(name="stock_mode")
	private String stockMode;
	@Column(name="stock_source")
	private String stockSource;
	@Column(name="s_status")
	private String orderStatus;
	@Column(name="s_Desc")
	private String orderDesc;


	@ManyToOne
	@JoinColumn(name="shipmentid")
	private ShipmentType shipmentType;

	@ManyToOne
	@JoinColumn(name="whuser_id")
	private WhUserType whUserType;
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="saleOrderId")
	private List<SalesDetails> salesDetails = new ArrayList<>();

	public SaleOrder() {
		super();
	}

	public SaleOrder(Integer saleOrderId) {
		super();
		this.saleOrderId = saleOrderId;
	}

	public Integer getSaleOrderId() {
		return saleOrderId;
	}

	public void setSaleOrderId(Integer saleOrderId) {
		this.saleOrderId = saleOrderId;
	}

	public String getSaleOrderCode() {
		return saleOrderCode;
	}

	public void setSaleOrderCode(String saleOrderCode) {
		this.saleOrderCode = saleOrderCode;
	}

	public String getRefNum() {
		return refNum;
	}

	public void setRefNum(String refNum) {
		this.refNum = refNum;
	}

	public String getStockMode() {
		return stockMode;
	}

	public void setStockMode(String stockMode) {
		this.stockMode = stockMode;
	}

	public String getStockSource() {
		return stockSource;
	}

	public void setStockSource(String stockSource) {
		this.stockSource = stockSource;
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

	public ShipmentType getShipmentType() {
		return shipmentType;
	}

	public void setShipmentType(ShipmentType shipmentType) {
		this.shipmentType = shipmentType;
	}

	public WhUserType getWhUserType() {
		return whUserType;
	}

	public void setWhUserType(WhUserType whUserType) {
		this.whUserType = whUserType;
	}

	public List<SalesDetails> getSalesDetails() {
		return salesDetails;
	}

	public void setSalesDetails(List<SalesDetails> salesDetails) {
		this.salesDetails = salesDetails;
	}
	
	



}
