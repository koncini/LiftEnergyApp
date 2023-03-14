package com.actum.springboot.liftEnergy.app.clients;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CommoditiesClient {

	private final RestTemplate restTemplate;

	public CommoditiesClient(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
	}

	public String getCommoditiesData() {
		String url = "https://commodities-api.com/api/latest?access_key=o33myrdoy95skx2zwzkp8pa3dm857xda0akg2vqqz9gear63ih4c8kv9t5l7";
		return this.restTemplate.getForObject(url, String.class);
	}

}