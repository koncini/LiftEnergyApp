package com.actum.springboot.liftEnergy.app.controllers.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.actum.springboot.liftEnergy.app.models.entity.Sensor;
import com.actum.springboot.liftEnergy.app.models.entity.SensorData;
import com.actum.springboot.liftEnergy.app.models.entity.Unit;
import com.actum.springboot.liftEnergy.app.models.service.IUnitService;

@RestController("api UnitController")
@RequestMapping("/api/unit")
public class UnitController {
	
	private final Logger log = LoggerFactory.getLogger(getClass());

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
	
	@GetMapping("/get-dinagraph-data/{unitId}")
	@Secured("permitAll")
	private List<SensorData> getDinagraphData(@PathVariable(value = "unitId") Long unitId) {
		return unitService.findDinagraphData();
	}
	
	@GetMapping("/get-unit-setup/{unitId}")
	@Secured("permitAll")
	private ResponseEntity<String> getUnitSetup(@PathVariable(value = "unitId") Long unitId) {
	    String json = "{\"start\": true, \"stop\": false, \"set_point\": 1800}";
	    log.info("Request Processed");
	    return new ResponseEntity<>(json, HttpStatus.OK);
	}
	
}
