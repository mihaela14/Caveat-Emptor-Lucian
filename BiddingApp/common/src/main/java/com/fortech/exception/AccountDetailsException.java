package com.fortech.exception;

public class AccountDetailsException extends Exception {


	private static final long serialVersionUID = 3121675128835739661L;
	
	private String errorMessage;

	
	public AccountDetailsException(String errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage;
	}

	
	public AccountDetailsException() {
		super();
	}


	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
