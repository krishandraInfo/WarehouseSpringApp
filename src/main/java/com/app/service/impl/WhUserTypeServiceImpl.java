package com.app.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.IWhUserTypeDao;
import com.app.model.WhUserType;
import com.app.service.IWhUserTypeService;

@Service
public class WhUserTypeServiceImpl implements IWhUserTypeService{

	@Autowired
	private IWhUserTypeDao dao;
	
	@Transactional
	public Integer saveWhUserType(WhUserType whUserType) {
		return dao.saveWhUserType(whUserType);
	}

	@Transactional
	public void updateWhUserType(WhUserType whUserType) {
		dao.updateWhUserType(whUserType);
	}

	@Transactional
	public void deleteWhUserType(Integer whUserTypeId) {
		dao.deleteWhUserType(whUserTypeId);
	}

	@Transactional(readOnly=true)
	public WhUserType getWhUserTypeById(Integer whUserTypeId) {
		return dao.getWhUserTypeById(whUserTypeId);
	}

	@Transactional(readOnly=true)
	public List<WhUserType> getAllWhUserTypes() {
		return dao.getAllWhUserTypes();
	}

	@Transactional(readOnly=true)
	public List<Object[]> getWhUserTypeCount() {
		
		return dao.getWhUserTypeCount();
	}

	@Transactional(readOnly=true)
	public boolean isWhUserCodeExist(String userCode) {
		return dao.isWhUserCodeExist(userCode);
	}

	@Transactional(readOnly=true)
	public Map<Integer, String> getAllWhUserByType(String userType) {
		return dao.getAllWhUserByType(userType);
	}

	@Transactional(readOnly=true)
	public boolean isEmailOrMobileExist(String type, String userEmailOrmobile) {
		return dao.isEmailOrMobileExist(type, userEmailOrmobile);
	}
}