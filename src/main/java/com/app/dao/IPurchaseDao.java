package com.app.dao;

import java.util.List;
import java.util.Map;

import com.app.model.Purchase;
import com.app.model.PurchaseDtl;

public interface IPurchaseDao {
	
	public Integer savePurchase(Purchase purchase);
	public void updatePurchase(Purchase purchase);
	public void deletePurchase(Integer orderId);
	public Purchase getPurchaseById(Integer orderId);
	public List<Purchase> getAllPurchases();
	public boolean isOrderCodeExist(String orderCode);
	public void deletePurchaseDtlById(Integer orderDtlId);
	public Map<Integer, String> getInvoicedPurchaseOrders(String status);
	
	//child class operartions
	public PurchaseDtl getPurchaseDtlsById(Integer orderDtlId);
	public void updatePurchaseDtls(PurchaseDtl purchaseDtl);
	public int updateAllPurchaseDtlsStatus(String grnStatus,Integer poHdrId);
	public long getNullGrnStatusCount(Integer orderId);

}
