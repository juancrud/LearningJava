package com.juancrud.framework.mapper.interfaces;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class GenericAutoMapper extends ModelMapper {

	@Autowired
	private IMapper serviceMapper;

	public abstract void init();

	public abstract void initForTesting();

}
