package com.finzly.loanApplicationManagement.serviceImplementation;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.finzly.loanApplicationManagement.Service.LoanService;
import com.finzly.loanApplicationManagement.entity.LoanDetails;
import com.finzly.loanApplicationManagement.entity.PaymentSchedule;
import com.finzly.loanApplicationManagement.entity.PaymentTerm;
import com.finzly.loanApplicationManagement.entity.Status;
import com.finzly.loanApplicationManagement.errorHandler.CustomerNotFoundException;
import com.finzly.loanApplicationManagement.errorHandler.DateNonExistException;
import com.finzly.loanApplicationManagement.errorHandler.EmptyListException;
import com.finzly.loanApplicationManagement.repository.LoanDetailsRepository;
import com.finzly.loanApplicationManagement.repository.PaymentScheduleRepository;
import com.finzly.loanApplicationManagement.response.SuccessResponse;

@Component
public class LoanServiceImpl implements LoanService {
	@Autowired
	private LoanDetailsRepository repository;
	
	@Autowired
	private PaymentScheduleRepository repo;

	//method to create a loan for a customer
	@Override
	public ResponseEntity<SuccessResponse> createLoan(LoanDetails details){
		if(details.getTradeDate().compareTo(details.getLoanStartDate())>0) {
			throw new DateNonExistException("TradeStart Date should be greater");	
		}
		details.setMaturityDate(details.getLoanStartDate().plusMonths(details.getTermOfLoanInMonths()));
		List<PaymentSchedule> schedules = details.getPaymentSchedules();
		int paymentSchedule = details.getTermOfLoanInMonths() / details.getPaymentFrequency();
		double tempLoanAmount = details.getLoanAmount();
		LocalDate tempPaymentDate = details.getLoanStartDate();
		while(tempPaymentDate.compareTo(details.getMaturityDate())<0) {
			PaymentSchedule schedule = new PaymentSchedule();
			tempPaymentDate = tempPaymentDate.plusMonths(details.getPaymentFrequency());
			schedule.setPaymentDate(tempPaymentDate);
			if (details.getPaymentTerm().equals(PaymentTerm.EvenPrincipal)) {
				schedule = paymentScheduleOfEvenPrinciple(schedule,details.getLoanAmount(),paymentSchedule,tempLoanAmount,details.getInterestRate());
				tempLoanAmount = tempLoanAmount - schedule.getPrincipal();
			} 
			else {
				schedule = paymentScheduleOfInterestOnly(schedule,details.getLoanAmount(),details.getMaturityDate(),details.getInterestRate());
			}
			schedule=paymentStatusSetter(schedule);
			schedules.add(schedule);
		}
		details.setPaymentSchedules(schedules);
		System.out.println(schedules.toString());
		repository.save(details);
		
	    SuccessResponse successResponse = new SuccessResponse("Created",LocalDateTime.now() , 201);
		return new ResponseEntity<SuccessResponse>(successResponse, HttpStatus.CREATED);
	}
	
	//method to get all the loan details
	@Override
	public ResponseEntity<List<LoanDetails>> getAllDetails() {
		List<LoanDetails> loanDetails = repository.findAll();
		if(loanDetails.isEmpty()) {
			throw new EmptyListException("List is Empty"); 
		}
		return new ResponseEntity<List<LoanDetails>>(loanDetails, HttpStatus.OK);
	}
	
	//method to get payment schedule deyails for a customer
	@Override
	public ResponseEntity<List<PaymentSchedule>> getLoansByCustomerId(int id) {
	 LoanDetails  loanDetail= repository.findById(id)
			 .orElseThrow(() -> new CustomerNotFoundException("Customer Not Found:"+id));
		return new ResponseEntity<List<PaymentSchedule>>(loanDetail.getPaymentSchedules(), HttpStatus.OK);
	}
	
	//method to update payment status when paid
	@Override
	public ResponseEntity<List<PaymentSchedule>> paymentUpdate(int id) {
		 LoanDetails  loanDetail= repository.findById(id)
				 .orElseThrow(() -> new CustomerNotFoundException("Customer Not Found:"+id));
			for(PaymentSchedule schedule : loanDetail.getPaymentSchedules()) {
				schedule = paymentStatusSetter(schedule);
				repo.save(schedule);
			}
			return new ResponseEntity<List<PaymentSchedule>>(loanDetail.getPaymentSchedules(), HttpStatus.OK);
		}
	
	//method to set payment status
	@Override
	public PaymentSchedule paymentStatusSetter(PaymentSchedule schedule) {
		if (schedule.getPaymentDate().compareTo(LocalDate.now()) < 0 || schedule.getPaymentStatus().equals(Status.AwaitingPayment)) {
			schedule.setPaymentStatus(Status.Paid);
		} else if (schedule.getPaymentDate().equals(LocalDate.now()) &&  !schedule.getPaymentStatus().equals(Status.Paid)) {
			schedule.setPaymentStatus(Status.AwaitingPayment);
		}
		return schedule;
	}
	
	//payment schedule for even principle
	public PaymentSchedule paymentScheduleOfEvenPrinciple(PaymentSchedule schedule,double loanAmount,int paymentSchedule,double tempLoanAmount,double interestRate) {
		schedule.setPrincipal(loanAmount / paymentSchedule);
		schedule.setProjectedInterest((tempLoanAmount * interestRate)/100);
		schedule.setPaymentAmount(schedule.getProjectedInterest() + schedule.getPrincipal());
		return schedule;
	}
	
	//payment schedule for interest only
	public PaymentSchedule paymentScheduleOfInterestOnly(PaymentSchedule schedule,double loanAmount,LocalDate maturityDate,double interestRate) {
		schedule.setPrincipal(0);
		if(schedule.getPaymentDate().equals(maturityDate)){
			schedule.setPrincipal(loanAmount);
		}
		schedule.setProjectedInterest(
				((loanAmount*interestRate)/100)/12);
		double paymentAmount = (schedule.getPaymentDate().equals(maturityDate))
				? schedule.getProjectedInterest() + loanAmount
				: schedule.getProjectedInterest();
		schedule.setPaymentAmount(paymentAmount);
		return schedule;
	}
	
	
}
