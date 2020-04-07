package salen.palikat.ohjelmistoprojekti.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HienoControlleri {

	
	
	
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
