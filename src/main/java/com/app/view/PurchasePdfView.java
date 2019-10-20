package com.app.view;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.app.model.Purchase;
import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class PurchasePdfView extends AbstractPdfView{

	@SuppressWarnings("unchecked")
	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		//set file name
		response.setHeader("Content-Disposition", "attachment;filename=Purchases.pdf");


		// read date from model fdata
		List<Purchase> purchase=(List<Purchase>) model.get("purchase");

		//create any element
		document.add(new Paragraph("ALL PURCHASES"));

		PdfPTable pdfPTable=new PdfPTable(8);
		pdfPTable.addCell("ID");
		pdfPTable.addCell("CODE");
		pdfPTable.addCell("REF NUM");
		pdfPTable.addCell("QC");
		pdfPTable.addCell("STATUS");
		pdfPTable.addCell("NOTE");
		pdfPTable.addCell("VENDOR NAME");
		pdfPTable.addCell("SHIPMENT CODE");

		for(Purchase p:purchase){
			pdfPTable.addCell(p.getOrderId().toString());
			pdfPTable.addCell(p.getOrderCode());
			pdfPTable.addCell(p.getRefNumber());
			pdfPTable.addCell(p.getQuaCheck());
			pdfPTable.addCell(p.getOrderStatus());
			pdfPTable.addCell(p.getOrderDesc());
			pdfPTable.addCell(p.getWhUserType().getUserName());
			pdfPTable.addCell(p.getShipmentType().getShipmentCode());
		}
		document.add(pdfPTable);
		document.add(new Paragraph(new Date().toString()));

	}

}
