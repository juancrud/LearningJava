package com.juancrud.mywebapp.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.juancrud.framework.model.ResponseModel;
import com.juancrud.mywebapp.model.MenuItemModel;

@Controller
@RequestMapping("/")
public class HomeController extends GenericController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Locale locale, Model model) {
		model.addAttribute("menuItems", getMenuItems(locale));
		return "start";
	}
	
	@RequestMapping(value = "/GetResources", method = RequestMethod.GET)
	public @ResponseBody ResponseModel getResources(Locale locale, Model model, @RequestParam(value = "keys") String[] resourceKeys) {
		Map<String, String> resources = new HashMap<String, String>();
		for(String resourceKey : resourceKeys) {
			String resourceValue = getMessage(resourceKey, locale);
			resources.put(resourceKey, resourceValue);
		}
		
		ResponseModel responseModel = new ResponseModel(true);
		responseModel.setEntity(resources);
		return responseModel;
	}
	
	private List<MenuItemModel> getMenuItems(Locale locale) {
		List<MenuItemModel> menuItems = new ArrayList<MenuItemModel>();
		menuItems.add(new MenuItemModel("goMarkets", getMessage("general.menu.markets", locale), "Market/List"));
		menuItems.add(new MenuItemModel("goMeasures", getMessage("general.menu.measures", locale), "Measure/List"));
		menuItems.add(new MenuItemModel("goCatalogs", getMessage("general.menu.catalog", locale), "Catalog/List"));
		menuItems.add(new MenuItemModel("goOrders", getMessage("general.menu.orders", locale), "Order/List"));
		
		return menuItems;
	}
	
}
