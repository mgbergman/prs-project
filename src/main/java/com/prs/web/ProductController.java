package com.prs.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import com.prs.db.ProductRepo;
import com.prs.business.Product;
import com.prs.business.Product;

@CrossOrigin
@RestController
@RequestMapping("api/products")

public class ProductController {

	@Autowired
	private ProductRepo productRepo;
	
	//Get All Products
	@GetMapping("")
	public List <Product> getAllProducts() {
		return productRepo.findAll();
	}
	
	//Add Products
	@PostMapping("")
	public Product addProduct(@RequestBody Product p) {
		return productRepo.save(p);
	
	}
	
	// Get Product By Id
	@GetMapping("/{id}")
	public Optional <Product> getProduct(@PathVariable int id){
		Optional <Product> p = productRepo.findById(id);
		if (p.isPresent()) {
			return p;
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
		}	
	}
	
	//Update Product
	@PutMapping("/{id}")
	public Product updateProduct(@RequestBody Product p, @PathVariable int id) {
		if (id==p.getId()) {
		return productRepo.save(p);
		}
		else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Actor not found");	
		}
	}
	
	//Delete Product
	@DeleteMapping("/{id}")
	public Optional <Product> deleteProduct(@PathVariable int id) {
		Optional <Product> p = productRepo.findById(id);
		if (p.isPresent()) {
			productRepo.deleteById(id);
		}
		else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Actor not found");
		}
		return p;
	}
}
