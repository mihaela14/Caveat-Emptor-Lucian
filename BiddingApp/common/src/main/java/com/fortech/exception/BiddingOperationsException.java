package com.fortech.exception;

public class BiddingOperationsException extends Exception{

	private static final long serialVersionUID = -7565760574885264697L;

	private String errorMessage;

	
	public BiddingOperationsException() {
		super();
	}
	public BiddingOperationsException(String errorMessage) {
		super();
		this.errorMessage = errorMessage;
	}

	
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}



}
