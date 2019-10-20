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
@Table(name="item_tab")
public class Item {

	@Id
	@Column(name="item_id")
	@GeneratedValue(generator="item")
	@GenericGenerator(name="item",strategy="increment")
	private Integer itemId;
	@Column(name="item_code")
	private String itemCode;
	@Column(name="item_length")
	private Double itemLength;
	@Column(name="item_width")
	private Double itemWidth;
	@Column(name="item_height")
	private Double itemHeight;
	@Column(name="item_basecost")
	private Double itemBaseCost;
	@Column(name="item_currency")
	private String itemCurrentCurrency;
	@Column(name="item_desc")
	private String itemDesc;

	// integration with Uom Module
	@ManyToOne
	@JoinColumn(name="uid")
	private Uom uom;
	
	// integration with OrderMethod Module
	@ManyToOne
	@JoinColumn(name="orderId")
	private OrderMethod orderMethod;

	public Item() {
		super();
	}

	public Item(Integer itemId) {
		super();
		this.itemId = itemId;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public Double getItemLength() {
		return itemLength;
	}

	public void setItemLength(Double itemLength) {
		this.itemLength = itemLength;
	}

	public Double getItemWidth() {
		return itemWidth;
	}

	public void setItemWidth(Double itemWidth) {
		this.itemWidth = itemWidth;
	}

	public Double getItemHeight() {
		return itemHeight;
	}

	public void setItemHeight(Double itemHeight) {
		this.itemHeight = itemHeight;
	}

	public Double getItemBaseCost() {
		return itemBaseCost;
	}

	public void setItemBaseCost(Double itemBaseCost) {
		this.itemBaseCost = itemBaseCost;
	}

	public String getItemCurrentCurrency() {
		return itemCurrentCurrency;
	}

	public void setItemCurrentCurrency(String itemCurrentCurrency) {
		this.itemCurrentCurrency = itemCurrentCurrency;
	}

	public String getItemDesc() {
		return itemDesc;
	}

	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}

	public Uom getUom() {
		return uom;
	}

	public void setUom(Uom uom) {
		this.uom = uom;
	}

	public OrderMethod getOrderMethod() {
		return orderMethod;
	}

	public void setOrderMethod(OrderMethod orderMethod) {
		this.orderMethod = orderMethod;
	}

	
}
