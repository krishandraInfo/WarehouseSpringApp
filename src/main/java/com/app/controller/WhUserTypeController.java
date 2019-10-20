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

import com.app.model.WhUserType;
import com.app.service.IWhUserTypeService;
import com.app.util.WhUserTypeUtil;
import com.app.validator.WhUserTypeValidator;
import com.app.view.WhUserTypeExcelView;
import com.app.view.WhUserTypePdfView;

@Controller
@RequestMapping("/whuser")
public class WhUserTypeController {

	@Autowired
	private IWhUserTypeService service;
	@Autowired
	private ServletContext servletContext;	
	@Autowired
	private WhUserTypeUtil util;
	@Autowired
	private WhUserTypeValidator validator;

	// 1. Show Register page
	@RequestMapping("/register")
	public String show(ModelMap map) {
		map.addAttribute("whUserType", new WhUserType());
		return "WhUserTypeRegister";
	}

	// 2.insert user type
	@RequestMapping(value="/insert",method=RequestMethod.POST)
	public String save(@ModelAttribute WhUserType whUserType,Errors errors,ModelMap map) {

		validator.validate(whUserType, errors);

		if (errors.hasErrors()) {
			map.addAttribute("message", "please check all fields !");
		}else {

			map.addAttribute("message", "WhareHouse User Type is succefully saved with Id : "+service.saveWhUserType(whUserType));
			map.addAttribute("whUserType", new WhUserType());
		}

		return "WhUserTypeRegister";
	}
	/*// 3.View All User types
	@RequestMapping("/viewAll")
	public String viewAllWhUserTypes(ModelMap map) {

		map.addAttribute("whUserType",service.getAllWhUserTypes());
		return "WhUserTypeData";
	}*/

	// 4.Delete One User type By Id
	@RequestMapping("/deleteOne")
	public String deleteOneUserType(@RequestParam Integer whUserTypeId,ModelMap map) {

		try {
			service.deleteWhUserType(whUserTypeId);
			map.addAttribute("message", "One WhUser with Id :"+whUserTypeId+" is deleted successfully");
		} catch (Exception e) {
			map.addAttribute("message", "No details with Id :"+whUserTypeId);
		}

		map.addAttribute("whUserType", service.getAllWhUserTypes());
		return "WhUserTypeData";
	}

	/*// 5.View One User Type By Id
	@RequestMapping("/viewOne")
	public String viewOneWhUserType(@RequestParam Integer whUserTypeId,ModelMap map) {

		map.addAttribute("whUserType", service.getWhUserTypeById(whUserTypeId));
		return "WhUserTypeView";
	}*/

	// 5.View One Row or object
	@RequestMapping("/view")
	public String view(@RequestParam(required=false,defaultValue="0") Integer whUserTypeId,ModelMap map) {

		String page=null;
		if (whUserTypeId!=0) {
			map.addAttribute("whUserType", service.getWhUserTypeById(whUserTypeId));
			page = "WhUserTypeView";
		} else {
			map.addAttribute("whUserType", service.getAllWhUserTypes());
			page = "WhUserTypeData";

		}

		return page;
	}

	// 6.Show Edit page
	@RequestMapping("/editOne")
	public String showEditPage(@RequestParam Integer whUserTypeId,ModelMap map) {

		map.addAttribute("whUserType", service.getWhUserTypeById(whUserTypeId));
		return "WhUserTypeEdit";
	}

	// 7.Do update
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public String updateOneWhUserType(@ModelAttribute WhUserType whUserType,ModelMap map) {

		service.updateWhUserType(whUserType);
		map.addAttribute("message", "WhUserType is Successfully updated");
		map.addAttribute("whUserType", service.getAllWhUserTypes());
		return "WhUserTypeData";
	}

	/*// 8.do excel export all rows
	@RequestMapping("/excelExp")
	public ModelAndView doExcelExport() {

		// load data from DB
		List<WhUserType> whUserType = service.getAllWhUserTypes();

		// set view ,key&object
		return new ModelAndView(new WhUserTypeExcelView(),"whUserType",whUserType);
	}

	// 9. doone excel export
	@RequestMapping("/exportExcelOne")
	public ModelAndView doExcelExportOne(@RequestParam Integer whUserTypeId) {

		// load data from DB
		//WhUserType whUserType=service.getWhUserTypeById(whUserTypeId);

		// set view , key&obj to modeandview
		return new ModelAndView(new WhUserTypeExcelView(),"whUserType",Arrays.asList(service.getWhUserTypeById(whUserTypeId)));
	}*/

	// 9. Excel export
	@RequestMapping("/excelExport")
	public ModelAndView excelExport(@RequestParam(required=false,defaultValue="0") Integer whUserTypeId) {

		if (whUserTypeId!=0) {

			return new ModelAndView(new WhUserTypeExcelView(),"whUserType",Arrays.asList(service.getWhUserTypeById(whUserTypeId)));
		} else {

			return new ModelAndView(new WhUserTypeExcelView(),"whUserType",service.getAllWhUserTypes());
		}
	}

	/*//10. export all rows to pdf
		@RequestMapping("/pdfExport")
		public ModelAndView doPdfExport() {
			return new ModelAndView(new WhUserTypePdfView(),"whUserType",service.getAllWhUserTypes());
		}

		//11.export all rows to pdf
		@RequestMapping("/exportPdfOne")
		public ModelAndView doOnePdfExport(@RequestParam Integer whUserTypeId) {
			return new ModelAndView(new WhUserTypePdfView(),"whUserType",Arrays.asList(service.getWhUserTypeById(whUserTypeId)));
		}*/

	// 10. pdf export
	@RequestMapping("/pdfExport")
	public ModelAndView pdfExport(@RequestParam(required=false,defaultValue="0") Integer whUserTypeId) {

		if (whUserTypeId!=0) {

			return new ModelAndView(new WhUserTypePdfView(),"whUserType",Arrays.asList(service.getWhUserTypeById(whUserTypeId)));
		} else {

			return new ModelAndView(new WhUserTypePdfView(),"whUserType",service.getAllWhUserTypes());
		}
	}

	//11.generate charts
	@RequestMapping("/report")
	public String generateCharts() {

		String path = servletContext.getRealPath("/");
		List<Object[]> whUserTypes = service.getWhUserTypeCount();
		util.generatePieChart(path, whUserTypes);
		util.generateBarChart(path, whUserTypes);
		return "WhUserTypeReport";
	}
}
