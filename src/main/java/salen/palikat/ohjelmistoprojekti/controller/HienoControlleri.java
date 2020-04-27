package salen.palikat.ohjelmistoprojekti.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import salen.palikat.ohjelmistoprojekti.domain.Kysely;
import salen.palikat.ohjelmistoprojekti.domain.KyselyRepository;
import salen.palikat.ohjelmistoprojekti.domain.Kysymys;
import salen.palikat.ohjelmistoprojekti.domain.KysymysRepository;
import salen.palikat.ohjelmistoprojekti.domain.SessioID;
import salen.palikat.ohjelmistoprojekti.domain.SessioIDRepository;
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

@Autowired

SessioIDRepository sessioidRepo; 

 @Autowired
KyselyRepository kyselyRepo;

	
	
	@GetMapping("/")
	public String indexGet(Model model) {
		return "index";
	}
	
	
	@GetMapping("/haekysymys")
	public String haeKysymys(Model model) {
		
		return "haeKysymys";
	}
	
	@CrossOrigin
	@GetMapping("/kyselyt")
	public @ResponseBody List<Kysely> kyselyListResti() {
		return (List<Kysely>) kyselyRepo.findAll();
	}
	
//	@GetMapping("/sessions/{id}")
//	public @ResponseBody List<Kysely> kyselySessioittainResti() {
//		return (List<Kysymys>) kysymysRepo.findAll();
//	}
	
	//Tällä kaverilla saadaan tallennettua uusi kysely
	@PostMapping("/tallenna_kysely")
	public @ResponseBody String kyselynTallennus(@RequestBody Kysely kysely) {
		//tallenetaan kysely kantaan
		kyselyRepo.save(kysely);
		//käydään läpi kaikki kyselyn kysymykset
		for (int i = 0; i < kysely.getKysymykset().size(); i++) {
			//tallenetaan kysymys kantaan
			kysymysRepo.save(kysely.getKysymykset().get(i));
			//sitten taas käydään kysymyksen kaikki vaihtoehdot läpi
			for (int j = 0; j < kysely.getKysymykset().get(i).getVaihtoehdot().size(); j++) {
				//ja tallennetaan nekin talteen
				vaihtoehtoRepo.save(kysely.getKysymykset().get(i).getVaihtoehdot().get(j));
			}
		}	
			
		return "Onnistuit";
	}
	
	//Tähän
	@PostMapping("/kysely")
	public @ResponseBody String kyselyynVastaukset(@RequestBody Kysely kysely) {
		System.out.println(kysely.getKysymykset().size());
		for (int i = 0; i < kysely.getKysymykset().size(); i++) {
			//Vastaus luokka voidaan ottaa kokonaan pois ja postata suoraan vaihtoehtoluokan tiedoilla kiakki vastauksen tietokannan uuteen vastaus tauluun (uusi repo?)
			for (int j = 0; j < kysely.getKysymykset().get(i).getVastaus().size(); j++) {
				//tässä kohdassa generoidaan sessionID
				//kysely.getKysymykset().get(i).getVastaus().get(j).setSessionkey(GENEROITU_KEY);
				SessioID sessioid = new SessioID();
				sessioidRepo.save(sessioid);
				Vastaus vastaus = kysely.getKysymykset().get(i).getVastaus().get(j);
				vastaus.setSessioid(sessioid.getId().intValue());
				vastausRepo.save(vastaus);
			}
		}
		
		return  "Vastausten lähettäminen onnistui.";
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
	public String palautaKysymys(@RequestBody List<Vastaus> vastaus) //Juu tässä vaadittiin vaan @RequestBody, converttaa jsonin java classiin
	{
		
		SessioID sessioid = new SessioID();
		sessioidRepo.save(sessioid);
		
		for(Vastaus uusivastaus : vastaus) {
			uusivastaus.setSessioid(sessioid.getId().intValue());
			System.out.println(kysymysok(uusivastaus));
		}
		
		//System.out.println(vastaus.toString());
		//  System.out.println(kysymysok(vastaus));
		return "index";
	}
	
	
	private boolean kysymysok(Vastaus vastaus)
	{

		Optional<Kysymys> kysymys = kysymysRepo.findById(vastaus.getKysymys().getKysymys_id());
		if(!kysymys.isPresent())
			return false;
		
		Kysymys oikeakysymys = kysymys.get();
		System.out.println("TESTIÄ");
		vastaus.setKysymys(oikeakysymys);
		vastausRepo.save(vastaus);
		return true;
	}
	
}
