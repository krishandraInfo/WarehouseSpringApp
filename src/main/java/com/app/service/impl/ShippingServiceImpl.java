package com.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.IShippingDao;
import com.app.model.Shipping;
import com.app.service.IShippingService;

@Service
public class ShippingServiceImpl implements IShippingService {

	@Autowired
	private IShippingDao dao;

	@Transactional
	public Integer saveShipping(Shipping shipping) {
		return dao.saveShipping(shipping);
	}

	@Transactional
	public void updateShipping(Shipping shipping) {
		dao.updateShipping(shipping);
	}

	@Transactional
	public void deleteShipping(Integer shipId) {
		dao.deleteShipping(shipId);
	}

	@Transactional(readOnly=true)
	public Shipping getShippingById(Integer shipId) {
		return dao.getShippingById(shipId);
	}

	@Transactional(readOnly=true)
	public List<Shipping> getAllShippings() {
		return dao.getAllShippings();
	}

	@Transactional(readOnly=true)
	public boolean isShippingCodeExist(String shipCode) {
		return dao.isShippingCodeExist(shipCode);
	}

}
