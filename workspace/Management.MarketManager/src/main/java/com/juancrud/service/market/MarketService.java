package com.juancrud.service.market;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.juancrud.dao.market.IMarketDao;
import com.juancrud.dao.measure.IMeasureDao;
import com.juancrud.dao.order.IOrderDao;
import com.juancrud.entity.Market;
import com.juancrud.entity.Order;
import com.juancrud.framework.service.ServiceResponse;
import com.juancrud.framework.service.ServiceResponseMessage;
import com.juancrud.framework.service.enums.ResponseType;
import com.juancrud.framework.service.interfaces.IServiceResponse;
import com.juancrud.mywebapp.service.ServiceErrorCodes;

@Service
@Transactional
public class MarketService implements IMarketService {
	
	@Autowired
    private IMarketDao marketDao;
	
	@Autowired
    private IMeasureDao measureDao;
	
	@Autowired
	private IOrderDao orderDao;
	
	@Override
	public IServiceResponse saveMarket(Market market) {
		boolean isNew = market.getId() == 0;
		ResponseType responseType = isNew ? ResponseType.CREATE : ResponseType.UPDATE;
		IServiceResponse serviceResponse = new ServiceResponse(responseType);
		
		if(isNew) {
			marketDao.create(market);
		}
		else {
			marketDao.update(market);
		}
		
		return serviceResponse;
	}
	
	@Override
	public IServiceResponse deleteMarket(long marketId) {
		IServiceResponse serviceResponse = new ServiceResponse(ResponseType.DELETE);
		
		Market market = marketDao.find(marketId);
		
		serviceResponse.getMessages().addAll(validateDeleteMarket(market));
		if(serviceResponse.isSuccess()) {
			marketDao.delete(market);
		}
		
		return serviceResponse;
	}
	
	@Override
	public Market getMarket(long marketId) {
		return marketDao.find(marketId);
	}
	
	@Override
	public Collection<Market> getAllMarkets() {
		return marketDao.findAll();
	}
	
	private Collection<ServiceResponseMessage> validateDeleteMarket(Market market) {
		Collection<ServiceResponseMessage> messages = new ArrayList<ServiceResponseMessage>();
		
		//Check if market is already used
		Collection<Order> orders = orderDao.findOrdersByMarket(market);
		if(orders.size() > 0) {
			messages.add(new ServiceResponseMessage(ServiceErrorCodes.CAN_NOT_DELETE_MARKET_IN_USE));
		}
		
		return messages;
	}
}
