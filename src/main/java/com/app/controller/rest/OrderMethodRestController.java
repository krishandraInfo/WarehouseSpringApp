package com.app.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.OrderMethod;
import com.app.service.IOrderMethodService;

@RestController
@RequestMapping("/rest/order")
public class OrderMethodRestController {

	@Autowired
	private IOrderMethodService service;
	
	@GetMapping("/all")
	public ResponseEntity<?> getAll() {
		
		ResponseEntity<?> responseEntity = null;
		List<OrderMethod> orderMethods = service.getAllOrderMethods();
		if (orderMethods!=null && !orderMethods.isEmpty()) {
			
			responseEntity = new ResponseEntity<List<OrderMethod>>(orderMethods, HttpStatus.OK);
		} else {

			responseEntity = new ResponseEntity<String>("No data is avialable", HttpStatus.OK);
		}
		return responseEntity;
	}
	
	@GetMapping("/get/{orderId}")
	public ResponseEntity<?> getOne(@PathVariable Integer orderId){
		
		ResponseEntity<?> responseEntity=null;
		OrderMethod orderMethod=service.getOrderMethodById(orderId);
		if (orderMethod!=null) {
			responseEntity = new ResponseEntity<OrderMethod>(orderMethod, HttpStatus.OK);
		} else {
			responseEntity = new ResponseEntity<String>("Order Id "+orderId+" is not avalable", HttpStatus.OK);
		}
		return responseEntity;
	}
	
	@DeleteMapping("/delete/{orderId}")
	public ResponseEntity<?> deleteOne(@PathVariable Integer orderId){
		
		ResponseEntity<?> responseEntity=null;
		try {
			service.deleteOrderMethod(orderId);
			responseEntity = new ResponseEntity<String>("Order Id "+orderId+" is deleted.", HttpStatus.OK);
		} catch (Exception e) {
			responseEntity = new ResponseEntity<String>("Order Id "+orderId+" is not available", HttpStatus.BAD_REQUEST);
			e.printStackTrace();
		}
		return responseEntity;
	}
	
	@PostMapping("/save")
	public ResponseEntity<?> save(@RequestBody OrderMethod orderMethod) {
		
		
		return new ResponseEntity<String>("Order is saved with Id "+service.saveOrderMethod(orderMethod), HttpStatus.OK);
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> update(@RequestBody OrderMethod orderMethod) {
		
		ResponseEntity<String> responseEntity=null;
		
		try {
			service.updateOrderMethod(orderMethod);
			responseEntity=new ResponseEntity<String>("Order "+orderMethod.getOrderId()+" is updated.", HttpStatus.OK);
		} catch (Exception e) {
			
			responseEntity=new ResponseEntity<String>("Order "+orderMethod.getOrderId()+" is not updated.", HttpStatus.BAD_REQUEST);
			e.printStackTrace();
		}
		return responseEntity;
	}
	
}





















