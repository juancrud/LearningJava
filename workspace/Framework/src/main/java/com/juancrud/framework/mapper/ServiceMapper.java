package com.juancrud.framework.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import com.juancrud.framework.mapper.interfaces.IMapper;
import com.juancrud.framework.model.ResponseMessageModel;
import com.juancrud.framework.model.ResponseModel;
import com.juancrud.framework.service.ServiceResponseMessage;
import com.juancrud.framework.service.interfaces.IServiceResponse;

public class ServiceMapper implements IMapper {
	
	public void setup(ModelMapper modelMapper) {
		
		modelMapper.addMappings(new PropertyMap<ServiceResponseMessage, ResponseMessageModel>() {
			protected void configure() {
//				map().setMessage(source.getMessage());
//				map().setMessageCode(source.getErrorCode().name());
			}
		});
		
		modelMapper.addMappings(new PropertyMap<IServiceResponse, ResponseModel>() {
			protected void configure() {
			}
		});
		
	}

}
