package com.actum.springboot.liftEnergy.app.clients;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CommoditiesClient {
	
	@Value("${commodities.request.url}")
	private String requestUrl;
	
	@Value("${commodities.request.api.key}")
	private String apiKey;

	private final RestTemplate restTemplate;

	public CommoditiesClient(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
	}

	public String getCommoditiesData() {
		String url = requestUrl.concat(apiKey);
		return this.restTemplate.getForObject(url, String.class);
	}

}