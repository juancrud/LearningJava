package com.juancrud.mywebapp.mapping;

import org.modelmapper.Condition;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.spi.MappingContext;

import com.juancrud.entity.Category;
import com.juancrud.entity.Product;
import com.juancrud.framework.mapper.interfaces.IMapper;
import com.juancrud.framework.model.SelectOptionModel;
import com.juancrud.mywebapp.model.ProductModel;

public class ProductMapper implements IMapper {
	
	public void setup(ModelMapper modelMapper) {
		
		final Converter<Long, Category> newCategoryConverter = new Converter<Long, Category>() {
			@Override
			public Category convert(MappingContext<Long, Category> context) {
				long id = context.getSource();
				return id == 0 ? null : new Category(id);
			}
		};
		
		final Condition<Long, Category> notZero = new Condition<Long, Category>() {
			public boolean applies(MappingContext<Long, Category> context) {
				return context.getSource() != 0;
			}
		};
		
		modelMapper.addMappings(new PropertyMap<ProductModel, Product>() {
			protected void configure() {
				when(notZero).using(newCategoryConverter).map(source.getParentId()).setParent(null);
			}
		});
		
		modelMapper.addMappings(new PropertyMap<Product, ProductModel>() {
			protected void configure() {
			}
		});
		
		modelMapper.addMappings(new PropertyMap<Product, SelectOptionModel>() {
			protected void configure() {
				map().setValue(String.valueOf(source.getId()));
				map().setDisplayValue(source.getName());
			}
		});
		
	}

}
