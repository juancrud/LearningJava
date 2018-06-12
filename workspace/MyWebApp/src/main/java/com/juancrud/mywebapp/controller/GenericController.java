package com.juancrud.mywebapp.controller;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import com.juancrud.framework.mapper.interfaces.GenericAutoMapper;

public abstract class GenericController {
	
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
    protected GenericAutoMapper mapper;
	
	@Autowired  
	protected MessageSource messageSource;
	
	
	protected String getMessage(String messageKey, Locale locale) {
		return messageSource.getMessage(messageKey, null, locale);
	}
	
}
