package com.actum.springboot.liftEnergy.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.actum.springboot.liftEnergy.app.models.entity.User;
import com.actum.springboot.liftEnergy.app.models.service.IUnitService;

@Controller
@RequestMapping("/usuarios")
public class UserController {
	
	@Autowired
	private IUnitService unitService;

	@GetMapping("/listar-usuarios")
	public String listarUsuarios(Model model) {
		List<User> usuarios = unitService.findAllUsers();
		model.addAttribute("title", "Usuarios");
		model.addAttribute("message", "Control de Usuarios");
		model.addAttribute("users", usuarios);
		return"user/listar";
	}
}
