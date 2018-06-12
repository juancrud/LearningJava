package com.juancrud.mywebapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/Modal")
public class ModalController extends GenericController {
	
	@RequestMapping(value = "/Confirmation", method = RequestMethod.GET)
	public String getConfirmationModal(Model model) {
		return "confirmationModal";
	}
	
	@RequestMapping(value = "/Alert", method = RequestMethod.GET)
	public String getAlertModal(Model model) {
		return "alertModal";
	}
	
}
