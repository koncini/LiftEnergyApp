package com.actum.springboot.liftEnergy.app.models.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ProductionCalculationService {
	
	@Autowired
	private IDataService dataService;
	
	@Scheduled(fixedRate = 60000)
	public void calculateZoneProduction() {
		System.out.println("Executing periodic task...");
	}
	
	@Scheduled(cron = "0 0 * * * *") 
	public void calculateHourlyUnitProduction() {
		System.out.println("Executing hourly task...");
		
	}

}
