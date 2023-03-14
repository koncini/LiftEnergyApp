package com.actum.springboot.liftEnergy.app.controllers.api;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.actum.springboot.liftEnergy.app.models.entity.Sensor;
import com.actum.springboot.liftEnergy.app.models.entity.SensorData;
import com.actum.springboot.liftEnergy.app.models.service.IUnitService;

@RestController("api SensorData")
@RequestMapping("/api/sensors-data")
public class SensorDataController {

	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private IUnitService unitService;

	@PostMapping("/upload-data/{sensor_id}")
	@Secured("permitAll")
	private ResponseEntity<String> uploadSensorData(@PathVariable(value = "sensor_id") Long sensorId,
			@RequestBody Map<String, Object> requestBody) {

		Sensor sensor = unitService.findEnabledSensorById(sensorId);

		if (sensor == null) {
			return ResponseEntity.ok("El sensor desde el que escribe datos no existe o está deshabilitado");
		}

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");

		Double data = (Double) requestBody.get("data");
		String sensorUnit = (String) requestBody.get("unit");
		Boolean dinagraphReading = (Boolean) requestBody.get("dinagraphReading");
		String strDate = (String) requestBody.get("timeStamp");
		Date timeStamp = null;
		try {
			timeStamp = dateFormat.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		log.info("Data parsed");
		unitService.insertSensorData(sensorId, data, sensorUnit, dinagraphReading, timeStamp);
		return ResponseEntity.ok("Data uploaded successfully");
	}
	
	@GetMapping("/get-data/{sensor_id}")
	private ResponseEntity<List<SensorData>> getSensorData(@PathVariable(value = "sensor_id") Long sensorId){
		List<SensorData> todayData = unitService.getSensorDataFromCurrentYear(sensorId);
		String fetchUrl = "http://localhost:8090/api/sensors-data/get-data/" + sensorId;
		Map<String, Object> responseBody = new HashMap<>();
		responseBody.put("data", todayData);
		responseBody.put("fetchUrl", fetchUrl);
        return ResponseEntity.ok(todayData);
	}
		
	@GetMapping("/get-dinagraph-data/{unit_id}")
	private ResponseEntity<List<SensorData>> getDinagraphData(@PathVariable(value = "unit_id") Long unitId){
		List<SensorData> todayData = unitService.getSensorDataFromCurrentYear(unitId);
        return ResponseEntity.ok(todayData);
	}
}
