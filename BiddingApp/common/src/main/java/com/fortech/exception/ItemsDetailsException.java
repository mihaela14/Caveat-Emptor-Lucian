package com.fortech.exception;

public class ItemsDetailsException extends Exception {
	
	private static final long serialVersionUID = -3113919970163183917L;
	
	private String errorMessage;

	
	public ItemsDetailsException(String errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage;
	}

	public ItemsDetailsException() {
		super();
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
}
