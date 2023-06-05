package com.actum.springboot.liftEnergy.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.actum.springboot.liftEnergy.app.models.WellData;
import com.actum.springboot.liftEnergy.app.models.WellDataWrapper;
import com.actum.springboot.liftEnergy.app.models.entity.Unit;
import com.actum.springboot.liftEnergy.app.models.entity.Zone;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ProductionCalculationService {
	
	@Autowired
	private IDataService dataService;
	
	@Scheduled(cron = "0 0 * * * *") 
	public void calculateHourlyZoneProduction() {
		List<Zone> zones = dataService.getAllEnabledZones();
		System.out.println("Executing periodic task...");
	}
	
	@Scheduled(cron = "0 0 * * * *") 
	public void calculateHourlyUnitProduction() throws JsonMappingException, JsonProcessingException {
		System.out.println("Executing hourly task...");
		List<Unit> units = dataService.getAllEnabledUnits();
		ObjectMapper mapper = new ObjectMapper();
		for (Unit unit : units) {
			Long production = dataService.getTotalUnitProductionFromLastHour(unit.getId());
			String metrics = unit.getMetrics();
			WellDataWrapper wellDataWrapper = mapper.readValue(metrics, WellDataWrapper.class);
			List<WellData> wellData = wellDataWrapper.getWellData();
			
			
		}
	}

}
