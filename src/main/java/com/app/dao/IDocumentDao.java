package com.app.dao;

import java.util.List;

import com.app.model.Document;

public interface IDocumentDao {

	public Integer saveDocument(Document document);
	public List<Object[]> getDocumentIdAndNames();
	public Document getDocumentById(Integer fileId);

}
