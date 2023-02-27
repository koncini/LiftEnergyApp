package com.actum.springboot.liftEnergy.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.actum.springboot.liftEnergy.app.models.dao.IZoneDao.ZoneNameAndId;
import com.actum.springboot.liftEnergy.app.models.service.IUnitService;

@Controller
public class ZoneController {

	@Autowired
	private IUnitService unitService;

	@GetMapping("/listar-zonas")
	public @ResponseBody List<ZoneNameAndId> listarZonas(Model model) {
		List<ZoneNameAndId> zoneNames = unitService.findEnabledZones();
		model.addAttribute("zoneNames", zoneNames);
		return zoneNames;
	}

	@GetMapping("/zona/{id}")
	public String verZona(Model model) {
		return "zona";
	}

}
