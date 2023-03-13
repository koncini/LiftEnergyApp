package com.actum.springboot.liftEnergy.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.actum.springboot.liftEnergy.app.models.entity.MotorData;
import com.actum.springboot.liftEnergy.app.models.entity.PowerCost;
import com.actum.springboot.liftEnergy.app.models.entity.Sensor;
import com.actum.springboot.liftEnergy.app.models.entity.Unit;
import com.actum.springboot.liftEnergy.app.models.entity.UnitData;
import com.actum.springboot.liftEnergy.app.models.entity.UnitSettings;
import com.actum.springboot.liftEnergy.app.models.entity.WellData;
import com.actum.springboot.liftEnergy.app.models.entity.WellDataWrapper;
import com.actum.springboot.liftEnergy.app.models.service.IUnitService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/unidad")
public class UnitController {

	@Autowired
	private IUnitService unitService;
	
	@Value("${texto.unitcontroller.watchunits.message}")
	private String messageWatchString;
	
	@Value("${texto.unitcontroller.watchunits.title}")
	private String titleWatchString;
	
	@Value("${texto.unitcontroller.watchsensor.title}")
	private String titleWatchSensorString;

	@Value("${texto.unitcontroller.watchsensor.message}")
	private String messageWatchSensorString;
		
	@GetMapping("/ver/{id}")
	public String verUnidad(@PathVariable Long id, Model model) throws JsonMappingException, JsonProcessingException {
		Unit unit = unitService.findOneUnit(id);

		if (unit == null) {
			return "redirect:/index";
		}
		
		String unitSettings = unit.getSettings();
		String unitMetrics = unit.getMetrics();
		ObjectMapper objectMapper = new ObjectMapper();
		List<UnitSettings> settings = objectMapper.readValue(unitSettings, new TypeReference<List<UnitSettings>>() {});
		
		ObjectMapper mapper = new ObjectMapper();
		WellDataWrapper wellDataWrapper = mapper.readValue(unitMetrics, WellDataWrapper.class);
		
		List<WellData> wellData = wellDataWrapper.getWellData();
		List<UnitData> unitData = wellDataWrapper.getUnitData();
		List<MotorData> motorData = wellDataWrapper.getMotorData();
		List<PowerCost> powerCost = wellDataWrapper.getPowerCost();

		model.addAttribute("title", titleWatchString);
		model.addAttribute("message", messageWatchString.concat(unit.getId().toString()));
		model.addAttribute("settings", settings);
		model.addAttribute("wellData", wellData);
		model.addAttribute("unitData", unitData);
		model.addAttribute("motorData", motorData);
		model.addAttribute("powerCost", powerCost);
		model.addAttribute("unit", unit);

		return "unit/ver";
	}
	
	@GetMapping("/listar-unidades-detallado")
	public String listarUnidades(Model model) {
		List<Unit> units = unitService.findAllUnits();
		model.addAttribute("title", "Oil Wells");
		model.addAttribute("message", "All Oil Wells");
		model.addAttribute("units", units);
		return "unit/listar";
	}

	@GetMapping("{id}/analisis")
	public String analizarUnidad(@PathVariable Long id, Model model) {
		List<Sensor> availableSensors = unitService.findEnabledSensors();

		model.addAttribute("sensors", availableSensors);
		model.addAttribute("title", titleWatchSensorString);
		model.addAttribute("message", messageWatchSensorString);
		return "sensor/graficar";
	}

}
