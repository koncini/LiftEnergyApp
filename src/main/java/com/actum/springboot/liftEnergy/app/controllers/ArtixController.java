package com.actum.springboot.liftEnergy.app.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.actum.springboot.liftEnergy.app.clients.ArtixClient;
import com.actum.springboot.liftEnergy.app.models.entity.DinagraphSample;
import com.actum.springboot.liftEnergy.app.models.service.IUnitService;

import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/artix")
public class ArtixController {
	
	@Autowired
	private IUnitService unitService;
	
	@Autowired
	private ArtixClient artixClient;
		
	private Long eventsUnattended;
	
	@PostConstruct
	public void init() {
		eventsUnattended = unitService.getCountOfUnattendedEvents();
	}
	
	@GetMapping("/result/{id}")
	public String artixResult(@PathVariable(value="id") Long id, Map<String, Object> model) {
		DinagraphSample dinagraphSample = unitService.findOneDinagraphSample(id);
		if(dinagraphSample==null) {
			return "redirect:../form";
		}
		
		model.put("sample", dinagraphSample);
		model.put("message", "Artix Result");
		model.put("title", "Artix Result");
		return "artix/result";
	}
	
	@GetMapping("/form")
	public String artixConosle(Map<String, Object> model) {
		List<DinagraphSample> samples = unitService.findAllDinagraphSamples();
		DinagraphSample dinagraphSample = new DinagraphSample();
		model.put("sample", dinagraphSample);
		model.put("title", "Artix Console");
		model.put("message", "Artix Console");
		model.put("samples", samples);
		return "artix/console";
	}
		
	@PostMapping("/form")
	public String save(@Valid DinagraphSample dinagraphSample, Model model, @RequestParam("file") MultipartFile sample) {
		
		if(!sample.isEmpty()) {
			String rootPath = "C://temp//uploads";
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
			String artixResponse = artixClient.getArtixConversion(sampleName);
			dinagraphSample.setData(artixResponse);
		}
		
		unitService.saveDinagraphSample(dinagraphSample);
		return "redirect:form";
	}

}
