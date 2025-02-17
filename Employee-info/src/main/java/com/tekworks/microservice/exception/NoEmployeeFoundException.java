package com.tekworks.microservice.exception;

public class NoEmployeeFoundException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public NoEmployeeFoundException() {
		
	}
    public NoEmployeeFoundException(String message) {
		super(message);
	}
}
