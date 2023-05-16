package com.actum.springboot.liftEnergy.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.actum.springboot.liftEnergy.app.models.service.IUnitService;

import jakarta.annotation.PostConstruct;

@Controller
public class UnitProductionController {
	
	@Autowired
	private IUnitService unitService; 
	
	private Long eventsUnattended;
	
	@PostConstruct
	public void init() {
		eventsUnattended = unitService.getCountOfUnattendedEvents();
	}


}
