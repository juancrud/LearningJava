package com.juancrud.service.market;

import java.util.Collection;

import com.juancrud.entity.Market;
import com.juancrud.framework.service.interfaces.IServiceResponse;

public interface IMarketService {
	
	//Market methods
	IServiceResponse saveMarket(Market market);
	
	IServiceResponse deleteMarket(long marketId);
	
	Market getMarket(long marketId);
	
	Collection<Market> getAllMarkets();
	
}
