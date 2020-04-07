package salen.palikat.ohjelmistoprojekti.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Kysely {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "kysymys")
	@JsonIgnore // Siirretty tämä Categoryyn jotta saataisiin "oikea" data kirjasta jossa myös
	private List<Kysymys> kysymykset;
	

}
