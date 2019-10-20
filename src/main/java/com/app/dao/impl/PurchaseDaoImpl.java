package com.app.dao.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.app.dao.IPurchaseDao;
import com.app.model.Purchase;
import com.app.model.PurchaseDtl;
import com.app.util.AppCollectionUtil;

@Repository
public class PurchaseDaoImpl implements IPurchaseDao {

	@Autowired
	private HibernateTemplate ht;

	public Integer savePurchase(Purchase purchase) {
		return (Integer) ht.save(purchase);
	}

	public void updatePurchase(Purchase purchase) {
		ht.update(purchase);
	}

	public void deletePurchase(Integer orderId) {
		ht.delete(new Purchase(orderId));
	}

	public Purchase getPurchaseById(Integer orderId) {
		return ht.get(Purchase.class, orderId);
	}

	public List<Purchase> getAllPurchases() {
		return ht.loadAll(Purchase.class);
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	public boolean isOrderCodeExist(String orderCode) {

		long count=0;
		String hql = "select count(orderCode) from "+Purchase.class.getName()+" where orderCode=?0";
		List<Long> purchases=(List<Long>) ht.find(hql, orderCode);
		if (purchases!=null && !purchases.isEmpty()) {
			count=purchases.get(0);
		}
		return count>0?true:false;
	}

	public void deletePurchaseDtlById(Integer orderDtlId) {
		ht.delete(new PurchaseDtl(orderDtlId));
	}

	@SuppressWarnings("unchecked")
	public Map<Integer, String> getInvoicedPurchaseOrders(String status) {

		DetachedCriteria hql = 
				DetachedCriteria.forClass(Purchase.class)
				.setProjection(Projections.projectionList()
						.add(Projections.property("orderId"))
						.add(Projections.property("orderCode"))
						)
				.add(Restrictions.eq("orderStatus", status));
		
		return AppCollectionUtil.toMap((List<Object[]>) ht.findByCriteria(hql));
	}

	public PurchaseDtl getPurchaseDtlsById(Integer orderDtlId) {
		return ht.get(PurchaseDtl.class, orderDtlId);
	}

	public void updatePurchaseDtls(PurchaseDtl purchaseDtl) {
		ht.update(purchaseDtl);
	}

	@SuppressWarnings("deprecation")
	public int updateAllPurchaseDtlsStatus(String grnStatus,Integer poHdrId) {
		
		String hql = "update "+PurchaseDtl.class.getName()
				+ " set grnStatus=? "
				+ " where grnStatus is null and poHdrId=?0";
		int rowCount=ht.bulkUpdate(hql, grnStatus,poHdrId);
		return rowCount;
	}
	
	@SuppressWarnings("unchecked")
	public long getNullGrnStatusCount(Integer orderId) {
		
		long count=0;
		
		DetachedCriteria hql=
				DetachedCriteria.forClass(PurchaseDtl.class)
				.setProjection(Projections.projectionList()
						.add(Projections.count("orderDtlId"))
						)
				.add(Restrictions.eq("poHdrId",orderId))
				.add(Restrictions.isNull("grnStatus"));
		List<Long> list=(List<Long>) ht.findByCriteria(hql);
		if (list != null && !list.isEmpty()) {
			count=list.get(0);
		}
		
		return count;
	}

}
