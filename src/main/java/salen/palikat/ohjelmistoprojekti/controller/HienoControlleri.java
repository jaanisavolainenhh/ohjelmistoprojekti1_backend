package salen.palikat.ohjelmistoprojekti.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import salen.palikat.ohjelmistoprojekti.domain.Kysymys;
import salen.palikat.ohjelmistoprojekti.domain.KysymysRepository;
import salen.palikat.ohjelmistoprojekti.domain.VaihtoehtoRepository;
import salen.palikat.ohjelmistoprojekti.domain.Vastaus;
import salen.palikat.ohjelmistoprojekti.domain.VastausRepository;


@Controller
public class HienoControlleri {

@Autowired
KysymysRepository kysymysRepo;

@Autowired
VastausRepository vastausRepo;

@Autowired
VaihtoehtoRepository vaihtoehtoRepo;
	
	
	@GetMapping("/")
	public String indexGet(Model model) {
		return "index";
	}
	
	
	@GetMapping("/haekysymys")
	public String haeKysymys(Model model) {
		
		return "haeKysymys";
	}
	
	
//	@CrossOrigin
//	@ResponseBody
//	@PostMapping("/palautakysely")
//	public String palautaKysymysLista(@RequestBody List<Vastaus >vastaus) //Juu tässä vaadittiin vaan @RequestBody, converttaa jsonin java classiin
//	{
//		System.out.println(vastaus.toString());
//		//System.out.println(kysymysok(vastaus));
//		return "index";
//	}
//	
//	
	
	
		
	@CrossOrigin
	@ResponseBody
	@PostMapping("/palautakysymys")
	public String palautaKysymys(@RequestBody Vastaus vastaus) //Juu tässä vaadittiin vaan @RequestBody, converttaa jsonin java classiin
	{
		System.out.println(vastaus.toString());
		System.out.println(kysymysok(vastaus));
		return "index";
	}
	
	
	private boolean kysymysok(Vastaus vastaus)
	{

		Optional<Kysymys> kysymys = kysymysRepo.findById(vastaus.getKysymys().getId());
		if(!kysymys.isPresent())
			return false;
		
		Kysymys oikeakysymys = kysymys.get();
		System.out.println("TESTIÄ");
		vastaus.setKysymys(oikeakysymys);
		vastausRepo.save(vastaus);
		return true;
	}
	
}
