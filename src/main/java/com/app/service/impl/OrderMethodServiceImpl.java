package com.app.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.IOrderMethodDao;
import com.app.model.OrderMethod;
import com.app.service.IOrderMethodService;

@Service
public class OrderMethodServiceImpl implements IOrderMethodService {

	@Autowired
	private IOrderMethodDao dao;

	@Transactional
	public Integer saveOrderMethod(OrderMethod orderMethod) {
		return dao.saveOrderMethod(orderMethod);
	}

	@Transactional
	public void updateOrderMethod(OrderMethod orderMethod) {

		dao.updateOrderMethod(orderMethod);
	}

	@Transactional
	public void deleteOrderMethod(Integer orderId) {

		dao.deleteOrderMethod(orderId);
	}

	@Transactional(readOnly=true)
	public OrderMethod getOrderMethodById(Integer orderId) {

		return dao.getOrderMethodById(orderId);
	}

	@Transactional(readOnly=true)
	public List<OrderMethod> getAllOrderMethods() {

		return dao.getAllOrderMethods();
	}
	@Transactional(readOnly=true)
	public List<Object[]> getOrderModeCount() {
		
		return dao.getOrderModeCount();
	}

	@Transactional(readOnly=true)
	public boolean isOrderMethodExist(String orderCode) {
		return dao.isOrderMethodExist(orderCode);
	}

	@Transactional(readOnly=true)
	public Map<Integer, String> getAllOrderMethodIdsAndCodes() {
		return dao.getAllOrderMethodIdsAndCodes();
	}

}
