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

import com.actum.springboot.liftEnergy.app.models.entity.Unit;
import com.actum.springboot.liftEnergy.app.models.entity.UnitNote;
import com.actum.springboot.liftEnergy.app.models.service.IUnitService;

import jakarta.annotation.PostConstruct;

@Controller
@RequestMapping("/unit-notes")
public class UnitNotesController {

	@Autowired
	private IUnitService unitService;

	@Value("${texto.unitnotecontroller.list.message}")
	private String messageString;

	@Value("${texto.unitnotecontroller.list.title}")
	private String titleString;

	private Long eventsUnattended;

	@PostConstruct
	public void init() {
		eventsUnattended = unitService.getCountOfUnattendedEvents();
	}

	@GetMapping("/list-notes/{id}")
	public String listNotesByUnit(@PathVariable Long id, Model model) {
		List<UnitNote> unitNotesFiltered = unitService.getNotesByUnit(id);
		model.addAttribute("title", titleString);
		model.addAttribute("message", messageString);
		model.addAttribute("unitNotes", unitNotesFiltered);
		model.addAttribute("eventsUnattended", eventsUnattended);

		return "notes/listar";
	}

	@GetMapping("/list-notes")
	public String listNotes(Model model) {
		List<UnitNote> unitNotes = unitService.findAllUnitNotes();
		Map<Long, String> users = new HashMap<>();
		Map<Long, Long> units = new HashMap<>();

		for (UnitNote unitNote : unitNotes) {
			Long unitId = unitNote.getUnit().getId();
			String userId = unitNote.getUser().getUsername();
			users.put(unitNote.getId(), userId);
			units.put(unitNote.getId(), unitId);
		}

		model.addAttribute("title", titleString);
		model.addAttribute("message", messageString);
		model.addAttribute("unitNotes", unitNotes);
		model.addAttribute("unitNotesUsers", users);
		model.addAttribute("unitNotesUnits", units);
		model.addAttribute("eventsUnattended", eventsUnattended);

		return "notes/listar";
	}
	
	@GetMapping("/new-note/{id}")
	public String addNewNote(@PathVariable Long id, Model model) {
		Unit unit = unitService.findOneUnit(id);
		model.addAttribute("title", titleString);
		model.addAttribute("message", messageString);
		model.addAttribute("unit", unit);
		model.addAttribute("eventsUnattended", eventsUnattended);

		return "notes/form";
	}

}
