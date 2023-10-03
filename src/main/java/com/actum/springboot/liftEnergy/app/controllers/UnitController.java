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

import com.actum.springboot.liftEnergy.app.models.MotorData;
import com.actum.springboot.liftEnergy.app.models.PowerCost;
import com.actum.springboot.liftEnergy.app.models.UnitData;
import com.actum.springboot.liftEnergy.app.models.UnitSetting;
import com.actum.springboot.liftEnergy.app.models.WellData;
import com.actum.springboot.liftEnergy.app.models.WellDataWrapper;
import com.actum.springboot.liftEnergy.app.models.entity.Unit;
import com.actum.springboot.liftEnergy.app.models.entity.Zone;
import com.actum.springboot.liftEnergy.app.models.service.IDataService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/unit")
@SessionAttributes("unit")
public class UnitController {

	@Autowired
	private IDataService dataService;

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
	
	@Value("${texto.unitcontroller.listunits.unit}")
	private String unitString;
	
	@Value("${texto.unitcontroller.listunits.zone}")
	private String zoneString;

	private Long eventsUnattended;

	@PostConstruct
	public void init() {
		eventsUnattended = dataService.getCountOfUnattendedEvents();
	}

	@GetMapping("/watch/{id}")
	public String watchUnit(@PathVariable Long id, Model model) throws JsonMappingException, JsonProcessingException {
		Unit unit = dataService.getOneUnit(id);

		if (unit == null) {
			return "redirect:/index";
		}

		String unitSettings = unit.getSettings();
		String unitMetrics = unit.getMetrics();
		ObjectMapper objectMapper = new ObjectMapper();
		List<UnitSetting> settings = objectMapper.readValue(unitSettings, new TypeReference<List<UnitSetting>>() {
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

	@GetMapping("/detailed-list")
	public String listUnits(Model model) throws JsonMappingException, JsonProcessingException {
		List<Unit> units = dataService.getAllUnits();

		Map<Long, List<UnitSetting>> unitSettingMap = new HashMap<>();
		Map<Long, Number> wellProductionMap = new HashMap<>();
		Map<Long, Number> unitProductionGoalMap = new HashMap<>();
		Map<Long, String> unitWorkModeMap = new HashMap<>();
		Map<Long, String> unitStatusMap = new HashMap<>();
		Map<Long, String> unitRelatedZone = new HashMap<>();

		for (Unit unit : units) {
			Long unitId = unit.getId();
			String unitSettings = unit.getSettings();
			ObjectMapper objectMapper = new ObjectMapper();

			List<UnitSetting> settingsData = objectMapper.readValue(unitSettings,
					new TypeReference<List<UnitSetting>>() {
					});

//			for (UnitSetting setting : settingsData) {
//				if (setting.getName().equals("production_goal")) {
//					Object productionGoalData = unitSettingService.getValueByName("production_goal");
//					unitProductionGoalMap.put(unitId, (Number) productionGoalData);
//					break; 
//				}
//			}

			String unitMetrics = unit.getMetrics();
			ObjectMapper mapper = new ObjectMapper();
			WellDataWrapper wellDataWrapper = mapper.readValue(unitMetrics, WellDataWrapper.class);

			WellData wellProductionData = wellDataWrapper.getWellDataByName("well_production");
//			Object workModeData = unitSettingService.getValueByName("work_mode");
//			Object statusData = unitSettingService.getValueByName("status");

			wellProductionMap.put(unitId, wellProductionData.getValue());
//			unitWorkModeMap.put(unitId, workModeData.toString());
//			unitStatusMap.put(unitId, statusData.toString());

			unitRelatedZone.put(unitId, dataService.getZoneNameByUnitId(unitId));

		}

		model.addAttribute("title", "Oil Wells");
		model.addAttribute("message", "All Oil Wells");
		model.addAttribute("units", units);
		model.addAttribute("unitRelatedZone", unitRelatedZone);
		model.addAttribute("unitSettings", unitSettingMap);
		model.addAttribute("wellProductionData", wellProductionMap);
		model.addAttribute("wellProductionGoal", unitProductionGoalMap);
		model.addAttribute("unitWorkMode", unitWorkModeMap);
		model.addAttribute("unitStatus", unitStatusMap);
		model.addAttribute("eventsUnattended", eventsUnattended);

		return "unit/list";
	}
	
	@GetMapping("/{id}/list-units")
	public String listUnits(@PathVariable Long id, Model model)
			throws JsonMappingException, JsonProcessingException {
		Zone zone = dataService.getOneZone(id);
		List<Unit> units = zone.getUnits();
		Map<Long, List<UnitSetting>> unitSettingMap = new HashMap<>();
		Map<Long, Number> wellProductionMap = new HashMap<>();
		Map<Long, Number> unitProductionGoalMap = new HashMap<>();
		Map<Long, String> unitWorkModeMap = new HashMap<>();
		Map<Long, String> unitStatusMap = new HashMap<>();
		Map<Long, String> unitRelatedZone = new HashMap<>();

		for (Unit unit : units) {
			Long unitId = unit.getId();
			String unitSettings = unit.getSettings();
			ObjectMapper objectMapper = new ObjectMapper();

			List<UnitSetting> settings = objectMapper.readValue(unitSettings, new TypeReference<List<UnitSetting>>() {});
			unitSettingMap.put(unitId, settings);

			String unitMetrics = unit.getMetrics();
			ObjectMapper mapper = new ObjectMapper();
			WellDataWrapper wellDataWrapper = mapper.readValue(unitMetrics, WellDataWrapper.class);

			WellData wellData = wellDataWrapper.getWellDataByName("well_production");
			wellProductionMap.put(unitId, wellData.getValue());
			
			unitRelatedZone.put(unitId, dataService.getZoneNameByUnitId(unitId));

		}

		model.addAttribute("title", "Oil Wells From Oil Field");
		model.addAttribute("message", unitString.concat(zoneString).concat(zone.getName()));
		model.addAttribute("units", units);
		model.addAttribute("unitRelatedZone", unitRelatedZone);
		model.addAttribute("unitSettings", unitSettingMap);
		model.addAttribute("wellProductionData", wellProductionMap);
		model.addAttribute("wellProductionGoal", unitProductionGoalMap);
		model.addAttribute("unitWorkMode", unitWorkModeMap);
		model.addAttribute("unitStatus", unitStatusMap);
		model.addAttribute("eventsUnattended", eventsUnattended);

		return "unit/list";
	}

	@GetMapping("/form/{unitId}")
	public String editUnit(@PathVariable Long unitId, Map<String, Object> model,
			RedirectAttributes flash) {

		Unit unit = dataService.getOneUnit(unitId);
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

	@GetMapping("/edit-settings/{unitId}")
	public String editUnitSettings(@PathVariable Long unitId, Model model, RedirectAttributes flash) {
		Unit unit = dataService.getOneUnit(unitId);
		model.addAttribute("unit", unit);
		model.addAttribute("title", titleFormUnitString);
		model.addAttribute("message", messageFormUnitString);
		model.addAttribute("eventsUnattended", eventsUnattended);

		return "unit/config";
	}

	@GetMapping("/delete/{unitId}")
	public String deleteUnit(@PathVariable Long unitId, Model model, RedirectAttributes flash) {
		if (unitId > 0) {
			dataService.deleteUnit(unitId);
		}
		return "redirect:../detailed-list";
	}

}
