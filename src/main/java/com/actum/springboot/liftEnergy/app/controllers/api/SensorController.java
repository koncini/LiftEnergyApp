package com.actum.springboot.liftEnergy.app.controllers.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.actum.springboot.liftEnergy.app.models.entity.Sensor;
import com.actum.springboot.liftEnergy.app.models.service.IUnitService;

@RestController("api SensorController")
@RequestMapping("/api/sensor")
public class SensorController {

	@Autowired
	private IUnitService unitService;

	@GetMapping("/get-enabled-sensors")
	private List<Sensor> getSensors() {
		return unitService.findEnabledSensors();
	}

	@GetMapping("/get-sensor")
	private Sensor getSensorById(@RequestParam(value = "id") Long id) {
		return unitService.findOneSensor(id);
	}

}
