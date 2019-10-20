package com.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="sales_dtls_tab")
public class SalesDetails {

	@Id
	@Column(name="sd_id")
	@GeneratedValue(generator="sd_gen")
	@GenericGenerator(name="sd_gen",strategy="increment")
	private Integer salesDtlsId;
	@Column(name="so_ho_id")
	private Integer soHoId;
	@Transient
	@Column(name="slno")
	private int slno;
	@Column(name="qnty")
	private Long quantity;
	@Column(name="ship_status")
	private String shipSatus;
	@Column(name="cost")
	private double baseCost;
	
	@ManyToOne
	@JoinColumn(name="itemId")
	private Item item;

	public SalesDetails() {
		super();
	}

	public SalesDetails(Integer salesDtlsId) {
		super();
		this.salesDtlsId = salesDtlsId;
	}

	public Integer getSalesDtlsId() {
		return salesDtlsId;
	}

	public void setSalesDtlsId(Integer salesDtlsId) {
		this.salesDtlsId = salesDtlsId;
	}

	public Integer getSoHoId() {
		return soHoId;
	}

	public void setSoHoId(Integer soHoId) {
		this.soHoId = soHoId;
	}

	public int getSlno() {
		return slno;
	}

	public void setSlno(int slno) {
		this.slno = slno;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	
	public String getShipSatus() {
		return shipSatus;
	}

	public void setShipSatus(String shipSatus) {
		this.shipSatus = shipSatus;
	}

	public double getBaseCost() {
		return baseCost;
	}

	public void setBaseCost(double baseCost) {
		this.baseCost = baseCost;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}
	
	
	
}
