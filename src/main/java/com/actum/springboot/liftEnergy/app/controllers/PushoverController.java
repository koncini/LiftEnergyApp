package com.actum.springboot.liftEnergy.app.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/pushover")
public class PushoverController {

	@Value("${pushover.api.token}")
	private String apiToken;

	@Value("${pushover.user.key}")
	private String userKey;

	@PostMapping("/message")
	@Secured("permitAll")
	public ResponseEntity<String> sendMessage(@RequestParam String title,
			@RequestParam String message) {

		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		map.add("token", apiToken);
		map.add("user", userKey);
		map.add("title", title);
		map.add("message", message);

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

		ResponseEntity<String> response = restTemplate.postForEntity("https://api.pushover.net/1/messages.json",
				request, String.class);

		return response;
	}
}
