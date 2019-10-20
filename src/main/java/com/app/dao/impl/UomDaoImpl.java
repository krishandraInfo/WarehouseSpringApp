package com.app.dao.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.app.dao.IUomDao;
import com.app.model.Uom;
import com.app.util.AppCollectionUtil;

@Repository
public class UomDaoImpl implements IUomDao{

	@Autowired
	private HibernateTemplate ht;

	public Integer saveUom(Uom uom) {
		return (Integer) ht.save(uom);
	}

	public void updateUom(Uom uom) {
		ht.update(uom);
	}

	public void deleteUom(Integer uomId) {
		ht.delete(new Uom(uomId));
	}

	public Uom getUomById(Integer uomId) {

		return ht.get(Uom.class, uomId);
	}

	public List<Uom> getAllUoms() {

		return ht.loadAll(Uom.class);
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> getuomTypeCount() {

		/*String hql =   " select uomType,count(uomType) from "
				+ Uom.class.getName()
				+ " group by uomType";*/
		DetachedCriteria hql=
				DetachedCriteria.forClass(Uom.class)
				.setProjection(
						Projections.projectionList()
						.add(Projections.groupProperty("uomType"))
						.add(Projections.count("uomType"))
						);
		List<Object[]> uomTypes=(List<Object[]>) ht.findByCriteria(hql);
		return uomTypes;
	}

	@SuppressWarnings("unchecked")
	public boolean isUomExist(String uomModel) {

		long count=0;
		//String hql= "select count(uomModel) from "+Uom.class.getName()+" where uomModel=?";
		DetachedCriteria hql=
				DetachedCriteria.forClass(Uom.class)
				.setProjection(Projections.count("uomModel"))
				.add(Restrictions.eq("uomModel", uomModel))
				;

		List<Long> uoms=(List<Long>) ht.findByCriteria(hql);

		if (uoms!=null && !uoms.isEmpty()) {
			count=uoms.get(0);
		} 
		return count>0?true:false;
	}

	@SuppressWarnings("unchecked")
	public Map<Integer, String> getAllUomIdsAndModels() {

		//String hql = "select uomId,uomModel from "+Uom.class.getName();
		DetachedCriteria hql=
				DetachedCriteria.forClass(Uom.class)
				.setProjection(
						Projections.projectionList()
						.add(Projections.property("uomId"))
						.add(Projections.property("uomModel"))
						);

		List<Object[]> list=(List<Object[]>)ht.findByCriteria(hql);
		return AppCollectionUtil.toMap(list);
	}
}




