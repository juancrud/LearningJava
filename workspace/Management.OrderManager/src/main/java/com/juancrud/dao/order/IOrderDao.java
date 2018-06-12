package com.juancrud.dao.order;

import java.util.Collection;

import com.juancrud.entity.Market;
import com.juancrud.entity.Order;
import com.juancrud.framework.dao.interfaces.IGenericDao;

public interface IOrderDao extends IGenericDao<Order, Long> {
	
	Collection<Order> findOrdersByMarket(Market market);

}
