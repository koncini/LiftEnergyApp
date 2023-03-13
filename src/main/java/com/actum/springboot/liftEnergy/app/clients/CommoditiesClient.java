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
		String url = "https://commodities-api.com/api/latest?access_key=79a690j553ihh3vagakhwak9835by77b4412k70t5ar1q9z290s24x32x1y5";
		return this.restTemplate.getForObject(url, String.class);
	}

}