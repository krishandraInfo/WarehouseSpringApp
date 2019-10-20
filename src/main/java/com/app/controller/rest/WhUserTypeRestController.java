package com.app.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.WhUserType;
import com.app.service.IWhUserTypeService;

@RestController
@RequestMapping("/rest/whuser")
public class WhUserTypeRestController {

	@Autowired
	private IWhUserTypeService service;

	@GetMapping("/all")
	public ResponseEntity<?> getAll(){

		ResponseEntity<?> responseEntity =null;
		List<WhUserType> whUserTypes=service.getAllWhUserTypes();

		if (whUserTypes!=null && !whUserTypes.isEmpty()) {
			responseEntity=new ResponseEntity<List<WhUserType>>(whUserTypes, HttpStatus.OK);
		} else {
			responseEntity=new ResponseEntity<String>("No data is available", HttpStatus.OK);
		}
		return responseEntity;
	}
	
	@GetMapping("/get/{whUserId}")
	public ResponseEntity<?> getOne(@PathVariable Integer whUserId) {
		
		ResponseEntity<?> responseEntity=null;
		WhUserType whUserType=service.getWhUserTypeById(whUserId);
		
		if (whUserType!=null) {
			responseEntity=new ResponseEntity<WhUserType>(whUserType, HttpStatus.OK);
		} else {
			responseEntity=new ResponseEntity<String>("WhUser "+whUserId+" is not available", HttpStatus.OK);

		}
		
		return responseEntity;
	}
	
	@PostMapping("/save")
	public ResponseEntity<?> save(@RequestBody WhUserType whUserType) {
		
		return new ResponseEntity<String>("WhUser is saved with Id "+service.saveWhUserType(whUserType), HttpStatus.OK);

	}
	
	@PutMapping("/update")
	public ResponseEntity<?> update(@RequestBody WhUserType whUserType) {
		ResponseEntity<String> responseEntity=null;
		
		try {
			service.updateWhUserType(whUserType);
			responseEntity=new ResponseEntity<String>("WhUser "+whUserType.getUserId()+" is updated.", HttpStatus.OK);
		} catch (Exception e) {
			responseEntity=new ResponseEntity<String>("WhUser "+whUserType.getUserId()+" is not updated.", HttpStatus.BAD_REQUEST);
			e.printStackTrace();
		}
		
		return responseEntity;
		
	}
	
	
	
	
	
	
	
	
	
	
	
}
