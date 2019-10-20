package com.app.view;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.app.model.WhUserType;
import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class WhUserTypePdfView extends AbstractPdfView{

	@SuppressWarnings("unchecked")
	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		//set file name
				response.setHeader("Content-Disposition", "attachment;filename=WhUserTypes.pdf");

				//loadind data from model
				List<WhUserType> whUserType=(List<WhUserType>) model.get("whUserType");
				//create any element
				document.add(new Paragraph("WhUserType Details"));

				// create table
				PdfPTable pdfPTable=new PdfPTable(8);
				pdfPTable.addCell("ID");
				pdfPTable.addCell("USER CODE");
				pdfPTable.addCell("USER NAME");
				pdfPTable.addCell("USER FOR");
				pdfPTable.addCell("EMAIL");
				pdfPTable.addCell("MOBILE");
				pdfPTable.addCell("ID TYPE");
				pdfPTable.addCell("ID NUMBER");

				for(WhUserType w :whUserType) {
					pdfPTable.addCell(w.getUserId().toString());
					pdfPTable.addCell(w.getUserCode());
					pdfPTable.addCell(w.getUserName());
					pdfPTable.addCell(w.getUserFor());
					pdfPTable.addCell(w.getUserEmail());
					pdfPTable.addCell(w.getUserNumber());
					pdfPTable.addCell(w.getUserIdType());
					pdfPTable.addCell(w.getUserIdNumber());
				}
				document.add(pdfPTable);

				document.add(new Paragraph(new Date().toString()));
			}
	}

