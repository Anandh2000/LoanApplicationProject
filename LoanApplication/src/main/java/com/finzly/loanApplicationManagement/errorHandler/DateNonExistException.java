package com.finzly.loanApplicationManagement.errorHandler;

public class DateNonExistException extends RuntimeException {
	public DateNonExistException(String message) {
		super(message);
	}
}