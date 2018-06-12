package com.juancrud.framework.service;

public class ServiceResponseMessage {

	// Fields
	private int errorCode;
	private String message;

	// Constructors
	public ServiceResponseMessage(int errorCode) {
		this.errorCode = errorCode;
	}

	public ServiceResponseMessage(int errorCode, String message) {
		this(errorCode);
		this.message = message;
	}

	// Getters - Setters
	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
