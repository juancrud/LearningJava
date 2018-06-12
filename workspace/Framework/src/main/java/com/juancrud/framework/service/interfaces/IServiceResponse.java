package com.juancrud.framework.service.interfaces;

import java.util.Collection;

import com.juancrud.framework.service.ServiceResponseMessage;
import com.juancrud.framework.service.enums.ResponseType;

public interface IServiceResponse {
	
	Collection<ServiceResponseMessage> getMessages();
	
	ResponseType getResponseType();
	
	boolean isSuccess();
	
	boolean hasErrors();
	
}
