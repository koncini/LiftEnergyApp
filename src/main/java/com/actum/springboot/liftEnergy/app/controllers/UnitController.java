package com.actum.springboot.liftEnergy.app.controllers;

import java.util.List;

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
	public String verUnidad(@PathVariable Long id, Model model) {
		Unit unit = unitService.findOneUnit(id);

		if (unit == null) {
			return "redirect:/index";
		}

		model.addAttribute("title", titleWatchString);
		model.addAttribute("message", messageWatchString.concat(unit.getId().toString()));
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
