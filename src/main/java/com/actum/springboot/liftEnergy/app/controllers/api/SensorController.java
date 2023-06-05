package com.actum.springboot.liftEnergy.app.controllers.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.actum.springboot.liftEnergy.app.models.service.IDataService;

@RestController("api Sensor")
@RequestMapping("/api/sensor")
public class SensorController {
	
	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private IDataService dataService;

}
