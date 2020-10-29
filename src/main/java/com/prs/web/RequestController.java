package com.prs.web;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.prs.db.LineitemRepo;
import com.prs.db.RequestRepo;
import com.prs.db.UserRepo;
import com.prs.business.Request;
import com.prs.business.User;
import com.prs.business.Request;

@CrossOrigin
@RestController
@RequestMapping("api/requests")

public class RequestController {

	@Autowired
	private RequestRepo requestRepo;
	@Autowired
	private LineitemRepo lineitemRepo;
	@Autowired
	private UserRepo userRepo;
	
	//Get All Requests
	@GetMapping("")
	public List <Request> getAllRequests() {
		return requestRepo.findAll();
	}
	
	//Add Requests	
	@PostMapping("")
	public Request addRequest(@RequestBody Request r) {
		r.setStatus("new");
		r.setSubmittedDate(LocalDate.now());
		return requestRepo.save(r);
	}
	
	//Get Request By Id
	@GetMapping("/{id}")
	public Optional <Request> getRequest(@PathVariable int id){
		Optional <Request> r = requestRepo.findById(id);
		if (r.isPresent()) {
			return r;
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Request not found");
		}	
	}
	
	//Update Request
	@PutMapping("/{id}")
	public Request updateRequest(@RequestBody Request r, @PathVariable int id) {
		if (id==r.getId()) {
		return requestRepo.save(r);
		}
		else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Actor not found");	
		}
	}
	
	//Review Requests By Not Own Id
	@GetMapping("/reviews/{id}")
	public @ResponseBody Iterable <Request>getReviewRequests(@PathVariable int id) {
		Iterable<Request> pr = requestRepo.findAllByUserIdNotAndStatus(id, "Review");
		
		return pr;
	}
	
	//Delete Request
	@DeleteMapping("/{id}")
	public Optional <Request> deleteRequest(@PathVariable int id) {
		Optional <Request> r = requestRepo.findById(id);
		if (r.isPresent()) {
			requestRepo.deleteById(id);
		}
		else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Actor not found");
		}
		return r;
	}
	
	//Initial Review of Requests
	@PutMapping(path="/review") 
	public Request submitForReview (@RequestBody Request r) {
		if (r.getTotal()>50)
			r.setStatus("Review");
		else
			r.setStatus("Approved");
		return requestRepo.save(r);
	}
	
	//Approval of Requests
	@PutMapping(path="/approve") 
	public Request submitForApprove (@RequestBody Request r) {
			r.setStatus("Approved");
		return requestRepo.save(r);
	}
	
	//Rejection of Requests
	@PutMapping(path="/reject") 
	public Request submitForReject (@RequestBody Request r) {
			r.setStatus("Rejected");
		return requestRepo.save(r);
	}
	

}
