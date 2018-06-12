package com.juancrud.dao.catalog;

import java.util.Collection;

import com.juancrud.entity.Category;
import com.juancrud.framework.dao.interfaces.IGenericDao;

public interface ICategoryDao extends IGenericDao<Category, Long> {
	
	Collection<Category> findCategoriesByParent(Category category);
	
}
