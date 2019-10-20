package com.app.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import com.app.model.OrderMethod;

public class OrderMethodExcelView extends AbstractXlsxView{

	@SuppressWarnings("unchecked")
	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		//set file name
		response.setHeader("Content-Disposition", "attachment;filename=OrderMethods.xlsx");
		
		// get data from model
		List<OrderMethod> orderMethod= (List<OrderMethod>) model.get("orderMethod");
		
		Sheet sheet=workbook.createSheet("Order Methods");
		
		// set row head 0
		setHead(sheet);
		//set body 1
		setBody(sheet,orderMethod);
	}

	private void setHead(Sheet sheet) {
		Row row=sheet.createRow(0);
		row.createCell(0).setCellValue("ID");
		row.createCell(1).setCellValue("MODE");
		row.createCell(2).setCellValue("CODE");
		row.createCell(3).setCellValue("EXETYPE");
		row.createCell(4).setCellValue("ACCEPT");
		row.createCell(5).setCellValue("NOTE");
		
	}

	private void setBody(Sheet sheet, List<OrderMethod> orderMethod) {
		int rowCount=1;
		for (OrderMethod o : orderMethod) {
			Row row=sheet.createRow(rowCount++);
			row.createCell(0).setCellValue(o.getOrderId());
			row.createCell(1).setCellValue(o.getOrderMode());
			row.createCell(2).setCellValue(o.getOrderCode());
			row.createCell(3).setCellValue(o.getOrderExeType());
			row.createCell(4).setCellValue(o.getOrderAccept()!=null?
					o.getOrderAccept().toString():"[]");
			row.createCell(5).setCellValue(o.getOrderDecs());
		}
		
	}

}
