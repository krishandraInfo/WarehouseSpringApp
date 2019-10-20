package com.app.view;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.app.model.Item;
import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class ItemPdfView extends AbstractPdfView{

	@SuppressWarnings("unchecked")
	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//set file name
		response.setHeader("Content-Disposition", "attachment;filename=Shipments.pdf");

		
		// read date from model fdata
		List<Item> item=(List<Item>) model.get("item");
		
		//create any element
		document.add(new Paragraph("ALL SHIPMENT TYPES"));
		
		PdfPTable pdfPTable=new PdfPTable(8);
		pdfPTable.addCell("ID");
		pdfPTable.addCell("CODE");
		pdfPTable.addCell("DIMENSIONS");
		pdfPTable.addCell("COST");
		pdfPTable.addCell("CURRENCY");
		pdfPTable.addCell("NOTE");
		pdfPTable.addCell("UOM MODEL");
		pdfPTable.addCell("ORDERMETHOD MODEL");
		
		for(Item i:item){
			pdfPTable.addCell(i.getItemId().toString());
			pdfPTable.addCell(i.getItemCode());
			pdfPTable.addCell(i.getItemLength()+"(Length),"+i.getItemWidth()+"(Width),"+i.getItemHeight()+"(Height)");
			pdfPTable.addCell(i.getItemBaseCost().toString());
			pdfPTable.addCell(i.getItemCurrentCurrency());
			pdfPTable.addCell(i.getItemDesc());
			pdfPTable.addCell(i.getUom().getUomModel());
			pdfPTable.addCell(i.getOrderMethod().getOrderMode());
		}
		document.add(pdfPTable);
		document.add(new Paragraph(new Date().toString()));
		
	}

}
