package com.juancrud.service.order;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.juancrud.dao.order.IOrderDao;
import com.juancrud.dao.order.IOrderLineDao;
import com.juancrud.entity.Order;
import com.juancrud.entity.OrderLine;
import com.juancrud.framework.service.ServiceResponse;
import com.juancrud.framework.service.enums.ResponseType;
import com.juancrud.framework.service.interfaces.IServiceResponse;

@Service
@Transactional
public class OrderService implements IOrderService {
	
	@Autowired
    private IOrderDao orderDao;
	
	@Autowired
    private IOrderLineDao orderLineDao;
	
	//Order methods
	public IServiceResponse saveOrder(Order order) {
		boolean isNew = order.getId() == 0;
		ResponseType responseType = isNew ? ResponseType.CREATE : ResponseType.UPDATE;
		IServiceResponse serviceResponse = new ServiceResponse(responseType);
		
		if(isNew) {
			orderDao.create(order);
		}
		else {
			orderDao.update(order);
		}
		
		return serviceResponse;
	}
	
	public IServiceResponse deleteOrder(long orderId) {
		IServiceResponse serviceResponse = new ServiceResponse(ResponseType.DELETE);
		
		Order order = orderDao.find(orderId);
		
		//serviceResponse.getMessages().addAll(validateDeleteMarket(market));
		if(serviceResponse.isSuccess()) {
			orderDao.delete(order);
		}
		
		return serviceResponse;
	}
	
	public Order getOrder(long orderId) {
		return orderDao.find(orderId);
	}
	
	public Collection<Order> getAllOrders() {
		return orderDao.findAll();
	}
	
	//Order Line methods
	public OrderLine getOrderLine(long orderLineId) {
		return orderLineDao.find(orderLineId);
	}
}
