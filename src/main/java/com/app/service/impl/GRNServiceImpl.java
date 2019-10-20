package com.app.service.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.IGRNDao;
import com.app.model.GoodRecieveNote;
import com.app.service.IGRNService;

@Service
public class GRNServiceImpl implements IGRNService {
	
	@Autowired
	private IGRNDao dao;

	@Transactional
	public Integer saveGRN(GoodRecieveNote grn) {
		return dao.saveGRN(grn);
	}

	@Transactional
	public void updateGRN(GoodRecieveNote grn) {
		dao.updateGRN(grn);
	}

	@Transactional
	public void deleteGRN(Integer grnId) {
		dao.deleteGRN(grnId);
	}

	
	@Transactional(readOnly=true)
	public GoodRecieveNote getGRNById(Integer grnId) {
		return dao.getGRNById(grnId);
	}

	@Transactional(readOnly=true)
	public List<GoodRecieveNote> getAllGRNs() {
		return dao.getAllGRNs();
	}

	@Transactional(readOnly=true)
	public boolean isOrderCodeExist(String grnCode) {
		return dao.isOrderCodeExist(grnCode);
	}

}
