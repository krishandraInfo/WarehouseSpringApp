package com.app.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.app.model.Uom;
import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class UomPdfView extends AbstractPdfView{

	@SuppressWarnings("unchecked")
	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		//set file name
		response.setHeader("Content-Disposition", "attachment;filename=Uoms.pdf");

		//loadind data from model
		List<Uom> uom=(List<Uom>) model.get("uom");
		//create any element
		document.add(new Paragraph("Uom Details"));

		// create table
		PdfPTable pdfPTable=new PdfPTable(4);
		pdfPTable.addCell("ID");
		pdfPTable.addCell("TYPE");
		pdfPTable.addCell("MODEL");
		pdfPTable.addCell("NOTE");

		for(Uom u :uom) {
			pdfPTable.addCell(u.getUomId().toString());
			pdfPTable.addCell(u.getUomType());
			pdfPTable.addCell(u.getUomModel());
			pdfPTable.addCell(u.getUomDesc());

		}
		document.add(pdfPTable);

	}
}

