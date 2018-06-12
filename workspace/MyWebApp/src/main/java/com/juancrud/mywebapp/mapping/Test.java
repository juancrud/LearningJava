package com.juancrud.mywebapp.mapping;

import com.juancrud.entity.Category;
import com.juancrud.mywebapp.model.CategoryModel;

public class Test {
	
	public static void main(String[] args) {
		System.out.println("Testing ModelMapper");
		AutoMapper mapper = new AutoMapper();
		mapper.initForTesting();
		mapper.init();
		
		CategoryModel categoryModel1 = new CategoryModel(1, "Category 1", 0);
		Category category1 = mapper.map(categoryModel1, Category.class);
		
		CategoryModel categoryModel2 = new CategoryModel(2, "Category 2", 1);
		Category category2 = mapper.map(categoryModel2, Category.class);
		
		System.out.println(categoryString(category1));
		System.out.println(categoryString(category2));
	}
	
	private static String categoryString(Category category) {
		StringBuilder result = new StringBuilder();
		
		result.append("{");
		result.append("'");
		result.append(category.getId());
		result.append("', '");
		result.append(category.getName());
		result.append("' ");
		
		if(category.getParent() != null) {
			result.append(categoryString(category.getParent()));
		}
		else{
			result.append("null");
		}
		
		result.append("}");
		return result.toString();
	}
	
}
