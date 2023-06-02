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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.actum.springboot.liftEnergy.app.models.entity.UnitNote;
import com.actum.springboot.liftEnergy.app.models.service.IUnitService;

import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/unit-notes")
public class UnitNotesController {

	@Autowired
	private IUnitService unitService;

	@Value("${texto.unitnotecontroller.list.message}")
	private String messageString;

	@Value("${texto.unitnotecontroller.list.title}")
	private String titleString;
	
	@Value("${texto.unitnotecontroller.create.message}")
	private String createMessageString;
	
	@Value("${texto.unitnotecontroller.edit.message}")
	private String editMessageString;
	
	@Value("${texto.unitnotecontroller.detail.message}")
	private String detailMessageString;

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

		return "notes/list";
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

		return "notes/list";
	}
	
	@GetMapping("/form/{noteId}")
	public String editUnitNote(@PathVariable(value = "noteId") Long noteId, Map<String, Object> model, RedirectAttributes flash) {
		UnitNote unitNote = unitService.findOneUnitNote(noteId);
		if (unitNote == null) {
			return "redirect:../list";
		}
		model.put("note", unitNote);
		model.put("title", titleString);
		model.put("message", editMessageString);
		model.put("messageDetail", detailMessageString);
		model.put("eventsUnattended", eventsUnattended);
		flash.addFlashAttribute("success", "Note Edited");
		
		return "notes/form";
	}
	
	@GetMapping("/form")
	public String createUnitNote(Map<String, Object> model, RedirectAttributes flash) {
		UnitNote unitNote = new UnitNote();
		model.put("unit", unitNote);
		model.put("title", titleString);
		model.put("message", createMessageString);
		model.put("messageDetail", detailMessageString);
		model.put("eventsUnattended", eventsUnattended);
		flash.addFlashAttribute("success", "New Note Created");

		return "notes/new";
	}
	
	@PostMapping("/form")
	public String saveUnitNote(@Valid UnitNote note, Model model, RedirectAttributes flash) {
		unitService.saveUnitNote(note);
		model.addAttribute("title", titleString);
		model.addAttribute("message", createMessageString);
		model.addAttribute("eventsUnattended", eventsUnattended);
		
		return "redirect:notes/form";
	}
	
	@GetMapping("/delete/{noteId}")
	public String deleteUnitNote(@PathVariable(value = "noteId") Long noteId, Model model, RedirectAttributes flash) {
		if(noteId > 0) {
			unitService.deleteUnitNote(noteId);
		}
		flash.addFlashAttribute("success", "Note Deleted");
		return "redirect:../notes/list";
	}

}
