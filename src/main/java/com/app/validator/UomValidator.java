package com.app.validator;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.app.model.Uom;
import com.app.service.IUomService;

@Component
public class UomValidator implements Validator{
	
	@Autowired 
	private IUomService uomService;

	public boolean supports(Class<?> clz) {
		return Uom.class.equals(clz);
	}

	public void validate(Object target, Errors errors) {
		
		//convert to model class obj
		Uom uom=(Uom)target;
		
		
		
		// checking uom type is empty or not
		if (StringUtils.isEmpty(uom.getUomType())) {
			errors.rejectValue("uomType", null, "please choose any one!!");
		}
		
		// checking uom model is empty or not
		if (!StringUtils.hasText(uom.getUomModel().trim())) {
			errors.rejectValue("uomModel", null, "please enter uom model!!");
		} else if(!Pattern.matches("[A-Z]{4,6}", uom.getUomModel())){
			errors.rejectValue("uomModel", null, "uom model should be 4-6 upper case letters!!");
		} else if(uomService.isUomExist(uom.getUomModel())){
			errors.rejectValue("uomModel", null, "uom model is already exist !!");
		}
		
		// checking uom description is empty or not
		if (!StringUtils.hasText(uom.getUomDesc().trim())) {
			errors.rejectValue("uomDesc", null, "please enter uom description!!");
		} else if(uom.getUomDesc().length()<10 || uom.getUomDesc().length()>100){
			errors.rejectValue("uomDesc", null, "uom model should be 10-100 letters!!");
		}
	}

}
