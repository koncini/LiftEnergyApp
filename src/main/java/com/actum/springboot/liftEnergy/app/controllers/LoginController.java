package com.actum.springboot.liftEnergy.app.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {
	
	@Value("${texto.logincontroller.login.title}")
	private String titleString;
	
	@Value("${texto.logincontroller.login.message}")
	private String messageString;
	
	@Value("${texto.logincontroller.login.error}")
	private String errorString;
	
	@Value("${texto.logincontroller.login.info}")
	private String infoString;

	@GetMapping("/login")
	public String login(@RequestParam(value = "error", required = false) String error, Model model, Principal principal,
			RedirectAttributes flash) {
		
		model.addAttribute("title", titleString);
		model.addAttribute("message", messageString);
		
		if (principal != null) {
			flash.addFlashAttribute("info", infoString);
			return "login";
		}

		if (error != null) {
			model.addAttribute("error", errorString);
		}
		
		return "login";
	}

}
