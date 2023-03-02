package com.actum.springboot.liftEnergy.app.controllers;

import java.util.Collection;
import java.util.List;
import java.util.Random;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.actum.springboot.liftEnergy.app.models.entity.Zone;
import com.actum.springboot.liftEnergy.app.models.service.IUnitService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class IndexController {
	
	@Autowired
	private IUnitService unitService;

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
		
		List<Zone> zones = unitService.findAllZones();
		
		//Borrar luego
		Random random = new Random();
		int production = random.nextInt(60);

		model.addAttribute("zones", zones);
		model.addAttribute("title", "Dashboard");
		model.addAttribute("zoneMessage", "Resumen de General");
		model.addAttribute("zoneSummary", "[{\"name\":\"producion_maxima_diaria\",\"value\":60},{\"name\":\"producion_minima_diaria\",\"value\":1},{\"name\":\"meta_produccion\",\"value\":\"45\"},{\"name\":\"promedio_produccion\",\"value\":\"52\"},{\"name\":\"costo_produccion\",\"value\":300},{\"name\":\"valor_generado\",\"value\":\"450\"}]");
		model.addAttribute("echonomicsMessage", "Resumen Financiero");
		model.addAttribute("echonomicsInfo", "{\"data\":{\"success\":true,\"timestamp\":1677703260,\"date\":\"2023-03-01\",\"base\":\"USD\",\"rates\":{\"BRENTOIL\":0.011841326228538,\"GBP\":0.8323683321252446,\"USD\":1,\"WTIOIL\":0.012858428700013},\"unit\":\"per barrel\"}}");
		model.addAttribute("productionZone", production);
		
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
