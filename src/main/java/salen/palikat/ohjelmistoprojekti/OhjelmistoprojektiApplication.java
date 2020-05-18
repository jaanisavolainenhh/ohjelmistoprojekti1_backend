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
import salen.palikat.ohjelmistoprojekti.domain.UserRepository;
import salen.palikat.ohjelmistoprojekti.domain.Useri;
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
	public CommandLineRunner bookstoreDemo(KyselyRepository kyselyRepo, KysymysRepository kysymysRepo, VastausRepository vastausRepo, VaihtoehtoRepository vaihtoehtoRepo, UserRepository userRepo) {
		
		return (args) -> {

			
			kyselyRepo.save(new Kysely("Kysely1"));
			kysymysRepo.save(new Kysymys("Tämä on kysymys 1, mistä väristä pidät?", Kysymystyyppi.Radio, kyselyRepo.findByName("Kysely1").get(0)));
			kysymysRepo.save(new Kysymys("Tämä on kysymys 2, anna teksti", Kysymystyyppi.Teksti, kyselyRepo.findByName("Kysely1").get(0)));
			vaihtoehtoRepo.save(new Vaihtoehto("Ruskea", kysymysRepo.findByKysymys("Tämä on kysymys 1, mistä väristä pidät?").get(0)));
			vaihtoehtoRepo.save(new Vaihtoehto("Oranssi", kysymysRepo.findByKysymys("Tämä on kysymys 1, mistä väristä pidät?").get(0)));
			vaihtoehtoRepo.save(new Vaihtoehto("Pinkki", kysymysRepo.findByKysymys("Tämä on kysymys 1, mistä väristä pidät?").get(0)));
			vaihtoehtoRepo.save(new Vaihtoehto("", kysymysRepo.findByKysymys("Tämä on kysymys 2, anna teksti").get(0)));
			Useri user2 = new Useri("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN", "admin@admin.com");
			userRepo.save(user2);
			


		};
	}
}
