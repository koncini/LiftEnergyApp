package com.actum.springboot.liftEnergy.app.controllers.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.actum.springboot.liftEnergy.app.models.entity.Sensor;
import com.actum.springboot.liftEnergy.app.models.entity.Unit;
import com.actum.springboot.liftEnergy.app.models.service.IUnitService;

@RestController("api UnitController")
@RequestMapping("/api/unit")
public class UnitController {

	@Autowired
	private IUnitService unitService;

	@GetMapping("/get-units")
	private List<Unit> getUnits() {
		return unitService.findAllUnits();
	}

	@GetMapping("/get-unit")
	private Unit getUnitById(@RequestParam(value = "id") Long id) {
		return unitService.findOneUnit(id);
	}

	@GetMapping("/get-unit-sensors")
	private List<Sensor> getUnitSensors(@RequestParam(value = "id") Long id) {
		return unitService.findEnabledSensorsById(id);
	}

	@PostMapping("/register-unit")
	private void registerUnit() {
		unitService.saveUnit(null);
	}
	
}
