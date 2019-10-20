package com.app.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.app.model.ShipmentType;
import com.app.service.IShipmentTypeService;
import com.app.util.ShipmentTypeUtil;
import com.app.validator.ShipmentTypeValidator;
import com.app.view.ShipmentTypeExcelView;
import com.app.view.ShipmentTypePdfView;

@Controller
@RequestMapping("/shipment")
public class ShipmentTypeController {

	@Autowired
	private IShipmentTypeService service;
	@Autowired
	private ServletContext servletContex;
	@Autowired
	private ShipmentTypeUtil util;
	@Autowired
	private ShipmentTypeValidator validator;

	// 1.Show Register Page
	@RequestMapping("/register")
	public String showReg(ModelMap map) {
		map.addAttribute("shipmentType", new ShipmentType());
		return "ShipmentTypeRegister";
	}

	// 2.Insert Data in DB
	@RequestMapping(value="/insert",method=RequestMethod.POST)
	public String save(@ModelAttribute ShipmentType shipmentType,Errors errors,ModelMap map) {

		validator.validate(shipmentType, errors);
		if (errors.hasErrors()) {
			map.addAttribute("message", "Please fill all fields!");
		} else {

			map.addAttribute("message", "Shipment saved with Id :"+service.saveShipmentType(shipmentType));
			map.addAttribute("shipmentType", new ShipmentType());
			/*int id = service.saveShipmentType(shipmentType);
			String msg = "Shipment saved with Id:"+id;
			map.addAttribute("message",msg);
			map.addAttribute("shipmentType", new ShipmentType());*/
		}
		return "ShipmentTypeRegister";

	}

	/*// 3.View All Records
	@RequestMapping("/viewAll")
	public String vewAll(ModelMap map) {
		List<ShipmentType> shipmentType=service.getAllShipmentTypes();
		map.addAttribute("shipmentType", shipmentType);
		return "ShipmentTypeData";
	}
	 */
	
	// 4.Delete Record
	@RequestMapping("/delete")
	public String delete(@RequestParam Integer shipmentId,ModelMap map) {
		
		try {
			//delete row
			service.deleteShipmentType(shipmentId);
			map.addAttribute("message", "One Shipment Details are deleted with Id : "+shipmentId);
		} catch (Exception e) {
			map.addAttribute("message", "Shipment Details are not found with Id : "+shipmentId);
		}

		//read data
		List<ShipmentType> shipmentType=service.getAllShipmentTypes();
		//add data to ui
		map.addAttribute("shipmentType", shipmentType);
		return "ShipmentTypeData";	
	}

	/*// 5.view one row or object
	@RequestMapping("/viewOne")
	public String viewOneObject(@RequestParam Integer shipmentId,ModelMap map) {
		//call service to get details based on id and send to ui

		map.addAttribute("shipmentType", service.getShipmentTypeById(shipmentId));


		return "ShipmentTypeView";
	}*/
	// 5.view one row or object
	@RequestMapping("/view")
	public String view(@RequestParam(required=false,defaultValue="0") Integer shipmentId,ModelMap map) {

		String page=null;

		if (shipmentId!=0) {
			//call service to get details based on id and send to ui
			map.addAttribute("shipmentType", service.getShipmentTypeById(shipmentId));
			page = "ShipmentTypeView";
		} else {
			map.addAttribute("shipmentType", service.getAllShipmentTypes());
			page = "ShipmentTypeData";
		}


		return page;
	}

	// 6.show edit page
	@RequestMapping("/editOne")
	public String showEdit(@RequestParam Integer shipmentId,ModelMap map) {

		//load db row as object and send data to ui
		map.addAttribute("shipmentType", service.getShipmentTypeById(shipmentId));

		return "ShipmentTypeEdit";
	}

	//7.Do update
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public String doUpdate(@ModelAttribute ShipmentType shipmentType,ModelMap map) {

		//call service to do update
		service.updateShipmentType(shipmentType);

		//success message
		map.addAttribute("message", "Shipment is successfully updated");

		//get new Data and send to ui
		map.addAttribute("shipmentType", service.getAllShipmentTypes());

		//return data.jsp
		return "ShipmentTypeData";
	}

	/*//8. Export Data to Excel 
	@RequestMapping("/excelExp") 
	public ModelAndView doExcelExport() {   
		//reading data from DB
		List<ShipmentType> shipmentType=service.getAllShipmentTypes();
		//view, key,val

		ModelAndView m=new ModelAndView();
		ShipmentTypeExcelView st=new ShipmentTypeExcelView();
		m.setView(st);
		m.addObject("shipmentType", shipmentType);
		return m;

		return new ModelAndView(new ShipmentTypeExcelView(),"shipmentType", shipmentType); 
	} 

	//9. Export Data to Excel 
	@RequestMapping("/exportExcelOne")  
	public ModelAndView doOneExcelExport(@RequestParam Integer shipmentId) {   
		//reading data from DB   
		ShipmentType shipmentType=service.getShipmentTypeById(shipmentId);   
		//view, key,val   
		return new ModelAndView(new ShipmentTypeExcelView(),"shipmentType", Arrays.asList(shipmentType)); 
	}*/

	// 8. Export Data to Excel 
	@RequestMapping("/excelExport")  
	public ModelAndView doOneExcelExport(@RequestParam(required=false,defaultValue="0") Integer shipmentId) {   
		if (shipmentId!=0) {
			return new ModelAndView(new ShipmentTypeExcelView(),"shipmentType",Arrays.asList(service.getShipmentTypeById(shipmentId)));
		} else {
			return new ModelAndView(new ShipmentTypeExcelView(),"shipmentType",service.getAllShipmentTypes());
		}

	}

	/*//10. Export all rows to pdf
	@RequestMapping("/pdfExp")
	public ModelAndView exportPdf() {

		return new ModelAndView(new ShipmentTypePdfView(),"shipmentType",service.getAllShipmentTypes());

	}

	//11.export one row as pdf
	@RequestMapping("/exportPdfOne")
	public ModelAndView exportOnePdf(@RequestParam Integer shipmentId) {

		return new ModelAndView(new ShipmentTypePdfView(),"shipmentType",Arrays.asList(service.getShipmentTypeById(shipmentId)));


	}*/

	// 9.export pdf
	@RequestMapping("/pdfExport")
	public ModelAndView exportPdf(@RequestParam(required=false,defaultValue="0") Integer shipmentId) {

		if (shipmentId!=0) {

			return new ModelAndView(new ShipmentTypePdfView(),"shipmentType",Arrays.asList(service.getShipmentTypeById(shipmentId)));
		} else {

			return new ModelAndView(new ShipmentTypePdfView(),"shipmentType",service.getAllShipmentTypes());
		}

	}

	//10.generate pie chart
	@RequestMapping("/report")
	public String generatePieChart() {

		String path=servletContex.getRealPath("/");
		List<Object[]> shipmentTypes=service.getShipmentTypeCount();
		util.generatePieChart(path, shipmentTypes);
		util.generateBarChart(path, shipmentTypes);
		return "ShipmentTypeReport";
	}














}