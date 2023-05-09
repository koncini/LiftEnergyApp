package com.actum.springboot.liftEnergy.app.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.actum.springboot.liftEnergy.app.models.entity.DinagraphSample;
import com.actum.springboot.liftEnergy.app.models.service.IUnitService;

import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/artix")
public class ArtixController {
	
	@Autowired
	private IUnitService unitService;
	
	private Long eventsUnattended;

	@PostConstruct
	public void init() {
		eventsUnattended = unitService.getCountOfUnattendedEvents();
	}
	
	@GetMapping("/console")
	public String artixConosle(Map<String, Object> model) {
		DinagraphSample dinagraphSample = new DinagraphSample();
		model.put("sample", dinagraphSample);
		model.put("title", "Artix Console");
		model.put("message", "Artix Console");
		return "artix/console";
	}
		
	@PostMapping(value = "/form")
	public String save(@Valid DinagraphSample dinagraphSample, Model model, @RequestParam("file") MultipartFile sample) {
		
		if(!sample.isEmpty()) {
			Path resourceDirectory = Paths.get("src//main//resources//static//uploads");
			String rootPath = resourceDirectory.toFile().getAbsolutePath();
			String sampleName = sample.getOriginalFilename();
			try {
				byte[] bytes = sample.getBytes();
				Path complePath = Paths.get(rootPath + "//" + sampleName);
				Files.write(complePath, bytes);
				
				dinagraphSample.setImage(sample.getOriginalFilename());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		unitService.saveDinagraphSample(dinagraphSample);
		return "redirect::artix/result";
	}

}
