package com.juancrud.mywebapp.mapping;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import com.juancrud.entity.Measure;
import com.juancrud.framework.mapper.interfaces.IMapper;
import com.juancrud.framework.model.SelectOptionModel;

public class MeasureMapper implements IMapper {
	
	public void setup(ModelMapper modelMapper) {
		
		modelMapper.addMappings(new PropertyMap<Measure, SelectOptionModel>() {
			protected void configure() {
				map().setValue(String.valueOf(source.getId()));
				map().setDisplayValue(source.getName());
			}
		});
		
	}
}
