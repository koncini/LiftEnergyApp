package com.actum.springboot.liftEnergy.app.controllers.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.actum.springboot.liftEnergy.app.models.entity.Zone;
import com.actum.springboot.liftEnergy.app.models.service.IDataService;

@RestController("api ZoneController")
@RequestMapping("/api/zone")
public class ZoneController {

	@Autowired
	private IDataService dataService;

	@GetMapping("/get-zones")
	public List<Zone> getZones() {
		return dataService.getAllZones();
	}

	@GetMapping("/get-zone")
	public Zone getZoneById(@RequestParam Long id) {
		return dataService.getOneZone(id);
	}

}
