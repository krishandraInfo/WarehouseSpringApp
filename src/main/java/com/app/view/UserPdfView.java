package com.app.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.app.model.User;
import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class UserPdfView extends AbstractPdfView{

	@SuppressWarnings("unchecked")
	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		//set file name
				response.setHeader("Content-Disposition", "attachment;filename=User.pdf");

				//loadind data from model
				List<User> user=(List<User>) model.get("user");
				//create any element
				document.add(new Paragraph("User Details"));

				// create table
				PdfPTable pdfPTable=new PdfPTable(7);
				pdfPTable.addCell("ID");
				pdfPTable.addCell("NAME");
				pdfPTable.addCell("GENDER");
				pdfPTable.addCell("EMAIL");
				pdfPTable.addCell("MOBILE");
				pdfPTable.addCell("PASSWORD");
				pdfPTable.addCell("ROLES");

				for(User u :user) {
					pdfPTable.addCell(u.getUserId().toString());
					pdfPTable.addCell(u.getUserName());
					pdfPTable.addCell(u.getGender());
					pdfPTable.addCell(u.getUserEmail());
					pdfPTable.addCell(u.getUserMobile());
					pdfPTable.addCell(u.getUserPassword());
					pdfPTable.addCell(u.getUserRoles()!=null?u.getUserRoles().toString():"[]");

				}
				document.add(pdfPTable);

			}
	}