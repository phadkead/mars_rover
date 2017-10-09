package com.rover.exception;

public class CordinatesOutOfBoundriesException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5048119459442428132L;
	private String message;

	public CordinatesOutOfBoundriesException(String message) {
		this.message = message;
	}
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}

}
