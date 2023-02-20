package com.actum.springboot.liftEnergy.app.controllers;

import java.util.Collection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class IndexController {

	protected final Log logger = LogFactory.getLog(this.getClass());

	@GetMapping(value = { "/index", "/" })
	public String index(Model model, Authentication authentication, HttpServletRequest request) {

		if (authentication != null) {
			logger.info("Usuario '" + authentication.getName() + "' Ha Iniciado Sesión con Exito");
		}

		if (hasRole("ROLE_ADMIN")) {
			logger.info("Usuario '" + authentication.getName() + "' Tiene Acceso");
		} else {
			logger.info("Usuario '" + authentication.getName() + "' No Tiene Acceso");
		}

		model.addAttribute("title", "Dashboard");
		model.addAttribute("message", "Dashboard Lift Energy");

		return "index";
	}

	private boolean hasRole(String role) {
		SecurityContext context = SecurityContextHolder.getContext();

		if (context == null) {
			return false;
		}

		Authentication auth = context.getAuthentication();

		if (auth == null) {
			return false;
		}

		Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();

		for (GrantedAuthority authority : authorities) {
			if (role.equals(authority.getAuthority())) {
				return true;
			}
		}

		return false;
	}
}
