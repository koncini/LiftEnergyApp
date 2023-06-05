package com.actum.springboot.liftEnergy.app.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;

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

import com.actum.springboot.liftEnergy.app.clients.CommoditiesClient;
import com.actum.springboot.liftEnergy.app.models.ExchangeRateData;
import com.actum.springboot.liftEnergy.app.models.entity.User;
import com.actum.springboot.liftEnergy.app.models.entity.Zone;
import com.actum.springboot.liftEnergy.app.models.service.IDataService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class IndexController {

	@Autowired
	private IDataService dataService;

	@Autowired
	private CommoditiesClient commoditiesClient;

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
	
	private Long eventsUnattended;

	@PostConstruct
	public void init() {
	    eventsUnattended = dataService.getCountOfUnattendedEvents();
	}

	@GetMapping(value = { "/index", "/" })
	public String index(Model model, Authentication authentication, HttpServletRequest request)
			throws JsonMappingException, JsonProcessingException {

		if (authentication != null) {
			logger.info(userString + authentication.getName() + ' ' +successString);
		} else {
			return "redirect:/login";
		}

		if (hasRole("ROLE_ADMIN")) {
			logger.info(userString + authentication.getName() + ' ' + accessString);
		} else {
			logger.info(userString + authentication.getName() + ' ' + noAccessString);
		}

		List<Zone> zones = null;

		if (hasRole("ROLE_ADMIN")) {
			zones = dataService.getTop5ZonesByProduction();
		} else {
			String userName = getCurrentUserName();
			User currentUser = dataService.getUserByName(userName);
			zones = dataService.getTop5ZonesByProductionAndUserId(currentUser.getId());
		}

		String brentoilResponse = commoditiesClient.getCommoditiesData("BRENT", "daily");
		String wtioilResponse = commoditiesClient.getCommoditiesData("WTI", "daily");

		ObjectMapper objectMapper = new ObjectMapper();

		ExchangeRateData brentoilData = objectMapper.readValue(brentoilResponse, ExchangeRateData.class);
		ExchangeRateData wtioilData = objectMapper.readValue(wtioilResponse, ExchangeRateData.class);
		
		LocalDate currentDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E dd MMM yyyy");
		String formattedDate = currentDate.format(formatter);
		
		model.addAttribute("zones", zones);
		model.addAttribute("title", titleString);
		model.addAttribute("zoneMessage", zoneMessageString);
		model.addAttribute("date", formattedDate);
		model.addAttribute("echonomicsMessage", financialMessageString);
		model.addAttribute("brentoilRateData", brentoilData.getData());
		model.addAttribute("wtioilRateData", wtioilData.getData());
		model.addAttribute("eventsUnattended", eventsUnattended);

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

	private String getCurrentUserName() {

		SecurityContext context = SecurityContextHolder.getContext();

		Authentication auth = context.getAuthentication();

		UserDetails userDetails = (UserDetails) auth.getPrincipal();

		String userName = userDetails.getUsername();

		return userName;
	}
}
