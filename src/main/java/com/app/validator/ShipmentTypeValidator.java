package com.app.validator;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.app.model.ShipmentType;
import com.app.service.IShipmentTypeService;

@Component
public class ShipmentTypeValidator implements Validator{

	@Autowired
	private IShipmentTypeService shipmentTypeService;

	public boolean supports(Class<?> clazz) {
		return ShipmentType.class.equals(clazz);
	}

	public void validate(Object target, Errors errors) {
		
		
		//convert to model class obj
		ShipmentType shipmentType=(ShipmentType)target;
		
		//check shipment mode (drop down) is empty or not
		if (StringUtils.isEmpty(shipmentType.getShipmentMode())) {
			errors.rejectValue("shipmentMode", null, "please select choose any one !");
		}
		
		//check shipment code (text) is empty or not
		if (!StringUtils.hasText(shipmentType.getShipmentCode().trim())) {
			errors.rejectValue("shipmentCode", null, "please enter shipment code !");
		} else if (!Pattern.matches("[A-Z]{4,6}", shipmentType.getShipmentCode())) {
			errors.rejectValue("shipmentCode", null, "shipment code should be 4-6 upper cases only!");
		} else if (shipmentTypeService.isShipmentCodeExist(shipmentType.getShipmentCode())) {
			errors.rejectValue("shipmentCode", null, "code is altredy exist!");
		}
		
		//check shipment enabled (drop down) is empty or not
		if (StringUtils.isEmpty(shipmentType.getEnableShipment())) {
			errors.rejectValue("enableShipment", null, "please select choose any one !");
		}
		
		//check shipment grade (radio button) is empty or not
		if (StringUtils.isEmpty(shipmentType.getShipmentGrade())) {
			errors.rejectValue("shipmentGrade", null, "please select choose any one !");
		}
		
		//check shipment description (text) is empty or not
		if (!StringUtils.hasText(shipmentType.getShipDesc().trim())) {
			errors.rejectValue("shipDesc", null, "please enter shipment description!");
		} else if(shipmentType.getShipDesc().length()<10 || shipmentType.getShipDesc().length()>100){
			errors.rejectValue("shipDesc", null, "shipment description should be 10-100 letters!");
		}
	}

}
