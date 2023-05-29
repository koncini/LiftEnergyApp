package com.actum.springboot.liftEnergy.app.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.actum.springboot.liftEnergy.app.clients.PushoverClient;
import com.actum.springboot.liftEnergy.app.models.entity.DinagraphSample;
import com.actum.springboot.liftEnergy.app.models.entity.Sensor;
import com.actum.springboot.liftEnergy.app.models.entity.Unit;
import com.actum.springboot.liftEnergy.app.models.entity.UnitEvent;
import com.actum.springboot.liftEnergy.app.models.service.IUnitService;

import jakarta.annotation.PostConstruct;

@Controller
@RequestMapping("/sensor")
public class SensorController {
	
	@Autowired
	private IUnitService unitService;
	
	@Autowired
	private PushoverClient pushoverClient;
	
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
	public String analisiDinagrafico(@PathVariable(value = "unitId") Long unitId, Model model) throws ParseException {

		Unit unit = unitService.findOneUnit(unitId);
		if (unit == null) {
			return "redirect:/listar";	
		}
		
		DinagraphSample sample = unitService.findOneDinagraphSample(unitId);
		
		LocalDate currentDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E dd MMM yyyy");
		SimpleDateFormat sqlFormatter = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
		Date sqlDate = new Date();
		String formattedDate = currentDate.format(formatter);
		String eventDate = sqlFormatter.format(sqlDate);
		
		if (!(sample.getName().equals("Bomba llena"))) {
			String response = pushoverClient.sendUnitDinagraphFailMessage("Oil Well with id " + unit.getId() + " Requires Immediate Tech Support");
			System.out.println(response + " dinagraph error");
			UnitEvent unitEvent = new UnitEvent();
			unitEvent.setUnit(unit);
			unitEvent.setEventName("Dinagraph Failure");
			unitEvent.setEventDetail("Oil Well with id " + unit.getId() + " Requires Immediate Tech Support");
			unitEvent.setEventPriority(1);
			unitEvent.setEventAttended(false);
			unitEvent.setTimestamp(sqlFormatter.parse(eventDate));
			unitService.saveUnitEvent(unitEvent);
		} 
		
		model.addAttribute("unit", unit);
		model.addAttribute("date", formattedDate);
		model.addAttribute("title", titleWatchSensorString);
		model.addAttribute("message", messageWatchSensorString);
		model.addAttribute("sample", sample);
		model.addAttribute("eventsUnattended", eventsUnattended);

		return "sensor/dinagraph";
	}
	
	@GetMapping("/new-sensor/{id}")
	public String addNewSensor(@PathVariable Long id, Model model) {
		Unit unit = unitService.findOneUnit(id);
		model.addAttribute("title", "New Sensor");
		model.addAttribute("message", "Add New Sensor");
		model.addAttribute("unit", unit);
		model.addAttribute("eventsUnattended", eventsUnattended);

		return "notes/form";
	}
	
}
