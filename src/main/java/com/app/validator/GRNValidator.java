package com.app.validator;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.app.model.GoodRecieveNote;
import com.app.service.IGRNService;

@Component
public class GRNValidator implements Validator{

	@Autowired
	private IGRNService grnService; 

	public boolean supports(Class<?> clazz) {
		return GoodRecieveNote.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		GoodRecieveNote goodRecieveNote = (GoodRecieveNote)target;


		// checking grn code is empty or not
		if (!StringUtils.hasText(goodRecieveNote.getGrnCode().trim())) {
			errors.rejectValue("grnCode", null, "please enter code !!");
		} else if(!Pattern.matches("[A-Z]{4,6}", goodRecieveNote.getGrnCode())){
			errors.rejectValue("grnCode", null, "code should be 4-6 upper case letters!!");
		} else if(grnService.isOrderCodeExist(goodRecieveNote.getGrnCode())){
			errors.rejectValue("grnCode", null, "code is already exist !!");
		}

		// checking grn type is empty or not
		if (!StringUtils.hasText(goodRecieveNote.getGrnType().trim())) {
			errors.rejectValue("grnType", null, "please enter type !!");
		} else if(!Pattern.matches("[A-Z]{4,6}", goodRecieveNote.getGrnCode())){
			errors.rejectValue("grnType", null, "type should be 4-6 upper case letters!!");
		}

		// checking grn description is empty or not
		if (!StringUtils.hasText(goodRecieveNote.getGrnDesc().trim())) {
			errors.rejectValue("grnDesc", null, "please enter description!!");
		} else if(goodRecieveNote.getGrnDesc().length()<10 || goodRecieveNote.getGrnDesc().length()>100){
			errors.rejectValue("grnDesc", null, "description should be 10-100 letters!!");
		}

		//checking drop down empty
		if (goodRecieveNote.getPurchase()==null || goodRecieveNote.getPurchase().getOrderId()==null || goodRecieveNote.getPurchase().getOrderId()<=0) {
			errors.rejectValue("purchase", null, "please choose any one !");
		}


	}

}
