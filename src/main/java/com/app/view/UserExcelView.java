package com.app.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import com.app.model.User;

public class UserExcelView extends AbstractXlsxView{

	@SuppressWarnings("unchecked")
	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// set file name 
				response.setHeader("Content-Disposition", "attachment;filename=User.xlsx");
				
				// read data from model
				List<User> user= (List<User>) model.get("user");
				// create sheet with name as uom deatails
				Sheet sheet=workbook.createSheet("User Details");
				//set head row as #0
				setHead(sheet);
				//set  body row 
				setBody(sheet,user);
			}

			private void setHead(Sheet sheet) {
				
				Row row=sheet.createRow(0);
				row.createCell(0).setCellValue("ID");
				row.createCell(1).setCellValue("NAME");
				row.createCell(1).setCellValue("GENDER");
				row.createCell(2).setCellValue("EMAIL");
				row.createCell(3).setCellValue("MOBILE");
				row.createCell(3).setCellValue("PASSWORD");
				row.createCell(3).setCellValue("ROLES");
			}

			private void setBody(Sheet sheet, List<User> user) {
				
				int rowCount=1;
				for (User u : user) {
					Row row=sheet.createRow(rowCount++);
					row.createCell(0).setCellValue(u.getUserId());
					row.createCell(1).setCellValue(u.getUserName());
					row.createCell(1).setCellValue(u.getGender());
					row.createCell(2).setCellValue(u.getUserEmail());
					row.createCell(3).setCellValue(u.getUserMobile());
					row.createCell(3).setCellValue(u.getUserPassword());
					row.createCell(3).setCellValue(u.getUserRoles()!=null?u.getUserRoles().toString():"[]");
				}
				
			}
	}