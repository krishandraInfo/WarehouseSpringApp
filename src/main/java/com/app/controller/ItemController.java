package com.app.controller;



import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.app.model.Item;
import com.app.service.IItemService;
import com.app.service.IOrderMethodService;
import com.app.service.IUomService;
import com.app.validator.ItemValidator;
import com.app.view.ItemExcelView;
import com.app.view.ItemPdfView;

@Controller
@RequestMapping("/item")
public class ItemController {

	@Autowired
	private IItemService itemService;
	@Autowired
	private IUomService uomService;
	@Autowired
	private IOrderMethodService orderMethodService;
	@Autowired
	private ItemValidator validator;


	@RequestMapping("/register")
	public String showRegPage(ModelMap map) {

		map.addAttribute("item", new Item());
		//get data from uom and send to ui
		map.addAttribute("uom", uomService.getAllUomIdsAndModels());
		map.addAttribute("orderMethod", orderMethodService.getAllOrderMethodIdsAndCodes());
		return "ItemRegister";
	}

	@RequestMapping(value="/insert",method=RequestMethod.POST)
	public String saveItem(@ModelAttribute Item item,Errors errors,ModelMap map) {

		validator.validate(item, errors);

		if (errors.hasErrors()) {
			map.addAttribute("message", "please check all fields !!");
		} else {
			map.addAttribute("message", "Item saved with Id :"+itemService.saveItem(item));
			map.addAttribute("item", new Item());
		}

		//get data from uom and send to ui
		map.addAttribute("uom", uomService.getAllUomIdsAndModels());
		//get data from orderMethod and send to ui
		map.addAttribute("orderMethod", orderMethodService.getAllOrderMethodIdsAndCodes());

		return "ItemRegister";
	}

	/*@RequestMapping("/viewAll")
	public String showAllData(ModelMap map) {

		map.addAttribute("item", itemService.getAllItems());

		//get data from uom and send to ui
		map.addAttribute("uom", uomService.getAllUoms());
		//get data from orderMethod and send to ui
		map.addAttribute("orderMethod", orderMethodService.getAllOrderMethods());

		return "ItemData";
	}*/

	@RequestMapping("/delete")
	public String deleteItem(@RequestParam Integer itemId,ModelMap map) {

		try {
			itemService.deleteItem(itemId);
			map.addAttribute("message", "Item with id "+itemId+" is deleted successfully");
		} catch (Exception e) {
			map.addAttribute("message", "Item with id "+itemId+" is not found");
		}

		map.addAttribute("item", itemService.getAllItems());
		return "ItemData";

	}

	@RequestMapping("/view")
	public String viewOne(@RequestParam(required=false,defaultValue="0") Integer itemId,ModelMap map) {

		String page=null;
		if (itemId!=0) {
			map.addAttribute("item", itemService.getItemById(itemId));
			page="ItemView";
		} else {
			//get data from uom and send to ui
			map.addAttribute("item", itemService.getAllItems());
			page="ItemData";
		}

		return page;
	}

	@RequestMapping("/edit")
	public String editItem(@RequestParam Integer itemId,ModelMap map) {

		map.addAttribute("item", itemService.getItemById(itemId));
		//get data from uom and send to ui
		map.addAttribute("uom", uomService.getAllUomIdsAndModels());
		//get data from orderMethod and send to ui
		map.addAttribute("orderMethod", orderMethodService.getAllOrderMethodIdsAndCodes());
		return "ItemEdit";
	}

	@RequestMapping(value="/update",method=RequestMethod.POST)
	public String updateItem(@ModelAttribute Item item,Errors errors,ModelMap map) {

		itemService.updateItem(item);
		map.addAttribute("message", "Item with Id "+item.getItemId()+" is updated successfully !!");
		map.addAttribute("item", itemService.getAllItems());

		return "ItemData"; 
	}

	@RequestMapping("/excelExport")
	public ModelAndView excelExport(@RequestParam(required=false,defaultValue="0") Integer itemId) {

		if (itemId!=0) {
			return new ModelAndView(new ItemExcelView(), "item", Arrays.asList(itemService.getItemById(itemId)));
		} else {
			return new ModelAndView(new ItemExcelView(), "item", itemService.getAllItems());
		}
	}

	@RequestMapping("/pdfExport")
	public ModelAndView pdfExport(@RequestParam(required=false,defaultValue="0") Integer itemId) {

		if (itemId!=0) {
			return new ModelAndView(new ItemPdfView(), "item", Arrays.asList(itemService.getItemById(itemId)));
		} else {
			return new ModelAndView(new ItemPdfView(), "item", itemService.getAllItems());
		}
	}

}
