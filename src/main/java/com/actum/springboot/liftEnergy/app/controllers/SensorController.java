package com.actum.springboot.liftEnergy.app.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.actum.springboot.liftEnergy.app.clients.PushoverClient;
import com.actum.springboot.liftEnergy.app.models.FormData;
import com.actum.springboot.liftEnergy.app.models.SensorSetting;
import com.actum.springboot.liftEnergy.app.models.entity.DinagraphSample;
import com.actum.springboot.liftEnergy.app.models.entity.Sensor;
import com.actum.springboot.liftEnergy.app.models.entity.Unit;
import com.actum.springboot.liftEnergy.app.models.entity.UnitEvent;
import com.actum.springboot.liftEnergy.app.models.service.IDataService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/sensor")
public class SensorController {

	@Autowired
	private IDataService dataService;

	@Autowired
	private PushoverClient pushoverClient;

	@Value("${texto.unitcontroller.watchsensor.title}")
	private String titleWatchSensorString;

	@Value("${texto.unitcontroller.watchsensor.message}")
	private String messageWatchSensorString;

	private Long eventsUnattended;

	@PostConstruct
	public void init() {
		eventsUnattended = dataService.getCountOfUnattendedEvents();
	}

	@GetMapping("{unitId}/analisis/{sensorId}")
	public String analizarUnidad(@PathVariable(value = "unitId") Long unitId,
			@PathVariable(value = "sensorId") Long sensorId, Model model) {

		Sensor sensor = dataService.getEnabledSensorById(sensorId);
		Unit unit = dataService.getOneUnit(unitId);

		LocalDate currentDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E dd MMM yyyy");
		String formattedDate = currentDate.format(formatter);

		model.addAttribute("sensor", sensor);
		model.addAttribute("unit", unit);
		model.addAttribute("date", formattedDate);
		model.addAttribute("title", titleWatchSensorString);
		model.addAttribute("message", messageWatchSensorString);
		model.addAttribute("eventsUnattended", eventsUnattended);

		return "sensor/graph";
	}

	@GetMapping("{unitId}/analisis-dinagrafico")
	public String analisiDinagrafico(@PathVariable(value = "unitId") Long unitId, Model model) throws ParseException {

		Unit unit = dataService.getOneUnit(unitId);
		if (unit == null) {
			return "redirect:/listar";
		}

		DinagraphSample sample = dataService.getOneDinagraphSample(unitId);

		LocalDate currentDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E dd MMM yyyy");
		SimpleDateFormat sqlFormatter = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
		Date sqlDate = new Date();
		String formattedDate = currentDate.format(formatter);
		String eventDate = sqlFormatter.format(sqlDate);

		if (!(sample.getName().equals("Bomba llena"))) {
			String response = pushoverClient.sendUnitDinagraphFailMessage(
					"Oil Well with id " + unit.getId() + " Requires Immediate Tech Support");
			System.out.println(response + " dinagraph error");
			UnitEvent unitEvent = new UnitEvent();
			unitEvent.setUnit(unit);
			unitEvent.setEventName("Dinagraph Failure");
			unitEvent.setEventDetail("Oil Well with id " + unit.getId() + " Requires Immediate Tech Support");
			unitEvent.setEventPriority(1);
			unitEvent.setEventAttended(false);
			unitEvent.setTimestamp(sqlFormatter.parse(eventDate));
			dataService.saveUnitEvent(unitEvent);
		}

		model.addAttribute("unit", unit);
		model.addAttribute("date", formattedDate);
		model.addAttribute("title", titleWatchSensorString);
		model.addAttribute("message", messageWatchSensorString);
		model.addAttribute("sample", sample);
		model.addAttribute("eventsUnattended", eventsUnattended);

		return "sensor/dinagraph";
	}

	@GetMapping("/form/{sensorId}")
	public String editSensor(@RequestParam(name = "unitId") Long unitId,
			@PathVariable(value = "sensorId") Long sensorId, Map<String, Object> model, RedirectAttributes flash)
			throws JsonMappingException, JsonProcessingException {

		Sensor sensor = dataService.getOneSensor(sensorId);
		if (sensor == null) {
			return "redirect:/list";
		}
		String sensorSettings = sensor.getSettings();
		ObjectMapper objectMapper = new ObjectMapper();
		SensorSetting sensorSetting = objectMapper.readValue(sensorSettings, SensorSetting.class);
		FormData formData = new FormData();
		formData.setSensor(sensor);
		formData.setSensorSetting(sensorSetting);
		model.put("sensor", sensor);
		model.put("formData", formData);
		model.put("unitId", unitId);
		model.put("title", "Edit Sensor");
		model.put("message", "Edit Sensor");
		model.put("eventsUnattended", eventsUnattended);
		flash.addFlashAttribute("success", "Sensor Edited Succesfully");

		return "sensor/form";
	}

	@GetMapping("/form")
	public String createSensor(@RequestParam(name = "unitId") Long unitId, Map<String, Object> model,
			RedirectAttributes flash) {
		Sensor sensor = new Sensor();
		SensorSetting sensorSetting = new SensorSetting();
		FormData formData = new FormData();
		formData.setSensor(sensor);
		formData.setSensorSetting(sensorSetting);
		model.put("formData", formData);
		model.put("unitId", unitId);
		model.put("title", "Create New Sensor");
		model.put("message", "Create New Sensor");
		model.put("eventsUnattended", eventsUnattended);
		flash.addFlashAttribute("success", "New Sensor Created");

		return "sensor/new";
	}

	@PostMapping("/form")
	public String saveSensor(@RequestParam(name = "unitId") Long unitId, @Valid FormData formData, BindingResult result,
			Model model, RedirectAttributes flash) throws JsonProcessingException {
		if(result.hasErrors()) {
			return "redirect:../../unit/watch/" + unitId;
		}
		ObjectMapper objectMapper = new ObjectMapper();
		Sensor sensor = formData.getSensor();
		SensorSetting settingObject = formData.getSensorSetting();
		String setting = objectMapper.writeValueAsString(settingObject);
		sensor.setSettings(setting);
		sensor.setEnabled(true);
		sensor.setUnit(dataService.getOneUnit(unitId));
		dataService.saveSensor(sensor);
		model.addAttribute("unitId", unitId);
		model.addAttribute("title", "Create Sensor");
		model.addAttribute("message", "Create Sensor");
		model.addAttribute("eventsUnattended", eventsUnattended);

		return "redirect:/unit/watch/" + unitId;
	}

	@GetMapping("/delete/{sensorId}")
	public String deleteSensor(@PathVariable(value = "sensorId") Long sensorId, Model model, RedirectAttributes flash) {
		Sensor sensor = dataService.getOneSensor(sensorId);
		dataService.deleteSensorDataBySensorId(sensorId);
		Unit unit = null;
		if (sensor != null) {
			dataService.deleteSensor(sensorId);
			unit = sensor.getUnit();
			flash.addFlashAttribute("warning", "Sensor Deleted Succesfully");
			return "redirect:../../unit/watch/" + unit.getId();
		}
		flash.addFlashAttribute("error", "Cannot Find Sensor");
		return "redirect:../../unit/list";
	}

}
