package com.app.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.IShipmentTypeDao;
import com.app.model.ShipmentType;
import com.app.service.IShipmentTypeService;

@Service
public class ShipmentTypeServiceImpl implements IShipmentTypeService {

	@Autowired
	private IShipmentTypeDao dao;

	@Transactional
	public Integer saveShipmentType(ShipmentType shipmentType) {
		return dao.saveShipmentType(shipmentType);
	}

	@Transactional
	public void updateShipmentType(ShipmentType shipmentType) {
		dao.updateShipmentType(shipmentType);
	}

	@Transactional
	public void deleteShipmentType(Integer shipmentId) {

		dao.deleteShipmentType(shipmentId);

	}

	@Transactional(readOnly=true)
	public ShipmentType getShipmentTypeById(Integer shipmentId) {
		return dao.getShipmentTypeById(shipmentId);
	}

	@Transactional(readOnly=true)
	public List<ShipmentType> getAllShipmentTypes() {
		return dao.getAllShipmentTypes();
	}

	@Transactional(readOnly=true)
	public List<Object[]> getShipmentTypeCount() {

		return dao.getShipmentTypeCount();
	}

	@Transactional(readOnly=true)
	public boolean isShipmentCodeExist(String shipmentCode) {
		return dao.isShipmentCodeExist(shipmentCode);
	}

	@Transactional(readOnly=true)
	public Map<Integer, String> getEnableShipmentIdsAndCodes() {
		return dao.getEnableShipmentIdsAndCodes();
	}

}
