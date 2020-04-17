package salen.palikat.ohjelmistoprojekti;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import salen.palikat.ohjelmistoprojekti.domain.Kysely;
import salen.palikat.ohjelmistoprojekti.domain.KyselyRepository;
import salen.palikat.ohjelmistoprojekti.domain.Kysymys;
import salen.palikat.ohjelmistoprojekti.domain.KysymysRepository;
import salen.palikat.ohjelmistoprojekti.domain.Kysymystyyppi;
import salen.palikat.ohjelmistoprojekti.domain.Vaihtoehto;
import salen.palikat.ohjelmistoprojekti.domain.VaihtoehtoRepository;
import salen.palikat.ohjelmistoprojekti.domain.Vastaus;
import salen.palikat.ohjelmistoprojekti.domain.VastausRepository;

@SpringBootApplication
public class OhjelmistoprojektiApplication {

	private static final Logger log = LoggerFactory.getLogger(OhjelmistoprojektiApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(OhjelmistoprojektiApplication.class, args);
	}

	@Bean // tähän voi tunkea loputtomasti argumentteja näköjään?
	public CommandLineRunner bookstoreDemo(KyselyRepository kyselyRepo, KysymysRepository kysymysRepo, VastausRepository vastausRepo, VaihtoehtoRepository vaihtoehtoRepo) {
		
		return (args) -> {
//			
			//Kysymys kysymys = new Kysymys("Moi");
//			Vaihtoehto vaihtoehto1 =new Vaihtoehto ("Ruskea");
//			Vaihtoehto vaihtoehto2 =new Vaihtoehto ("Oranssi");
//			Vaihtoehto vaihtoehto3 =new Vaihtoehto ("Pinkki");
//			
//			List<Vaihtoehto> vaihtoehdot1 = new ArrayList<Vaihtoehto>();
//			vaihtoehdot1.add(vaihtoehto1);
//			vaihtoehdot1.add(vaihtoehto2);
//			vaihtoehdot1.add(vaihtoehto3);
//			
//			Kysymys kysymys1 = new Kysymys("Tämä on kysymys 1, mistä väristä pidät?", Kysymystyyppi.Radio, vaihtoehdot1);
//			Kysymys kysymys2 = new Kysymys("Tämä on kysymys 2, anna teksti", Kysymystyyppi.Teksti);
//			
//			List<Kysymys> kysymykset1 = new ArrayList<Kysymys>();
//			kysymykset1.add(kysymys1);
//			kysymykset1.add(kysymys2);
//			
//			Kysely kysely1 = new Kysely(kysymykset1);
//			
//			log.info("tämä tulee");
//			vaihtoehto1.setKysymys(kysymys1);
//			vaihtoehto2.setKysymys(kysymys1);
//			vaihtoehto3.setKysymys(kysymys1);
//			log.info("tämä tulee2");
//			kysymys1.setKysely(kysely1);
//			kysymys2.setKysely(kysely1);
//			log.info("tämä tulee3");
//			kyselyRepo.save(kysely1);
//			log.info("tämä tulee4");
//			kysymysRepo.save(kysymys1);
//			kysymysRepo.save(kysymys2);
//			log.info("tämä tulee5");
//			vaihtoehtoRepo.save(vaihtoehto1);
//			vaihtoehtoRepo.save(vaihtoehto2);
//			vaihtoehtoRepo.save(vaihtoehto3);
//			log.info("tämä tulee6");
			
			kyselyRepo.save(new Kysely("Kysely1"));
			kysymysRepo.save(new Kysymys("Tämä on kysymys 1, mistä väristä pidät?", Kysymystyyppi.Radio, kyselyRepo.findByName("Kysely1").get(0)));
			kysymysRepo.save(new Kysymys("Tämä on kysymys 2, anna teksti", Kysymystyyppi.Teksti, kyselyRepo.findByName("Kysely1").get(0)));
			vaihtoehtoRepo.save(new Vaihtoehto("Ruskea", kysymysRepo.findByKysymys("Tämä on kysymys 1, mistä väristä pidät?").get(0)));
			vaihtoehtoRepo.save(new Vaihtoehto("Oranssi", kysymysRepo.findByKysymys("Tämä on kysymys 1, mistä väristä pidät?").get(0)));
			vaihtoehtoRepo.save(new Vaihtoehto("Pinkki", kysymysRepo.findByKysymys("Tämä on kysymys 1, mistä väristä pidät?").get(0)));
			
			
//			Vastaus vastaus1 = new Vastaus("Punainen", kysymys);
//			Vastaus vastaus2 = new Vastaus("Ruskea", kysymys);
//			Vastaus vastaus3 = new Vastaus("Oranssi", kysymys);
//			vastausRepo.save(vastaus1);
//			vastausRepo.save(vastaus2);
//			vastausRepo.save(vastaus3);


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
