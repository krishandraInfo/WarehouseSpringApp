package com.app.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.app.model.OrderMethod;
import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class OrderMethodPdfView extends AbstractPdfView{

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {



		//set file name
		response.setHeader("Content-Disposition", "attachment;filename=OrderMethods.pdf");

		//loadind data from model
		@SuppressWarnings("unchecked")
		List<OrderMethod> orderMethod=(List<OrderMethod>) model.get("orderMethod");
		//create any element
		document.add(new Paragraph("OrderMethod Details"));

		// create table
		PdfPTable pdfPTable=new PdfPTable(6);
		pdfPTable.addCell("ID");
		pdfPTable.addCell("MODE");
		pdfPTable.addCell("CODE");
		pdfPTable.addCell("EXECUTE TYPE");
		pdfPTable.addCell("ACCEPTABLE");
		pdfPTable.addCell("NOTE");

		for(OrderMethod o :orderMethod) {
			pdfPTable.addCell(o.getOrderId().toString());
			pdfPTable.addCell(o.getOrderMode());
			pdfPTable.addCell(o.getOrderCode());
			pdfPTable.addCell(o.getOrderExeType());
			pdfPTable.addCell(o.getOrderAccept()!=null?
					o.getOrderAccept().toString():"[]");
			pdfPTable.addCell(o.getOrderDecs());
		}
		document.add(pdfPTable);

	}

}
