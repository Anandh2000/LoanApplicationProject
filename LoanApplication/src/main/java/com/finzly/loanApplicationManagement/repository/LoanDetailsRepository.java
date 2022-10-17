package com.finzly.loanApplicationManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.finzly.loanApplicationManagement.entity.LoanDetails;
@Repository
public interface LoanDetailsRepository extends JpaRepository<LoanDetails, Integer>{
	
	@Query("select c from LoanDetails c where c.customerId = ?1")
	LoanDetails customerRegisterValidation(int id);

}
