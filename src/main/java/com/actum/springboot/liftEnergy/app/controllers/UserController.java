package com.actum.springboot.liftEnergy.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.actum.springboot.liftEnergy.app.models.entity.User;
import com.actum.springboot.liftEnergy.app.models.service.IUnitService;

import jakarta.annotation.PostConstruct;

@Controller
@RequestMapping("/usuarios")
public class UserController {

	@Autowired
	private IUnitService unitService;

	@Value("${texto.usercontroller.list.message}")
	private String messageString;

	@Value("${texto.usercontroller.list.title}")
	private String titleString;

	private Long eventsUnattended;

	@PostConstruct
	public void init() {
		eventsUnattended = unitService.getCountOfUnattendedEvents();
	}

	@GetMapping("/listar-usuarios")
	public String listarUsuarios(Model model) {
		List<User> usuarios = unitService.findAllUsers();
		model.addAttribute("title", titleString);
		model.addAttribute("message", messageString);
		model.addAttribute("users", usuarios);
		model.addAttribute("eventsUnattended", eventsUnattended);

		return "user/list";
	}

	@GetMapping("/form/{userId}")
	public String editUser(@PathVariable(value = "userId") Long userId, Model model, RedirectAttributes flash) {

		User user = unitService.findOneUser(userId);
		if (user == null) {
			return "redirect:/list";
		}

		model.addAttribute("user", user);
		model.addAttribute("title", "Edit User ");
		model.addAttribute("message", "Edit User ");
		model.addAttribute("eventsUnattended", eventsUnattended);

		return "user/form";
	}

	@GetMapping("/form")
	public String createUser(Model model, RedirectAttributes flash) {

		model.addAttribute("title", "Create User");
		model.addAttribute("message", "Create User");
		model.addAttribute("eventsUnattended", eventsUnattended);

		return "user/new";
	}

	@GetMapping("/delete/{userId}")
	public String deleteUser(@PathVariable(value = "userId") Long userId, Model model, RedirectAttributes flash) {
		if(userId > 0) {
			unitService.deleteUser(userId);
		}
		return "redirect:zone/listar-usuarios";
	}
}
