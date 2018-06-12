package com.juancrud.dao.order;

import java.util.Collection;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.juancrud.entity.OrderLine;
import com.juancrud.entity.Product;
import com.juancrud.framework.dao.GenericDao;

@Repository
@Transactional( propagation = Propagation.MANDATORY )
public class OrderLineDao extends GenericDao<OrderLine, Long> implements IOrderLineDao {
	
	public Collection<OrderLine> findOrderLinesByProduct(Product product) {
		TypedQuery<OrderLine> query = this.getEntityManager().createQuery("SELECT ol FROM OrderLine ol WHERE ol.product.id=:productId", OrderLine.class);
		query.setParameter("productId", product.getId());
		return query.getResultList();
	}
	
}
