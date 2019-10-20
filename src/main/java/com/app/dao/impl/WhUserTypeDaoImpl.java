package com.app.dao.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.app.dao.IWhUserTypeDao;
import com.app.model.WhUserType;
import com.app.util.AppCollectionUtil;

@Repository
public class WhUserTypeDaoImpl implements IWhUserTypeDao {

	@Autowired
	private HibernateTemplate ht; 

	public Integer saveWhUserType(WhUserType whUserType) {
		return (Integer) ht.save(whUserType);
	}
	public void updateWhUserType(WhUserType whUserType) {
		ht.update(whUserType);
	}
	public void deleteWhUserType(Integer whUserTypeId) {
		ht.delete(new WhUserType(whUserTypeId));
	}
	public WhUserType getWhUserTypeById(Integer whUserTypeId) {
		return ht.get(WhUserType.class, whUserTypeId);
	}
	public List<WhUserType> getAllWhUserTypes() {
		return ht.loadAll(WhUserType.class);
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> getWhUserTypeCount() {

		/*String hql=  " select userFor,count(userFor) from "
				+ WhUserType.class.getName()
				+ " group by userFor";*/
		DetachedCriteria hql=
				DetachedCriteria.forClass(WhUserType.class)
				.setProjection(
						Projections.projectionList()
						.add(Projections.groupProperty("userFor"))
						.add(Projections.count("userFor"))
						);
		List<Object[]> whUserTypes = (List<Object[]>) ht.findByCriteria(hql);

		return whUserTypes;
	}

	@SuppressWarnings("unchecked")
	public boolean isWhUserCodeExist(String userCode) {

		long count=0;
		//String hql="select count(userCode) from "+WhUserType.class.getName()+" where userCode=?";
		DetachedCriteria hql=
				DetachedCriteria.forClass(WhUserType.class)
				.setProjection(Projections.count("userCode"))
				.add(Restrictions.eq("userCode", userCode))
				;

		List<Long> whUserType=(List<Long>) ht.findByCriteria(hql);

		if(whUserType!=null && !whUserType.isEmpty()) {
			count=whUserType.get(0);
		}
		return count>0?true:false;
	}

	@SuppressWarnings("unchecked")
	public Map<Integer, String> getAllWhUserByType(String userType) {
		//String hql = "select userId,userCode from "+WhUserType.class.getName()+" where userType=?";
		DetachedCriteria hql=
				DetachedCriteria.forClass(WhUserType.class)
				.setProjection(
						Projections.projectionList()
						.add(Projections.property("userId"))
						.add(Projections.property("userCode"))

						)
				.add(Restrictions.eq("userType", userType))
				;
		List<Object[]> list=(List<Object[]>) ht.findByCriteria(hql);
		return AppCollectionUtil.toMap(list);
	}

	@SuppressWarnings("unchecked")
	public boolean isEmailOrMobileExist(String type, String userEmailOrmobile) {
		long count=0;
		//String hql="select count("+type+") from "+WhUserType.class.getName()+" where "+type+"=?";

		DetachedCriteria hql=
				DetachedCriteria.forClass(WhUserType.class)
				.setProjection(Projections.count(type))
				.add(Restrictions.eq(type, userEmailOrmobile));

		List<Long> user=(List<Long>) ht.findByCriteria(hql);

		if (user!=null && !user.isEmpty()) {
			count=user.get(0);
		}
		return count>0?true:false;
	}

}
