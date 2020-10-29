package com.prs.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import com.prs.db.VendorRepo;

import com.prs.business.Vendor;

@CrossOrigin
@RestController
@RequestMapping("/api/vendors")

public class VendorController {

	@Autowired
	private VendorRepo vendorRepo;
	
	//All Vendors
	@GetMapping("")
	public List <Vendor> getAllVendors() {
		return vendorRepo.findAll();
	}
	
	//Add Vendors
	@PostMapping("")
	public Vendor addVendor(@RequestBody Vendor v) {
		return vendorRepo.save(v);
	}
	
	//Get Vendor By Id
	@GetMapping("/{id}")
	public Optional <Vendor> getVendor(@PathVariable int id){
		Optional <Vendor> v = vendorRepo.findById(id);
		if (v.isPresent()) {
			return v;
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Vendor not found");
		}	
	}
	
	//Update Vendor
	@PutMapping("/{id}")
	public Vendor updatevendor(@RequestBody Vendor v, @PathVariable int id) {
		if (id==v.getId()) {
		return vendorRepo.save(v);
		}
		else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Actor not found");	
		}
	}
	
	//Delete Vendor
	@DeleteMapping("/{id}")
	public Optional <Vendor> deletevendor(@PathVariable int id) {
		Optional <Vendor> v = vendorRepo.findById(id);
		if (v.isPresent()) {
			vendorRepo.deleteById(id);
		}
		else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Actor not found");
		}
		return v;
	}
}
