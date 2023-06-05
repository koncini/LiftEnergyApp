package com.actum.springboot.liftEnergy.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.actum.springboot.liftEnergy.app.models.service.IDataService;

import jakarta.annotation.PostConstruct;

@Controller
public class UnitProductionController {
	
	@Autowired
	private IDataService dataService; 
	
	private Long eventsUnattended;
	
	@PostConstruct
	public void init() {
		eventsUnattended = dataService.getCountOfUnattendedEvents();
	}


}
