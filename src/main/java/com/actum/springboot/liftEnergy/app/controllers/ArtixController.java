package com.actum.springboot.liftEnergy.app.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

	@GetMapping("/result/{sampleId}")
	public String artixResult(@PathVariable(value = "sampleId") Long sampleId, Map<String, Object> model) {
		DinagraphSample dinagraphSample = unitService.findOneDinagraphSample(sampleId);
		if (dinagraphSample == null) {
			return "redirect:../form";
		}

		model.put("sample", dinagraphSample);
		model.put("message", "Artix Result");
		model.put("title", "Artix Result");
		model.put("eventsUnattended", eventsUnattended);
		return "artix/result";
	}

	@GetMapping("/form")
	public String artixConsole(Map<String, Object> model) {
		List<DinagraphSample> samples = unitService.findAllDinagraphSamples();
		DinagraphSample dinagraphSample = new DinagraphSample();
		model.put("sample", dinagraphSample);
		model.put("title", "Artix Console");
		model.put("message", "Artix Console");
		model.put("samples", samples);
		model.put("eventsUnattended", eventsUnattended);
		return "artix/console";
	}

	@PostMapping("/form")
	public String saveSample(@Valid DinagraphSample dinagraphSample, Model model, @RequestParam("file") MultipartFile sample,
			RedirectAttributes flash) {

		if (!sample.isEmpty()) {
			String rootPath = "C://temp//uploads";
			String sampleName = sample.getOriginalFilename();
			String artixResponse = artixClient.getArtixConversion(sampleName);
			if (artixResponse.contains("500 INTERNAL_SERVER_ERROR")) {
				flash.addFlashAttribute("error", "Artix Conversion Failure");
				return "redirect:form";
			}
			dinagraphSample.setData(artixResponse);
			try {
				byte[] bytes = sample.getBytes();
				Path complePath = Paths.get(rootPath + "//" + sampleName);
				Files.write(complePath, bytes);
				dinagraphSample.setImage(sample.getOriginalFilename());
			} catch (IOException e) {
				flash.addFlashAttribute("error", "File Upload Failure");
				return "redirect:form";
			}
		}
		unitService.saveDinagraphSample(dinagraphSample);
		flash.addFlashAttribute("success", "Sample Upload Succesfully");
		return "redirect:form";
	}

	@GetMapping("/delete/{sampleId}")
	public String deleteSample(@PathVariable(name = "sampleId") Long sampleId, RedirectAttributes flash) {
		unitService.deleteDinagraphSample(sampleId);
		flash.addFlashAttribute("warning", "Sample Deleted Succesfully");
		return "redirect:../form";
	}

}
