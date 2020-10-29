package com.prs.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import com.prs.db.LineitemRepo;
import com.prs.db.ProductRepo;
import com.prs.db.RequestRepo;
import com.prs.business.Lineitem;
import com.prs.business.Product;
import com.prs.business.Request;


@CrossOrigin
@RestController
@RequestMapping("api/lines")

public class LineitemController {

	@Autowired
	private LineitemRepo lineitemRepo;
	@Autowired	
	private RequestRepo requestRepo;
	@Autowired
	private ProductRepo productRepo;
	
	
    //Get All
	@GetMapping("")
	public List <Lineitem> getAllLineitems() {
		return lineitemRepo.findAll();
	}
	
	//Get All By Request
	@GetMapping("for-req/{id}")
	public @ResponseBody Iterable <Lineitem> getLinesForPR(@PathVariable int id) {
	Iterable <Lineitem> l = lineitemRepo.findAllByRequestId(id);
		return l;
		}
	
	//Add Line Item
	@PostMapping("/{id}")
	public Lineitem addLineitem(@RequestBody Lineitem li) {	
		
		lineitemRepo.save(li);
		recalculateRequestTotal(li.getRequest());	
		return li;
		
	}
	
	//Get By Id
	@GetMapping("/{id}")
	public Optional <Lineitem> getLineitem(@PathVariable int id){
		Optional <Lineitem> l = lineitemRepo.findById(id);
		if (l.isPresent()) {
			return l;
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "LineItem not found");
		}
	}
	
	//Update Line Item
	@PutMapping("/{id}")
	public Lineitem updateLineitem(@RequestBody Lineitem l, @PathVariable int id) {
		if (id==l.getId()) {
		l = lineitemRepo.save(l);
		recalculateRequestTotal(l.getRequest());
		}
		else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Actor not found");	
		}
	return lineitemRepo.save(l);
	}
	
	//Delete Line Item
	@DeleteMapping("/{id}")
	public Lineitem deleteLineitem(@PathVariable int id) {
		Lineitem l = null;
		if (lineitemRepo.existsById(id)) {
			l = lineitemRepo.findById(id).get();
			Request r = l.getRequest();
			lineitemRepo.deleteById(id);
			recalculateRequestTotal(r);
		}
		else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Actor not found");
		}
		return l;
	}

	
	//Recalculations
	private void recalculateRequestTotal(Request r) {
		List<Lineitem> liList = lineitemRepo.findAllByRequestId(r.getId());
		// loop thru list to sum a total
		double totalPrice = 0.0;
		for (Lineitem li: liList) {		
			double itemPrice = (li.getQuantity())*(li.getProduct().getPrice());
			totalPrice += itemPrice;	
		}
		// save that total in the User instance
		r.setTotal(totalPrice);
		requestRepo.save(r);
	}
	}
	
	

