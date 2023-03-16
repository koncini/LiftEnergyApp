package com.actum.springboot.liftEnergy.app.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.actum.springboot.liftEnergy.app.models.dao.IZoneDao.ZoneNameAndId;
import com.actum.springboot.liftEnergy.app.models.entity.Unit;
import com.actum.springboot.liftEnergy.app.models.entity.UnitSettings;
import com.actum.springboot.liftEnergy.app.models.entity.WellData;
import com.actum.springboot.liftEnergy.app.models.entity.WellDataWrapper;
import com.actum.springboot.liftEnergy.app.models.entity.Zone;
import com.actum.springboot.liftEnergy.app.models.service.IUnitService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.annotation.PostConstruct;

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

	private Long eventsUnattended;

	@PostConstruct
	public void init() {
		eventsUnattended = unitService.getCountOfUnattendedEvents();
	}

	@GetMapping("/listar-zonas")
	public @ResponseBody List<ZoneNameAndId> mostrarZonas(Model model) {
		List<ZoneNameAndId> zoneNames = unitService.findEnabledZones();
		model.addAttribute("zoneNames", zoneNames);
		model.addAttribute("eventsUnattended", eventsUnattended);

		return zoneNames;
	}

	@GetMapping("/{id}")
	public String verZona(Model model) {
		return "zone/watch";
	}

	@GetMapping("/listar-zonas-detallado")
	public String listarZonas(Model model) {
		List<Zone> zones = unitService.findAllZones();
		model.addAttribute("title", titleString);
		model.addAttribute("message", "All Oil Fields");
		model.addAttribute("zones", zones);
		model.addAttribute("eventsUnattended", eventsUnattended);

		return "zone/list";
	}

	@GetMapping("/{id}/listar-unidades")
	public String listarUnidades(@PathVariable Long id, Model model)
			throws JsonMappingException, JsonProcessingException {
		Zone zone = unitService.findOneZone(id);
		List<Unit> units = zone.getUnits();
		Map<Long, List<UnitSettings>> unitSettingMap = new HashMap<>();
		Map<Long, Number> wellProductionMap = new HashMap<>();

		for (Unit unit : units) {
			Long unitId = unit.getId();
			String unitSettings = unit.getSettings();
			ObjectMapper objectMapper = new ObjectMapper();

			List<UnitSettings> settings = objectMapper.readValue(unitSettings, new TypeReference<List<UnitSettings>>() {
			});
			unitSettingMap.put(unitId, settings);

			String unitMetrics = unit.getMetrics();
			ObjectMapper mapper = new ObjectMapper();
			WellDataWrapper wellDataWrapper = mapper.readValue(unitMetrics, WellDataWrapper.class);

			WellData wellData = wellDataWrapper.getWellDataByName("well_production");
			wellProductionMap.put(unitId, wellData.getValue());

		}

		model.addAttribute("title", titleString);
		model.addAttribute("message", unitString.concat(zoneString).concat(zone.getName()));
		model.addAttribute("units", units);
		model.addAttribute("unitSettings", unitSettingMap);
		model.addAttribute("wellData", wellProductionMap);
		model.addAttribute("eventsUnattended", eventsUnattended);

		return "unit/list";
	}

	@GetMapping("/form/{zoneId}")
	public String editZone(@PathVariable(value = "zoneId") Long zoneId, Model model, RedirectAttributes flash) {

		Zone zone = unitService.findOneZone(zoneId);
		if (zone == null) {
			return "redirect:/list";
		}

		model.addAttribute("zone", zone);
		model.addAttribute("title", "Edit Oil Field ");
		model.addAttribute("message", "Edit Oil Field ");
		model.addAttribute("eventsUnattended", eventsUnattended);

		return "zone/form";
	}

	@GetMapping("/form")
	public String createZone(Model model, RedirectAttributes flash) {

		model.addAttribute("title", "Create Oil Field ");
		model.addAttribute("message", "Create Oil Field");
		model.addAttribute("eventsUnattended", eventsUnattended);

		return "zone/new";
	}

}
