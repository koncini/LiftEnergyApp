package com.actum.springboot.liftEnergy.app.controllers.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.actum.springboot.liftEnergy.app.models.entity.SensorData;
import com.actum.springboot.liftEnergy.app.models.service.IUnitService;

@RestController("api SensorData")
@RequestMapping("/api/sensors-data")
public class SensorDataController {

	@Autowired
	private IUnitService unitService;

	@PostMapping("/upload-data")
	private ResponseEntity<String> uploadSensorData(@RequestBody SensorData data) {
		unitService.saveSensorData(data);
		return ResponseEntity.ok("Data uploaded successfully");
	}

	@GetMapping("/get_dinagraph_data")
	private List<SensorData> getDinagraphData() {
		return unitService.findDinagraphData();
	}

}
