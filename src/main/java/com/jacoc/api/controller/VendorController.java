package com.jacoc.api.controller;

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

import com.jacoc.api.entity.Vendor;
import com.jacoc.api.service.VendorService;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/api/vendor")
public class VendorController {

	@Autowired
	private VendorService vendorService;
	
	@PostMapping
	public ResponseEntity<Vendor> createdVendor(@RequestBody  Vendor vendor){
		Vendor vendor2 = vendorService.createVendor(vendor);
		return new ResponseEntity<Vendor>(vendor2,HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<Vendor>> getAllVendor(){
		List<Vendor> vendorList = vendorService.getAllVendor();
		return new ResponseEntity<List<Vendor>>(vendorList,HttpStatus.OK);
	}
	@GetMapping("/vendorId/{vendorId}")
	public ResponseEntity<Vendor> getVendorById(@PathVariable Integer vendorId){
		Vendor vendorbyId = vendorService.getVendorbyId(vendorId);
		return new ResponseEntity<Vendor>(vendorbyId,HttpStatus.OK);
	}
	@GetMapping("/vendorName/{vendorName}")
	public ResponseEntity<List<Vendor>> getVendorInforByVendorName(@PathVariable String vendorName){
		List<Vendor> byVendorNameInfoList = vendorService.getVendorInformationByVendorName(vendorName);
		return new ResponseEntity<List<Vendor>>(byVendorNameInfoList,HttpStatus.OK);
	}
	@Transactional
	@DeleteMapping("/{vendorId}")
	public ResponseEntity<String> deleteVendorById(@PathVariable Integer vendorId){
		String deleteVendorById = vendorService.deleteVendorById(vendorId);
		return new ResponseEntity<String>("Success fully vendor is deleted: "+deleteVendorById,HttpStatus.OK);
	}
	
	@PutMapping("/{vendorId}")
	public ResponseEntity<String> vendorUpdated(@PathVariable Integer vendorId,@RequestBody Vendor updatedVendor){
		Vendor vendorUpdated = vendorService.updateVendorByVendorId(vendorId, updatedVendor);
		return new ResponseEntity<String>("Successfully vendor Updated:"+vendorUpdated,HttpStatus.ACCEPTED);
	}
	
}
