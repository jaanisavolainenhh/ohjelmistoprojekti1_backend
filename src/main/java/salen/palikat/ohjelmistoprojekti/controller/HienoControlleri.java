package salen.palikat.ohjelmistoprojekti.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import salen.palikat.ohjelmistoprojekti.domain.Kysely;
import salen.palikat.ohjelmistoprojekti.domain.KyselyRepository;
import salen.palikat.ohjelmistoprojekti.domain.Kysymys;
import salen.palikat.ohjelmistoprojekti.domain.KysymysRepository;
import salen.palikat.ohjelmistoprojekti.domain.SessioID;
import salen.palikat.ohjelmistoprojekti.domain.SessioIDRepository;
import salen.palikat.ohjelmistoprojekti.domain.Vaihtoehto;
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

	@CrossOrigin
	@GetMapping("/kyselytadmin")
	public @ResponseBody List<Kysely> kyselyListAdminResti() {
		return (List<Kysely>) kyselyRepo.findAll();
	}

	@CrossOrigin
	@GetMapping("/kyselyt")
	public @ResponseBody List<Kysely> kyselyListResti() {
		List<Kysely> kyselyt = (List<Kysely>) kyselyRepo.findAll();
		for (int i = 0; i < kyselyt.size(); i++) {
			kyselyt.get(i).setSessioidt(null);
			for (int j = 0; j < kyselyt.get(i).getKysymykset().size(); j++) {
				kyselyt.get(i).getKysymykset().get(j).setVastaus(null);
			}
		}
		return kyselyt;
	}

	// Tähän käyttäjä postaa kyselyn vastaukset kysely-oliona
	@CrossOrigin
	@PostMapping("/kyselyt")
	public @ResponseBody String kyselyynVastaukset(@RequestBody Kysely kysely) {
		System.out.println(kysely.getKysymykset().size());
		SessioID sessioid = new SessioID();
		sessioid.setKysely(kyselyRepo.findById(kysely.getKysely_id()).get());
		sessioidRepo.save(sessioid);
		for (int i = 0; i < kysely.getKysymykset().size(); i++) {
			// Vastaus luokka voidaan ottaa kokonaan pois ja postata suoraan
			// vaihtoehtoluokan tiedoilla kiakki vastauksen tietokannan uuteen
			// vastaus tauluun (uusi repo?)
			for (int j = 0; j < kysely.getKysymykset().get(i).getVastaus().size(); j++) {
				// tässä kohdassa generoidaan sessionID
				// kysely.getKysymykset().get(i).getVastaus().get(j).setSessionkey(GENEROITU_KEY);

				Vastaus vastaus = kysely.getKysymykset().get(i).getVastaus().get(j);
				vastaus.setSessioid(sessioid.getId().intValue());
				vastausRepo.save(vastaus);
			}
		}

		return "Vastausten lähettäminen onnistui.";
	}

	// @CrossOrigin
	// @GetMapping("/sessions/{id}")
	// public @ResponseBody List<Vastaus>
	// vastauksetSessioittainResti(@PathVariable("id") int sessioid) {
	// return vastausRepo.findBySessioid(sessioid);
	// }

	// Tällä kaverilla saadaan tallennettua uusi kysely
	@CrossOrigin
	@PostMapping("/tallennauusikysely")
	public @ResponseBody String kyselynTallennus(@RequestBody Kysely kysely) {
		// tallenetaan kysely kantaan
		kyselyRepo.save(kysely);
		// käydään läpi kaikki kyselyn kysymykset
		for (int i = 0; i < kysely.getKysymykset().size(); i++) {
			// tallenetaan kysymys kantaan
			kysymysRepo.save(kysely.getKysymykset().get(i));
			// sitten taas käydään kysymyksen kaikki vaihtoehdot läpi
			for (int j = 0; j < kysely.getKysymykset().get(i).getVaihtoehdot().size(); j++) {
				// ja tallennetaan nekin talteen
				vaihtoehtoRepo.save(kysely.getKysymykset().get(i).getVaihtoehdot().get(j));
			}
		}

		return "Onnistuit";
	}

	@CrossOrigin
	// tässä endpointissa annetaan endpointin id osaan kysymyksen id, joka
	// halutaan poistaa
	@RequestMapping(value = "/kysymys/{id}", method = RequestMethod.DELETE)
	public @ResponseBody String poistakysymys(@PathVariable("id") Long kysymys_id) {
		Kysymys kysymys = kysymysRepo.findById(kysymys_id).get();
//		if (kysymys == null) {
//			return "Ei löytynyt kysymystä tällä ID:llä";
//		}
//		for (int i = 0; i < kysymys.getVaihtoehdot().size(); i++) {
//			vaihtoehtoRepo.deleteById(kysymys.getVaihtoehdot().get(i).getVaihtoehto_id());
//		}
//		for (int i = 0; i < kysymys.getVastaus().size(); i++) {
//			vastausRepo.deleteById(kysymys.getVastaus().get(i).getVastaus_id());
//		}
		kysymysRepo.deleteById(kysymys_id);
		return "Onnistuit poistamaan";
	}

	@CrossOrigin
	// tässä endpointissa lähetetään lisättävä kysymys oliona ja endpointin id
	// osaan laitetaan kyselyn id, joohn halutaan kysymys lisätä
	@PostMapping("/kysymys/{id}")
	public @ResponseBody String lisaakysymys(@PathVariable("id") Long kysely_id, @RequestBody Kysymys kysymys) {
		Kysely kysely = kyselyRepo.findById(kysely_id).get();
		kysymys.setKysely(kysely);
		kysymysRepo.save(kysymys);
		return "Onnistuit lisäämään kysymyksen";
	}
	
	
	@CrossOrigin
	// id osaan muokattavan kysymyksen id
	@RequestMapping(value = "/kysymys/{id}", method = RequestMethod.PUT)
	public @ResponseBody String muokkaakysymys(@PathVariable("id") Long kysymys_id, @RequestBody Kysymys kysymys) {
		if (kysymys == null) {
			return "Ei löytynyt kysymystä tällä ID:llä";
		}
		
		Kysymys kysymys2 = kysymysRepo.findById(kysymys_id).get();
		
		for (int i = 0; i < kysymys2.getVaihtoehdot().size(); i++) {
			vaihtoehtoRepo.deleteById(kysymys2.getVaihtoehdot().get(i).getVaihtoehto_id());
		}
		for (int i = 0; i < kysymys2.getVastaus().size(); i++) {
			vastausRepo.deleteById(kysymys2.getVastaus().get(i).getVastaus_id());
		}

		kysymys2.setTyyppi(kysymys.getTyyppi());
		kysymys2.setKysymys(kysymys.getKysymys());
		kysymys2.setVaihtoehdot(new ArrayList<Vaihtoehto>());
		kysymys2.setVastaus(new ArrayList<Vastaus>());
		kysymysRepo.save(kysymys2);
		
		List<Vaihtoehto> vaihtoehdot = kysymys.getVaihtoehdot();
		
		for (int i = 0; i < vaihtoehdot.size(); i++) {
			Vaihtoehto vaiht = vaihtoehdot.get(i);
			vaiht.setKysymys(kysymys2);
			vaihtoehtoRepo.save(vaiht);
		}
		
		return "Onnistuit";
	}
	
	// @CrossOrigin
	// @ResponseBody
	// @PostMapping("/palautakysely")
	// public String palautaKysymysLista(@RequestBody List<Vastaus >vastaus)
	// //Juu tässä vaadittiin vaan @RequestBody, converttaa jsonin java classiin
	// {
	// System.out.println(vastaus.toString());
	// //System.out.println(kysymysok(vastaus));
	// return "index";
	// }
	//
	//

	// EI käytössä atm, jätetään jostain syystä
	@CrossOrigin
	@ResponseBody
	@PostMapping("/palautakysymys")
	public String palautaKysymys(@RequestBody List<Vastaus> vastaus) // Juu
																		// tässä
																		// vaadittiin
																		// vaan
																		// @RequestBody,
																		// converttaa
																		// jsonin
																		// java
																		// classiin
	{

		SessioID sessioid = new SessioID();
		sessioidRepo.save(sessioid);

		for (Vastaus uusivastaus : vastaus) {
			uusivastaus.setSessioid(sessioid.getId().intValue());
			System.out.println(kysymysok(uusivastaus));
		}

		// System.out.println(vastaus.toString());
		// System.out.println(kysymysok(vastaus));
		return "index";
	}

	private boolean kysymysok(Vastaus vastaus) {

		Optional<Kysymys> kysymys = kysymysRepo.findById(vastaus.getKysymys().getKysymys_id());
		if (!kysymys.isPresent())
			return false;

		Kysymys oikeakysymys = kysymys.get();
		System.out.println("TESTIÄ");
		vastaus.setKysymys(oikeakysymys);
		vastausRepo.save(vastaus);
		return true;
	}

}
