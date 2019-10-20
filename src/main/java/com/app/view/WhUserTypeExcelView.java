package com.app.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import com.app.model.WhUserType;

public class WhUserTypeExcelView extends AbstractXlsxView{

	@SuppressWarnings("unchecked")
	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		response.setHeader("Content-Disposition", "attachment;filename=WhuserTypes.xlsx");
		
		List<WhUserType> whUserType=(List<WhUserType>) model.get("whUserType");
	
		Sheet sheet=workbook.createSheet("WhuserTypes");
		
		setHead(sheet);
		setBody(sheet,whUserType);
		
		
	}

	private void setHead(Sheet sheet) {

		Row row=sheet.createRow(0);
		row.createCell(0).setCellValue("ID");
		row.createCell(1).setCellValue("CODE");
		row.createCell(2).setCellValue("NAME");
		row.createCell(3).setCellValue("USER FOR");
		row.createCell(4).setCellValue("EMAIL");
		row.createCell(5).setCellValue("MOBILE");
		row.createCell(6).setCellValue("ID TYPE");
		row.createCell(7).setCellValue("ID NUMBER");
	}
	
	private void setBody(Sheet sheet, List<WhUserType> whUserType) {

		int rowNumber=1;
		for(WhUserType wh:whUserType) {
			Row row=sheet.createRow(rowNumber++);
			row.createCell(0).setCellValue(wh.getUserId().toString());
			row.createCell(1).setCellValue(wh.getUserCode());
			row.createCell(2).setCellValue(wh.getUserName());
			row.createCell(3).setCellValue(wh.getUserFor());
			row.createCell(4).setCellValue(wh.getUserEmail());
			row.createCell(5).setCellValue(wh.getUserNumber());
			row.createCell(6).setCellValue(wh.getUserIdType());
			row.createCell(7).setCellValue(wh.getUserIdNumber());
		}
		
	}

	}
