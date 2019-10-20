package com.app.view;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.app.model.ShipmentType;
import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class ShipmentTypePdfView extends AbstractPdfView{

	@SuppressWarnings("unchecked")
	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//set file name
		response.setHeader("Content-Disposition", "attachment;filename=Shipments.pdf");

		
		// read date from model fdata
		List<ShipmentType> shipmentType=(List<ShipmentType>) model.get("shipmentType");
		
		//create any element
		document.add(new Paragraph("ALL SHIPMENT TYPES"));
		
		PdfPTable pdfPTable=new PdfPTable(6);
		pdfPTable.addCell("ID");
		pdfPTable.addCell("MODE");
		pdfPTable.addCell("CODE");
		pdfPTable.addCell("STATUS");
		pdfPTable.addCell("GRADE");
		pdfPTable.addCell("NOTE");
		
		for(ShipmentType s:shipmentType){
			pdfPTable.addCell(s.getShipmentid().toString());
			pdfPTable.addCell(s.getShipmentMode());
			pdfPTable.addCell(s.getShipmentCode());
			pdfPTable.addCell(s.getEnableShipment());
			pdfPTable.addCell(s.getShipmentGrade());
			pdfPTable.addCell(s.getShipDesc());
		}
		document.add(pdfPTable);
		document.add(new Paragraph(new Date().toString()));
		
	}

}
