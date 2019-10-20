package com.app.service;

import java.util.List;
import java.util.Map;

import com.app.model.SaleOrder;
import com.app.model.SalesDetails;

public interface ISaleOrderService {

	public Integer saveSaleOrder(SaleOrder saleOrder);
	public void updateSaleOrder(SaleOrder saleOrder);
	public void deleteSaleOrder(Integer saleOrderId);
	public SaleOrder getOneSaleOrder(Integer saleOrderId);
	public List<SaleOrder> getAllSaleOrders();
	public boolean isOrderCodeExist(String saleOrderCode);
	public void deleteSalesDetailsById(Integer salesDtlsId);
	public Map<Integer, String> getInvoicedSaleOrders(String status);

	//child class operationd
	public SalesDetails getSalesDetailsById(Integer salesDtlsId);
	public void updateSalesDetails(SalesDetails salesDetails);
	public int updateAllSalesDetailsStatus(String shipSatus,Integer soHoId);
	public long getNullShippingStatusCount(Integer saleOrderId);

}
