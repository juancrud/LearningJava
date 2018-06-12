package com.juancrud.service.measure;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.juancrud.dao.catalog.IProductDao;
import com.juancrud.dao.measure.IMeasureDao;
import com.juancrud.entity.Measure;
import com.juancrud.entity.Product;
import com.juancrud.framework.service.ServiceResponse;
import com.juancrud.framework.service.ServiceResponseMessage;
import com.juancrud.framework.service.enums.ResponseType;
import com.juancrud.framework.service.interfaces.IServiceResponse;
import com.juancrud.mywebapp.service.ServiceErrorCodes;

@Service
@Transactional
public class MeasureService implements IMeasureService {
	
	@Autowired
    private IMeasureDao measureDao;
	
	@Autowired
	private IProductDao productDao;
	
	@Override
	public IServiceResponse saveMeasure(Measure measure) {
		boolean isNew = measure.getId() == 0;
		ResponseType responseType = isNew ? ResponseType.CREATE : ResponseType.UPDATE;
		IServiceResponse serviceResponse = new ServiceResponse(responseType);
		
		if(isNew) {
			measureDao.create(measure);
		}
		else {
			measureDao.update(measure);
		}
		
		return serviceResponse;
	}
	
	@Override
	public IServiceResponse deleteMeasure(long measureId) {
		IServiceResponse serviceResponse = new ServiceResponse(ResponseType.DELETE);
		
		Measure measure = measureDao.find(measureId);
		serviceResponse.getMessages().addAll(validateDeleteMeasure(measure));
		if(serviceResponse.isSuccess()) {
			measureDao.delete(measure);
		}
		
		return serviceResponse;
	}

	@Override
	public Measure getMeasure(long measureId) {
		return measureDao.find(measureId);
	}

	@Override
	public Collection<Measure> getAllMeasures() {
		return measureDao.findAll();
	}
	
	private Collection<ServiceResponseMessage> validateDeleteMeasure(Measure measure) {
		Collection<ServiceResponseMessage> messages = new ArrayList<ServiceResponseMessage>();
		
		//Check if measure is used on a product
		Collection<Product> products = productDao.findProductsByMeasure(measure);
		if(products.size() > 0) {
			messages.add(new ServiceResponseMessage(ServiceErrorCodes.CAN_NOT_DELETE_MEASURE_IN_USE));
		}
		
		return messages;
	}
}
