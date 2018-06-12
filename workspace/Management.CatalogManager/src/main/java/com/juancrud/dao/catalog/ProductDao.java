package com.juancrud.dao.catalog;

import java.util.Collection;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.juancrud.entity.Category;
import com.juancrud.entity.Measure;
import com.juancrud.entity.Product;
import com.juancrud.framework.dao.GenericDao;

@Repository
@Transactional( propagation = Propagation.MANDATORY )
public class ProductDao extends GenericDao<Product, Long> implements IProductDao {
	
	public Collection<Product> findProductsByParent(Category category) {
		TypedQuery<Product> query;
		if(category == null) {
			query = this.getEntityManager().createQuery("SELECT p FROM Product p WHERE p.parent.id is null", Product.class);
		}
		else {
			query = this.getEntityManager().createQuery("SELECT p FROM Product p WHERE p.parent.id = :categoryId", Product.class);
			query.setParameter("categoryId", category.getId());
		}
		return (Collection<Product>) query.getResultList();
	}
	
	public Collection<Product> findProductsByMeasure(Measure measure) {
		TypedQuery<Product> query = this.getEntityManager().createQuery("SELECT p FROM Product p WHERE p.measure.id=:measureId", Product.class);
		query.setParameter("measureId", measure.getId());
		return query.getResultList();
	}
	
}
