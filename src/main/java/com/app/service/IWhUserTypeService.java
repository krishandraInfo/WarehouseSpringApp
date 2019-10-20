package com.app.service;

import java.util.List;
import java.util.Map;

import com.app.model.WhUserType;

public interface IWhUserTypeService {

	public Integer saveWhUserType(WhUserType whUserType);
	public void updateWhUserType(WhUserType whUserType);
	public void deleteWhUserType(Integer whUserTypeId);
	public WhUserType getWhUserTypeById(Integer whUserTypeId);
	public List<WhUserType> getAllWhUserTypes();
	public List<Object[]> getWhUserTypeCount();
	public boolean isWhUserCodeExist(String userCode);
	public boolean isEmailOrMobileExist(String type,String userEmailOrmobile);
	public Map<Integer, String> getAllWhUserByType(String userType);
	
}
