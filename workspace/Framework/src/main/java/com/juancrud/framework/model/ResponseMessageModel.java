package com.juancrud.framework.model;

public class ResponseMessageModel {
	
	private String messageCode;
	private String message;
	
	public ResponseMessageModel() {
	}
	
	public ResponseMessageModel(String messageCode, String message) {
		this.messageCode = messageCode;
		this.message = message;
	}

	public String getMessageCode() {
		return messageCode;
	}

	public void setMessageCode(String messageCode) {
		this.messageCode = messageCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
