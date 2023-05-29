package com.actum.springboot.liftEnergy.app.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.actum.springboot.liftEnergy.app.models.entity.MotorData;
import com.actum.springboot.liftEnergy.app.models.entity.PowerCost;
import com.actum.springboot.liftEnergy.app.models.entity.Unit;
import com.actum.springboot.liftEnergy.app.models.entity.UnitData;
import com.actum.springboot.liftEnergy.app.models.entity.UnitSettings;
import com.actum.springboot.liftEnergy.app.models.entity.WellData;
import com.actum.springboot.liftEnergy.app.models.entity.WellDataWrapper;
import com.actum.springboot.liftEnergy.app.models.service.IUnitService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/unidad")
@SessionAttributes("unit")
public class UnitController {

	@Autowired
	private IUnitService unitService;

	@Value("${texto.unitcontroller.watchunits.message}")
	private String messageWatchString;

	@Value("${texto.unitcontroller.watchunits.title}")
	private String titleWatchString;

	@Value("${texto.unitcontroller.watchsensor.title}")
	private String titleWatchSensorString;

	@Value("${texto.unitcontroller.watchsensor.message}")
	private String messageWatchSensorString;
	
	@Value("${texto.unitcontroller.form.title}")
	private String titleFormUnitString;

	@Value("${texto.unitcontroller.form.message}")
	private String messageFormUnitString;

	private Long eventsUnattended;

	@PostConstruct
	public void init() {
		eventsUnattended = unitService.getCountOfUnattendedEvents();
	}

	@GetMapping("/ver/{id}")
	public String verUnidad(@PathVariable Long id, Model model) throws JsonMappingException, JsonProcessingException {
		Unit unit = unitService.findOneUnit(id);

		if (unit == null) {
			return "redirect:/index";
		}

		String unitSettings = unit.getSettings();
		String unitMetrics = unit.getMetrics();
		ObjectMapper objectMapper = new ObjectMapper();
		List<UnitSettings> settings = objectMapper.readValue(unitSettings, new TypeReference<List<UnitSettings>>() {
		});

		ObjectMapper mapper = new ObjectMapper();
		WellDataWrapper wellDataWrapper = mapper.readValue(unitMetrics, WellDataWrapper.class);

		List<WellData> wellData = wellDataWrapper.getWellData();
		List<UnitData> unitData = wellDataWrapper.getUnitData();
		List<MotorData> motorData = wellDataWrapper.getMotorData();
		List<PowerCost> powerCost = wellDataWrapper.getPowerCost();
		
		LocalDate currentDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E dd MMM yyyy");
		String formattedDate = currentDate.format(formatter);

		model.addAttribute("title", titleWatchString);
		model.addAttribute("message", messageWatchString.concat(unit.getId().toString()));
		model.addAttribute("date", formattedDate);
		model.addAttribute("settings", settings);
		model.addAttribute("wellData", wellData);
		model.addAttribute("unitData", unitData);
		model.addAttribute("motorData", motorData);
		model.addAttribute("powerCost", powerCost);
		model.addAttribute("unit", unit);
		model.addAttribute("eventsUnattended", eventsUnattended);

		return "unit/watch";
	}

	@GetMapping("/listar-unidades-detallado")
	public String listarUnidades(Model model) throws JsonMappingException, JsonProcessingException {
		List<Unit> units = unitService.findAllUnits();

		Map<Long, List<UnitSettings>> unitSettingMap = new HashMap<>();
		Map<Long, Number> wellProductionMap = new HashMap<>();
		Map<Long, String> unitRelatedZone = new HashMap<>();

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
			
			unitRelatedZone.put(unitId, unitService.getZoneNameByUnitId(unitId));

		}

		model.addAttribute("title", "Oil Wells");
		model.addAttribute("message", "All Oil Wells");
		model.addAttribute("units", units);
		model.addAttribute("unitRelatedZone", unitRelatedZone);
		model.addAttribute("unitSettings", unitSettingMap);
		model.addAttribute("wellData", wellProductionMap);
		model.addAttribute("eventsUnattended", eventsUnattended);

		return "unit/list";
	}
	
	@GetMapping("/edit-settings/{unitId}")
	public String editUnitSettings(@PathVariable(value = "unitId") Long unitId, Model model, RedirectAttributes flash) {
		Unit unit = unitService.findOneUnit(unitId);
		model.addAttribute("unit", unit);
		model.addAttribute("title", titleFormUnitString);
		model.addAttribute("message", messageFormUnitString);
		model.addAttribute("eventsUnattended", eventsUnattended);
		
		return "unit/config";
	}
	
	@GetMapping("/form/{unitId}")
	public String editUnit(@PathVariable(value = "unitId") Long unitId, Map<String, Object> model, RedirectAttributes flash) {
		
		Unit unit = unitService.findOneUnit(unitId);
		if (unit == null) {
			return "redirect:/list";	
		}
		model.put("unit", unit);
		model.put("title", titleFormUnitString);
		model.put("message", messageFormUnitString);
		model.put("eventsUnattended", eventsUnattended);
		flash.addFlashAttribute("success", "Oil Well Edited");
		
		return "unit/form";
	}
	
	@GetMapping("/form")
	public String createUnit(Map<String, Object> model, RedirectAttributes flash) {
		Unit unit = new Unit();		
		model.put("unit", unit);
		model.put("title", "Create New Oil Well ");
		model.put("message", "Create New Oil Well");
		model.put("eventsUnattended", eventsUnattended);
		flash.addFlashAttribute("success", "New Oil Well Created");
		
		return "unit/new";
	}
	
	@PostMapping("/form")
	public String saveUnit(@Valid Unit unit, Model model, RedirectAttributes flash) {
				
		model.addAttribute("title", "Create Oil Well ");
		model.addAttribute("message", "Create Oil Well");
		model.addAttribute("eventsUnattended", eventsUnattended);
		
		return "redirect:form";
	}
	
	@GetMapping("/delete/{unitId}")
	public String deleteUnit(@PathVariable(value = "unitId") Long unitId, Model model, RedirectAttributes flash) {
		if(unitId > 0) {
			unitService.deleteUnit(unitId);
		}
		return "redirect:../listar-unidades-detallado";
	}

}
