package com.juancrud.mywebapp.controller;

import java.lang.reflect.Type;
import java.util.ArrayList;
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

import com.juancrud.entity.Category;
import com.juancrud.entity.Measure;
import com.juancrud.entity.Product;
import com.juancrud.framework.model.ResponseModel;
import com.juancrud.framework.model.SelectOptionModel;
import com.juancrud.framework.service.interfaces.IServiceResponse;
import com.juancrud.mywebapp.model.CatalogItemModel;
import com.juancrud.mywebapp.model.CategoryModel;
import com.juancrud.mywebapp.model.ProductModel;
import com.juancrud.service.catalog.ICatalogService;
import com.juancrud.service.market.IMarketService;
import com.juancrud.service.measure.IMeasureService;

@Controller
@RequestMapping("/Catalog")
public class CatalogController extends GenericController {
	
	@Autowired
    private ICatalogService catalogService;
	
	@Autowired
    private IMarketService marketService;
	
	@Autowired
	private IMeasureService measureService;
	
	@RequestMapping(value = "/List", method = RequestMethod.GET)
	public String listItems(Model model, 
			@RequestParam(value = "isLazyload", defaultValue = "false", required = false) boolean isLazyload,
			@RequestParam(value = "isEditable", defaultValue = "true", required = false) boolean isEditable,
			@RequestParam(value = "isDeletable", defaultValue = "true", required = false) boolean isDeletable,
			@RequestParam(value = "isAddable", defaultValue = "true", required = false) boolean isAddable) {
		Collection<Category> rootCategories = catalogService.getRootCategories(!isLazyload);
		Type categoriesType = new TypeToken<Collection<CatalogItemModel>>(){}.getType();
		Collection<CatalogItemModel> rootCategoriesModel = this.mapper.map(rootCategories, categoriesType);
		
		Collection<Product> rootProducts = catalogService.getRootProducts(!isLazyload);
		Type productsType = new TypeToken<Collection<CatalogItemModel>>(){}.getType();
		Collection<CatalogItemModel> rootProductsModel = this.mapper.map(rootProducts, productsType);
		
		model.addAttribute("isAddable", isAddable);
		model.addAttribute("isEditable", isEditable);
		model.addAttribute("isDeletable", isDeletable);
		model.addAttribute("categoriesList", rootCategoriesModel);
		model.addAttribute("productsList", rootProductsModel);
		return "listCatalog";
	}
	
	@RequestMapping(value = "/AddEditCategory", method = RequestMethod.GET)
	public String addEditCategory(Model model, 
								  @RequestParam(value = "id", defaultValue = "") long categoryId,
								  @RequestParam(value = "parentId", defaultValue = "") long parentId) {
		CategoryModel categoryModel;
		if(categoryId == 0) {
			categoryModel = new CategoryModel(parentId);
		}
		else {
			Category category = catalogService.getCategory(categoryId);
			categoryModel = this.mapper.map(category, CategoryModel.class);
		}
		
		model.addAttribute("categoriesList", getFlattenedCategories());
		model.addAttribute("newCategory", categoryModel);
		return "addEditCategory";
	}
	
	@RequestMapping(value = "/AddEditCategory", method = RequestMethod.POST)
	public @ResponseBody ResponseModel addEditCategory(@Valid @ModelAttribute("newCategory") CategoryModel newCategoryModel, BindingResult result, Model model) {
		if (result.hasErrors()) {
            return null;
        }
		
		Category category = this.mapper.map(newCategoryModel, Category.class);
		IServiceResponse response = catalogService.saveCategory(category);
		
		ResponseModel responseModel = this.mapper.map(response, ResponseModel.class);
		if(responseModel.isSuccess()) {
			CategoryModel categoryModel = this.mapper.map(category, CategoryModel.class);
			responseModel.setEntity(categoryModel);
		}
        return responseModel;
	}
	
