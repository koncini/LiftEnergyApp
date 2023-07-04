package com.actum.springboot.liftEnergy.app.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.actum.springboot.liftEnergy.app.models.entity.Zone;
import com.actum.springboot.liftEnergy.app.models.service.IDataService;

import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/zone")
public class ZoneController {

	@Autowired
	private IDataService dataService;

	@Value("${texto.zonecontroller.listunits.title}")
	private String titleString;

	private Long eventsUnattended;

	@PostConstruct
	public void init() {
		eventsUnattended = dataService.getCountOfUnattendedEvents();
	}

	@GetMapping("/watch/{id}")
	public String watchZone(Model model) {
		return "zone/watch";
	}

	@GetMapping("/detailed-list")
	public String detailedListZones(Model model) {
		List<Zone> zones = dataService.getAllZones();
		model.addAttribute("title", titleString);
		model.addAttribute("message", "All Oil Fields");
		model.addAttribute("zones", zones);
		model.addAttribute("eventsUnattended", eventsUnattended);

		return "zone/list";
	}

	@GetMapping("/form/{zoneId}")
	public String editZone(@PathVariable(value = "zoneId") Long zoneId, Map<String, Object> model, RedirectAttributes flash) {

		Zone zone = dataService.getOneZone(zoneId);
		if (zone == null) {
			return "redirect:/list";
		}
		model.put("zone", zone);
		model.put("title", "Edit Oil Field");
		model.put("message", "Edit Oil Field");
		model.put("eventsUnattended", eventsUnattended);
		flash.addFlashAttribute("success", "Oil Field Edited");

		return "zone/form";
	}

	@GetMapping("/form")
	public String createZone(Map<String, Object> model, RedirectAttributes flash) {
		Zone zone = new Zone();
		model.put("zone", zone);
		model.put("title", "New Oil Field");
		model.put("message", "Create New Oil Field");
		model.put("eventsUnattended", eventsUnattended);
		flash.addFlashAttribute("success", "New Oil Field Created");

		return "zone/new";
	}
		
	@PostMapping("/form")
	public String saveZone(@Valid Zone zone, Model model, RedirectAttributes flash) {
		dataService.saveZone(zone);
		model.addAttribute("title", "Create Oil Field ");
		model.addAttribute("message", "Create Oil Field");
		model.addAttribute("eventsUnattended", eventsUnattended);

		return "redirect:form";
	}
	
	@GetMapping("/delete/{zoneId}")
	public String deleteZone(@PathVariable(value = "zoneId") Long zoneId, Model model, RedirectAttributes flash) {
		if(zoneId > 0) {
			dataService.deleteZone(zoneId);
		}
		return "redirect:zone/detailed-list";
	}

}
