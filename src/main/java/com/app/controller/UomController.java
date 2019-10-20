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

import com.app.model.Uom;
import com.app.service.IUomService;
import com.app.util.UomUtil;
import com.app.validator.UomValidator;
import com.app.view.UomExcelView;
import com.app.view.UomPdfView;

@Controller
@RequestMapping("/uom")
public class UomController {

	@Autowired
	private IUomService service;
	@Autowired
	private ServletContext servletContext;
	@Autowired
	private UomUtil util;
	@Autowired
	private UomValidator validator;

	// 1.Show Register page
	@RequestMapping("/register")
	public String show(ModelMap map) {
		map.addAttribute("uom",new Uom());
		return "UomRegister";
	}

	// 2. Insert uom Data
	@RequestMapping(value="/insert",method=RequestMethod.POST)
	public String save(@ModelAttribute Uom uom,Errors errors,ModelMap map) {

		validator.validate(uom, errors);

		if (errors.hasErrors()) {
			map.addAttribute("message", "Please check all fields");
		} else {
			map.addAttribute("message", "Uom saved with Id : "+service.saveUom(uom));
			map.addAttribute("uom", new Uom());
		}
		
		return "UomRegister";
	}

	/*// 3. View All Records

	@RequestMapping("/viewAll")
	public String viewAll(ModelMap map) {

		List<Uom> allUoms = service.getAllUoms();
		map.addAttribute("allUoms", allUoms);
		return "UomData";
	}*/

	// 4. Delete Record by Id
	@RequestMapping("/delete")
	public String delete(@RequestParam Integer uomId,ModelMap map) {
		
		try {
			// delete row
			service.deleteUom(uomId);
			map.addAttribute("message", "One Uom details with "+uomId+" are deleted");
		} catch (Exception e) {
			map.addAttribute("message", "No details found with Id : "+uomId);
		}

		//load  data
		List<Uom> allUoms = service.getAllUoms();
		//send data to ui
		map.addAttribute("uom", allUoms);
		return "UomData";
	}

	@RequestMapping("/view")
	public String viewOne(@RequestParam(required=false,defaultValue="0") Integer uomId,ModelMap map) {

		String page=null;
		if (uomId!=0) {
			// load one user data and send to UI
			map.addAttribute("uom", service.getUomById(uomId));
			page = "UomView";

		} else {
			// load one user data and send to UI
			map.addAttribute("uom", service.getAllUoms());
			page = "UomData";
		}

		return page;
	}


	// 6.Show edit page
	@RequestMapping("/editOne")
	public String showEdit(@RequestParam Integer uomId,ModelMap map) {
		//get one object and send to ui
		map.addAttribute("uom", service.getUomById(uomId));
		return "UomEdit";
	}

	// 7.Do update
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public String doUpdate(@ModelAttribute Uom uom,ModelMap map) {

		service.updateUom(uom);
		map.addAttribute("message", "Uom is successfully updated");
		map.addAttribute("uom", service.getAllUoms());

		return "UomData";
	}

	/*// 8.do excell export
	@RequestMapping("/excelExp")
	public ModelAndView doExcelExport() {

		// load all rows from db
		List<Uom> uom= service.getAllUoms();
		//set vie,key&val
		return new ModelAndView(new UomExcelView(),"uom",uom);
	}

	// 9.do one row excell export
	@RequestMapping("/exportExcelOne")
	public ModelAndView doExcelExportOne(@RequestParam Integer uomId) {

		// load all rows from db
		Uom uom= service.getUomById(uomId);

		// set view ,key&val

		return new ModelAndView(new UomExcelView(),"uom",Arrays.asList(uom));
	}*/

	// 8.excel export
	@RequestMapping("/excelExport")
	public ModelAndView excelExport(@RequestParam(required=false,defaultValue="0") Integer uomId) {

		if (uomId!=0) {

			return new ModelAndView(new UomExcelView(),"uom",Arrays.asList(service.getUomById(uomId)));
		} else {

			return new ModelAndView(new UomExcelView(),"uom",service.getAllUoms());
		}
	}

	/*//10. export all rows to pdf
	@RequestMapping("/pdfExport")
	public ModelAndView doPdfExport() {
		return new ModelAndView(new UomPdfView(),"uom",service.getAllUoms());
	}

	//11.export all rows to pdf
	@RequestMapping("/exportPdfOne")
	public ModelAndView doOnePdfExport(@RequestParam Integer uomId) {
	}*/

	// 9. Pdf export
	@RequestMapping("/pdfExport")
	public ModelAndView pdfExport(@RequestParam(required=false,defaultValue="0") Integer uomId) {

		if (uomId!=0) {

			return new ModelAndView(new UomPdfView(),"uom",Arrays.asList(service.getUomById(uomId)));
		} else {

			return new ModelAndView(new UomPdfView(),"uom",service.getAllUoms());
		}
	}

	//10.generate charts
	@RequestMapping("/report")
	public String generateCharts() {

		String path = servletContext.getRealPath("/");
		List<Object[]> uomTypes = service.getuomTypeCount();
		util.generatePieChart(path, uomTypes);
		util.generateBarChart(path, uomTypes);
		return "UomReport";
	}

}
