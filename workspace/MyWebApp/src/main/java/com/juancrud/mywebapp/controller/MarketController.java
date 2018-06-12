package com.juancrud.mywebapp.controller;

import java.lang.reflect.Type;
import java.util.Collection;

import javax.validation.Valid;

import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.juancrud.entity.Market;
import com.juancrud.framework.model.ResponseModel;
import com.juancrud.framework.service.interfaces.IServiceResponse;
import com.juancrud.mywebapp.model.MarketModel;
import com.juancrud.service.market.IMarketService;

@Controller
@RequestMapping("/Market")
public class MarketController extends GenericController {
	
	@Autowired
    private IMarketService marketService;
	
	@RequestMapping(value = "/List", method = RequestMethod.GET)
	public String listMarkets(Model model) {
		Collection<Market> markets = marketService.getAllMarkets();
	    Type targetType = new TypeToken<Collection<MarketModel>>(){}.getType();
		Collection<MarketModel> marketModels = this.mapper.map(markets, targetType);
		
		model.addAttribute("marketsList", marketModels);
		return "listMarkets";
	}
	
	@RequestMapping(value = "/AddEditMarket", method = RequestMethod.GET)
	public String addEditMarket(Model model, @RequestParam(value = "id", defaultValue = "") long marketId) {
		MarketModel marketModel;
		if(marketId == 0) {
			marketModel = new MarketModel();
		}
		else {
			Market market = marketService.getMarket(marketId);
			marketModel = this.mapper.map(market, MarketModel.class);
		}
		
		model.addAttribute("newMarket", marketModel);
		return "addEditMarket";
	}
	
	@RequestMapping(value = "/AddEditMarket", method = RequestMethod.POST)
	public @ResponseBody ResponseModel addEditMarket(@Valid @ModelAttribute("newMarket") MarketModel newMarketModel, BindingResult result, Model model) {
		if (result.hasErrors()) {
            return null;
        }
		
		Market market = this.mapper.map(newMarketModel, Market.class);
		IServiceResponse response = marketService.saveMarket(market);
		
		ResponseModel responseModel = this.mapper.map(response, ResponseModel.class);
		if(responseModel.isSuccess()) {
			MarketModel marketModel = this.mapper.map(market, MarketModel.class);
			responseModel.setEntity(marketModel);
		}
        return responseModel;
	}
	
	@RequestMapping(value = "/DeleteMarket", method = RequestMethod.POST)
	public @ResponseBody ResponseModel deleteMarket(Model model, @RequestParam(value = "id", defaultValue = "") long marketId) {
		IServiceResponse response = marketService.deleteMarket(marketId);
		
		ResponseModel responseModel = this.mapper.map(response, ResponseModel.class);
        return responseModel;
	}
	
}
