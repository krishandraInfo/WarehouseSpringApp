package com.app.dao.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.app.dao.ISaleOrderDao;
import com.app.model.SaleOrder;
import com.app.model.SalesDetails;
import com.app.util.AppCollectionUtil;

@Repository
public class SaleOrderDaoImpl implements ISaleOrderDao {

	@Autowired
	private HibernateTemplate ht;

	public Integer saveSaleOrder(SaleOrder saleOrder) {
		return (Integer) ht.save(saleOrder);
	}

	public void updateSaleOrder(SaleOrder saleOrder) {
		ht.update(saleOrder);
	}

	public void deleteSaleOrder(Integer saleOrderId) {
		ht.delete(new SaleOrder(saleOrderId));
	}

	public SaleOrder getOneSaleOrder(Integer saleOrderId) {
		return ht.get(SaleOrder.class, saleOrderId);
	}

	public List<SaleOrder> getAllSaleOrders() {
		return ht.loadAll(SaleOrder.class);
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	public boolean isOrderCodeExist(String saleOrderCode) {

		long count=0;
		String hql="select count(saleOrderCode) from "+SaleOrder.class.getName()+" where saleOrderCode=?";
		List<Long> saleOrders=(List<Long>) ht.find(hql, saleOrderCode);
		if (saleOrders!=null && !saleOrders.isEmpty()) {
			count=saleOrders.get(0);
		}
		return count>0?true:false;
	}

	public void deleteSalesDetailsById(Integer salesDtlsId) {
		ht.delete(new SalesDetails(salesDtlsId));
	}

	@SuppressWarnings("unchecked")
	public Map<Integer, String> getInvoicedSaleOrders(String status) {

		DetachedCriteria hql = 
				DetachedCriteria.forClass(SaleOrder.class)
				.setProjection(Projections.projectionList()
						.add(Projections.property("saleOrderId"))
						.add(Projections.property("saleOrderCode"))
						)
				.add(Restrictions.eq("orderStatus", status));

		return AppCollectionUtil.toMap((List<Object[]>) ht.findByCriteria(hql));
	}

	public SalesDetails getSalesDetailsById(Integer salesDtlsId) {
		return ht.get(SalesDetails.class, salesDtlsId);
	}

	public void updateSalesDetails(SalesDetails salesDetails) {
		ht.update(salesDetails);
	}

	@SuppressWarnings("deprecation")
	public int updateAllSalesDetailsStatus(String shipSatus, Integer soHoId) {
		String hql = "update "+SalesDetails.class.getName()
				+ " set shipSatus=? "
				+ " where shipSatus is null and soHoId=?";
		int rowCount=ht.bulkUpdate(hql, shipSatus,soHoId);
		return rowCount;
	}

	@SuppressWarnings("unchecked")
	public long getNullShippingStatusCount(Integer saleOrderId) {
		long count=0;

		DetachedCriteria hql=
				DetachedCriteria.forClass(SalesDetails.class)
				.setProjection(Projections.projectionList()
						.add(Projections.count("salesDtlsId"))
						)
				
				.add(Restrictions.eq("soHoId",saleOrderId))
				.add(Restrictions.isNull("shipSatus"));
		List<Long> list=(List<Long>) ht.findByCriteria(hql);
		
		if (list != null && !list.isEmpty()) {
			count=list.get(0);
		}

		return count;
	}

}
