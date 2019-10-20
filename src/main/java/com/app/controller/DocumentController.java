package com.app.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.app.model.Document;
import com.app.service.IDocumentService;

@Controller
@RequestMapping("/document")
public class DocumentController {

	@Autowired
	private IDocumentService service;

	//1.Show Document Uplaod Page
	@RequestMapping("/uploadPage")
	public String showDocPage() {

		return "Documents";
	}

	//2. upload documnnt
	@RequestMapping(value="/upload",method=RequestMethod.POST)
	public String documentUpload(
			@RequestParam CommonsMultipartFile commonsMultipartFile,
			ModelMap map) {

		Document document=new Document();
		document.setFileName(commonsMultipartFile.getOriginalFilename());
		document.setFileData(commonsMultipartFile.getBytes());

		map.addAttribute("message", document.getFileName()+" is saved with Id  :"+service.saveDocument(document));
		map.addAttribute("documents",service.getDocumentIdAndNames());
		return "Documents";
	}

	//3. get Documents
	@RequestMapping("/getAllDocuments")
	public String getAllDocuments(ModelMap map) {

		map.addAttribute("documents",service.getDocumentIdAndNames());

		return "Documents";
	}

	//4. Download One document
	@RequestMapping("/download")
	public String donloadOneDocument(@RequestParam Integer fileId,HttpServletResponse response) {
		
		Document document=service.getDocumentById(fileId);
		response.setHeader("Content-Disposition", "attachment;filename="+document.getFileName());
		
		try {
			FileCopyUtils.copy(document.getFileData(), response.getOutputStream());
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return null;

	}




















}
