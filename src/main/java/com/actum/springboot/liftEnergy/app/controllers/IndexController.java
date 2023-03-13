package com.actum.springboot.liftEnergy.app.controllers;

import java.util.Collection;
import java.util.List;
import java.util.Random;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.actum.springboot.liftEnergy.app.models.entity.User;
import com.actum.springboot.liftEnergy.app.models.entity.Zone;
import com.actum.springboot.liftEnergy.app.models.service.IUnitService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class IndexController {
	
	@Autowired
	private IUnitService unitService;

	protected final Log logger = LogFactory.getLog(this.getClass());
	
	@Value("${texto.indexcontroller.index.user}")
	private String userString;
	
	@Value("${texto.indexcontroller.index.title}")
	private String titleString;
	
	@Value("${texto.indexcontroller.index.success}")
	private String successString;
	
	@Value("${texto.indexcontroller.index.hasaccess}")
	private String accessString;
	
	@Value("${texto.indexcontroller.index.hasnoaccess}")
	private String noAccessString;
	
	@Value("${texto.indexcontroller.index.zonemessage}")
	private String zoneMessageString;
	
	@Value("${texto.indexcontroller.index.financialmessage}")
	private String financialMessageString;

	@GetMapping(value = { "/index", "/" })
	public String index(Model model, Authentication authentication, HttpServletRequest request) {

		if (authentication != null) {
			logger.info(userString + authentication.getName() + successString);
		}

		if (hasRole("ROLE_ADMIN")) {
			logger.info(userString + authentication.getName() + accessString);
		} else {
			logger.info(userString + authentication.getName() + noAccessString);
		}
		
		List<Zone> zones = null;
		
		if (hasRole("ROLE_ADMIN")) {
			zones = unitService.findAllZones();
		} else {
			String userName = getCurrentUserName();
			User currentUser = unitService.findUserByName(userName);
			zones = unitService.findZonesbyUserId(currentUser.getId());
		}
		
		//Borrar luego
		Random random = new Random();
		int production = random.nextInt(100000) + 2000;

		model.addAttribute("zones", zones);
		model.addAttribute("title", titleString);
		model.addAttribute("zoneMessage", zoneMessageString);
		model.addAttribute("echonomicsMessage", financialMessageString);
		model.addAttribute("productionZone", production);
		
		return "index";
	}
	
	private Long zoneProductionSummary(Long zoneId) {
		return null;
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
	
	private String getCurrentUserName() {
		
		SecurityContext context = SecurityContextHolder.getContext();
		
		Authentication auth = context.getAuthentication();

		UserDetails userDetails = (UserDetails) auth.getPrincipal();
		
		String userName = userDetails.getUsername();
				
		return userName;
	}
}
