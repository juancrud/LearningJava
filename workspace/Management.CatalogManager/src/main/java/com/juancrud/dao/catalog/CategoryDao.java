package com.juancrud.dao.catalog;

import java.util.Collection;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.juancrud.entity.Category;
import com.juancrud.framework.dao.GenericDao;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public class CategoryDao extends GenericDao<Category, Long> implements ICategoryDao {

	public Collection<Category> findCategoriesByParent(Category category) {
		TypedQuery<Category> query;
		if (category == null) {
			query = this.getEntityManager().createQuery("SELECT c FROM Category c WHERE c.parent.id is null", Category.class);
		} else {
			query = this.getEntityManager().createQuery("SELECT c FROM Category c WHERE c.parent.id = :categoryId", Category.class);
			query.setParameter("categoryId", category.getId());
		}
		return (Collection<Category>) query.getResultList();
	}
	
}
