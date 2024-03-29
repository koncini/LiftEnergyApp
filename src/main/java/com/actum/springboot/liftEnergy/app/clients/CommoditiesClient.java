package com.actum.springboot.liftEnergy.app.clients;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

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

	public String getCommoditiesData(String commodity, String interval) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(requestUrl)
				.queryParam("function", commodity)
				.queryParam("interval", interval)
				.queryParam("apikey", apiKey);
		String url = builder.toUriString();
		return this.restTemplate.getForObject(url, String.class);
	}

}