package com.juancrud.dao.order;

import java.util.Collection;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.juancrud.entity.Market;
import com.juancrud.entity.Order;
import com.juancrud.framework.dao.GenericDao;

@Repository
@Transactional( propagation = Propagation.MANDATORY )
public class OrderDao extends GenericDao<Order, Long> implements IOrderDao {
	
	public Collection<Order> findOrdersByMarket(Market market) {
		TypedQuery<Order> query = this.getEntityManager().createQuery("SELECT o FROM Order o WHERE o.market.id=:marketId", Order.class);
		query.setParameter("marketId", market.getId());
		return (Collection<Order>) query.getResultList();
	}
}
