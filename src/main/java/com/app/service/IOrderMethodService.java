package com.app.service;

import java.util.List;
import java.util.Map;

import com.app.model.OrderMethod;

public interface IOrderMethodService {
	
	public Integer saveOrderMethod(OrderMethod orderMethod);
	public void updateOrderMethod(OrderMethod orderMethod);
	public void deleteOrderMethod(Integer orderId);
	public OrderMethod getOrderMethodById(Integer orderId);
	public List<OrderMethod> getAllOrderMethods();
	public List<Object[]> getOrderModeCount();
	public boolean isOrderMethodExist(String orderCode);
	public Map<Integer, String> getAllOrderMethodIdsAndCodes();

	
}
