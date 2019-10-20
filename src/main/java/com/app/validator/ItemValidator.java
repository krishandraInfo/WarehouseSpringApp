package com.app.validator;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.app.model.Item;
import com.app.service.IItemService;

@Component
public class ItemValidator implements Validator {
	
	@Autowired
	private IItemService itemService;

	public boolean supports(Class<?> clazz) {
		return Item.class.equals(clazz);
	}

	public void validate(Object target, Errors errors) {

		Item item=(Item)target;

		//checking text empty
		if (!StringUtils.hasText(item.getItemCode().trim())) {
			errors.rejectValue("itemCode", null, "please enter code !");
		} else if (!Pattern.matches("[A-Z]{4,6}", item.getItemCode())) {
			errors.rejectValue("itemCode", null, "code shold be 4-6 uppercase lettrs !");
		} else if (itemService.isItemCodeExist(item.getItemCode())) {
			errors.rejectValue("itemCode", null, "code is alredy exist !");
		}

		//checking text area empty and size
		if (StringUtils.isEmpty(item.getItemLength())) {
			errors.rejectValue("itemLength", null, "enter length !");
		} else if(item.getItemLength()<=0) {
			errors.rejectValue("itemLength", null, "item length must be > 0 !");
		}

		//checking text area empty and size
		if (StringUtils.isEmpty(item.getItemWidth())) {
			errors.rejectValue("itemWidth", null, "enter width !");
		} else if(item.getItemWidth()<=0) {
			errors.rejectValue("itemWidth", null, "item width must be > 0 !");
		}

		//checking text area empty and size
		if (StringUtils.isEmpty(item.getItemHeight())) {
			errors.rejectValue("itemHeight", null, "enter height !");
		} else if(item.getItemHeight()<=0) {
			errors.rejectValue("itemHeight", null, "item height must be > 0 !");
		}

		//checking text area empty and size
		if (StringUtils.isEmpty(item.getItemBaseCost())) {
			errors.rejectValue("itemBaseCost", null, "enter price !");
		} else if(item.getItemBaseCost()<=0) {
			errors.rejectValue("itemBaseCost", null, "item price must be > 0 !");
		}

		//checking drop down empty
		if (StringUtils.isEmpty(item.getItemCurrentCurrency())) {
			errors.rejectValue("itemCurrentCurrency", null, "please choose any one !");
		}

		//checking text area empty and size
		if (!StringUtils.hasText(item.getItemDesc().trim())) {
			errors.rejectValue("itemDesc", null, "please enter description!");
		} else if(item.getItemDesc().length()<10 || item.getItemDesc().length()>100) {
			errors.rejectValue("itemDesc", null, "description should be 10-100 characters!");
		}
		//checking drop down empty
		if (item.getUom()==null || item.getUom().getUomId()==null || item.getUom().getUomId()<=0) {
			errors.rejectValue("uom", null, "please choose any one !");
		}
		//checking drop down empty
		if (item.getOrderMethod()==null || item.getOrderMethod().getOrderId()==null || item.getOrderMethod().getOrderId()<=0) {
			errors.rejectValue("orderMethod", null, "please choose any one !");
		}

	}

}
