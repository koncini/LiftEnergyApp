package com.actum.springboot.liftEnergy.app.controllers.api;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.actum.springboot.liftEnergy.app.models.entity.DinagraphSample;
import com.actum.springboot.liftEnergy.app.models.service.IUnitService;

@RestController("api DinagraphSample")
@RequestMapping("/api/samples")
public class DinagraphSampleContoller {
	
	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private IUnitService unitService;
	
	@GetMapping("/get-data/{sample_id}")
	private ResponseEntity<DinagraphSample> getSampleData(@PathVariable(value = "sample_id") Long sampleId) {
		DinagraphSample sample = unitService.findOneDinagraphSample(sampleId);
		String fetchUrl = "http://localhost:8090/api/samples/get-data/" + sampleId;
		Map<String, Object> responseBody = new HashMap<>();
		responseBody.put("data", sample);
		responseBody.put("fetchUrl", fetchUrl);
		return ResponseEntity.ok(sample);
	}

}
