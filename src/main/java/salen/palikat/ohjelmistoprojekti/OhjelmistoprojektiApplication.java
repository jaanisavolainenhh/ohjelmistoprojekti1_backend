package salen.palikat.ohjelmistoprojekti;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import salen.palikat.ohjelmistoprojekti.domain.Kysymys;
import salen.palikat.ohjelmistoprojekti.domain.KysymysRepository;
import salen.palikat.ohjelmistoprojekti.domain.Vaihtoehto;
import salen.palikat.ohjelmistoprojekti.domain.VaihtoehtoRepository;
import salen.palikat.ohjelmistoprojekti.domain.VastausRepository;

@SpringBootApplication
public class OhjelmistoprojektiApplication {

	private static final Logger log = LoggerFactory.getLogger(OhjelmistoprojektiApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(OhjelmistoprojektiApplication.class, args);
	}

	@Bean // tähän voi tunkea loputtomasti argumentteja näköjään?
	public CommandLineRunner bookstoreDemo(KysymysRepository kysymysRepo, VastausRepository vastausRepo, VaihtoehtoRepository vaihtoehtoRepo) {
		
		return (args) -> {
//			
			//Kysymys kysymys = new Kysymys("Moi");
			Vaihtoehto vaihtoehto1 =new Vaihtoehto ("Vaihtoehto 1");
			Vaihtoehto vaihtoehto2 =new Vaihtoehto ("Vaihtoehto 2");
			Vaihtoehto vaihtoehto3 =new Vaihtoehto ("Vaihtoehto 3");
			vaihtoehtoRepo.save(vaihtoehto1);
			vaihtoehtoRepo.save(vaihtoehto2);
			vaihtoehtoRepo.save(vaihtoehto3);
			List<Vaihtoehto> lista = vaihtoehtoRepo.findAll();
			System.out.println(lista.get(0).getVaihtoehto());
			Kysymys kysymys = new Kysymys("Kysymys 1", lista);
		
			kysymysRepo.save(new Kysymys("Kysymys", lista));
			System.out.println(kysymys.getVaihtoehdot().get(0).getVaihtoehto());
			
			
//			lainatyypit.save(new Lainatyyppi("Erotiikka"));
//			lainatyypit.save(new Lainatyyppi("Politiikka"));
//			lainatyypit.save(new Lainatyyppi("Eroottinen politiikka"));
//			asiakkaat.save(new Asiakas("Asiakas 1"));
//			asiakkaat.save(new Asiakas("Asiakas 2"));
//			asiakkaat.save(new Asiakas("Asiakas 3"));
//			
//			//log.info("Toimii ennen addaamista.");
//			lainat.save(new Kysymys(asiakkaat.findByNimi("Asiakas 1"), 100,lainatyypit.findByName("Erotiikka").get(0)));
//			lainat.save(new Kysymys(asiakkaat.findByNimi("Asiakas 2"),200,lainatyypit.findByName("Politiikka").get(0)));
//			lainat.save(new Kysymys(asiakkaat.findByNimi("Asiakas 3"), 300, lainatyypit.findByName("Eroottinen politiikka").get(0)));
//			//TODO Thymeleafiin fixaukset
//			User user1 = new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER", "email@email.com");
//			User user2 = new User("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN", "posti@posti.com");
//			userit.save(user1);
//			userit.save(user2);
//
//			for (Kysymys book : lainat.findAll()) {
//				log.info(book.toString());
//			}
//
//			for (Lainatyyppi category : lainatyypit.findAll()) {
//				log.info(category.toString());
//			}
//			
//			for (User user: userit.findAll()) {
//				log.info(user.toString());
//			}

		};
	}
}
