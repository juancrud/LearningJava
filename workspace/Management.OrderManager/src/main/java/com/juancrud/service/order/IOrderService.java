package com.juancrud.service.order;

import java.util.Collection;

import com.juancrud.entity.Order;
import com.juancrud.entity.OrderLine;
import com.juancrud.framework.service.interfaces.IServiceResponse;

public interface IOrderService {
	
	//Order methods
	IServiceResponse saveOrder(Order order);
	IServiceResponse deleteOrder(long orderId);
	Order getOrder(long orderId);
	Collection<Order> getAllOrders();
	
	//Order line methods
	OrderLine getOrderLine(long orderLineId);
}
