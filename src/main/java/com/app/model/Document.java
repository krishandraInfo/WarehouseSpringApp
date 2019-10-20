package com.app.model;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name="doc_tab")
public class Document {

	@Id
	@GeneratedValue
	@Column(name="fid")
	private Integer fileId;
	@Column(name="fname")
	private String fileName;
	@Column(name="fdata")
	@Lob
	private byte[] fileData;
	public Document() {
		super();
	}
	public Document(Integer fileId) {
		super();
		this.fileId = fileId;
	}
	public Integer getFileId() {
		return fileId;
	}
	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public byte[] getFileData() {
		return fileData;
	}
	public void setFileData(byte[] fileData) {
		this.fileData = fileData;
	}
	@Override
	public String toString() {
		return "Document [fileId=" + fileId + ", fileName=" + fileName + ", fileData=" + Arrays.toString(fileData)
				+ "]";
	}
	
	
}
