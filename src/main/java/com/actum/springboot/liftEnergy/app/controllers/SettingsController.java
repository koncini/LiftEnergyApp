package com.actum.springboot.liftEnergy.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.actum.springboot.liftEnergy.app.models.entity.User;
import com.actum.springboot.liftEnergy.app.models.service.IUnitService;

import jakarta.annotation.PostConstruct;

@Controller
@RequestMapping("/settings")
public class SettingsController {
	
	@Autowired
	private IUnitService unitService;
	
	private Long eventsUnattended;

	@PostConstruct
	public void init() {
		eventsUnattended = unitService.getCountOfUnattendedEvents();
	}
	
	@GetMapping("/listar-settings")
	public String listarSettings(Model model) {
		List<User> usuarios = unitService.getAllUsers();
		model.addAttribute("title", "Settings");
		model.addAttribute("message", "App Setings");
		model.addAttribute("users", usuarios);
		model.addAttribute("eventsUnattended", eventsUnattended);
		
		return "settings/list";
	}
	
}
