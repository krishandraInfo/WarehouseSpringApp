package com.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.IDocumentDao;
import com.app.model.Document;
import com.app.service.IDocumentService;

@Service
public class DocumentServiceImpl implements IDocumentService{
	
	@Autowired
	private IDocumentDao dao;

	@Transactional
	public Integer saveDocument(Document document) {

		return dao.saveDocument(document);
	}

	@Override
	public List<Object[]> getDocumentIdAndNames() {

		List<Object[]> documents=dao.getDocumentIdAndNames();
		
		return documents;
	}

	@Transactional(readOnly=true)
	public Document getDocumentById(Integer fileId) {
		
		return dao.getDocumentById(fileId);
	}

}
