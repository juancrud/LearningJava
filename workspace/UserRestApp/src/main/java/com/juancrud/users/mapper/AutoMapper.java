package com.juancrud.users.mapper;

import org.springframework.beans.factory.annotation.Autowired;

import com.juancrud.framework.mapper.interfaces.GenericAutoMapper;
import com.juancrud.framework.mapper.interfaces.IMapper;

public class AutoMapper extends GenericAutoMapper {
	
	@Autowired
	private IMapper userMapper;
	
	public void init() {
 		userMapper.setup(this);
	}
	
	public void initForTesting() {
		this.userMapper = new UserMapper();
	}
	
}
