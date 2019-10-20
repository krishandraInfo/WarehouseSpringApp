package com.app.validator;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.app.model.SaleOrder;
import com.app.service.ISaleOrderService;

@Component
public class SaleOrderValidator implements Validator {

	@Autowired
	private ISaleOrderService saleOrderService;

	public boolean supports(Class<?> clazz) {
		return SaleOrder.class.equals(clazz);
	}

	public void validate(Object target, Errors errors) {

		SaleOrder saleOrder=(SaleOrder)target;

		//checking text empty
		if (!StringUtils.hasText(saleOrder.getSaleOrderCode().trim())) {
			errors.rejectValue("saleOrderCode", null, "please enter code !");
		} else if (!Pattern.matches("[A-Z]{4,6}", saleOrder.getSaleOrderCode())) {
			errors.rejectValue("saleOrderCode", null, "code shold be 4-6 uppercase lettrs !");
		} else if (saleOrderService.isOrderCodeExist(saleOrder.getSaleOrderCode())) {
			errors.rejectValue("saleOrderCode", null, "code is already exist !");
		}

		//text check
		if (!StringUtils.hasText(saleOrder.getRefNum().trim())) {
			errors.rejectValue("refNum", null, "please enter reference number !");
		} else if(!Pattern.matches("[A-Za-z0-9]{8,12}", saleOrder.getRefNum())){
			errors.rejectValue("refNum", null, "atleast one digit and one uppercase letter");
		}

		//checking radio button empty
		if(StringUtils.isEmpty(saleOrder.getStockMode())) {
			errors.rejectValue("stockMode", null, "please choose atleast one !");
		}

		//checking drop down empty
		if (StringUtils.isEmpty(saleOrder.getStockSource())) {
			errors.rejectValue("stockSource", null, "please choose any one !");
		}

		//checking drop down empty
		if (StringUtils.isEmpty(saleOrder.getOrderStatus())) {
			errors.rejectValue("orderStatus", null, "please choose any one !");
		}

		//checking text area empty and size
		if (!StringUtils.hasText(saleOrder.getOrderDesc().trim())) {
			errors.rejectValue("orderDesc", null, "please enter description!");
		} else if(saleOrder.getOrderDesc().length()<10 || saleOrder.getOrderDesc().length()>100) {
			errors.rejectValue("orderDesc", null, "description should be 10-100 characters!");
		}
		
		//checking drop down empty
		if (saleOrder.getShipmentType()==null || saleOrder.getShipmentType().getShipmentid()==null || saleOrder.getShipmentType().getShipmentid()<=0) {
			errors.rejectValue("shipmentType", null, "please choose any one !");
		}
		//checking drop down empty
		if (saleOrder.getWhUserType()==null || saleOrder.getWhUserType().getUserId()==null || saleOrder.getWhUserType().getUserId()<=0) {
			errors.rejectValue("whUserType", null, "please choose any one !");
		}

	}

}