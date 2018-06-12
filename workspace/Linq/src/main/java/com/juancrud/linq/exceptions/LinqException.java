package com.juancrud.linq.exceptions;

@SuppressWarnings("serial")
public class LinqException extends Exception {
	
	public LinqException(String message) {
		super(message);
	}
	
	public LinqException(String message, Throwable cause) {
		super(message, cause);
	}
}
