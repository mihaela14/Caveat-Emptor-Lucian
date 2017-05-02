package com.fortech.exception;

public class CategoriesDetailsException extends Exception {


	private static final long serialVersionUID = 3121675128835739661L;
	
	private String errorMessage;

	
	public CategoriesDetailsException(String errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage;
	}

	public CategoriesDetailsException() {
		super();
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
}
