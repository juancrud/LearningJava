package com.juancrud.framework.model;

import java.util.ArrayList;
import java.util.Collection;

public class ResponseModel {
	
	private boolean isSuccess;
	private String responseType;
	private Collection<ResponseMessageModel> messages;
	private Object entity;
	
	public ResponseModel() {
	}
	
	public ResponseModel(boolean isSuccess) {
		this.isSuccess = isSuccess;
		messages = new ArrayList<ResponseMessageModel>();
	}

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	
	public String getResponseType() {
		return responseType;
	}

	public void setResponseType(String responseType) {
		this.responseType = responseType;
	}

	public Collection<ResponseMessageModel> getMessages() {
		return messages;
	}

	public void setMessages(Collection<ResponseMessageModel> messages) {
		this.messages = messages;
	}

	public Object getEntity() {
		return entity;
	}

	public void setEntity(Object entity) {
		this.entity = entity;
	}
	
}
