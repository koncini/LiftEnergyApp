package com.actum.springboot.liftEnergy.app.clients;

import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class ArtixClient {

	@Value("${artix.request.url}")
	private String requestUrl;

	private final RestTemplate restTemplate;

	public ArtixClient(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
	}

	public String getArtixConversion(String name) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(requestUrl)
				.queryParam("name", name)
				.encode(StandardCharsets.UTF_8);
		String url = builder.toUriString();
		return this.restTemplate.getForObject(url, String.class);
	}
}
