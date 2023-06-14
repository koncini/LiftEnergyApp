package com.actum.springboot.liftEnergy.app.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.actum.springboot.liftEnergy.app.models.entity.UnitEvent;
import com.actum.springboot.liftEnergy.app.models.service.IDataService;
import com.actum.springboot.liftEnergy.app.util.paginator.PageRender;

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

	@GetMapping("/list-unattended-events")
	public String listUnattendedEvents(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {
		
		Pageable pageRequest = PageRequest.of(page, 10);
		
		Page<UnitEvent> unitEventsPage = dataService.getAllUnattendedUnitEvents(pageRequest);
		PageRender<UnitEvent> pageRender = new PageRender<>("/unit-events/list-unattended-events", unitEventsPage);
		
		List<UnitEvent> unitEvents = unitEventsPage.getContent();	
		Map<Long, Long> units = new HashMap<>();
		
		for(UnitEvent unitEvent: unitEvents) {
			Long unitId = unitEvent.getUnit().getId();
			units.put(unitEvent.getId(), unitId);
		}
		
		model.addAttribute("title", titleString);
		model.addAttribute("message", messageString);
		model.addAttribute("detail", "Unattended");
		model.addAttribute("unitEvents", unitEvents);
		model.addAttribute("unitEventsUnits", units);
		model.addAttribute("unattendedList", true);
		model.addAttribute("page", pageRender);
		model.addAttribute("eventsUnattended", eventsUnattended);

		return "events/list";
	}
	
	@GetMapping("/list-attended-events")
	public String listAttendedEvents(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {
		
		Pageable pageRequest = PageRequest.of(page, 10);
		
		Page<UnitEvent> unitEventsPage = dataService.getAllAttendedUnitEvents(pageRequest);
		PageRender<UnitEvent> pageRender = new PageRender<>("/unit-events/list-attended-events", unitEventsPage);
		
		List<UnitEvent> unitEvents = unitEventsPage.getContent();	
		Map<Long, Long> units = new HashMap<>();
		
		for(UnitEvent unitEvent: unitEvents) {
			Long unitId = unitEvent.getUnit().getId();
			units.put(unitEvent.getId(), unitId);
		}
		
		model.addAttribute("title", titleString);
		model.addAttribute("message", messageString);
		model.addAttribute("detail", "Attended");
		model.addAttribute("unitEvents", unitEvents);
		model.addAttribute("unitEventsUnits", units);
		model.addAttribute("unattendedList", false);
		model.addAttribute("page", pageRender);
		model.addAttribute("eventsUnattended", eventsUnattended);

		return "events/list";
	}
	
	@GetMapping("/attend/{eventId}")
	public String attendEvent(@PathVariable(value = "eventId") Long eventId, Model model, RedirectAttributes flash) {
		String currentUserName = getCurrentUserDetails().getUsername();
		UnitEvent unitEvent = dataService.getOneUnitEvent(eventId);
		unitEvent.setEventAttended(true);
		unitEvent.setAttendedBy(currentUserName);
		dataService.saveUnitEvent(unitEvent);
		flash.addFlashAttribute("success", "Event noted as attended");
		return "redirect:../list-unattended-events";
	}
	
	private UserDetails getCurrentUserDetails() {

		SecurityContext context = SecurityContextHolder.getContext();

		Authentication auth = context.getAuthentication();

		UserDetails userDetails = (UserDetails) auth.getPrincipal();

		return userDetails;
	}

}
