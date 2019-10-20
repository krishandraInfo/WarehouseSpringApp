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

import com.app.model.ShipmentType;
import com.app.service.IShipmentTypeService;

@RestController
@RequestMapping("/rest/shipment")
public class ShipmentTypeRestController {

	@Autowired
	private IShipmentTypeService service;

	@GetMapping("all")
	public ResponseEntity<?> getAll() {

		ResponseEntity<?> responseEntity =null;
		List<ShipmentType> shipmentTypes=service.getAllShipmentTypes();
		if (shipmentTypes!=null && !shipmentTypes.isEmpty()) {
			responseEntity = new ResponseEntity<List<ShipmentType>>(shipmentTypes, HttpStatus.OK);
		} else {
			responseEntity = new ResponseEntity<String>("No data is available", HttpStatus.OK);

		}

		return responseEntity;
	}
	
	@GetMapping("/get/{shipmentId}")
	public ResponseEntity<?> getOne(@PathVariable Integer shipmentId){
		
		ResponseEntity<?> responseEntity;
		ShipmentType shipmentType=service.getShipmentTypeById(shipmentId);
		
		if (shipmentType!=null) {
			responseEntity = new ResponseEntity<ShipmentType>(shipmentType,HttpStatus.OK);
		} else {
			responseEntity = new ResponseEntity<String>("Shipment "+shipmentId+" is not avialable", HttpStatus.BAD_REQUEST);
		}
		/*try {
			ShipmentType shipmentType=service.getShipmentTypeById(shipmentId);
			responseEntity = new ResponseEntity<ShipmentType>(shipmentType,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		return responseEntity;
	}
	
	@DeleteMapping("/delete/{shipmentId}")
	public ResponseEntity<?> deleteOne(@PathVariable Integer shipmentId){
		
		ResponseEntity<?> responseEntity;
		try {
			service.deleteShipmentType(shipmentId);
			responseEntity = new ResponseEntity<String>("Shipment "+shipmentId+" is successfully deleted", HttpStatus.OK);
		} catch (Exception e) {
			responseEntity = new ResponseEntity<String>("Shipment "+shipmentId+" is not available", HttpStatus.OK);

		}
		return responseEntity;
	}
	
	@PostMapping("/save")
	public ResponseEntity<?> save(@RequestBody ShipmentType shipmentType){
		
		int shipmenTypetId=service.saveShipmentType(shipmentType);
		
		String body="Shipment is saved '"+shipmenTypetId+"'.";
		ResponseEntity<String> responseEntity=new ResponseEntity<String>(body, HttpStatus.OK);
		return responseEntity;
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> update(@RequestBody ShipmentType shipmentType) {
		
		ResponseEntity<String> responseEntity=null;
		try {
			service.updateShipmentType(shipmentType);
			responseEntity=new ResponseEntity<String>("ShipmentType "+shipmentType.getShipmentid()+" is updated.", HttpStatus.OK);
		} catch (Exception e) {
			
			responseEntity=new ResponseEntity<String>("ShipmentType "+shipmentType.getShipmentid()+" is not updated.", HttpStatus.BAD_REQUEST);
			e.printStackTrace();
		}
		return responseEntity;
	}
}




















