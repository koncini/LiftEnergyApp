package com.actum.springboot.liftEnergy.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.actum.springboot.liftEnergy.app.models.entity.UnitEvent;
import com.actum.springboot.liftEnergy.app.models.service.IUnitService;

@Controller
@RequestMapping("/unit-events")
public class UnitEventsController {

	@Autowired
	private IUnitService unitService;
	
	@Value("${texto.uniteventcontroller.list.message}")
	private String messageString;
	
	@Value("${texto.uniteventcontroller.list.title}")
	private String titleString;

	@GetMapping("/list-events/{id}")
	public String listEventsByUnit(@PathVariable Long id, Model model) {
		List<UnitEvent> unitEventsFiltered = null;
		model.addAttribute("title", titleString);
		model.addAttribute("message", messageString);
		model.addAttribute("unitEvents", unitEventsFiltered);
		return "events/listar";
	}

	@GetMapping("/list-events")
	public String listEvents(Model model) {
		List<UnitEvent> unitEvents = unitService.findAllUnitEvents();
		model.addAttribute("title", titleString);
		model.addAttribute("message", messageString);
		model.addAttribute("unitEvents", unitEvents);
		return "events/listar";
	}

}
