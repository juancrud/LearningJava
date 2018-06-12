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

import com.juancrud.entity.Measure;
import com.juancrud.framework.model.ResponseModel;
import com.juancrud.framework.service.interfaces.IServiceResponse;
import com.juancrud.mywebapp.model.MeasureModel;
import com.juancrud.service.measure.IMeasureService;

@Controller
@RequestMapping("/Measure")
public class MeasureController extends GenericController {
	
	@Autowired
    private IMeasureService measureService;
	
	@RequestMapping(value = "/List", method = RequestMethod.GET)
	public String listMeasures(Model model) {
		Collection<Measure> measures = measureService.getAllMeasures();
	    Type targetType = new TypeToken<Collection<MeasureModel>>(){}.getType();
		Collection<MeasureModel> measureModels = this.mapper.map(measures, targetType);
		
		model.addAttribute("measuresList", measureModels);
		return "listMeasures";
	}
	
	@RequestMapping(value = "/AddEditMeasure", method = RequestMethod.GET)
	public String addEditMeasure(Model model, @RequestParam(value = "id", defaultValue = "") long measureId) {
		MeasureModel measureModel;
		if(measureId == 0) {
			measureModel = new MeasureModel();
		}
		else {
			Measure measure = measureService.getMeasure(measureId);
			measureModel = this.mapper.map(measure, MeasureModel.class);
		}
		
		model.addAttribute("newMeasure", measureModel);
		return "addEditMeasure";
	}
	
	@RequestMapping(value = "/AddEditMeasure", method = RequestMethod.POST)
	public @ResponseBody ResponseModel addEditMeasure(@Valid @ModelAttribute("newMeasure") MeasureModel newMeasureModel, BindingResult result, Model model) {
		if (result.hasErrors()) {
            return null;
        }
		
		Measure measure = this.mapper.map(newMeasureModel, Measure.class);
		IServiceResponse response = measureService.saveMeasure(measure);
		
		ResponseModel responseModel = this.mapper.map(response, ResponseModel.class);
		if(responseModel.isSuccess()) {
			MeasureModel measureModel = this.mapper.map(measure, MeasureModel.class);
			responseModel.setEntity(measureModel);
		}
        return responseModel;
	}
	
	@RequestMapping(value = "/DeleteMeasure", method = RequestMethod.POST)
	public @ResponseBody ResponseModel deleteMeasure(Model model, @RequestParam(value = "id", defaultValue = "") long measureId) {
		IServiceResponse response = measureService.deleteMeasure(measureId);
		
		ResponseModel responseModel = this.mapper.map(response, ResponseModel.class);
        return responseModel;
	}
}
