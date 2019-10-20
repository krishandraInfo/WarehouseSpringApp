package com.app.validator;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.app.model.OrderMethod;
import com.app.service.IOrderMethodService;

@Component
public class OrderMethodValidator implements Validator{
	
	@Autowired
	private IOrderMethodService orderMethodService;

	public boolean supports(Class<?> clz) {
		return OrderMethod.class.equals(clz);
	}

	public void validate(Object target, Errors errors) {

		//convert to model class object
		OrderMethod orderMethod=(OrderMethod)target;
		
		//checking radio button empty
		if(StringUtils.isEmpty(orderMethod.getOrderMode())) {
			errors.rejectValue("orderMode", null, "please choose atleast one !");
		}
		
		//checking text empty
		if (!StringUtils.hasText(orderMethod.getOrderCode().trim())) {
			errors.rejectValue("orderCode", null, "please enter code !");
		}else if (!Pattern.matches("[A-Z]{4,6}", orderMethod.getOrderCode())) {
			errors.rejectValue("orderCode", null, "code shold be 4-6 uppercase lettrs !");
		}else if (orderMethodService.isOrderMethodExist(orderMethod.getOrderCode())) {
			errors.rejectValue("orderCode", null, "code is already exist !");
		}
		
		//checking drop down empty
		if (StringUtils.isEmpty(orderMethod.getOrderExeType())) {
			errors.rejectValue("orderExeType", null, "please select any one !");
		}
		
		//checking list is empty
		if (orderMethod.getOrderAccept().isEmpty()||orderMethod.getOrderAccept()==null) {
			errors.rejectValue("orderAccept", null, "please select atleast one or more!");
		}
		
		//checking text area empty and size
		if (!StringUtils.hasText(orderMethod.getOrderDecs().trim())) {
			errors.rejectValue("orderDecs", null, "please enter description!");
		} else if(orderMethod.getOrderDecs().length()<10 || orderMethod.getOrderDecs().length()>100) {
			errors.rejectValue("orderDecs", null, "description should be 10-100 characters!");
		}
		
	}

}
