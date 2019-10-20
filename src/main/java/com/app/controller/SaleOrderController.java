package com.app.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.app.model.SaleOrder;
import com.app.model.SalesDetails;
import com.app.service.IItemService;
import com.app.service.ISaleOrderService;
import com.app.service.IShipmentTypeService;
import com.app.service.IWhUserTypeService;
import com.app.validator.SaleOrderValidator;
import com.app.view.CustomerInvoicePdfView;
import com.app.view.SaleOrderExcelView;
import com.app.view.SaleOrderPdfView;

@Controller
@RequestMapping("/sale")
public class SaleOrderController {

	@Autowired
	private ISaleOrderService saleOrderService;
	@Autowired
	private IShipmentTypeService shipmentTypeService;
	@Autowired
	private IWhUserTypeService whUserTypeService;
	@Autowired
	private IItemService itemService;
	@Autowired
	private SaleOrderValidator validator;

	@RequestMapping("/register")
	public String showRegister(ModelMap map) {
		map.addAttribute("saleOrder", new SaleOrder());
		map.addAttribute("whUserType", whUserTypeService.getAllWhUserByType("CUSTOMER"));
		map.addAttribute("shipmentType", shipmentTypeService.getEnableShipmentIdsAndCodes());
		return "SaleOrderRegister";
	}

	@RequestMapping("/insert")
	public String saveSaleOrder(@ModelAttribute SaleOrder saleOrder,
			Errors errors,
			ModelMap map) {
		validator.validate(saleOrder, errors);

		if (errors.hasErrors()) {
			map.addAttribute("message", "please check all fields !!");
		} else {
			map.addAttribute("message", "SaleOrder saved with Id : "+saleOrderService.saveSaleOrder(saleOrder));
			map.addAttribute("saleOrder", new SaleOrder());
		}
		map.addAttribute("whUserType", whUserTypeService.getAllWhUserByType("CUSTOMER"));
		map.addAttribute("shipmentType", shipmentTypeService.getEnableShipmentIdsAndCodes());
		return "SaleOrderRegister";
	}

	/*@RequestMapping("/viewAll")
	public String showAll(ModelMap map) {

		map.addAttribute("saleOrder", saleOrderService.getAllSaleOrders());
		return "SaleOrderData";
	}*/

	@RequestMapping("/view")
	public String viewOne(@RequestParam(required=false,defaultValue="0") Integer saleOrderId,ModelMap map) {

		String page=null;
		if (saleOrderId!=0) {
			map.addAttribute("saleOrder", saleOrderService.getOneSaleOrder(saleOrderId));
			page="SaleOrderView";
		} else {
			map.addAttribute("saleOrder", saleOrderService.getAllSaleOrders());
			page="SaleOrderData";
		}

		return page;
	}

	@RequestMapping("/delete")
	public String deleteOne(@RequestParam Integer saleOrderId,ModelMap map) {
		try {
			saleOrderService.deleteSaleOrder(saleOrderId);
			map.addAttribute("message", "One record deleted with Id : "+saleOrderId);
		} catch (Exception e) {
			map.addAttribute("message", saleOrderId+" is not found");
			e.printStackTrace();
		}
		map.addAttribute("saleOrder", saleOrderService.getAllSaleOrders());
		return "SaleOrderData";
	}

	@RequestMapping("/edit")
	public String editOne(@RequestParam Integer saleOrderId,ModelMap map) {

		map.addAttribute("saleOrder", saleOrderService.getOneSaleOrder(saleOrderId));
		map.addAttribute("whUserType", whUserTypeService.getAllWhUserByType("CUSTOMER"));
		map.addAttribute("shipmentType", shipmentTypeService.getEnableShipmentIdsAndCodes());
		return "SaleOrderEdit"; 
	}

	@RequestMapping("/update")
	public String updateSaleOrder(@ModelAttribute SaleOrder saleOrder,Errors errors,ModelMap map) {

		saleOrderService.updateSaleOrder(saleOrder);
		map.addAttribute("message", "SaleOrder is updated succesfully");
		map.addAttribute("saleOrder", saleOrderService.getAllSaleOrders());
		return "SaleOrderData";
	}