	@RequestMapping(value = "/AddEditProduct", method = RequestMethod.GET)
	public String addEditProduct(Model model, 
								 @RequestParam(value = "id", defaultValue = "") long productId,
								 @RequestParam(value = "parentId", defaultValue = "") long parentId) {
		ProductModel productModel;
		if(productId == 0) {
			productModel = new ProductModel(parentId);
		}
		else {
			Product product = catalogService.getProduct(productId);
			productModel = this.mapper.map(product, ProductModel.class);
		}
		
		Collection<Measure> measures = measureService.getAllMeasures();
		Type targetType = new TypeToken<Collection<SelectOptionModel>>(){}.getType();
		Collection<SelectOptionModel> measuresList = this.mapper.map(measures, targetType);
		
		model.addAttribute("categoriesList", getFlattenedCategories());
		model.addAttribute("measuresList", measuresList);
		model.addAttribute("newProduct", productModel);
		return "addEditProduct";
	}
	
	@RequestMapping(value = "/AddEditProduct", method = RequestMethod.POST)
	public @ResponseBody ResponseModel addEditProduct(@Valid @ModelAttribute("newProduct") ProductModel newProductModel, BindingResult result, Model model) {
		if (result.hasErrors()) {
            return null;
        }
		
		Product product = this.mapper.map(newProductModel, Product.class);
		IServiceResponse response = catalogService.saveProduct(product);
		
		ResponseModel responseModel = this.mapper.map(response, ResponseModel.class);
		if(responseModel.isSuccess()) {
			ProductModel productModel = this.mapper.map(product, ProductModel.class);
			responseModel.setEntity(productModel);
		}
        return responseModel;
	}
	
	@RequestMapping(value = "/DeleteCategory", method = RequestMethod.POST)
	public @ResponseBody ResponseModel deleteCategory(Model model, @RequestParam(value = "id", defaultValue = "") long categoryId) {
		IServiceResponse response = catalogService.deleteCategory(categoryId);
		
		ResponseModel responseModel = this.mapper.map(response, ResponseModel.class);
        return responseModel;
	}
	
	@RequestMapping(value = "/DeleteProduct", method = RequestMethod.POST)
	public @ResponseBody ResponseModel deleteProduct(Model model, @RequestParam(value = "id", defaultValue = "") long productId) {
		IServiceResponse response = catalogService.deleteProduct(productId);
		
		ResponseModel responseModel = this.mapper.map(response, ResponseModel.class);
        return responseModel;
	}
	
	@RequestMapping(value = "/MoveCategory", method = RequestMethod.POST)
	public @ResponseBody ResponseModel moveCategory(Model model, @RequestParam(value = "id") long categoryId, @RequestParam(value = "parentId") long parentId) {
		IServiceResponse response = catalogService.moveCategory(categoryId, parentId);
		
		ResponseModel responseModel = this.mapper.map(response, ResponseModel.class);
		return responseModel;
	}
	
	@RequestMapping(value = "/MoveProduct", method = RequestMethod.POST)
	public @ResponseBody ResponseModel moveProduct(Model model, @RequestParam(value = "id") long productId, @RequestParam(value = "parentId") long parentId) {
		IServiceResponse response = catalogService.moveProduct(productId, parentId);
		
		ResponseModel responseModel = this.mapper.map(response, ResponseModel.class);
		return responseModel;
	}
	
	private Collection<SelectOptionModel> getFlattenedCategories() {
		Collection<SelectOptionModel> result = new ArrayList<SelectOptionModel>();
		
		Collection<Category> rootCategories = catalogService.getRootCategories(true);
		result.addAll(getFlattenedCategories(rootCategories, ""));
		
		return result;
	}
	
	private Collection<SelectOptionModel> getFlattenedCategories(Collection<Category> categories, String parentPath) {
		Collection<SelectOptionModel> result = new ArrayList<SelectOptionModel>();
		
		for(Category category : categories) {
			String newDisplayValue = parentPath + category.getName();
			String newParentPath = newDisplayValue + " - ";
			
			SelectOptionModel model = new SelectOptionModel();
			model.setValue(String.valueOf(category.getId()));
			model.setDisplayValue(newDisplayValue);
			result.add(model);
			
			result.addAll(getFlattenedCategories(category.getSubcategories(), newParentPath));
		}
		
		return result;
	}
}
