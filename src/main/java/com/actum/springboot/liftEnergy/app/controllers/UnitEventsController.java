package com.actum.springboot.liftEnergy.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.actum.springboot.liftEnergy.app.models.service.IUnitService;

@Controller
@RequestMapping("/unit-events")
public class UnitEventsController {
	
	@Autowired
	private IUnitService unitService;
	
	@GetMapping("/list-events")
	public String listEvents() {
		return null;
	}

}
