package com.app.service;

import java.util.List;

import com.app.model.Document;

public interface IDocumentService {
	
	public Integer saveDocument(Document document);
	public List<Object[]> getDocumentIdAndNames();
	public Document getDocumentById(Integer fileId);

}
