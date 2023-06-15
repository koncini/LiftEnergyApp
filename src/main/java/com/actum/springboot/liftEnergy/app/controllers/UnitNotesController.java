package com.actum.springboot.liftEnergy.app.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.actum.springboot.liftEnergy.app.models.entity.UnitNote;
import com.actum.springboot.liftEnergy.app.models.service.IDataService;
import com.actum.springboot.liftEnergy.app.util.paginator.PageRender;

import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/unit-notes")
public class UnitNotesController {

	@Autowired
	private IDataService dataService;

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
		eventsUnattended = dataService.getCountOfUnattendedEvents();
	}

	@GetMapping("/list-notes")
	public String listNotes(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {

		Pageable pageRequest = PageRequest.of(page, 10);

		Page<UnitNote> unitNotesPage = dataService.getAllUnitNotes(pageRequest);
		PageRender<UnitNote> pageRender = new PageRender<>("/unit-notes/list-notes", unitNotesPage);

		List<UnitNote> unitNotes = unitNotesPage.getContent();
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
		model.addAttribute("page", pageRender);
		model.addAttribute("eventsUnattended", eventsUnattended);

		return "notes/list";
	}

	@GetMapping("/form/{noteId}")
	public String editUnitNote(@RequestParam(name = "unitId") Long unitId, @PathVariable(value = "noteId") Long noteId,
			Map<String, Object> model, RedirectAttributes flash) {
		UnitNote unitNote = dataService.getOneUnitNote(noteId);
		if (unitNote == null) {
			return "redirect:../list";
		}
		model.put("unitNote", unitNote);
		model.put("unitId", unitId);
		model.put("title", titleString);
		model.put("message", editMessageString);
		model.put("messageDetail", detailMessageString);
		model.put("eventsUnattended", eventsUnattended);
		flash.addFlashAttribute("success", "Note Edited");

		return "notes/form";
	}

	@GetMapping("/form")
	public String createUnitNote(@RequestParam(name = "unitId") Long unitId, Map<String, Object> model,
			RedirectAttributes flash) {
		UnitNote unitNote = new UnitNote();

		model.put("unitNote", unitNote);
		model.put("unitId", unitId);
		model.put("title", titleString);
		model.put("message", createMessageString);
		model.put("messageDetail", detailMessageString);
		model.put("eventsUnattended", eventsUnattended);
		flash.addFlashAttribute("success", "New Note Created");

		return "notes/new";
	}

	@PostMapping("/form")
	public String saveUnitNote(@RequestParam(name = "unitId") Long unitId, @Valid UnitNote note, Model model,
			RedirectAttributes flash) {

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date currentDate = new Date();
		String formattedDate = dateFormat.format(currentDate);

		Date parsedDate = null;
		try {
			parsedDate = dateFormat.parse(formattedDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		UserDetails userDetails = getCurrentUserDetails();
		note.setUnit(dataService.getOneUnit(unitId));
		note.setUser(dataService.getUserByName(userDetails.getUsername()));
		note.setTimestamp(parsedDate);

		dataService.saveUnitNote(note);
		model.addAttribute("title", titleString);
		model.addAttribute("message", createMessageString);
		model.addAttribute("eventsUnattended", eventsUnattended);

		return "redirect:../unit/watch/" + unitId;
	}

	@GetMapping("/delete/{noteId}")
	public String deleteUnitNote(@PathVariable(value = "noteId") Long noteId, Model model, RedirectAttributes flash) {
		if (noteId > 0) {
			dataService.deleteUnitNote(noteId);
		}
		flash.addFlashAttribute("warning", "Note Deleted");
		return "redirect:/unit-notes/list-notes";
	}

	private UserDetails getCurrentUserDetails() {

		SecurityContext context = SecurityContextHolder.getContext();

		Authentication auth = context.getAuthentication();

		UserDetails userDetails = (UserDetails) auth.getPrincipal();

		return userDetails;
	}

}
