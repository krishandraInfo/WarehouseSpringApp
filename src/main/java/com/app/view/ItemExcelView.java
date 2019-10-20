package com.app.view; 

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import com.app.model.Item; 

public class ItemExcelView extends AbstractXlsxView{ 

	@SuppressWarnings("unchecked")
	@Override 
	protected void buildExcelDocument(   
			Map<String, Object> model,    
			Workbook workbook,     HttpServletRequest request,    
			HttpServletResponse response) throws Exception {   
	
		response.setHeader("Content-Disposition", "attachment;filename=Items.xlsx");
		
		
		
		//reading data from model   
		List<Item> item=(List<Item>) model.get("item");  
		//creating sheet
		Sheet sheet=workbook.createSheet("Items");   
		//set Head-row-0
		setHead(sheet);   
		//set Body-Row#1
		setBody(sheet,item);    
	} 

	private void setHead(Sheet sheet) {   
		Row row=sheet.createRow(0);   
		row.createCell(0).setCellValue("ID");   
		row.createCell(1).setCellValue("CODE");   
		row.createCell(2).setCellValue("DIMENSIONS");  
		row.createCell(3).setCellValue("COST");  
		row.createCell(4).setCellValue("CURRENCY");  
		row.createCell(5).setCellValue("NOTE");    
		row.createCell(6).setCellValue("UOM");    
		row.createCell(7).setCellValue("OREDER METHOD");    
	}   
	private void setBody(Sheet sheet, List<Item> item) {   
		int rowNum=1;   
		for(Item i:item) {    
			Row row=sheet.createRow(rowNum++);    
			row.createCell(0).setCellValue(i.getItemId());    
			row.createCell(1).setCellValue(i.getItemCode()); 
			row.createCell(2).setCellValue(i.getItemLength()+"(Length),"+i.getItemWidth()+"(Width),"+i.getItemHeight()+"(Height)");    
			row.createCell(3).setCellValue(i.getItemBaseCost());    
			row.createCell(4).setCellValue(i.getItemCurrentCurrency());   
			row.createCell(5).setCellValue(i.getItemDesc());   
			row.createCell(6).setCellValue(i.getUom().getUomModel());   
			row.createCell(7).setCellValue(i.getOrderMethod().getOrderMode());   
		}
	}
} 
