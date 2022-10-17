package com.finzly.loanApplicationManagement.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.finzly.loanApplicationManagement.entity.LoanDetails;
import com.finzly.loanApplicationManagement.entity.PaymentSchedule;
import com.finzly.loanApplicationManagement.errorHandler.ErrorHandlerService;
import com.finzly.loanApplicationManagement.serviceImplementation.LoanServiceImpl;
@SpringBootApplication
@CrossOrigin(origins="http://localhost:4200")
@RestController
public class LoanApplicationController{
	@Autowired
	LoanServiceImpl service;
	
	Logger logger = LoggerFactory.getLogger(LoanApplicationController.class);
	
	@PostMapping("/createNewLoan")
	public ResponseEntity<?> loanBook(@RequestBody @Valid LoanDetails details){
		ResponseEntity<?> storedLoan = service.createLoan(details);
		logger.trace("created a loan");
		return storedLoan;
	}
	
	@GetMapping("/loanDetails")
	public Object customerDetails(){
		return service.getAllDetails();
	}
	
	@GetMapping("/customer/{id}")
	public Object customerPaymentDetails(@PathVariable int id){
		return service.getLoansByCustomerId(id);
	}
	
	@GetMapping("/customer/payment/{id}")
	public Object payment(@PathVariable int id){
		logger.trace("paid a loan");
		return service.paymentUpdate(id);
	}
	
	// for getting the structure of inputs in postman
//	@GetMapping("/loan")
//	public Object printDetail(){
//		return new LoanDetails();
//	}
//	
//	@GetMapping("/schedule")
//	public Object printDetai(){
//		return new PaymentSchedule();
//	}
	
	
}
