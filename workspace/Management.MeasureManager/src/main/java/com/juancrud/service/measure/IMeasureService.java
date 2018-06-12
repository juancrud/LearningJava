package com.juancrud.service.measure;

import java.util.Collection;

import com.juancrud.entity.Measure;
import com.juancrud.framework.service.interfaces.IServiceResponse;

public interface IMeasureService {
	
	IServiceResponse saveMeasure(Measure measure);
	
	IServiceResponse deleteMeasure(long measureId);
	
	Measure getMeasure(long measureId);
	
	Collection<Measure> getAllMeasures();
	
}
