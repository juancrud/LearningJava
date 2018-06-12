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
import com.juancrud.entity.Order;
import com.juancrud.entity.OrderLine;
import com.juancrud.entity.Product;
import com.juancrud.framework.model.ResponseModel;
import com.juancrud.framework.model.SelectOptionModel;
import com.juancrud.framework.service.interfaces.IServiceResponse;
import com.juancrud.mywebapp.model.OrderLineModel;
import com.juancrud.mywebapp.model.OrderModel;
import com.juancrud.service.catalog.ICatalogService;
import com.juancrud.service.market.IMarketService;
import com.juancrud.service.order.IOrderService;

@Controller
@RequestMapping("/Order")
public class OrderController extends GenericController {
	
	@Autowired
    private IOrderService orderService;
	
	@Autowired
    private IMarketService marketService;
	
	@Autowired
    private ICatalogService catalogService;
	
	@RequestMapping(value = "/List", method = RequestMethod.GET)
	public String listOrders(Model model) {
		Collection<Order> orders = orderService.getAllOrders();
	    Type targetType = new TypeToken<Collection<OrderModel>>(){}.getType();
		Collection<OrderModel> orderModels = this.mapper.map(orders, targetType);
		
		model.addAttribute("ordersList", orderModels);
		return "listOrders";
	}
	
	@RequestMapping(value = "/ListOrderLines", method = RequestMethod.GET)
	public String listOrderLines(Model model, @RequestParam(value = "id") long orderId) {
		Order order = orderService.getOrder(orderId);
	    Type targetType = new TypeToken<Collection<OrderLineModel>>(){}.getType();
		Collection<OrderLineModel> orderLineModels = this.mapper.map(order.getOrderLines(), targetType);
		
		model.addAttribute("orderId", orderId);
		model.addAttribute("orderLinesList", orderLineModels);
		return "listOrderLines";
	}
	
	@RequestMapping(value = "/AddEditOrder", method = RequestMethod.GET)
	public String addEditOrder(Model model, @RequestParam(value = "id", defaultValue = "") long orderId) {
		OrderModel orderModel;
		if(orderId == 0) {
			orderModel = new OrderModel();
		}
		else {
			Order order = orderService.getOrder(orderId);
			orderModel = this.mapper.map(order, OrderModel.class);
		}
		
		Collection<Market> markets = marketService.getAllMarkets();
		Type optionsType = new TypeToken<Collection<SelectOptionModel>>(){}.getType();
		Collection<SelectOptionModel> marketsListModel = this.mapper.map(markets, optionsType);
		
		model.addAttribute("marketsList", marketsListModel);
		model.addAttribute("newOrder", orderModel);
		return "addEditOrder";
	}
	
	@RequestMapping(value = "/AddEditOrder", method = RequestMethod.POST)
	public @ResponseBody ResponseModel addEditOrder(@Valid @ModelAttribute("newOrder") OrderModel newOrderModel, BindingResult result, Model model) {
		if (result.hasErrors()) {
            return null;
        }
		
		Order order = this.mapper.map(newOrderModel, Order.class);
		for(OrderLineModel orderLineModel : newOrderModel.getOrderLines()) {
			OrderLine ol = this.mapper.map(orderLineModel, OrderLine.class);
			order.addOrderLine(ol);
		}
		IServiceResponse response = orderService.saveOrder(order);
		
		ResponseModel responseModel = this.mapper.map(response, ResponseModel.class);
		if(responseModel.isSuccess()) {
			OrderModel orderModel = this.mapper.map(order, OrderModel.class);
			Market market = marketService.getMarket(orderModel.getMarketId());
			orderModel.setMarketName(market.getName());
			responseModel.setEntity(orderModel);
		}
        return responseModel;
	}
	
	@RequestMapping(value = "/DeleteOrder", method = RequestMethod.POST)
	public @ResponseBody ResponseModel deleteOrder(Model model, @RequestParam(value = "id", defaultValue = "") long orderId) {
		IServiceResponse response = orderService.deleteOrder(orderId);
		
		ResponseModel responseModel = this.mapper.map(response, ResponseModel.class);
        return responseModel;
	}
	
	@RequestMapping(value = "/AddEditOrderLine", method = RequestMethod.GET)
	public String addEditOrderLine(Model model, @RequestParam(value = "index", defaultValue = "-1") int index) {
		OrderLineModel orderLineModel = new OrderLineModel(index);
		
		Collection<Product> products = catalogService.getAllProducts();
		Type optionsType = new TypeToken<Collection<SelectOptionModel>>(){}.getType();
		Collection<SelectOptionModel> productsListModel = this.mapper.map(products, optionsType);
		
		model.addAttribute("productsList", productsListModel);
		model.addAttribute("newOrderLine", orderLineModel);
		return "addEditOrderLine";
	}
}
