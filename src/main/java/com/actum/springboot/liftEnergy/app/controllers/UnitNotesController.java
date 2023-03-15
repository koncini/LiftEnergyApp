package com.actum.springboot.liftEnergy.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.actum.springboot.liftEnergy.app.models.entity.UnitNote;
import com.actum.springboot.liftEnergy.app.models.service.IUnitService;

@Controller
@RequestMapping("/unit-notes")
public class UnitNotesController {
	
	@Autowired
	private IUnitService unitService;
	
	@Value("${texto.unitnotecontroller.list.message}")
	private String messageString;
	
	@Value("${texto.unitnotecontroller.list.title}")
	private String titleString;
		
	@GetMapping("/list-notes/{id}")
	public String listNotesByUnit(@PathVariable Long id, Model model) {
		List<UnitNote> unitNotesFiltered = unitService.getNotesByUnit(id);
		model.addAttribute("title", titleString);
		model.addAttribute("message", messageString);
		model.addAttribute("unitNotes", unitNotesFiltered);
		return "notes/listar";
	}
	
	@GetMapping("/list-notes")
	public String listNotes(Model model) {
		List<UnitNote> unitNotes = unitService.findAllUnitNotes();
		model.addAttribute("title", titleString);
		model.addAttribute("message", messageString);
		model.addAttribute("unitNotes", unitNotes);
		return "notes/listar";
	}

}
