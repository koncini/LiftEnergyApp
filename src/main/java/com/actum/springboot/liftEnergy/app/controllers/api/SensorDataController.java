package com.actum.springboot.liftEnergy.app.controllers.api;

import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.actum.springboot.liftEnergy.app.models.entity.Sensor;
import com.actum.springboot.liftEnergy.app.models.entity.SensorData;
import com.actum.springboot.liftEnergy.app.models.entity.SensorSetting;
import com.actum.springboot.liftEnergy.app.models.entity.Unit;
import com.actum.springboot.liftEnergy.app.models.entity.UnitEvent;
import com.actum.springboot.liftEnergy.app.models.service.IUnitService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController("api SensorData")
@RequestMapping("/api/sensors-data")
public class SensorDataController {

	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private IUnitService unitService;

	@PostMapping("/upload-data/{sensor_id}")
	@Secured("permitAll")
	private ResponseEntity<String> uploadSensorData(@PathVariable(value = "sensor_id") Long sensorId,
			@RequestBody Map<String, Object> requestBody) throws JsonMappingException, JsonProcessingException {

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

		detectOutOfRangeVariable(data, sensor, timeStamp);

		log.info("Data parsed");
		unitService.insertSensorData(sensorId, data, sensorUnit, dinagraphReading, timeStamp);
		return ResponseEntity.ok("Data uploaded successfully");
	}

	@GetMapping("/get-data/{sensor_id}")
	private ResponseEntity<List<SensorData>> getSensorData(@PathVariable(value = "sensor_id") Long sensorId) {
		List<SensorData> sennsorData = unitService.getSensorDataFromCurrentYear(sensorId);
		String fetchUrl = "http://localhost:8090/api/sensors-data/get-data/" + sensorId;
		Map<String, Object> responseBody = new HashMap<>();
		responseBody.put("data", sennsorData);
		responseBody.put("fetchUrl", fetchUrl);
		return ResponseEntity.ok(sennsorData);
	}

	@GetMapping("/get-dinagraph-data/{unit_id}")
	private ResponseEntity<List<SensorData>> getDinagraphData(@PathVariable(value = "unit_id") Long unitId) {
		List<SensorData> todayData = unitService.getSensorDataFromCurrentYear(unitId);
		return ResponseEntity.ok(todayData);
	}

	private void detectOutOfRangeVariable(Double data, Sensor sensor, Date timestamp)
			throws JsonMappingException, JsonProcessingException {

		String sensorSetting = sensor.getSettings();
		ObjectMapper objectMapper = new ObjectMapper();
		SensorSetting settings = objectMapper.readValue(sensorSetting, SensorSetting.class);
		UnitEvent unitEvent = new UnitEvent();
		Unit unit = sensor.getUnit();

		if (data < settings.getMinValue()) {
			String response = sendMessage("Variable " + sensor.getType() + " From Oil Well " + unit.getId() + " Is Over Range", sensor);
			unitEvent.setUnit(unit);
			unitEvent.setEventName("Variable Over Range");
			unitEvent.setEventDetail("Variable " + sensor.getType() + " From Oil Well " + unit.getId() + " Is Over Range");
			unitEvent.setEventPriority(3);
			unitEvent.setEventAttended(false);
			unitEvent.setTimestamp(timestamp);
			unitService.saveUnitEvent(unitEvent);
			System.out.println(response + " value over range");
		} else if (data > settings.getMaxValue()) {
			String response = sendMessage("Variable " + sensor.getType() + " From Oil Well " + unit.getId() + " Is Under Range", sensor);
			unitEvent.setUnit(unit);
			unitEvent.setEventName("Variable Over Range");
			unitEvent.setEventDetail("Variable " + sensor.getType() + " From Oil Well " + unit.getId() + " Is Under Range");
			unitEvent.setEventPriority(3);
			unitEvent.setEventAttended(false);
			unitEvent.setTimestamp(timestamp);
			unitService.saveUnitEvent(unitEvent);
			System.out.println(response + " value under range");
		} else {
			System.out.println("value between limit");
		}
	}

	private String sendMessage(String message, Sensor sensor) {
		RestTemplate restTemplate = new RestTemplate();
		URI uri = UriComponentsBuilder.fromUriString("http://localhost:8090/pushover/message")
				.queryParam("title", message).queryParam("message", sensor.getType()).build().toUri();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> requestEntity = new HttpEntity<>("", headers);

		ResponseEntity<String> responseEntity = restTemplate.exchange(uri, HttpMethod.POST, requestEntity,
				String.class);
		String responseBody = responseEntity.getBody();
		return responseBody;
	}
}
