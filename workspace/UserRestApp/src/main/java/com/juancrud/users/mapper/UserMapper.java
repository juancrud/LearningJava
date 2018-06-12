package com.juancrud.users.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import com.juancrud.entity.User;
import com.juancrud.framework.mapper.interfaces.IMapper;
import com.juancrud.users.model.UserModel;

public class UserMapper implements IMapper {

	public void setup(ModelMapper modelMapper) {

		modelMapper.addMappings(new PropertyMap<User, UserModel>() {
			@Override
			protected void configure() {
			}
		});
		
		modelMapper.addMappings(new PropertyMap<UserModel, User>() {
			@Override
			protected void configure() {
			}
		});
	}

}
