package com.juancrud.users.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.juancrud.framework.mapper.interfaces.GenericAutoMapper;

public abstract class GenericController {
	
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
    protected GenericAutoMapper mapper;

}
