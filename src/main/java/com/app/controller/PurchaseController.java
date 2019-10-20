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

import com.app.model.Purchase;
import com.app.model.PurchaseDtl;
import com.app.service.IItemService;
import com.app.service.IPurchaseService;
import com.app.service.IShipmentTypeService;
import com.app.service.IWhUserTypeService;
import com.app.validator.PurchaseValidator;
import com.app.view.PurchaseExcelView;
import com.app.view.PurchasePdfView;
import com.app.view.VendorInvoicePdfView;

@Controller
@RequestMapping("/purchase")
public class PurchaseController {

	@Autowired
	private IPurchaseService purchaseService;
	@Autowired
	private IWhUserTypeService whUserTypeService;
	@Autowired
	private IShipmentTypeService shipmentTypeService;
	@Autowired
	private PurchaseValidator validator;
	@Autowired
	private IItemService itemService;

	@RequestMapping("/register")
	public String showRegister(ModelMap map) {
		map.addAttribute("purchase", new Purchase());
		map.addAttribute("whUserType", whUserTypeService.getAllWhUserByType("VENDOR"));
		map.addAttribute("shipmentType", shipmentTypeService.getEnableShipmentIdsAndCodes());
		return "PurchaseRegister";
	}

	@RequestMapping(value="/insert",method=RequestMethod.POST)
	public String savePurchase(@ModelAttribute Purchase purchase,Errors errors,ModelMap map) {

		validator.validate(purchase, errors);

		if (errors.hasErrors()) {
			map.addAttribute("message", "please check all fields !!");

		} else {
			map.addAttribute("message", "Purchase is saved with Id :"+purchaseService.savePurchase(purchase));
			map.addAttribute("purchase", new Purchase());
		}
		map.addAttribute("whUserType", whUserTypeService.getAllWhUserByType("VENDOR"));
		map.addAttribute("shipmentType", shipmentTypeService.getEnableShipmentIdsAndCodes());
		return "PurchaseRegister";
	}

	/*@RequestMapping("/viewAll")
	public String viewAll(ModelMap map) {

		map.addAttribute("purchase", purchaseService.getAllPurchases());
		return "PurchaseData";
	}*/

	@RequestMapping("/view")
	public String viewOne(@RequestParam(required=false,defaultValue="0") Integer orderId,ModelMap map) {

		String page=null;
		if (orderId!=0) {
			map.addAttribute("purchase", purchaseService.getPurchaseById(orderId));
			page="PurchaseView";
		} else {
			map.addAttribute("purchase", purchaseService.getAllPurchases());
			page = "PurchaseData";

		}
		return page;
	}

	@RequestMapping("/delete")
	public String deletePurchase(@RequestParam Integer orderId,ModelMap map) {

		purchaseService.deletePurchase(orderId);
		map.addAttribute("message", "Purchase deleted successfully with id :"+orderId+" !!");
		map.addAttribute("purchase", purchaseService.getAllPurchases());
		return "PurchaseData";
	}

	@RequestMapping("/edit")
	public String editOne(@RequestParam Integer orderId,ModelMap map) {
		map.addAttribute("purchase", purchaseService.getPurchaseById(orderId));
		map.addAttribute("whUserType", whUserTypeService.getAllWhUserByType("VENDOR"));
		map.addAttribute("shipmentType", shipmentTypeService.getEnableShipmentIdsAndCodes());
		return "PurchaseEdit";
	}

	@RequestMapping(value="/update",method=RequestMethod.POST)
	public String updatePurchase(@ModelAttribute Purchase purchase,Errors errors,ModelMap map) {

		purchaseService.updatePurchase(purchase);
		map.addAttribute("purchase", purchaseService.getAllPurchases());
		return "PurchaseData";

	}

	@RequestMapping("/excelExport")
	public ModelAndView excelExport(@RequestParam(required=false,defaultValue="0") Integer orderId,ModelMap map) {
		ModelAndView mv=null;
		if (orderId!=0) {
			mv=new ModelAndView(new PurchaseExcelView(), "purchase", Arrays.asList(purchaseService.getPurchaseById(orderId)));
		} else {
			mv=new ModelAndView(new PurchaseExcelView(), "purchase", purchaseService.getAllPurchases());
		}
		return mv;
	}
	@RequestMapping("/pdfExport")
	public ModelAndView pdfExport(@RequestParam(required=false,defaultValue="0") Integer orderId,ModelMap map) {
		ModelAndView mv=null;
		if (orderId!=0) {
			mv=new ModelAndView(new PurchasePdfView(), "purchase", Arrays.asList(purchaseService.getPurchaseById(orderId)));
		} else {
			mv=new ModelAndView(new PurchasePdfView(), "purchase", purchaseService.getAllPurchases());
		}
		return mv;
	}

