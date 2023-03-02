package com.actum.springboot.liftEnergy.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

	@GetMapping("/ver/{id}")
	public String verUnidad(@PathVariable Long id, Model model) {
		Unit unit = unitService.findOneUnit(id);

		if (unit == null) {
			return "redirect:/index";
		}

		model.addAttribute("title", "Unidad");
		model.addAttribute("message", "Resumen de Unidad ".concat(unit.getId().toString()));
		model.addAttribute("unit", unit);

		return "unit/ver";
	}

	@GetMapping("{id}/analisis")
	public String analizarUnidad(@PathVariable Long id, Model model) {
		List<Sensor> availableSensors = unitService.findEnabledSensors();

		model.addAttribute("sensors", availableSensors);
		model.addAttribute("title", "Sensores");
		model.addAttribute("message", "Analisis de unidad");
		return "sensor/graficar";
	}

}
