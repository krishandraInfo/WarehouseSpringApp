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

import com.app.model.Uom;
import com.app.service.IUomService;

@RestController
@RequestMapping("/rest/uom")
public class UomRestController {

	@Autowired
	private IUomService service;

	@GetMapping("/all")
	public ResponseEntity<?> getAll(){

		ResponseEntity<?> responseEntity = null;
		List<Uom> uoms = service.getAllUoms();

		if (uoms!=null && !uoms.isEmpty()) {
			responseEntity = new ResponseEntity<List<Uom>>(uoms, HttpStatus.OK);
		} else {

			responseEntity = new ResponseEntity<String>("No data is available !", HttpStatus.OK);
		}

		return responseEntity;
	}

	@GetMapping("/get/{uomId}")
	public ResponseEntity<?> getOne(@PathVariable Integer uomId) {

		ResponseEntity<?> responseEntity=null;

		Uom uom= service.getUomById(uomId);
		if (uom!=null) {
			responseEntity = new ResponseEntity<Uom>(uom, HttpStatus.OK);
		} else {
			responseEntity = new ResponseEntity<String>("Uom "+uomId+" is not available", HttpStatus.BAD_REQUEST);
		}

		return responseEntity;
	}

	@DeleteMapping("/delete/{uomId}")
	public ResponseEntity<?> deleteOne(@PathVariable Integer uomId) {

		ResponseEntity<?> responseEntity=null;

		try {
			service.deleteUom(uomId);
			responseEntity = new ResponseEntity<String>("Uom "+uomId+" is deleted", HttpStatus.BAD_REQUEST);
		} catch (Exception e) {

			responseEntity = new ResponseEntity<String>("Uom "+uomId+" is not available", HttpStatus.BAD_REQUEST);
		}

		return responseEntity;
	}

	@PostMapping("/save")
	public ResponseEntity<?> save(@RequestBody Uom uom) {

		
		return new ResponseEntity<String>("Uom is saved with Id : "+service.saveUom(uom), HttpStatus.OK);
	}

	
	@PutMapping("/update")
	public ResponseEntity<?> update(@RequestBody Uom uom) {
		
		ResponseEntity<String> responseEntity=null;
		
		try {
			service.updateUom(uom);
			responseEntity=new ResponseEntity<String>("Uom "+uom.getUomId()+" is updated.", HttpStatus.OK);
		} catch (Exception e) {
			responseEntity=new ResponseEntity<String>("Uom "+uom.getUomId()+" is not updated.", HttpStatus.BAD_REQUEST);
			e.printStackTrace();
		}
		return responseEntity;
		
	}
}


















