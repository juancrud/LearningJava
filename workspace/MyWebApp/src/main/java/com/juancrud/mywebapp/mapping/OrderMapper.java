package com.juancrud.mywebapp.mapping;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import com.juancrud.entity.Order;
import com.juancrud.entity.OrderLine;
import com.juancrud.framework.mapper.interfaces.IMapper;
import com.juancrud.mywebapp.model.OrderLineModel;
import com.juancrud.mywebapp.model.OrderModel;

public class OrderMapper implements IMapper {
	
	public void setup(ModelMapper modelMapper) {
		
		modelMapper.addMappings(new PropertyMap<Order, OrderModel>() {
			protected void configure() {
				map(source.getOrderLines()).setOrderLines(null);
			}
		});
		
		modelMapper.addMappings(new PropertyMap<OrderModel, Order>() {
			protected void configure() {
				
			}
		});
		
		modelMapper.addMappings(new PropertyMap<OrderLine, OrderLineModel>() {
			protected void configure() {
				
			}
		});
	}
}
