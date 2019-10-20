package com.app.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import com.app.model.Uom;


public class UomExcelView extends AbstractXlsxView{

	@SuppressWarnings("unchecked")
	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		// set file name 
		response.setHeader("Content-Disposition", "attachment;filename=Uoms.xlsx");
		
		// read data from model
		List<Uom> uom= (List<Uom>) model.get("uom");
		// create sheet with name as uom deatails
		Sheet sheet=workbook.createSheet("Uom Details");
		//set head row as #0
		setHead(sheet);
		//set  body row 
		setBody(sheet,uom);
	}

	private void setHead(Sheet sheet) {
		
		Row row=sheet.createRow(0);
		row.createCell(0).setCellValue("ID");
		row.createCell(1).setCellValue("TYPE");
		row.createCell(2).setCellValue("MODEL");
		row.createCell(3).setCellValue("NOTE");
	}

	private void setBody(Sheet sheet, List<Uom> uom) {
		
		int rowCount=1;
		for (Uom u : uom) {
			Row row=sheet.createRow(rowCount++);
			row.createCell(0).setCellValue(u.getUomId());
			row.createCell(1).setCellValue(u.getUomType());
			row.createCell(2).setCellValue(u.getUomModel());
			row.createCell(3).setCellValue(u.getUomDesc());
		}
		
	}

}
