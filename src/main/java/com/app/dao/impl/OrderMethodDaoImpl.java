package com.app.dao.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.app.dao.IOrderMethodDao;
import com.app.model.OrderMethod;
import com.app.util.AppCollectionUtil;

@Repository
public class OrderMethodDaoImpl implements IOrderMethodDao{

	@Autowired
	private HibernateTemplate ht;

	public Integer saveOrderMethod(OrderMethod orderMethod) {
		return (Integer) ht.save(orderMethod);
	}

	public void updateOrderMethod(OrderMethod orderMethod) {
		ht.update(orderMethod);
	}

	public void deleteOrderMethod(Integer orderId) {
		ht.delete(new OrderMethod(orderId));
	}

	public OrderMethod getOrderMethodById(Integer orderId) {
		return ht.get(OrderMethod.class, orderId);
	}

	public List<OrderMethod> getAllOrderMethods() {
		return ht.loadAll(OrderMethod.class);
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> getOrderModeCount() {

		//String hql=" select orderMode,count(orderMode) from com.app.model.OrderMethod group by orderMode";
		DetachedCriteria hql=
				DetachedCriteria.forClass(OrderMethod.class)
				.setProjection(
						Projections.projectionList()
						.add(Projections.groupProperty("orderMode"))
						.add(Projections.count("orderMode"))
						);
		List<Object[]> orderModes=(List<Object[]>) ht.findByCriteria(hql);

		return orderModes;
	}

	@SuppressWarnings("unchecked")
	public boolean isOrderMethodExist(String orderCode) {
		
		long count=0;
		//String hql= "select count(orderCode) from "+OrderMethod.class.getName()+" where orderCode=?";
		DetachedCriteria hql=
				DetachedCriteria.forClass(OrderMethod.class)
				.setProjection(Projections.count("orderCode"))
				.add(Restrictions.eq("orderCode", orderCode))
				;

		List<Long> orderMethods=(List<Long>) ht.findByCriteria(hql);
			
		if (orderMethods!=null && !orderMethods.isEmpty()) {
			count=orderMethods.get(0);
		} else {
		}
		return count>0?true:false;
	}

	@SuppressWarnings("unchecked")
	public Map<Integer, String> getAllOrderMethodIdsAndCodes() {
		
		//String hql="select orderId,orderCode from "+OrderMethod.class.getName();
		DetachedCriteria hql=
				DetachedCriteria.forClass(OrderMethod.class)
				.setProjection(
						Projections.projectionList()
						.add(Projections.property("orderId"))
						.add(Projections.property("orderCode"))
						);

		List<Object[]> list=(List<Object[]>)ht.findByCriteria(hql);
		return AppCollectionUtil.toMap(list);
	}
}
