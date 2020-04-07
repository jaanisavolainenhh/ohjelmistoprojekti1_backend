package salen.palikat.ohjelmistoprojekti.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Vaihtoehto {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String vaihtoehto;
	@ManyToOne
	@JoinColumn(name = "kysymys")
	private Kysymys kysymys;
	
	public Kysymys getKysymys() {
		return kysymys;
	}
	public void setKysymys(Kysymys kysymys) {
		this.kysymys = kysymys;
	}

	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getVaihtoehto() {
		return vaihtoehto;
	}
	public void setVaihtoehto(String vaihtoehto) {
		this.vaihtoehto = vaihtoehto;
	}
	public Vaihtoehto(String vaihtoehto) {
		this.vaihtoehto = vaihtoehto;
	}
	public Vaihtoehto() {
		
	}
}
