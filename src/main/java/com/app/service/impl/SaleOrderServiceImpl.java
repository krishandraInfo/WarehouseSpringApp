package com.app.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.ISaleOrderDao;
import com.app.model.SaleOrder;
import com.app.model.SalesDetails;
import com.app.service.ISaleOrderService;

@Service
public class SaleOrderServiceImpl implements ISaleOrderService {

	@Autowired
	private ISaleOrderDao dao;
	
	@Transactional
	public Integer saveSaleOrder(SaleOrder saleOrder) {
		return dao.saveSaleOrder(saleOrder);
	}
	
	@Transactional
	public void updateSaleOrder(SaleOrder saleOrder) {
		dao.updateSaleOrder(saleOrder);
	}

	@Transactional
	public void deleteSaleOrder(Integer saleOrderId) {
		dao.deleteSaleOrder(saleOrderId);
	}
	
	@Transactional(readOnly=true)
	public SaleOrder getOneSaleOrder(Integer saleOrderId) {
		return dao.getOneSaleOrder(saleOrderId);
	}
	
	@Transactional(readOnly=true)
	public List<SaleOrder> getAllSaleOrders() {
		return dao.getAllSaleOrders();
	}

	@Transactional(readOnly=true)
	public boolean isOrderCodeExist(String saleOrderCode) {
		return dao.isOrderCodeExist(saleOrderCode);
	}

	@Transactional
	public void deleteSalesDetailsById(Integer salesDtlsId) {
		dao.deleteSalesDetailsById(salesDtlsId);
	}
	
	@Transactional(readOnly=true)
	public Map<Integer, String> getInvoicedSaleOrders(String status) {
		return dao.getInvoicedSaleOrders(status);
	}

	@Transactional(readOnly=true)
	public SalesDetails getSalesDetailsById(Integer salesDtlsId) {
		return dao.getSalesDetailsById(salesDtlsId);
	}

	@Transactional
	public void updateSalesDetails(SalesDetails salesDetails) {
		dao.updateSalesDetails(salesDetails);
	}

	@Transactional
	public int updateAllSalesDetailsStatus(String shipSatus, Integer soHoId) {
		return dao.updateAllSalesDetailsStatus(shipSatus, soHoId);
	}

	@Transactional(readOnly=true)
	public long getNullShippingStatusCount(Integer saleOrderId) {
		return dao.getNullShippingStatusCount(saleOrderId);
	}

}
