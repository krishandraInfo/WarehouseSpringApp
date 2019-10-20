package com.app.controller;

import java.util.Arrays;
import java.util.List;

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
import com.app.model.Shipping;
import com.app.service.ISaleOrderService;
import com.app.service.IShippingService;
import com.app.validator.ShippingValidator;
import com.app.view.ShippingExcelView;
import com.app.view.ShippingPdfView;

@Controller
@RequestMapping("/shipping")
public class ShippingController {

	@Autowired
	private IShippingService shippingService;
	@Autowired
	private ISaleOrderService saleOrderService;
	@Autowired
	private ShippingValidator validator;

	@RequestMapping("/register")
	public String showRegister(ModelMap map) {
		map.addAttribute("shipping", new Shipping());
		map.addAttribute("saleOrder", saleOrderService.getInvoicedSaleOrders("INVOICED"));
		return "ShippingRegister";
	}

	@RequestMapping(value="/insert",method=RequestMethod.POST)
	public String savePurchase(@ModelAttribute Shipping shipping,Errors errors,ModelMap map) {

		validator.validate(shipping, errors);

		if (errors.hasErrors()) {
			map.addAttribute("message", "please check all fields !!");

		} else {

			//getting child data by id
			Integer saleOrderId = shipping.getSaleOrder().getSaleOrderId();
			SaleOrder saleOrder=saleOrderService.getOneSaleOrder(saleOrderId);
			saleOrder.setOrderStatus("SHIPPED");
			saleOrderService.updateSaleOrder(saleOrder);

			map.addAttribute("message", "Shipping is done with Id :"+shippingService.saveShipping(shipping));
			map.addAttribute("shipping", new Shipping());
		}
		map.addAttribute("saleOrder", saleOrderService.getInvoicedSaleOrders("INVOICED"));
		return "ShippingRegister";
	}

	@RequestMapping("/view")
	public String viewOne(@RequestParam(required=false,defaultValue="0") Integer shipId,ModelMap map) {

		String page=null;
		if (shipId!=0) {
			map.addAttribute("shipping", shippingService.getShippingById(shipId));
			page="ShippingView";
		} else {
			map.addAttribute("shipping", shippingService.getAllShippings());
			page = "ShippingData";

		}
		return page;
	}

	@RequestMapping("/delete")
	public String deletePurchase(@RequestParam Integer shipId,ModelMap map) {

		shippingService.deleteShipping(shipId);
		map.addAttribute("message", "Shipping deleted successfully with id :"+shipId+" !!");
		map.addAttribute("shipping", shippingService.getAllShippings());
		return "ShippingData";
	}

	@RequestMapping("/edit")
	public String editOne(@RequestParam Integer shipId,ModelMap map) {
		map.addAttribute("shipping", shippingService.getShippingById(shipId));
		map.addAttribute("saleOrder", saleOrderService.getInvoicedSaleOrders("INVOICED"));
		return "ShippingEdit";
	}

	@RequestMapping(value="/update",method=RequestMethod.POST)
	public String updatePurchase(@ModelAttribute Shipping shipping,Errors errors,ModelMap map) {

		shippingService.updateShipping(shipping);
		map.addAttribute("shipping", shippingService.getAllShippings());
		return "ShippingData";

	}

	@RequestMapping("/excelExport")
	public ModelAndView excelExport(@RequestParam(required=false,defaultValue="0") Integer shipId,ModelMap map) {
		ModelAndView mv=null;
		if (shipId!=0) {
			mv=new ModelAndView(new ShippingExcelView(), "shipping", Arrays.asList(shippingService.getShippingById(shipId)));
		} else {
			mv=new ModelAndView(new ShippingExcelView(), "shipping", shippingService.getAllShippings());
		}
		return mv;
	}
	@RequestMapping("/pdfExport")
	public ModelAndView pdfExport(@RequestParam(required=false,defaultValue="0") Integer shipId,ModelMap map) {
		ModelAndView mv=null;
		if (shipId!=0) {
			mv=new ModelAndView(new ShippingPdfView(), "shipping", Arrays.asList(shippingService.getShippingById(shipId)));
		} else {
			mv=new ModelAndView(new ShippingPdfView(), "shipping", shippingService.getAllShippings());
		}
		return mv;
	}

	private void getSaleDtls(Integer shipId,ModelMap map) {

		Shipping shipping = shippingService.getShippingById(shipId);
		Integer saleOrderId=shipping.getSaleOrder().getSaleOrderId();

		map.addAttribute("shipId", shipping.getShipId());
		map.addAttribute("shipCode", shipping.getShipCode());
		map.addAttribute("saleOrderId", saleOrderId);
		map.addAttribute("orderStatus", shipping.getSaleOrder().getOrderStatus());

		List<SalesDetails> salesDetails = shipping.getSaleOrder().getSalesDetails();

		int count=0;
		if (salesDetails!=null && !salesDetails.isEmpty()) {
			for (SalesDetails salesDetail : salesDetails) {
				salesDetail.setSlno(++count);
			}
		} 

		map.addAttribute("nullCount",saleOrderService.getNullShippingStatusCount(saleOrderId));
		map.addAttribute("salesDetails", salesDetails);
	}

	@RequestMapping("/viewItems")
	public String viewItems(@RequestParam Integer shipId,ModelMap map) {

		getSaleDtls(shipId, map);
		return "ShippingItems";
	}

	@RequestMapping("/updateOrderStatus")
	public String acceptPurchaseOrders(
			@RequestParam Integer shipId,
			@RequestParam(required=false,defaultValue="0") Integer saleOrderId,
			@RequestParam(required=false,defaultValue="0") Integer salesDtlsId,
			@RequestParam String shipSatus,ModelMap map) {


		if (salesDtlsId!=0 && saleOrderId==0) {
			SalesDetails salesDetails = saleOrderService.getSalesDetailsById(salesDtlsId);
			salesDetails.setShipSatus(shipSatus);;
			saleOrderService.updateSalesDetails(salesDetails);
		} else {
			saleOrderService.updateAllSalesDetailsStatus(shipSatus,saleOrderId);
		}

		getSaleDtls(shipId, map);
		return "ShippingItems";
	}


}
