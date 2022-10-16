package com.finzly.loanApplicationManagement.Service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.finzly.loanApplicationManagement.entity.LoanDetails;
import com.finzly.loanApplicationManagement.entity.PaymentSchedule;
import com.finzly.loanApplicationManagement.response.SuccessResponse;
@Service
public interface LoanService {
	public ResponseEntity<?> createLoan(LoanDetails details);
	public ResponseEntity<?> getAllDetails();
	public ResponseEntity<?> getLoansByCustomerId(int id);
	public ResponseEntity<List<PaymentSchedule>> paymentUpdate(int id);
	public PaymentSchedule paymentStatusSetter(PaymentSchedule schedule);

}