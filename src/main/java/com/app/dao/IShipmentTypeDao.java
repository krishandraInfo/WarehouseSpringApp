package com.app.dao;

import java.util.List;
import java.util.Map;

import com.app.model.ShipmentType;


public interface IShipmentTypeDao {
	
	public Integer saveShipmentType(ShipmentType shipmentType);
	public void updateShipmentType(ShipmentType shipmentType);
	public void deleteShipmentType(Integer shipmentId);
	public ShipmentType getShipmentTypeById(Integer shipmentId);
	public List<ShipmentType> getAllShipmentTypes();
	public List<Object[]> getShipmentTypeCount();
	public boolean isShipmentCodeExist(String shipmentCode);
	public Map<Integer, String> getEnableShipmentIdsAndCodes();
	
}
