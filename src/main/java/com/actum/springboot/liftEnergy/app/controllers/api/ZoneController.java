package com.actum.springboot.liftEnergy.app.controllers.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.actum.springboot.liftEnergy.app.models.entity.Zone;
import com.actum.springboot.liftEnergy.app.models.service.IUnitService;

@RestController("api ZoneController")
@RequestMapping("/api/zone")
public class ZoneController {

	@Autowired
	private IUnitService unitService;

	@GetMapping("/get-zones")
	public List<Zone> getZones() {
		return unitService.getAllZones();
	}

	@GetMapping("/get-zone")
	public Zone getZoneById(@RequestParam(value = "id") Long id) {
		return unitService.getOneZone(id);
	}

}