	@RequestMapping("/excelExport")
	public ModelAndView excelExport(@RequestParam(required=false,defaultValue="0") 
	Integer saleOrderId,ModelMap map) {
		ModelAndView mv=null;
		if (saleOrderId!=0) {
			mv=new ModelAndView(new SaleOrderExcelView(), "saleOrder", Arrays.asList(saleOrderService.getOneSaleOrder(saleOrderId)));
		} else {
			mv=new ModelAndView(new SaleOrderExcelView(), "saleOrder", saleOrderService.getAllSaleOrders());
		}
		return mv;
	}

	@RequestMapping("/pdfExport")
	public ModelAndView pdfExport(@RequestParam(required=false,defaultValue="0") 
	Integer saleOrderId,ModelMap map) {
		ModelAndView mv=null;
		if (saleOrderId!=0) {
			mv=new ModelAndView(new SaleOrderPdfView(), "saleOrder", Arrays.asList(saleOrderService.getOneSaleOrder(saleOrderId)));
		} else {
			mv=new ModelAndView(new SaleOrderPdfView(), "saleOrder", saleOrderService.getAllSaleOrders());
		}
		return mv;
	}

	@RequestMapping("/viewItems")
	public String viewItems(@RequestParam Integer saleOrderId,ModelMap map) {
		getSalesDtls(saleOrderId,map);
		return "SaleItems";
	}

	@RequestMapping(value="/addItem",method=RequestMethod.POST)
	public String addItem(@ModelAttribute SalesDetails salesDetails,ModelMap map) {

		SaleOrder saleOrder=saleOrderService.getOneSaleOrder(salesDetails.getSoHoId());
		saleOrder.setOrderStatus("READY");
		saleOrder.getSalesDetails().add(salesDetails);
		saleOrderService.updateSaleOrder(saleOrder);
		getSalesDtls(salesDetails.getSoHoId(), map);
		return "SaleItems";
	}

	@RequestMapping("/removeItem")
	public String removeItem(@RequestParam Integer salesDtlsId,@RequestParam Integer saleOrderId,ModelMap map) {

		saleOrderService.deleteSalesDetailsById(salesDtlsId);
		getSalesDtls(saleOrderId, map);
		return "SaleItems";

	}

	@RequestMapping("/updateOrderStatus")
	public String updateOrderStatus(@RequestParam Integer saleOrderId,@RequestParam String orderStatus,ModelMap map) {
		SaleOrder saleOrder = saleOrderService.getOneSaleOrder(saleOrderId);
		saleOrder.setOrderStatus(orderStatus);
		saleOrderService.updateSaleOrder(saleOrder);
		String page=null;
		if (orderStatus.equals("CONFIRM")) {
			page="SaleItems";
			getSalesDtls(saleOrderId, map);
		} else {
			map.addAttribute("saleOrder", saleOrderService.getAllSaleOrders());
			page="SaleOrderData";
		}
		return page;
	}
	
	@RequestMapping("/viewInvoice")
	public ModelAndView viewInvoice(@RequestParam Integer saleOrderId) {
		
		ModelAndView m = new ModelAndView(new CustomerInvoicePdfView(), 
				"saleOrder", 
				saleOrderService.getOneSaleOrder(saleOrderId));
		return m;
	}


	private void getSalesDtls(Integer saleOrderId,ModelMap map) {

		SaleOrder saleOrder = saleOrderService.getOneSaleOrder(saleOrderId);

		// add code and status to SaleItem page
		map.addAttribute("saleOrderId", saleOrder.getSaleOrderId());
		map.addAttribute("saleOrderCode", saleOrder.getSaleOrderCode());
		map.addAttribute("orderStatus", saleOrder.getOrderStatus());


		//new child obj
		SalesDetails salesDetails=new SalesDetails();
		salesDetails.setSoHoId(saleOrder.getSaleOrderId());
		//add child obj to ui
		map.addAttribute("salesDetails", salesDetails);

		//add item code and id 
		Map<Integer, String> item = itemService.getItemIdNameCode();
		map.addAttribute("item", item);

		List<SalesDetails> salesDetailsList=saleOrder.getSalesDetails();

		if (salesDetailsList==null || salesDetailsList.isEmpty()) {
			saleOrder.setOrderStatus("OPEN");
			map.addAttribute("orderStatus", "OPEN");
			saleOrderService.updateSaleOrder(saleOrder);
		} else {
			int count=0;
			for (SalesDetails salesDetails3 : salesDetailsList) {
				salesDetails3.setSlno(++count);
			}

			map.addAttribute("salesDetailsList", salesDetailsList);

		}
	}
}
