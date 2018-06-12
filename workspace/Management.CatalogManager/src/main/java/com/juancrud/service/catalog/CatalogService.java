package com.juancrud.service.catalog;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.juancrud.dao.catalog.ICategoryDao;
import com.juancrud.dao.catalog.IProductDao;
import com.juancrud.dao.order.IOrderDao;
import com.juancrud.dao.order.IOrderLineDao;
import com.juancrud.entity.CatalogItem;
import com.juancrud.entity.Category;
import com.juancrud.entity.OrderLine;
import com.juancrud.entity.Product;
import com.juancrud.framework.service.ServiceResponse;
import com.juancrud.framework.service.ServiceResponseMessage;
import com.juancrud.framework.service.enums.ResponseType;
import com.juancrud.framework.service.interfaces.IServiceResponse;
import com.juancrud.mywebapp.service.ServiceErrorCodes;

@Service
@Transactional
public class CatalogService implements ICatalogService {
	
	@Autowired
    private ICategoryDao categoryDao;
	
	@Autowired
    private IProductDao productDao;
	
	@Autowired
	private IOrderDao orderDao;
	
	@Autowired
	private IOrderLineDao orderLineDao;
	
	public Collection<CatalogItem> getCatalog(boolean isRecursive) {
		return getChildCatalogItems(null, isRecursive);
	}
	
	public Collection<CatalogItem> getChildCatalogItems(Category category, boolean isRecursive) {
		Collection<CatalogItem> result = new ArrayList<CatalogItem>();
		result.addAll(getChildCategories(category, isRecursive));
		result.addAll(getChildProducts(category, isRecursive));
		
		return result;
	}
	
	//Category methods
	public IServiceResponse saveCategory(Category category) {
		boolean isNew = category.getId() == 0;
		ResponseType responseType = isNew ? ResponseType.CREATE : ResponseType.UPDATE;
		IServiceResponse serviceResponse = new ServiceResponse(responseType);
		
		serviceResponse.getMessages().addAll(validateSaveCategory(category));
		if(serviceResponse.isSuccess() && isNew) {
			categoryDao.create(category);
		}
		else if(serviceResponse.isSuccess()) {
			categoryDao.update(category);
		}
		
		return serviceResponse;
	}
	
	public IServiceResponse deleteCategory(long categoryId) {
		IServiceResponse serviceResponse = new ServiceResponse(ResponseType.DELETE);
		
		Category category = categoryDao.find(categoryId);
		
		serviceResponse.getMessages().addAll(validateDeleteCategory(category));
		if(serviceResponse.isSuccess()) {
			categoryDao.delete(category);
		}
		
		return serviceResponse;
	}
	
	public IServiceResponse moveCategory(long categoryId, long parentId) {
		IServiceResponse serviceResponse = new ServiceResponse(ResponseType.UPDATE);
		
		Category category = categoryDao.find(categoryId);
		Category parentCategory = categoryDao.find(parentId);
		category.setParent(parentCategory);
		categoryDao.update(category);
		
		return serviceResponse;
	}
	
	public Category getCategory(long categoryId) {
		return categoryDao.find(categoryId);
	}
	
	public Collection<Category> getRootCategories(boolean isRecursive) {
		return getChildCategories(null, isRecursive);
	}
	
	public Collection<Category> getChildCategories(Category category, boolean isRecursive) {
		Collection<Category> result = categoryDao.findCategoriesByParent(category);
		
		return result;
	}
	
	private Collection<ServiceResponseMessage> validateSaveCategory(Category category) {
		Collection<ServiceResponseMessage> messages = new ArrayList<ServiceResponseMessage>();
		
		//Check if parent is valid - selected parent shouldn't be the same category
		if(category.getId() == category.getParent().getId()) {
			messages.add(new ServiceResponseMessage(ServiceErrorCodes.CAN_NOT_SET_PARENT_TO_ITSELF_POTENTIAL_CIRCULAR_REFERENCE));
		}
		
		//Check if parent is valid - selected parent shouldn't be a (nested) child of this category to prevent circular references
		Collection<Category> childCategories = getChildCategories(category, false);
		Collection<Category> flattenedCategories = getAllFlattenedSubCategories(childCategories);
		for(Category cat : flattenedCategories) {
			if(cat.getId() == category.getParent().getId()) {
				messages.add(new ServiceResponseMessage(ServiceErrorCodes.CAN_NOT_MOVE_CATEGORY_POTENTIAL_CIRCULAR_REFERENCE));
				break;
			}
		}
		
		return messages;
	}
	
