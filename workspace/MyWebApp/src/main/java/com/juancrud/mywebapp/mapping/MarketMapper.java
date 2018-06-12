package com.juancrud.mywebapp.mapping;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import com.juancrud.entity.Market;
import com.juancrud.framework.mapper.interfaces.IMapper;
import com.juancrud.framework.model.SelectOptionModel;
import com.juancrud.mywebapp.model.MarketModel;

public class MarketMapper implements IMapper {
	
	public void setup(ModelMapper modelMapper) {
		
		modelMapper.addMappings(new PropertyMap<MarketModel, Market>() {
			protected void configure() {
			}
		});
		
		modelMapper.addMappings(new PropertyMap<Market, MarketModel>() {
			protected void configure() {
			}
		});
		
		modelMapper.addMappings(new PropertyMap<Market, SelectOptionModel>() {
			protected void configure() {
				map().setValue(String.valueOf(source.getId()));
				map().setDisplayValue(source.getName());
			}
		});
	}
}
