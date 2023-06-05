package com.actum.springboot.liftEnergy.app.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.actum.springboot.liftEnergy.app.models.entity.UnitEvent;
import com.actum.springboot.liftEnergy.app.models.service.IDataService;

import jakarta.annotation.PostConstruct;

@Controller
@RequestMapping("/unit-events")
public class UnitEventsController {

	@Autowired
	private IDataService dataService;
	
	@Value("${texto.uniteventcontroller.list.message}")
	private String messageString;
	
	@Value("${texto.uniteventcontroller.list.title}")
	private String titleString;
	
	private Long eventsUnattended;

	@PostConstruct
	public void init() {
		eventsUnattended = dataService.getCountOfUnattendedEvents();
	}
	
	@GetMapping("/list-events/{id}")
	public String listEventsByUnit(@PathVariable Long id, Model model) {
		List<UnitEvent> unitEventsFiltered = null;
		model.addAttribute("title", titleString);
		model.addAttribute("message", messageString);
		model.addAttribute("unitEvents", unitEventsFiltered);
		model.addAttribute("eventsUnattended", eventsUnattended);

		return "events/list";
	}

	@GetMapping("/list-events")
	public String listEvents(Model model) {
		List<UnitEvent> unitEvents = dataService.getAllUnitEvents();
		Map<Long, Long> units = new HashMap<>();
		
		for(UnitEvent unitEvent: unitEvents) {
			Long unitId = unitEvent.getUnit().getId();
			units.put(unitEvent.getId(), unitId);
		}
		
		model.addAttribute("title", titleString);
		model.addAttribute("message", messageString);
		model.addAttribute("unitEvents", unitEvents);
		model.addAttribute("unitEventsUnits", units);
		model.addAttribute("eventsUnattended", eventsUnattended);

		return "events/list";
	}
	
	@GetMapping("/attend/{eventId}")
	public String attendEvent(@PathVariable(value = "eventId") Long eventId, Model model, RedirectAttributes flash) {
		UnitEvent unitEvent = dataService.getOneUnitEvent(eventId);
		unitEvent.setEventAttended(true);
		dataService.saveUnitEvent(unitEvent);
		flash.addFlashAttribute("success", "Event noted as attended");
		return "redirect:../list-events";
	}

}