	private Collection<ServiceResponseMessage> validateDeleteCategory(Category category) {
		Collection<ServiceResponseMessage> messages = new ArrayList<ServiceResponseMessage>();
		
		//Check if category has products
		Collection<Product> childProducts = getChildProducts(category, false);
		if(childProducts.size() > 0) {
			messages.add(new ServiceResponseMessage(ServiceErrorCodes.CAN_NOT_DELETE_CATEGORY_WITH_PRODUCTS));
		}
		
		//Check if category has nested product
		Collection<Category> childCategories = getChildCategories(category, false);
		Collection<Product> flattenedCategories = getAllFlattenedSubProducts(childCategories);
		if(flattenedCategories.size() > 0) {
			messages.add(new ServiceResponseMessage(ServiceErrorCodes.CAN_NOT_DELETE_CATEGORY_WITH_NESTED_PRODUCTS));
		}
		
		return messages;
	}
	
	private Collection<Category> getAllFlattenedSubCategories(Collection<Category> categories) {
		Collection<Category> result = new ArrayList<Category>();
		
		for(Category category : categories) {
			Collection<Category> childCategories = getChildCategories(category, false);
			result.addAll(categories);
			result.addAll(getAllFlattenedSubCategories(childCategories));
		}
		
		return result;
	}
	
	private Collection<Product> getAllFlattenedSubProducts(Collection<Category> categories) {
		Collection<Product> result = new ArrayList<Product>();
		
		for(Category category : categories) {
			Collection<Product> childProducts = getChildProducts(category, false);
			Collection<Category> childCategories = getChildCategories(category, false);
			
			result.addAll(childProducts);
			result.addAll(getAllFlattenedSubProducts(childCategories));
		}
		
		return result;
	}
	
	
	//Product methods
	public IServiceResponse saveProduct(Product product) {
		boolean isNew = product.getId() == 0;
		ResponseType responseType = isNew ? ResponseType.CREATE : ResponseType.UPDATE;
		IServiceResponse serviceResponse = new ServiceResponse(responseType);
		
		if(isNew) {
			productDao.create(product);
		}
		else {
			productDao.update(product);
		}
		
		return serviceResponse;
	}
	
	public IServiceResponse deleteProduct(long productId) {
		IServiceResponse serviceResponse = new ServiceResponse(ResponseType.DELETE);
		Product product = productDao.find(productId);
		
		serviceResponse.getMessages().addAll(validateDeleteProduct(product));
		if(serviceResponse.isSuccess()) {
			productDao.delete(product);
		}
		
		return serviceResponse;
	}
	
	public IServiceResponse moveProduct(long productId, long parentId) {
		IServiceResponse serviceResponse = new ServiceResponse(ResponseType.UPDATE);
		
		Product product = productDao.find(productId);
		Category parentCategory = categoryDao.find(parentId);
		product.setParent(parentCategory);
		productDao.update(product);
		
		return serviceResponse;
	}
	
	public Product getProduct(long productId) {
		return productDao.find(productId);
	}
	
	public Collection<Product> getRootProducts(boolean isRecursive) {
		return getChildProducts(null, isRecursive);
	}
	
	public Collection<Product> getChildProducts(Category category, boolean isRecursive) {
		Collection<Product> result = productDao.findProductsByParent(category);
		if(isRecursive) {
			
		}
		
		return result;
	}
	
	public Collection<Product> getAllProducts() {
		return productDao.findAll();
	}
	
	private Collection<ServiceResponseMessage> validateDeleteProduct(Product product) {
		Collection<ServiceResponseMessage> messages = new ArrayList<ServiceResponseMessage>();
		
		//Check if product is used on an order
		Collection<OrderLine> orderLines = orderLineDao.findOrderLinesByProduct(product);
		if(orderLines.size() > 0) {
			messages.add(new ServiceResponseMessage(ServiceErrorCodes.CAN_NOT_DELETE_PRODUCT_IN_USE));
		}
		
		return messages;
	}
	
}
