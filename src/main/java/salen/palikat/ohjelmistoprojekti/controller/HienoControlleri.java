package salen.palikat.ohjelmistoprojekti.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import salen.palikat.ohjelmistoprojekti.domain.KysymysRepository;

@Controller
public class HienoControlleri {

@Autowired
KysymysRepository kysymysRepo;
	
	
	@GetMapping("/")
	public String indexGet(Model model) {
		return "index";
	}
	
	
	@GetMapping("/haekysymys")
	public String haeKysymys(Model model) {
		
		return "haeKysymys";
	}
	
	
	@PostMapping("/palautakysymys")
	public String palautaKysymys(Model model)
	{
		return "index";
	}
	
}
