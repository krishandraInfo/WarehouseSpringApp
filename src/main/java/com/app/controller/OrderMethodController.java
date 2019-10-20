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

import com.app.model.OrderMethod;
import com.app.service.IOrderMethodService;
import com.app.util.OrderMethodUtil;
import com.app.validator.OrderMethodValidator;
import com.app.view.OrderMethodExcelView;
import com.app.view.OrderMethodPdfView;

@Controller
@RequestMapping("/order")
public class OrderMethodController {

	@Autowired
	private IOrderMethodService service;
	@Autowired
	private ServletContext servletContext;
	@Autowired
	private OrderMethodUtil orderMethodUtil;
	@Autowired
	private OrderMethodValidator validator;

	// 1.Show Register Page
	@RequestMapping("/register")
	public String showReg(ModelMap map) {
		map.addAttribute("orderMethod", new OrderMethod());
		return "OrderMehodRegister";
	}

	// 2.Insert Data in DB
	@RequestMapping(value="/insert",method=RequestMethod.POST)
	public String save(@ModelAttribute OrderMethod orderMethod,Errors errors,ModelMap map) {

		//call validate method
		validator.validate(orderMethod, errors);
		
		//check for errors
		if (errors.hasErrors()) {
			map.addAttribute("message", "Please fill all fields !");
		} else {
			//save data in DB and send message to UI
			map.addAttribute("message", "OderMethod saved with Id :"+service.saveOrderMethod(orderMethod));
			//clean form
			map.addAttribute("orderMethod", new OrderMethod());
		}
		
		return "OrderMehodRegister";
	}

	/*// 3.View All Records
	@RequestMapping("/viewAll")
	public String vewAll(ModelMap map) {
		List<OrderMethod> allOrderMethods=service.getAllOrderMethods();
		map.addAttribute("orderMethods", allOrderMethods);
		return "OrderMethodData";
	}*/

	// 4.Delete Record
	@RequestMapping("/delete")
	public String delete(@RequestParam Integer orderId,ModelMap map) {
		
		try {
			//delete row
			service.deleteOrderMethod(orderId);
			map.addAttribute("message", "Oreder Method Details are deleted with Id : "+orderId);
		} catch (Exception e) {
			map.addAttribute("message", "No Details found with Id : "+orderId);
		}

		//read data
		List<OrderMethod> allOrderMethods=service.getAllOrderMethods();
		map.addAttribute("orderMethod", allOrderMethods);
		return "OrderMethodData";	
	}

	/*// 5. View One Row or Object
	@RequestMapping("/viewOne")
	public String viewOneObject(@RequestParam Integer orderId,ModelMap map) {

		//call service and send to ui
		map.addAttribute("orderMethod", service.getOrderMethodById(orderId));
		return "OrderMethodView";
	}*/
	
	// 5.View One Row or object
		@RequestMapping("/view")
		public String view(@RequestParam(required=false,defaultValue="0") Integer orderId,ModelMap map) {
			
			String page=null;
			if (orderId!=0) {
				map.addAttribute("orderMethod", service.getOrderMethodById(orderId));
				page = "OrderMethodView";
			} else {
				map.addAttribute("orderMethod", service.getAllOrderMethods());
				page = "OrderMethodData";
			}
			
			return page;
		}

	// 6.Show edit page
	@RequestMapping("/editOne")
	public String showEdit(@RequestParam Integer orderId,ModelMap map) {
		//get one object and send to ui
		map.addAttribute("orderMethod", service.getOrderMethodById(orderId));
		return "OrderMethodEdit";
	}

	// 7.Do update
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public String doUpdate(@ModelAttribute OrderMethod orderMethod,ModelMap map) {

		//call service to update
		service.updateOrderMethod(orderMethod);;
		map.addAttribute("message", "Order is successfully updated");
		//grt new data
		map.addAttribute("orderMethods", service.getAllOrderMethods());
		//return data.jsp
		return "OrderMethodData";
	}

	/*// 8.do excell export
	@RequestMapping("/excelExport")
	public ModelAndView doExcelExport() {

		// load data from DB
		List<OrderMethod> orderMethod=service.getAllOrderMethods();

		// set view,key&value
		return new ModelAndView(new OrderMethodExcelView(),"orderMethod",orderMethod);
	}

	// 9.do one excel export
	@RequestMapping("/excelExportOne")
	public ModelAndView doOneExcelExport(@RequestParam Integer orderId) {

		//load data from db
		OrderMethod orderMethod=service.getOrderMethodById(orderId);

		// set view,key&value
		return new ModelAndView(new OrderMethodExcelView(),"orderMethod",Arrays.asList(orderMethod));

	}*/

	// 8.do excell export
	@RequestMapping("/excelExport")
	public ModelAndView excelExport(@RequestParam(required=false,defaultValue="0") Integer orderId) {

		if (orderId!=0) {

			return new ModelAndView(new OrderMethodExcelView(),"orderMethod",Arrays.asList(service.getOrderMethodById(orderId)));
		} else {

			return new ModelAndView(new OrderMethodExcelView(),"orderMethod",service.getAllOrderMethods());
		}
	}

	/*//10. export all rows to pdf
	@RequestMapping("/pdfExport")
	public ModelAndView doPdfExport() {
		return new ModelAndView(new OrderMethodPdfView(),"orderMethod",service.getAllOrderMethods());
	}

	//11.export all rows to pdf
	@RequestMapping("/exportPdfOne")
	public ModelAndView doOnePdfExport(@RequestParam Integer orderId) {
		return new ModelAndView(new OrderMethodPdfView(),"orderMethod",Arrays.asList(service.getOrderMethodById(orderId)));
	}*/

	// 9.do PDF export
	@RequestMapping("/pdfExport")
	public ModelAndView pdfExport(@RequestParam(required=false,defaultValue="0") Integer orderId) {

		if (orderId!=0) {

			return new ModelAndView(new OrderMethodPdfView(),"orderMethod",Arrays.asList(service.getOrderMethodById(orderId)));
		} else {

			return new ModelAndView(new OrderMethodPdfView(),"orderMethod",service.getAllOrderMethods());
		}
	}

	//10.generate pie chart
	@RequestMapping("/report")
	public String pieChart() {

		String path = servletContext.getRealPath("/");
		List<Object[]> orderModes=service.getOrderModeCount();
		orderMethodUtil.generatePieChart(path, orderModes);
		orderMethodUtil.generateBarChart(path, orderModes);

		return "OrderMethodReport";
	}

























}
