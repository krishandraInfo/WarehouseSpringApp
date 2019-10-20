package com.app.validator;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.app.model.WhUserType;
import com.app.service.IWhUserTypeService;

@Component
public class WhUserTypeValidator implements Validator{
	
	@Autowired
	private IWhUserTypeService WhUserTypeService;

	public boolean supports(Class<?> clz) {
		return WhUserType.class.equals(clz);
	}

	public void validate(Object target, Errors errors) {


		//convert to model class obj
		WhUserType whUserType=(WhUserType)target;

		//check whuser code (text) is empty or not
		if (!StringUtils.hasText(whUserType.getUserCode().trim())) {
			errors.rejectValue("userCode", null, "please enter WH user code code !");
		} else if (!Pattern.matches("[A-Z]{4,6}", whUserType.getUserCode())) {
			errors.rejectValue("userCode", null, "code should be 4-6 upper cases only!");
		} else if (WhUserTypeService.isWhUserCodeExist(whUserType.getUserCode())) {
			errors.rejectValue("userCode", null, "code is already exist!");
		}

		//check user type (radio button) is empty or not
		if (StringUtils.isEmpty(whUserType.getUserType())) {
			errors.rejectValue("userType", null, "please select choose any one !");
		}

		//check mobile (text) is empty or not
		if (!StringUtils.hasText(whUserType.getUserName().trim())) {
			errors.rejectValue("userName", null, "please enter your name !");
		} else if (!Pattern.matches("[a-z\\sA-Z]{5,25}", whUserType.getUserName())) {
			errors.rejectValue("userName", null, "Name must be 5-25 !");
		}

		//check user for (drop down) is empty or not
		if (StringUtils.isEmpty(whUserType.getUserFor())) {
			errors.rejectValue("userFor", null, "please select choose any one !");
		}

		String EMAIL_REGEX = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";
		//check mail (text) is empty or not
		if (!StringUtils.hasText(whUserType.getUserEmail().trim())) {
			errors.rejectValue("userEmail", null, "please enter your email !");
		} else if (!Pattern.matches(EMAIL_REGEX, whUserType.getUserEmail())) {
			errors.rejectValue("userEmail", null, "please enter valid email !");
		} else if (WhUserTypeService.isEmailOrMobileExist("userEmail", whUserType.getUserEmail()) ) {
			errors.rejectValue("userEmail", null, "email already exist !");
		}

		String MOBILE_REGEX = "[6-9]{1}[\\d]{9}";
		//check mobile (text) is empty or not
		if (!StringUtils.hasText(whUserType.getUserNumber().trim())) {
			errors.rejectValue("userNumber", null, "please enter your mobile number !");
		} else if (!Pattern.matches(MOBILE_REGEX, whUserType.getUserNumber())) {
			errors.rejectValue("userNumber", null, "please enter valid number without country code!");
		} else if (WhUserTypeService.isEmailOrMobileExist("userNumber", whUserType.getUserNumber()) ) {
			errors.rejectValue("userNumber", null, "mobile number already exist !");
		}

		//check id type (drop down) is empty or not
		if (StringUtils.isEmpty(whUserType.getUserIdType())) {
			errors.rejectValue("userIdType", null, "please select choose any one !");
		} 
		
		if (whUserType.getUserIdType().equals("OTHER") && !StringUtils.hasText(whUserType.getIfOther())) {
			errors.rejectValue("ifOther", null, "please enter doument type !");
		} else if (StringUtils.hasText(whUserType.getIfOther()) && 
				(whUserType.getUserIdType().equals("VOTER CARD") || 
				whUserType.getUserIdType().equals("AADHAAR CARD") || 
				whUserType.getUserIdType().equals("PANCARD"))) {
			errors.rejectValue("ifOther", null, "please remove other doument type or choose Id type as other!");
		}

		//check Id number (text) is empty or not
		if (!StringUtils.hasText(whUserType.getUserIdNumber().trim())) {
			errors.rejectValue("userIdNumber", null, "please enter your Id number !");
		} else if (whUserType.getUserIdType().equals("PANCARD") && !Pattern.matches("[A-Z]{5}[0-9]{4}[A-Z]", whUserType.getUserIdNumber())) {
			errors.rejectValue("userIdNumber", null, "please enter valid pan card number, ex:-JKLMX7854N !");
		} else if (whUserType.getUserIdType().equals("AADHAAR CARD") && !Pattern.matches("[0-9]{4}[\\s][0-9]{4}[\\s][0-9]{4}", whUserType.getUserIdNumber())) {
			errors.rejectValue("userIdNumber", null, "please enter valid aadaar card number, ex:-1234 1234 1234 !");
		} else if (whUserType.getUserIdType().equals("VOTER CARD") && !Pattern.matches("[A-Z]{3}[0-9]{7}", whUserType.getUserIdNumber())) {
			errors.rejectValue("userIdNumber", null, "please enter valid voter card number, ex:-IGQ1234567 !");
		}
	}

}
