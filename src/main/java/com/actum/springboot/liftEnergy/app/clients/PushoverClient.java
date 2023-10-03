package com.actum.springboot.liftEnergy.app.clients;

import java.net.URI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.actum.springboot.liftEnergy.app.models.entity.Sensor;

@Component
public class PushoverClient {
	
	@Value("${pushover.request.url}")
	private String requestUrl;
	
	public String sendSensorOverRangeMessage(String message, Sensor sensor) {
		RestTemplate restTemplate = new RestTemplate();
		URI uri = UriComponentsBuilder.fromUriString(requestUrl)
				.queryParam("title", message).queryParam("message", sensor.getType()).build().toUri();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> requestEntity = new HttpEntity<>("", headers);

		ResponseEntity<String> responseEntity = restTemplate.exchange(uri, HttpMethod.POST, requestEntity,
				String.class);
		String responseBody = responseEntity.getBody();
		return responseBody;
	}
	
	public String sendUnitDinagraphFailMessage(String message) {
		RestTemplate restTemplate = new RestTemplate();
		URI uri = UriComponentsBuilder.fromUriString(requestUrl)
				.queryParam("title", message).queryParam("message", "Dinagraph Error").build().toUri();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> requestEntity = new HttpEntity<>("", headers);

		ResponseEntity<String> responseEntity = restTemplate.exchange(uri, HttpMethod.POST, requestEntity,
				String.class);
		String responseBody = responseEntity.getBody();
		return responseBody;
	}

}
