package com.app.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.IUomDao;
import com.app.model.Uom;
import com.app.service.IUomService;

@Service
public class UomServiceImpl implements IUomService {

	@Autowired
	private IUomDao dao;
	
	@Transactional
	public Integer saveUom(Uom uom) {
		return dao.saveUom(uom);
	}

	@Transactional
	public void updateUom(Uom uom) {

		dao.updateUom(uom);
	}

	@Transactional
	public void deleteUom(Integer uomId) {

		dao.deleteUom(uomId);
	}

	@Transactional
	public Uom getUomById(Integer uomId) {

		return dao.getUomById(uomId);
	}

	@Transactional
	public List<Uom> getAllUoms() {

		return dao.getAllUoms();
	}

	@Transactional(readOnly=true)
	public List<Object[]> getuomTypeCount() {

		return dao.getuomTypeCount();
	}

	
	@Transactional(readOnly=true)
	public boolean isUomExist(String uomModel) {
		return dao.isUomExist(uomModel);
	}
	
	@Transactional(readOnly=true)
	public Map<Integer, String> getAllUomIdsAndModels() {
		return dao.getAllUomIdsAndModels();
	}

}
