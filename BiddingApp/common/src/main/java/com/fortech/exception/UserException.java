package com.fortech.exception;

public class UserException extends Exception {

	private static final long serialVersionUID = -2366496590728800657L;
	
	private String errorMessage;

	public UserException(String errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
