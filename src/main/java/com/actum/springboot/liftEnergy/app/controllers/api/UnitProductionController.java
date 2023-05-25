package com.actum.springboot.liftEnergy.app.controllers.api;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.actum.springboot.liftEnergy.app.models.entity.Unit;
import com.actum.springboot.liftEnergy.app.models.entity.UnitProduction;
import com.actum.springboot.liftEnergy.app.models.service.IUnitService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RestController("api UnitProduction")
@RequestMapping("/api/unit-production")
public class UnitProductionController {
	
	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private IUnitService unitService;
	
	@PostMapping("/upload-data/{unit_id}")
	@Secured("permitAll")
	private ResponseEntity<String> uploadUnitProduction(@PathVariable(value = "unit_id") Long unitId,
			@RequestBody Map<String, Object> requestBody) throws JsonMappingException, JsonProcessingException {

		Unit unit = unitService.findOneUnit(unitId);

		if (unit == null) {
			return ResponseEntity.ok("La unidad a la que desea escribir datos no existe o está deshabilitada");
		}

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");

		Double production = (Double) requestBody.get("data");
		String strDate = (String) requestBody.get("timeStamp");
		Date timeStamp = null;
		try {
			timeStamp = dateFormat.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		log.info("Data parsed");
		unitService.insertUnitProduction(unitId, production, timeStamp);
		return ResponseEntity.ok("Data uploaded successfully");
	}
	
	@GetMapping("/get-today-production-data/{unit_id}")
	private ResponseEntity<List<UnitProduction>> getTodayProductionData(@PathVariable(value = "unit_id")Long unitId){
		List<UnitProduction> production = unitService.getUnitProductionFromToday(unitId);
		return ResponseEntity.ok(production);
	}
	
	@GetMapping("/get-month-production-data/{unit_id}")
	private ResponseEntity<List<UnitProduction>> getMonthProductionData(@PathVariable(value = "unit_id")Long unitId){
		List<UnitProduction> production = unitService.getUnitProductionFromCurrentMonth(unitId);
		return ResponseEntity.ok(production);
	}
	
	@GetMapping("/get-week-production-data/{unit_id}")
	private ResponseEntity<List<UnitProduction>> getWeekProductionData(@PathVariable(value = "unit_id")Long unitId){
		List<UnitProduction> production = unitService.getUnitProductionFromCurrentMonth(unitId);
		return ResponseEntity.ok(production);
	}
	
	@GetMapping("/get-year-production-data/{unit_id}")
	private ResponseEntity<List<UnitProduction>> getYearProductionData(@PathVariable(value = "unit_id")Long unitId){
		List<UnitProduction> production = unitService.getUnitProductionFromCurrentYear(unitId);
		return ResponseEntity.ok(production);
	}

}
