package com.actum.springboot.liftEnergy.app.models.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ProductionCalculationService {
	
	@Scheduled(fixedRate = 60000)
	public void calculateZoneProduction() {
		System.out.println("Executing periodic task...");
	}

}
