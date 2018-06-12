package com.juancrud.framework.service;

import java.util.ArrayList;
import java.util.Collection;

import com.juancrud.framework.service.enums.ResponseType;
import com.juancrud.framework.service.interfaces.IServiceResponse;

public class ServiceResponse implements IServiceResponse {
	
	//Fields
	private ResponseType responseType;
	private Collection<ServiceResponseMessage> messages;
	
	//Constructors
	public ServiceResponse(ResponseType responseType) {
		this.responseType = responseType;
		this.messages = new ArrayList<ServiceResponseMessage>();
	}
	
	//Getters - Setters
	public ResponseType getResponseType() {
		return responseType;
	}

	public void setResponseType(ResponseType responseType) {
		this.responseType = responseType;
	}

	public Collection<ServiceResponseMessage> getMessages() {
		return messages;
	}

	public void setMessages(Collection<ServiceResponseMessage> messages) {
		this.messages = messages;
	}
	
	//Methods
	public boolean isSuccess() {
		return this.messages.size() == 0;
	}
	
	public boolean hasErrors() {
		return this.messages.size() > 0;
	}
	
}
