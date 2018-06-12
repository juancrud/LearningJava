package com.juancrud.framework.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.juancrud.framework.dao.interfaces.IGenericDao;

@Repository
@Transactional (propagation=Propagation.MANDATORY)
public abstract class GenericDao<T extends Serializable, Id extends Serializable> implements IGenericDao<T, Id> {
	
	protected Class<T> persistentClass;
	
	@Autowired
    private EntityManager entityManager;
    
    @SuppressWarnings("unchecked")
    public GenericDao() {
    	ParameterizedType type = ((ParameterizedType) getClass().getGenericSuperclass());
    	this.persistentClass = (Class<T>) type.getActualTypeArguments()[0];
    }

    protected EntityManager getEntityManager() {
        return entityManager;
    }
    
    protected CriteriaBuilder getCriteriaBuilder() {
    	return entityManager.getCriteriaBuilder();
    }
    
    public void create(final T entity) {
    	entityManager.persist(entity);
    }

    public void update(final T entity) {
    	entityManager.merge(entity);
    }
    
    public void delete(final T entity) {
    	entityManager.remove(entity);
    }

    public T find(final Id id) {
        return entityManager.find(persistentClass, id);
    }
    
    public Collection<T> findAll() {
    	CriteriaQuery<T> criteria = getCriteriaBuilder().createQuery(persistentClass);
        criteria.select(criteria.from(persistentClass));
        return entityManager.createQuery(criteria).getResultList();
    }

}
