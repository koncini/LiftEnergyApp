package com.actum.springboot.liftEnergy.app.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.actum.springboot.liftEnergy.app.models.entity.Sensor;
import com.actum.springboot.liftEnergy.app.models.entity.Unit;
import com.actum.springboot.liftEnergy.app.models.service.IUnitService;

import jakarta.annotation.PostConstruct;

@Controller
@RequestMapping("/sensor")
public class SensorController {
	
	@Autowired
	private IUnitService unitService;
	
	@Value("${texto.unitcontroller.watchsensor.title}")
	private String titleWatchSensorString;

	@Value("${texto.unitcontroller.watchsensor.message}")
	private String messageWatchSensorString;
	
	private Long eventsUnattended;

	@PostConstruct
	public void init() {
		eventsUnattended = unitService.getCountOfUnattendedEvents();
	}
	
	@GetMapping("{unitId}/analisis/{sensorId}")
	public String analizarUnidad(@PathVariable(value = "unitId") Long unitId,
			@PathVariable(value = "sensorId") Long sensorId, Model model) {

		Sensor sensor = unitService.findEnabledSensorById(sensorId);
		Unit unit = unitService.findOneUnit(unitId);

		LocalDate currentDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E dd MMM yyyy");
		String formattedDate = currentDate.format(formatter);

		model.addAttribute("sensor", sensor);
		model.addAttribute("unit", unit);
		model.addAttribute("date", formattedDate);
		model.addAttribute("title", titleWatchSensorString);
		model.addAttribute("message", messageWatchSensorString);
		model.addAttribute("eventsUnattended", eventsUnattended);

		return "sensor/graficar";
	}
	
	@GetMapping("{unitId}/analisis-dinagrafico")
	public String analisiDinagrafico(@PathVariable(value = "unitId") Long unitId, Model model) {

		Unit unit = unitService.findOneUnit(unitId);
		if (unit == null) {
			return "redirect:/listar";	
		}
		
		LocalDate currentDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E dd MMM yyyy");
		String formattedDate = currentDate.format(formatter);
		
		model.addAttribute("unit", unit);
		model.addAttribute("date", formattedDate);
		model.addAttribute("title", titleWatchSensorString);
		model.addAttribute("message", messageWatchSensorString);
		model.addAttribute("eventsUnattended", eventsUnattended);

		return "sensor/dinagraph";
	}
	
}
