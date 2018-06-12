package com.juancrud.mywebapp.mapping;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.spi.MappingContext;

import com.juancrud.entity.Category;
import com.juancrud.framework.mapper.interfaces.IMapper;
import com.juancrud.mywebapp.model.CatalogItemModel;
import com.juancrud.mywebapp.model.CategoryModel;

public class CategoryMapper implements IMapper {
	
	public void setup(ModelMapper modelMapper) {
		
		final Converter<Long, Category> newCategoryConverter = new Converter<Long, Category>() {
			@Override
			public Category convert(MappingContext<Long, Category> context) {
				long id = context.getSource();
				return id == 0 ? null : new Category(id);
			}
		};
		
		modelMapper.addMappings(new PropertyMap<CategoryModel, Category>() {
			@Override
			protected void configure() {
				using(newCategoryConverter).map(source.getParentId()).setParent(null);
			}
		});
		
		modelMapper.addMappings(new PropertyMap<Category, CategoryModel>() {
			@Override
			protected void configure() {
				map().setParentId(source.getParent().getId());
			}
		});
		
		modelMapper.addMappings(new PropertyMap<Category, CatalogItemModel>() {
			@Override
			protected void configure() {
				map(source.getSubcategories()).setChildCategories(null);
				map(source.getProducts()).setChildProducts(null);
			}
		});
		
	}
}
