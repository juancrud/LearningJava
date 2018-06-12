package com.juancrud.dao.order;

import java.util.Collection;

import com.juancrud.entity.OrderLine;
import com.juancrud.entity.Product;
import com.juancrud.framework.dao.interfaces.IGenericDao;

public interface IOrderLineDao extends IGenericDao<OrderLine, Long> {
	
	Collection<OrderLine> findOrderLinesByProduct(Product product);
}
