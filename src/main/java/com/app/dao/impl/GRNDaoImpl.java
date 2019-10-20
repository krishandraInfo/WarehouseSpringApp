package com.app.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.app.dao.IGRNDao;
import com.app.model.GoodRecieveNote;

@Repository
public class GRNDaoImpl implements IGRNDao {
	
	@Autowired
	private HibernateTemplate ht;

	public Integer saveGRN(GoodRecieveNote grn) {
		return (Integer) ht.save(grn);
	}

	public void updateGRN(GoodRecieveNote grn) {
		ht.update(grn);
	}

	public void deleteGRN(Integer grnId) {
		ht.delete(new GoodRecieveNote(grnId));
	}

	public GoodRecieveNote getGRNById(Integer grnId) {
		return ht.get(GoodRecieveNote.class, grnId);
	}

	public List<GoodRecieveNote> getAllGRNs() {
		return ht.loadAll(GoodRecieveNote.class);
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	public boolean isOrderCodeExist(String grnCode) {
		
		long count=0;
		String hql = "select count(grnCode) from "+GoodRecieveNote.class.getName()+" where grnCode=?";
		List<Long> grns=(List<Long>) ht.find(hql, grnCode);
		if (grns!=null && !grns.isEmpty()) {
			count=grns.get(0);
		}
		return count>0?true:false;
	}

}
