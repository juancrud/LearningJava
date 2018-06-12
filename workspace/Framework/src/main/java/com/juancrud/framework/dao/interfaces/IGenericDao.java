package com.juancrud.framework.dao.interfaces;

import java.io.Serializable;
import java.util.Collection;

public interface IGenericDao<T extends Serializable, Id extends Serializable> {
	
	void create(final T entity);
	
	void update(final T entity);
	
	void delete(final T entity);
	
	T find(final Id id);
	
	Collection<T> findAll();
	
}
