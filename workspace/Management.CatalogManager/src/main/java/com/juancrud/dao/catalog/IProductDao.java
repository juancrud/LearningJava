package com.juancrud.dao.catalog;

import java.util.Collection;

import com.juancrud.entity.Category;
import com.juancrud.entity.Measure;
import com.juancrud.entity.Product;
import com.juancrud.framework.dao.interfaces.IGenericDao;

public interface IProductDao extends IGenericDao<Product, Long> {
	
	Collection<Product> findProductsByParent(Category category);
	
	Collection<Product> findProductsByMeasure(Measure measure);
	
}
