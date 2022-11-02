package com.finzly.loanApplicationManagement.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
@Entity
public class LoanDetails {
	@Id
	@Pattern(regexp = "^\\d{7,7}$",message = "Customer name is invalids")
	private String customerId;
	@Min(1000) @Max(1000000000)
	private double loanAmount;
	private LocalDate tradeDate;
	private LocalDate loanStartDate;
	private int termOfLoanInMonths;
	@Enumerated(EnumType.STRING)
	private PaymentTerm paymentTerm;
	private LocalDate  maturityDate;
	private int paymentFrequency;
	@Min(1) 
	@Max(99)
	private double interestRate;
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinColumn(name = "paymentId",referencedColumnName = "customerId")
	public List<PaymentSchedule> paymentSchedules = new ArrayList<>();
	
	public LoanDetails(String customerId, double loanAmount, LocalDate tradeDate, LocalDate loanStartDate,
				int termOfLoanInMonths, PaymentTerm paymentTerm, LocalDate maturityDate, int paymentFrequency, double interestRate,
				List<PaymentSchedule> paymentSchedules) {
			super();
			this.customerId = customerId;
			this.loanAmount = loanAmount;
			this.tradeDate = tradeDate;
			this.loanStartDate = loanStartDate;
			this.termOfLoanInMonths = termOfLoanInMonths;
			this.paymentTerm = paymentTerm;
			this.maturityDate = maturityDate;
			this.paymentFrequency = paymentFrequency;
			this.interestRate = interestRate;
			this.paymentSchedules = paymentSchedules;
		}

	public PaymentTerm getPaymentTerm() {
		return paymentTerm;
	}

	public void setPaymentTerm(PaymentTerm paymentTerm) {
		this.paymentTerm = paymentTerm;
	}


	public List<PaymentSchedule> getPaymentSchedules() {
		return paymentSchedules;
	}

	public void setPaymentSchedules(List<PaymentSchedule> paymentSchedules) {
		this.paymentSchedules = paymentSchedules;
	}

	public int getTermOfLoanInMonths() {
		return termOfLoanInMonths;
	}

	public void setTermOfLoanInMonths(int termOfLoanInMonths) {
		this.termOfLoanInMonths = termOfLoanInMonths;
	}

	public LoanDetails() {
	}


	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public double getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(double loanAmount) {
		this.loanAmount = loanAmount;
	}

	public LocalDate getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(LocalDate tradeDate) {
		this.tradeDate = tradeDate;
	}

	public LocalDate getLoanStartDate() {
		return loanStartDate;
	}

	public void setLoanStartDate(LocalDate loanStartDate) {
		this.loanStartDate = loanStartDate;
	}

	public LocalDate getMaturityDate() {
		return maturityDate;
	}

	public void setMaturityDate(LocalDate maturityDate) {
		this.maturityDate = maturityDate;
	}

	public int getPaymentFrequency() {
		return paymentFrequency;
	}

	public void setPaymentFrequency(int paymentFrequency) {
		this.paymentFrequency = paymentFrequency;
	}



	public double getInterestRate() {
		return interestRate;
	}



	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}

	@Override
	public String toString() {
		return "LoanDetails [customerId=" + customerId + ", loanAmount=" + loanAmount + ", tradeDate=" + tradeDate
				+ ", loanStartDate=" + loanStartDate + ", termOfLoanInMonths=" + termOfLoanInMonths + ", paymentTerm="
				+ paymentTerm + ", maturityDate=" + maturityDate + ", paymentFrequency=" + paymentFrequency
				+ ", intrestRate=" + interestRate + ", paymentSchedules=" + paymentSchedules + "]";
	}
	
	
	
}
