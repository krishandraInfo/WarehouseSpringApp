
package com.app.validator;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.app.model.Purchase;
import com.app.service.IPurchaseService;

@Component
public class PurchaseValidator implements Validator {
	
	@Autowired
	private IPurchaseService purchaseService;

	public boolean supports(Class<?> clazz) {
		return Purchase.class.equals(clazz);
	}

	public void validate(Object target, Errors errors) {

		Purchase purchase=(Purchase)target;

		//checking text empty
		if (!StringUtils.hasText(purchase.getOrderCode().trim())) {
			errors.rejectValue("orderCode", null, "please enter code !");
		} else if (!Pattern.matches("[A-Z]{4,6}", purchase.getOrderCode())) {
			errors.rejectValue("orderCode", null, "code shold be 4-6 uppercase lettrs !");
		} else if (purchaseService.isOrderCodeExist(purchase.getOrderCode())) {
			errors.rejectValue("orderCode", null, "code is already exist !");
		}

		//checking radio button empty
		if(StringUtils.isEmpty(purchase.getQuaCheck())) {
			errors.rejectValue("quaCheck", null, "please choose atleast one !");
		}
		//text check
		if (!StringUtils.hasText(purchase.getRefNumber().trim())) {
			errors.rejectValue("refNumber", null, "please enter reference number !");
		} else if(!Pattern.matches("[A-Za-z0-9]{8,12}", purchase.getRefNumber())){
			errors.rejectValue("refNumber", null, "reference number must be 8-12 combination of uppercase letters and digits ");
		}

		//checking drop down empty
		if (StringUtils.isEmpty(purchase.getOrderStatus())) {
			errors.rejectValue("orderStatus", null, "please choose any one !");
		}

		//checking text area empty and size
		if (!StringUtils.hasText(purchase.getOrderDesc().trim())) {
			errors.rejectValue("orderDesc", null, "please enter description!");
		} else if(purchase.getOrderDesc().length()<10 || purchase.getOrderDesc().length()>100) {
			errors.rejectValue("orderDesc", null, "description should be 10-100 characters!");
		}

		//checking drop down empty
		if (purchase.getWhUserType()==null || purchase.getWhUserType().getUserId()==null || purchase.getWhUserType().getUserId()<=0) {
			errors.rejectValue("whUserType", null, "please choose any one !");
		}
		//checking drop down empty
		if (purchase.getShipmentType()==null || purchase.getShipmentType().getShipmentid()==null || purchase.getShipmentType().getShipmentid()<=0) {
			errors.rejectValue("shipmentType", null, "please choose any one !");
		}

	}

}
