package salen.palikat.ohjelmistoprojekti.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import salen.palikat.ohjelmistoprojekti.domain.Kysymys;
import salen.palikat.ohjelmistoprojekti.domain.KysymysRepository;
import salen.palikat.ohjelmistoprojekti.domain.Vastaus;
import salen.palikat.ohjelmistoprojekti.domain.VastausRepository;

@Controller
public class HienoControlleri {

@Autowired
KysymysRepository kysymysRepo;

@Autowired
VastausRepository vastausRepo;
	
	
	@GetMapping("/")
	public String indexGet(Model model) {
		return "index";
	}
	
	
	@GetMapping("/haekysymys")
	public String haeKysymys(Model model) {
		
		return "haeKysymys";
	}
	
	@PostMapping("/muntesti")
	public String palautaKysymys()
	{
		System.out.println("JEEEEEEEEEEEEEEE");

		return "index";
	}
	
	
	
	
	@PostMapping("/palautakysymys")
	public String palautaKysymys(Vastaus vastaus)
	{
		System.out.println("JEEEEEEEEEEEEEEE");
		System.out.println(kysymysok(vastaus));
		return "index";
	}
	
	
	private boolean kysymysok(Vastaus vastaus)
	{
		Kysymys kysymys = kysymysRepo.findByid(vastaus.getKysymys().getId());
		if(kysymys== null)
			return false;
		
		vastaus.setKysymys(kysymys);
		vastausRepo.save(vastaus);
		return true;
	}
	
}