	/***
	 * Child Operations starts here
	 */

	private void getDtlUi(Integer orderId,ModelMap map) {
		Purchase po=purchaseService.getPurchaseById(orderId);

		//PO Code to show as Read Only
		map.addAttribute("poId", po.getOrderId());
		map.addAttribute("poCode", po.getOrderCode());
		map.addAttribute("poStatus", po.getOrderStatus());

		/**
		 * Form Data START
		 */
		//new empty form child
		PurchaseDtl dtl=new PurchaseDtl();
		dtl.setPoHdrId(po.getOrderId());
		map.addAttribute("purchaseDtl", dtl);

		//display items drop down
		Map<Integer,String> itemsMap=itemService.getItemIdNameCode();
		map.addAttribute("itemsMap", itemsMap);
		/**
		 *  FORM DATA END
		 */
		List<PurchaseDtl> dtls=po.getDetails();
		if(dtls==null || dtls.isEmpty()) {
			po.setOrderStatus("OPEN");
			map.addAttribute("poStatus","OPEN");
			purchaseService.updatePurchase(po);
		}else {
			//adjust slnos
			int count=0;
			for(PurchaseDtl d:dtls) {
				d.setSlno(++count);
			}
		}

		//all added items to show in table
		map.addAttribute("dtls",dtls );

	}

	/** 1. Show Add Items Pages
	 * 
	 */
	@RequestMapping("/viewItems")
	public String showItemsPage(@RequestParam Integer orderId,ModelMap map) {
		//complete common setup is provided here
		getDtlUi(orderId, map);
		return "PurchaseItems";
	}

	/**
	 * 2. Add Items to PO and update status to PICKING (if items count >=1)
	 * 
	 */
	@RequestMapping(value="/addItem",method=RequestMethod.POST)
	public String addPoItem(@ModelAttribute PurchaseDtl purchaseDtl,ModelMap map) {
		//do form validation PurchaseDtlValidator

		//save child data
		Purchase po=purchaseService.getPurchaseById(purchaseDtl.getPoHdrId());
		po.setOrderStatus("PICKING"); // check here status update
		po.getDetails().add(purchaseDtl);
		purchaseService.updatePurchase(po);

		//setup Purchase Items JSP Data
		getDtlUi(po.getOrderId(), map);
		return "PurchaseItems";
	}

	/**
	 * 3. Delete Item based on dtlId and update status to OPEN if Items Count=0
	 * 
	 */
	@RequestMapping("/removeItem")
	public String deletePoDtl(@RequestParam Integer orderDtlId,@RequestParam Integer orderId,ModelMap map) {
		purchaseService.deletePurchaseDtlById(orderDtlId);
		//setup Purchase Items JSP Data
		getDtlUi(orderId, map);
		return "PurchaseItems";
	}

	/**
	 * 4. Confirm Order ie chnage status to ORDERED 
	 */
	@RequestMapping("/updateOrderStatus")
	public String updateOrderConfirm(@RequestParam Integer orderId,@RequestParam String status,ModelMap map) {
		Purchase po=purchaseService.getPurchaseById(orderId);
		po.setOrderStatus(status);
		purchaseService.updatePurchase(po);
		String page=null;
		if(status.equals("ORDERED")) {
			page="PurchaseItems";
			getDtlUi(orderId, map);
		}else {
			map.addAttribute("purchase", purchaseService.getAllPurchases());
			page="PurchaseData";
		}
		return page;
	}
	
	/**
	 * 5. Generate Vendor Invoice
	 */
	@RequestMapping("/viewInvoice")
	public ModelAndView generateInvoice(@RequestParam Integer orderId){
		Purchase po=purchaseService.getPurchaseById(orderId);
		ModelAndView m=null;
		if(po.getOrderStatus().equals("INVOICED")){
			m=new ModelAndView(new VendorInvoicePdfView(),"po",po);
		}else{
			m=new ModelAndView("PurchaseData","purchase", purchaseService.getAllPurchases());
		}
		return m;
	}

}