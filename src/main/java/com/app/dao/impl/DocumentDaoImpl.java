package com.app.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.app.dao.IDocumentDao;
import com.app.model.Document;

@Repository
public class DocumentDaoImpl implements IDocumentDao {
	
	@Autowired
	private HibernateTemplate ht;

	public Integer saveDocument(Document document) {
		
		return (Integer) ht.save(document);
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<Object[]> getDocumentIdAndNames() {

		String hql = "select fileId,fileName from "+Document.class.getName();
		
		List<Object[]> documents = (List<Object[]>) ht.find(hql);
		
		return documents;
	}

	public Document getDocumentById(Integer fileId) {

		
		return ht.get(Document.class, fileId);

	}

}
