package com.juancrud.service.catalog;

import java.util.Collection;

import com.juancrud.entity.CatalogItem;
import com.juancrud.entity.Category;
import com.juancrud.entity.Product;
import com.juancrud.framework.service.interfaces.IServiceResponse;

public interface ICatalogService {
	
	//CatalogItems methods
	Collection<CatalogItem> getCatalog(boolean isRecursive);
	
	Collection<CatalogItem> getChildCatalogItems(Category category, boolean isRecursive);
	
	//Category methods
	IServiceResponse saveCategory(Category category);
	
	IServiceResponse deleteCategory(long categoryId);
	
	IServiceResponse moveCategory(long categoryId, long parentId);
	
	Category getCategory(long categoryId);
	
	Collection<Category> getRootCategories(boolean isRecursive);
	
	Collection<Category> getChildCategories(Category category, boolean isRecursive);
	
	
	//Product methods
	IServiceResponse saveProduct(Product product);
	
	IServiceResponse deleteProduct(long productId);
	
	IServiceResponse moveProduct(long productId, long parentId);
	
	Product getProduct(long productId);
	
	Collection<Product> getRootProducts(boolean isRecursive);
	
	Collection<Product> getChildProducts(Category category, boolean isRecursive);
	
	Collection<Product> getAllProducts();
	
}
