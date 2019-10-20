package com.app.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.IPurchaseDao;
import com.app.model.Purchase;
import com.app.model.PurchaseDtl;
import com.app.service.IPurchaseService;

@Service
public class PurchaseServiceImpl implements IPurchaseService {

	@Autowired
	private IPurchaseDao dao;

	@Transactional
	public Integer savePurchase(Purchase purchase) {
		return dao.savePurchase(purchase);
	}

	@Transactional
	public void updatePurchase(Purchase purchase) {
		dao.updatePurchase(purchase);
	}

	@Transactional
	public void deletePurchase(Integer orderId) {
		dao.deletePurchase(orderId);
	}

	@Transactional(readOnly=true)
	public Purchase getPurchaseById(Integer orderId) {
		return dao.getPurchaseById(orderId);
	}

	@Transactional(readOnly=true)
	public List<Purchase> getAllPurchases() {
		return dao.getAllPurchases();
	}

	@Transactional(readOnly=true)
	public boolean isOrderCodeExist(String orderCode) {
		return dao.isOrderCodeExist(orderCode);
	}
	

	@Transactional
	public void deletePurchaseDtlById(Integer orderDtlId) {
		dao.deletePurchaseDtlById(orderDtlId);
	}

	@Transactional(readOnly=true)
	public Map<Integer, String> getInvoicedPurchaseOrders(String status) {
		return dao.getInvoicedPurchaseOrders(status);
	}

	@Transactional(readOnly=true)
	public PurchaseDtl getPurchaseDtlsById(Integer orderDtlId) {
		return dao.getPurchaseDtlsById(orderDtlId);
	}

	@Transactional
	public void updatePurchaseDtls(PurchaseDtl purchaseDtl) {
		dao.updatePurchaseDtls(purchaseDtl);
	}

	@Transactional
	public int updateAllPurchaseDtlsStatus(String grnStatus,Integer poHdrId) {
		return dao.updateAllPurchaseDtlsStatus(grnStatus,poHdrId);
	}

	@Transactional
	public long getNullGrnStatusCount(Integer orderId) {
		return dao.getNullGrnStatusCount(orderId);
	}

}
