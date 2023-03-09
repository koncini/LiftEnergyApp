package com.actum.springboot.liftEnergy.app.controllers;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.actum.springboot.liftEnergy.app.models.dao.IZoneDao.ZoneNameAndId;
import com.actum.springboot.liftEnergy.app.models.entity.Unit;
import com.actum.springboot.liftEnergy.app.models.entity.Zone;
import com.actum.springboot.liftEnergy.app.models.service.IUnitService;

@Controller
@RequestMapping("/zona")
public class ZoneController {

	@Autowired
	private IUnitService unitService;
	
	@Value("${texto.zonecontroller.listunits.unit}")
	private String unitString;
	
	@Value("${texto.zonecontroller.listunits.title}")
	private String titleString;
	
	@Value("${texto.zonecontroller.listunits.zone}")
	private String zoneString;

	@GetMapping("/listar-zonas")
	public @ResponseBody List<ZoneNameAndId> mostrarZonas(Model model) {
		List<ZoneNameAndId> zoneNames = unitService.findEnabledZones();
		model.addAttribute("zoneNames", zoneNames);
		return zoneNames;
	}

	@GetMapping("/{id}")
	public String verZona(Model model) {
		return "zone/ver";
	}
	
	@GetMapping("/listar-zonas-detallado")
	public String listarZonas(Model model) {
		return"zone/listar";
	}
	
	@GetMapping("/{id}/listar-unidades")
	public String listarUnidades(@PathVariable Long id, Model model) {
		Zone zone = unitService.findOneZone(id);
		List<Unit> units = zone.getUnits();
		
		//Borrar luego
		Random random = new Random();
		int production = random.nextInt(48000) + 2000;
		
		model.addAttribute("title", titleString);
		model.addAttribute("message", unitString.concat(zoneString).concat(zone.getName()));
		model.addAttribute("productionUnit", production);
		model.addAttribute("units", units);
		return "unit/listar";
	}

}
